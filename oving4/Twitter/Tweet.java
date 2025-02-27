package oving4;

public class Tweet {
    private TwitterAccount owner;
    private String text;
    private Tweet originalTweet;
    private int retweetCount;

    public Tweet(TwitterAccount owner, String text) {
        this.owner = owner;
        this.text = text;
        this.originalTweet = null;
        this.retweetCount = 0;
    }

    public Tweet(TwitterAccount owner, Tweet originalTweet) {
        if (owner == originalTweet.getOwner()) {
            throw new RuntimeException("Cannot retweet your own tweet.");
        }
        this.owner = owner;
        this.text = originalTweet.getText();
        this.originalTweet = originalTweet;
        this.originalTweet.incrementRetweetCount();
    }

    public String getText() {
        return text;
    }

    public TwitterAccount getOwner() {
        return owner;
    }

    public Tweet getOriginalTweet() {
        return originalTweet;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void incrementRetweetCount() {
        this.retweetCount++;
    }
}
