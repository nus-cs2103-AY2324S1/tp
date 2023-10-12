public class Word {
    private static final String word;

    private static final String USAGE = "Word"

    public Word(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return USAGE + " is " + word;
    }
}
