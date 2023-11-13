package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENROL_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEC_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.OrganizeData;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.tableresults.EnrolDateTableCommandResult;
import seedu.address.logic.commands.tableresults.GenderTableCommandResult;
import seedu.address.logic.commands.tableresults.SecLevelTableCommandResult;
import seedu.address.logic.commands.tableresults.SubjectTableCommandResult;
import seedu.address.model.Model;

/**
 * Calculate counts for each category(to be specified, eg: by gender, Sec-Level, Subject)
 * for table generation.
 */
public class TableCommand extends Command {
    public static final String COMMAND_WORD = "table";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": show the statistics of student in a table "
            + "by the attributes. \n"
            + "[" + PREFIX_GENDER + "] "
            + "[" + PREFIX_SEC_LEVEL + "] "
            + "[" + PREFIX_SUBJECT + "]"
            + "[" + PREFIX_ENROL_DATE + "{year}" + "]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_GENDER + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ENROL_DATE + "2023";

    public static final String MESSAGE_INCORRECT_COMMAND = "To view a table, please do one of the following:\n"
                                                        + COMMAND_WORD + " " + PREFIX_GENDER + " or\n"
                                                        + COMMAND_WORD + " " + PREFIX_SEC_LEVEL + " or\n"
                                                        + COMMAND_WORD + " " + PREFIX_SUBJECT + " or\n"
                                                        + COMMAND_WORD + " " + PREFIX_ENROL_DATE + "{year}\n";
    private final String args;
    private final int year;

    /**
     * Constructor for TableCommand.
     * @param args represents the category for table, eg: s/, l/, g/
     */
    public TableCommand(String args) {
        this(args, 0);

    }

    /**
     * Constructor for TableCommand for user input d/yyyy
     * @param args represents the category for table, d/
     * @param year represents the year in integer
     */
    public TableCommand(String args, int year) {
        this.args = args.trim();
        this.year = year;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (args.equals(PREFIX_GENDER.getPrefix())) {
            return executeGender(model);
        } else if (args.equals(PREFIX_SEC_LEVEL.getPrefix())) {
            return executeSecLevel(model);
        } else if (args.equals(PREFIX_SUBJECT.getPrefix())) {
            return executeSubject(model);
        } else if (args.equals(PREFIX_ENROL_DATE.getPrefix())) {
            return executeEnrolDate(model);
        } else {
            throw new CommandException(MESSAGE_INCORRECT_COMMAND);
        }
    }

    /**
     * Generate GenderTableCommandResult instance.
     * @param model instance of Model subclass, e.g. ModelManager instance
     * @return GenderTableCommandResult instance containing the column titles and values.
     */
    private GenderTableCommandResult executeGender(Model model) {
        return new GenderTableCommandResult(OrganizeData.byGender(model));
    }

    /**
     * Generate SecLevelTableCommandResult instance
     * @param model instance of Model subclass, e.g. ModelManager instance
     * @return SecLevelTableCommandResult instance containing column titles and values.
     */
    private SecLevelTableCommandResult executeSecLevel(Model model) {
        return new SecLevelTableCommandResult(OrganizeData.bySecLevel(model));
    }

    /**
     * Generate SubjectTableCommandResult instance.
     * @param model instance of Model subclass, e.g. ModelManager instance.
     * @return SubjectTableCommandResult instance containing column titles and values.
     */
    private SubjectTableCommandResult executeSubject(Model model) {
        return new SubjectTableCommandResult(OrganizeData.bySubject(model));
    }

    /**
     * Generate EnrolDateTableCommandResult instance.
     * @param model instance of model subclass, e.g. ModelManager instance.
     * @return an EnrolDateTableCommandResult instance.
     */
    private EnrolDateTableCommandResult executeEnrolDate(Model model) {
        return new EnrolDateTableCommandResult(OrganizeData.byEnrolDate(model, year));
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TableCommand)) {
            return false;
        }

        TableCommand otherCommand = (TableCommand) other;
        return otherCommand.args.equals(this.args) && otherCommand.year == this.year;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("category: ", args)
                .toString();
    }

}
