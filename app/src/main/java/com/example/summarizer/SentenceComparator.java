package com.example.summarizer;
import java.util.Comparator;

public class SentenceComparator implements Comparator<Sentence> {
    public int compare(Sentence obj1, Sentence obj2) {
        if(obj1.score > obj2.score){
            return -1;
        }else if(obj1.score < obj2.score){
            return 1;
        }else{
            return 0;
        }
    }
}
