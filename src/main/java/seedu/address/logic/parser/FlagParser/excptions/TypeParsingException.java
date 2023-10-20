package seedu.address.logic.parser.FlagParser.excptions;

public class TypeParsingException extends Exception {
    public TypeParsingException(String type, String invalidInput, String sampleUsage) {
        super("Invalid input for type " + type + ": " + invalidInput + "\n" + "Sample input: "+sampleUsage);
    }
}
