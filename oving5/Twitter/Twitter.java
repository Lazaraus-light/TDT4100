package oving5.Twitter;

import oving5.Twitter.Twitter.TwitterAccount;

public class Twitter {
    public static void main(String[] args) {
        TwitterAccount nils = new TwitterAccount("Nils");
        TwitterAccount ole = new TwitterAccount("Ole");
        TwitterAccount kari = new TwitterAccount("Kari");

        nils.tweet("Kvitre!");
        System.out.println(nils.getUserName() + "'s first tweet: " + nils.getTweet(1).getText());

        ole.retweet(nils.getTweet(1));
        System.out.println(ole.getUserName() + " retweeted: " + ole.getTweet(1).getText());

        kari.retweet(ole.getTweet(1));
        System.out.println(kari.getUserName() + " retweeted: " + kari.getTweet(1).getText());
    }
}