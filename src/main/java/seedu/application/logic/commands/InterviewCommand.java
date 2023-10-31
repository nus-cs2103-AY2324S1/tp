package seedu.application.logic.commands;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.job.Job;
import seedu.application.model.job.JobType;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.application.logic.parser.CliSyntax.*;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_ADDRESS;

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

    public enum SubCommandWords {
        add, delete, edit
    }

    public Job getJob(Model model, Index index) throws CommandException {
        requireNonNull(model);
        List<Job> lastShownList = model.getFilteredJobList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        return lastShownList.get(index.getZeroBased());
    }

    public static boolean isValidSubCommandWord(String subCommandWord) {
        for (InterviewCommand.SubCommandWords w : InterviewCommand.SubCommandWords.values()) {
            if (subCommandWord.equalsIgnoreCase(w.toString())) {
                return true;
            }
        }
        return false;
    }
}
