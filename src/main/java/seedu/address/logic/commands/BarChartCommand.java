package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENROL_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEC_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.OrganizeData;
import seedu.address.logic.commands.barchartresults.EnrolDateBarChartCommandResult;
import seedu.address.logic.commands.barchartresults.GenderBarChartCommandResult;
import seedu.address.logic.commands.barchartresults.SecLevelBarChartCommandResult;
import seedu.address.logic.commands.barchartresults.SubjectBarChartCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Calculate counts for each category(to be specified, eg: by gender, Sec-Level, Subject)
 * for bar chart generation.
 */
public class BarChartCommand extends Command {
    public static final String COMMAND_WORD = "bar";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": show the statistics of student in a bar chart "
            + "by the attributes. \n"
            + "[" + PREFIX_GENDER + "] "
            + "[" + PREFIX_SEC_LEVEL + "] "
            + "[" + PREFIX_SUBJECT + "]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_GENDER;

    public static final String MESSAGE_INCORRECT_COMMAND = "To view a bar chart, please do one of the following:\n"
            + COMMAND_WORD + " " + PREFIX_GENDER + " or\n"
            + COMMAND_WORD + " " + PREFIX_SEC_LEVEL + " or\n"
            + COMMAND_WORD + " " + PREFIX_SUBJECT;
    private final String args;
    private final int year;

    /**
     * Constructor for BarChartCommand.
     * @param args represents the category for table, eg: s/, l/, g/
     */
    public BarChartCommand(String args) {
        this.args = args.trim();
        this.year = -1;
    }

    /**
     * Constructor for BarChartCommand with year.
     * @param args represents the category for table, eg: d/2023
     */
    public BarChartCommand(String args, int year) {
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
     * Generate GenderBarChartCommandResult instance.
     * @param model instance of Model subclass, e.g. ModelManager instance
     * @return GenderBarChartCommandResult instance containing the column titles and values.
     */
    private GenderBarChartCommandResult executeGender(Model model) {
        return new GenderBarChartCommandResult(OrganizeData.byGender(model));
    }

    /**
     * Generate SecLevelBarChartCommandResult instance
     * @param model instance of Model subclass, e.g. ModelManager instance
     * @return SecLevelBarChartCommandResult instance containing column titles and values.
     */
    private SecLevelBarChartCommandResult executeSecLevel(Model model) {
        return new SecLevelBarChartCommandResult(OrganizeData.bySecLevel(model));
    }

    /**
     * Generate SubjectBarChartCommandResult instance.
     * @param model instance of Model subclass, e.g. ModelManager instance.
     * @return SubjectBarChartCommandResult instance containing column titles and values.
     */
    private SubjectBarChartCommandResult executeSubject(Model model) {
        return new SubjectBarChartCommandResult(OrganizeData.bySubject(model));
    }

    /**
     * Generate EnrolDateBarChartCommandResult instance.
     * @param model instance of Model subclass, e.g. ModelManager instance.
     * @return EnrolDateBarChartCommandResult instance containing column titles and values.
     */
    private EnrolDateBarChartCommandResult executeEnrolDate(Model model) {
        return new EnrolDateBarChartCommandResult(OrganizeData.byEnrolDate(model, year));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BarChartCommand)) {
            return false;
        }

        BarChartCommand otherCommand = (BarChartCommand) other;
        return otherCommand.args.equals(this.args);
    }

    @Override
    public String toString() {
        if (year == -1) {
            return new ToStringBuilder(this)
                    .add("category: ", args)
                    .toString();
        } else {
            return new ToStringBuilder(this)
                    .add("category: ", args + year)
                    .toString();
        }
    }

}
