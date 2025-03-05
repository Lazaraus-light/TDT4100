package oving5.Twitter;

import java.util.Comparator;

import oving5.Twitter.Twitter.TwitterAccount;



public class FollowerCountsComparator implements Comparator<TwitterAccount> {
   
    @Override
    public int compare(TwitterAccount a1, TwitterAccount a2){
       
        if (a1.getFollowerCount() > a2.getFollowerCount()) {
            return -1;
        }
        if (a1.getFollowerCount() < a2.getFollowerCount()) {
            return 1;
        }
        
        return 0;
    }
}