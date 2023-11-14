package seedu.address.logic.commands.partnercourse;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.partnercourse.PartnerCourseAttribute;
import seedu.address.model.partnercourse.PartnerCourseContainsKeywordsPredicate;
import seedu.address.seplendidui.UiUtil;

/**
 * Finds and lists all partnercourses in SEPlendid whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class PartnerCourseSearchCommand extends PartnerCourseCommand {
    public static final String ACTION_WORD = "search"; // Use "search" as the command word
    public static final String MESSAGE_SUCCESS = "Partner courses searched: %1$s";

    private final PartnerCourseAttribute attribute;

    private final PartnerCourseContainsKeywordsPredicate predicate;

    private String query;

    /**
     * Creates a PartnerCourseSearchCommand to sort the partner course list based on the predicate.
     * @param predicate Predicate use for search
     */
    public PartnerCourseSearchCommand(PartnerCourseAttribute attribute,
                                    PartnerCourseContainsKeywordsPredicate predicate,
                                    String query) {
        this.attribute = attribute;
        this.predicate = predicate;
        this.query = query;
    }

    @Override
    public CommandResult execute(SeplendidModel model) throws CommandException {
        requireNonNull(model);

        model.searchPartnerCourses(attribute, predicate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(model.getFilteredPartnerCourseList())),
                UiUtil.ListViewModel.PARTNER_COURSE_LIST);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PartnerCourseSearchCommand)) {
            return false;
        }

        PartnerCourseSearchCommand otherPartnerCourseSearchCommand = (PartnerCourseSearchCommand) other;
        return attribute == otherPartnerCourseSearchCommand.attribute
                && Objects.equals(predicate, otherPartnerCourseSearchCommand.predicate)
                && Objects.equals(query, otherPartnerCourseSearchCommand.query);
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
