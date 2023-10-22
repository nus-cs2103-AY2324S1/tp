package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.card.Card;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_CARD_DISPLAYED_INDEX = "The card index provided is invalid";
    public static final String MESSAGE_CARDS_LISTED_OVERVIEW = "%1$d cards listed!";
    public static final String MESSAGE_CARDS_SOLVE_VIEW_EASY = "%1$s";
    public static final String MESSAGE_CARDS_SOLVE_VIEW_MEDIUM = "%1$s";
    public static final String MESSAGE_CARDS_SOLVE_VIEW_HARD = "%1$s";
    public static final String MESSAGE_CARDS_SOLVE_VIEW_INVALID = " is not an invalid difficult "
            + "level! Please enter easy, medium or hard!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    public static final String MESSAGE_CARDS_PRACTISE_VIEW = "%1$s";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code card} for display to the user.
     */
    public static String format(Card card) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Question: ")
                .append(card.getQuestion())
                    .append("; Answer: ")
                     .append(card.getAnswer());
        return stringBuilder.toString();
    }

    /**
     * Formats the {@code card} for display to the user as an Answer.
     */
    public static String formatSolve(Card card, Index index) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Solved Question ")
                .append(index.getOneBased())
                .append(" (Difficulty level: ")
                .append(card.getDifficulty())
                .append(")");
        return stringBuilder.toString();
    }

    /**
     * Formats the {@code card} for display to the user as an Answer.
     */
    public static String formatPractise(Card card, Index index) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Practising question ")
                .append(index.getOneBased())
                .append(" : ")
                .append(card.getQuestion());
        return stringBuilder.toString();
    }

    /**
     * Formats the {@code card} for display to the user as a Question.
     */
    public static String formatQuestion(Card card) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Question: ")
                .append(card.getQuestion());
        return stringBuilder.toString();
    }

}
