package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.exceptions.DuplicateConsultationException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentSet;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Creates a consultation to the consultation list.
 */
public class CreateConsultCommand extends Command {
    public static final String COMMAND_WORD = "createconsult";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a consultation with the student(s). "
            + "Parameters: "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + "[" + PREFIX_NAME + "NAME]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "10/10/2023 "
            + PREFIX_TIME + "15:00 "
            + PREFIX_NAME + "John Doe "
            + PREFIX_NAME + "Foo Bar";
    public static final String MESSAGE_SUCCESS = "New consultation added: %1$s";
    public static final String MESSAGE_PERSON_NOT_FOUND = "No student matching given name(s)";
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "Consultation list contains duplicate consultation(s).";

    public static final CommandType COMMAND_TYPE = CommandType.CREATE_CONSULT;
    private final LocalDate date;
    private final LocalTime time;
    private Set<Name> names;
    private Consultation consultationToAdd;

    /**
     * Creates an CreateConsultCommand to with the specified {@code date, time, names}
     */
    public CreateConsultCommand(LocalDate date, LocalTime time, Set<Name> names) {
        requireAllNonNull(date, time, names);

        this.date = date;
        this.time = time;
        this.names = names;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            StudentSet studentsToAdd = new StudentSet();
            for (Name name : names) {
                Person studentToAdd = model.getMatchingStudentName(name);
                studentsToAdd.add(studentToAdd);
            }
            this.consultationToAdd = new Consultation(date, time, studentsToAdd);
        } catch (PersonNotFoundException pe) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }

        try {
            model.addConsultation(this.consultationToAdd);
        } catch (DuplicateConsultationException dce) {
            throw new CommandException(MESSAGE_DUPLICATE_CONSULTATION);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(this.consultationToAdd)),
                COMMAND_TYPE);
    }

    @Override
    public CommandType getCommandType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles null case
        if (!(other instanceof CreateConsultCommand)) {
            return false;
        }

        CreateConsultCommand otherCreateConsultCommand = (CreateConsultCommand) other;
        return this.date.equals(otherCreateConsultCommand.date)
                && this.time.equals(otherCreateConsultCommand.time)
                && this.names.equals(otherCreateConsultCommand.names);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", consultationToAdd)
                .toString();
    }
}
