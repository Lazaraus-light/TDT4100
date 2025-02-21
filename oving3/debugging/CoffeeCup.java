package oving3.debugging;

public class CoffeeCup {

	private double capacity;
	private double currentVolume;

	public CoffeeCup() {
		this.capacity = 0.0;
		this.currentVolume = 0.0;
	}

	public CoffeeCup(double capacity, double currentVolume) {
		if (!this.isValidCapacity(capacity)) {
			throw new IllegalArgumentException("Illegal capacity given.");
		}
		this.capacity = capacity;

		if (!this.isValidVolume(currentVolume)) {
			throw new IllegalArgumentException("Illegal volume given.");
		}
		this.currentVolume = currentVolume;
	}

	private boolean isValidCapacity(double capacity) {
		return capacity >= 0.0;
	}

	public void increaseCupSize(double biggerCapacity) {
		if (this.isValidCapacity(biggerCapacity)) {
			this.capacity += biggerCapacity;
		}
	}

	private boolean isValidVolume(double volume) {
		return volume <= this.capacity && volume >= 0.0;
	}

	private boolean canDrink(double volume) {
		return volume <= this.currentVolume;
	}

	public void drinkCoffee(double volume) {
		if (!this.isValidVolume(volume) || !this.canDrink(volume)) {
			throw new IllegalArgumentException("You can't drink that much coffee!"); // utløses på part1() for at man forsøker å drikke mer kaffe enn det som er i koppen.
		}

		this.currentVolume -= volume;
	}

	public void fillCoffee(double volume) {
		if (!this.isValidVolume(this.currentVolume + volume)) {
			throw new IllegalArgumentException(
					"You just poured coffee all over the table. Good job.");
		}

		this.currentVolume += volume;
	}
}
