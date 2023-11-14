package seedu.address.logic.commands.localcourse;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.localcourse.LocalCourseAttribute;
import seedu.address.model.localcourse.LocalCourseContainsKeywordsPredicate;
import seedu.address.seplendidui.UiUtil;

/**
 * Finds and lists all localcourses in SEPlendid whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class LocalCourseSearchCommand extends LocalCourseCommand {
    public static final String ACTION_WORD = "search"; // Use "search" as the command word

    public static final String MESSAGE_SUCCESS = "Local courses searched: %1$s";
    private final LocalCourseAttribute attribute;

    private final LocalCourseContainsKeywordsPredicate predicate;

    private String query;

    /**
     * Creates a LocalCourseSearchCommand to sort the local course list based on the code predicate.
     * @param codePredicate Predicate use for search
     */
    public LocalCourseSearchCommand(LocalCourseAttribute attribute,
                                    LocalCourseContainsKeywordsPredicate predicate,
                                    String query) {
        this.attribute = attribute;
        this.predicate = predicate;
        this.query = query;
    }

    @Override
    public CommandResult execute(SeplendidModel model) throws CommandException {
        requireNonNull(model);

        model.searchLocalCourses(attribute, predicate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(model.getFilteredLocalCourseList())),
                UiUtil.ListViewModel.LOCAL_COURSE_LIST);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LocalCourseSearchCommand)) {
            return false;
        }

        LocalCourseSearchCommand otherLocalCourseSearchCommand = (LocalCourseSearchCommand) other;
        return attribute == otherLocalCourseSearchCommand.attribute
                && Objects.equals(predicate, otherLocalCourseSearchCommand.predicate)
                && Objects.equals(query, otherLocalCourseSearchCommand.query);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("attribute", attribute)
                .add("predicate", predicate)
                .add("query", query)
                .toString();
    }
}

