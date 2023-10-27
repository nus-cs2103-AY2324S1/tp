//package flashlingo.logic.parser;
//
//import flashlingo.logic.parser.exceptions.ParseException;
//import seedu.flashlingo.logic.commands.LanguageCommand;
//import seedu.flashlingo.model.flashcard.WordLanguagePredicate;
//
//import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//
//
///**
// * Parses input arguments and creates a new LanguageCommandTest object
// */
//public class LanguageCommandParser implements Parser<LanguageCommand> {
//
//    /**
//     * Parses the given {@code String} of arguments in the context of the LanguageCommandTest
//     * and returns a LanguageCommandTest object for execution.
//     * @throws ParseException if the user input does not conform the expected format
//     */
//    public LanguageCommand parse(String args) throws ParseException {
//        String trimmedArgs = args.trim();
//        if (trimmedArgs.isEmpty()) {
//            throw new ParseException(
//                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, LanguageCommand.MESSAGE_USAGE));
//        }
//
//        return new LanguageCommand(new WordLanguagePredicate(trimmedArgs));
//    }
//
//}
