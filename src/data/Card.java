package data;

public class Card implements Comparable<Card>{

    private final int value;
    private final String suite;
    private final String valueAsString;

    public Card(){
        this.value = -1;
        this.suite = "";
        this.valueAsString = "";
    }

    public Card(int value, String suite, String valueAsString){
        this.value = value;
        this.suite = suite;
        this.valueAsString = valueAsString;
    }

    public int getValue() {
        return value;
    }

    public String getSuite() {
        return suite;
    }

    public String getValueAsString() {
        return valueAsString;
    }

    /** Generate image filename as "suite_value.png" */
    public String getCardPath(){
        return suite + "_" + valueAsString + ".png";
    }

    public boolean isConsecutive(Card o) {
        return Math.abs( value - o.getValue() ) == 1;
    }

    public boolean sameSuite(Card o) {
        return suite.equals(o.getSuite());
    }

    @Override public int compareTo(Card o) {
        return Integer.compare(this.value, o.getValue());
    }   
}
