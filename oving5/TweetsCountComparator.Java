package oving5;

import java.util.Comparator;


public class TweetsCountComparator implements Comparator<Tweet> {

    @Override
    public int compare(Tweet a1, Tweet a2){
       
        if (a1.getTweetCount() > a2.getTweetCount()) {
            return -1;
        }
        if (a1.getTweetCount() < a2.getTweetCount()) {
            return 1;
        }
        
        return 0;
    }

}
