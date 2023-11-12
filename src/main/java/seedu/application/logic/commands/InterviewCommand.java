package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.logic.parser.CliSyntax.*;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_ADDRESS;

import java.util.List;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.job.Job;

/**
 * Represents an interview command with hidden internal logic and the ability to be executed.
 */
public abstract class InterviewCommand extends Command {

    public static final String COMMAND_WORD = "interview";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Adds / Deletes / Edits an interview in the application.\n"
        + "Parameters: command word (add / delete / list)\n"
        + "INDEX (must be a positive integer)\n"
        + PREFIX_INTERVIEW_TYPE + "INTERVIEW TYPE "
        + PREFIX_INTERVIEW_DATETIME + "INTERVIEW DATE AND TIME "
        + PREFIX_INTERVIEW_ADDRESS + "INTERVIEW ADDRESS\n"
        + "Example: \n"
        + PREFIX_INTERVIEW_TYPE + "Technical "
        + PREFIX_INTERVIEW_DATETIME + "Dec 31 2030 1200 "
        + PREFIX_INTERVIEW_ADDRESS + "Home\n";

    public static final String MESSAGE_CONSTRAINTS = "interview can only be followed by add, delete, or edit";
    /**
     * Retrieves the job associated with a given index in the filtered job list.
     *
     * @param model The model containing the job list.
     * @param index The index of the job to retrieve.
     * @return The job associated with the given index.
     * @throws CommandException If the index is out of bounds or if the job is not found.
     */
    public Job getJob(Model model, Index index) throws CommandException {
        requireNonNull(model);
        List<Job> lastShownList = model.getFilteredJobList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        return lastShownList.get(index.getZeroBased());
    }
}
