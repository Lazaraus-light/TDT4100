package oving5.Twitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TwitterAccount {
    private String userName;
    private Set<TwitterAccount> following;
    private Set<TwitterAccount> followers;
    private List<Tweet> tweets;

    public TwitterAccount(String userName) {
        this.userName = userName;
        this.following = new HashSet<>();
        this.followers = new HashSet<>();
        this.tweets = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public int getFollowerCount() {
        return followers.size();
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
        if (tweet.getOwner() == this) {
            throw new RuntimeException("Cannot retweet your own tweet.");
        }
        Tweet original = tweet.getOriginalTweet() != null ? tweet.getOriginalTweet() : tweet;
        tweets.add(0, new Tweet(this, original));
        original.incrementRetweetCount();
    }

    public Tweet getTweet(int i) {
        if (i <= 0 || i > tweets.size()) {
            throw new RuntimeException("Invalid tweet index.");
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

    public List<TwitterAccount> getFollowers(Comparator<TwitterAccount> comparator) {
        List<TwitterAccount> sortedFollowers = new ArrayList<>(followers);
        if (comparator != null) {
            Collections.sort(sortedFollowers, comparator);
        }
        return sortedFollowers;
    }
}
