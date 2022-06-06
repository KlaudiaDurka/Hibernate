package tomtom.interview;

public class Main {

    public static void main(String[] args) {
        BagOfWords bagOfWords;
        String document;

        document = "Real time analytics approach.";
        bagOfWords = new BagOfWords(document);
        System.out.println("number of unique tokens should be 4: " + bagOfWords.size());

        document = "Streaming architecture on top of Apache Flink and apache Kafka. Real time analytics approach.";
        bagOfWords = new BagOfWords(document);
        System.out.println("number of unique tokens should be 13: " + bagOfWords.size());
        System.out.println("token \"Apache\" should have frequency 2: " + bagOfWords.tokenFrequency("Apache"));
    }
}
