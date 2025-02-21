package oving4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
class Tweet {
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
        throw new IllegalArgumentException("Cannot retweet your own tweet.");
    }
    this.owner = owner;
    this.text = originalTweet.getText();
    this.originalTweet = originalTweet;
    originalTweet.incrementRetweetCount();
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

private void incrementRetweetCount() {
    this.retweetCount++;
}

}
class TwitterAccount {
private String userName;
private Set following;
private Set followers;
private List tweets;
public TwitterAccount(String userName) {
    this.userName = userName;
    this.following = new HashSet<>();
    this.followers = new HashSet<>();
    this.tweets = new ArrayList<>();
}

public String getUserName() {
    return userName;
}

public void follow(TwitterAccount account) {
    if (account == this) {
        throw new IllegalArgumentException("Cannot follow yourself.");
    }
    following.add(account);
    account.followers.add(this);
}

public void unfollow(TwitterAccount account) {
    following.remove(account);
    account.followers.remove(this);
}

public boolean isFollowing(TwitterAccount account) {
    return following.contains(account);
}

public boolean isFollowedBy(TwitterAccount account) {
    return followers.contains(account);
}

public void tweet(String text) {
    tweets.add(0, new Tweet(this, text));
}

public void retweet(Tweet tweet) {
    tweets.add(0, new Tweet(this, tweet));
}

public Tweet getTweet(int i) {
    if (i <= 0 || i > tweets.size()) {
        throw new IndexOutOfBoundsException("Invalid tweet index.");
    }
    return tweets.get(i - 1);
}

public int getTweetCount() {
    return tweets.size();
}

public int getRetweetCount() {
    int count = 0;
    for (Tweet tweet : tweets) {
        if (tweet.getOriginalTweet() != null) {
            count++;
        }
    }
    return count;
}

}
