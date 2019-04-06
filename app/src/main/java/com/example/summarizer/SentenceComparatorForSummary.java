package com.example.summarizer;
import java.util.Comparator;

public class SentenceComparatorForSummary  implements Comparator<Sentence>{
    public int compare(Sentence obj1, Sentence obj2) {
        if(obj1.number > obj2.number){
            return 1;
        }else if(obj1.number < obj2.number){
            return -1;
        }else{
            return 0;
        }
    }
}
