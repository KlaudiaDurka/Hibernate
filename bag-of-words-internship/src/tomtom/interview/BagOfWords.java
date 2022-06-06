package tomtom.interview;

import java.util.HashMap;
import java.util.Map;

class BagOfWords {

    String document = "Real time analytics approach.";

    BagOfWords(String document) {
        this.document = document;
    }

    long tokenFrequency(String token) {

        return 0;
    }

    long size() {
        String [] tab = document.split(" ");
        for (String e : tab){
            System.out.println(e);
        }

        HashMap<String, Integer> a = new HashMap<>();
        for (int i = 0; i < tab.length; i++){

            a.put(tab[i], i + 1);
        }
        return tab.length;
    }
}
//         document = "Real time analytics approach.";
//        bagOfWords = new BagOfWords(document);
//        System.out.println("number of unique tokens should be 4: " + bagOfWords.size());
//
//        document = "Streaming architecture on top of Apache Flink and apache Kafka. Real time analytics approach.";
//        bagOfWords = new BagOfWords(document);
//        System.out.println("number of unique tokens should be 13: " + bagOfWords.size());
//        System.out.println("token \"Apache\" should have frequency 2: " + bagOfWords.tokenFrequency("Apache"));
