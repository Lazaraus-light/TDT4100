package oving3.debugging;

import java.util.Random;

public class RandomSeedTest {
    public static void main(String[] args) {
        Random r = new Random(123456789L); //seed 123456789L og 987654321L

        
        for (int i = 0; i < 5; i++) {
            double randomValue = r.nextDouble();
            System.out.println(randomValue);
        }
    }
}

