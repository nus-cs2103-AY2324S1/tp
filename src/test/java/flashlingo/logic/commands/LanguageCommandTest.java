//package flashlingo.logic.commands;
//
//import seedu.flashlingo.commons.util.ToStringBuilder;
//import seedu.flashlingo.logic.Messages;
//import seedu.flashlingo.model.Model;
//import seedu.flashlingo.model.flashcard.WordLanguagePredicate;
//
//import static java.util.Objects.requireNonNull;
//
///**
// * Finds and lists all flashcards in flashlingo whose original words or translation is a certain language.
// */
//public class LanguageCommandTest extends CommandTest {
//
//    public static final String COMMAND_WORD = "language";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all flashcards whose language is "
//            + "the specified language and displays them as a list with index numbers.\n"
//            + "Parameters: LANGUAGE\n"
//            + "Example: " + COMMAND_WORD + " English";
//
//    private final WordLanguagePredicate predicate;
//
//    public LanguageCommandTest(WordLanguagePredicate predicate) {
//        this.predicate = predicate;
//    }
//    @Override
//    public CommandResultTest execute(Model model) {
//        requireNonNull(model);
//        model.updateFilteredFlashCardList(predicate);
//        return new CommandResultTest(
//                String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW + "\n"
//                                + model.getFilteredFlashCardList(),
//                        model.getFilteredFlashCardList().size()));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof LanguageCommandTest)) {
//            return false;
//        }
//
//        LanguageCommandTest otherLanguageCommand = (LanguageCommandTest) other;
//        return predicate.equals(otherLanguageCommand.predicate);
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .add("predicate", predicate)
//                .toString();
//    }
//}
