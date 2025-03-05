package oving5;

import java.util.Comparator;



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
