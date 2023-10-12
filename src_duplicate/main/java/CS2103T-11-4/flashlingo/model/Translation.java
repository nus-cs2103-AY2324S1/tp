public class Transaltion {
    private static final String translation;

    private static final USAGE = "Translation";

    public Transaltion(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return USAGE + " is " + translation;
    }
}