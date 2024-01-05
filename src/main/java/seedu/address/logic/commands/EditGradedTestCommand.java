package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FINALS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MIDTERMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRACTICAL_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_READING_ASSESSMENT1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_READING_ASSESSMENT2;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.gradedtest.GradedTest.DEFAULT_VALUE;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.gradedtest.Finals;
import seedu.address.model.gradedtest.GradedTest;
import seedu.address.model.gradedtest.MidTerms;
import seedu.address.model.gradedtest.PracticalExam;
import seedu.address.model.gradedtest.ReadingAssessment1;
import seedu.address.model.gradedtest.ReadingAssessment2;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.person.assignment.AssignmentMap;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new and updated EditGradedTestCommand object
 */
public class EditGradedTestCommand extends Command {

    public static final String COMMAND_WORD = "editgradedtest";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the graded test of a person, "
            + "identified by the student index number used in the displayed person list. \n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + "[" + PREFIX_READING_ASSESSMENT1 + "RA1] "
            + "[" + PREFIX_READING_ASSESSMENT2 + "RA2] "
            + "[" + PREFIX_MIDTERMS + "MIDTERMS] "
            + "[" + PREFIX_FINALS + "FINALS] "
            + "[" + PREFIX_PRACTICAL_EXAM + "PE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_READING_ASSESSMENT1 + "10 "
            + PREFIX_READING_ASSESSMENT2 + "20 "
            + PREFIX_MIDTERMS + "70 "
            + PREFIX_FINALS + "100 "
            + PREFIX_PRACTICAL_EXAM + "100 \n"
            + "Note: At least 1 field must be present after the STUDENT_INDEX";

    public static final String MESSAGE_EDIT_GRADEDTEST_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    public static final CommandType COMMAND_TYPE = CommandType.EDIT_GRADED_TEST;

    private final Optional<ReadingAssessment1> ra1;
    private final Optional<ReadingAssessment2> ra2;
    private final Optional<MidTerms> midTerms;
    private final Optional<Finals> finals;
    private final Optional<PracticalExam> pe;
    private final Index index;

    /**
     * Creates an EditGradedTestCommand to edit the specified score of a person's graded test.
     */
    public EditGradedTestCommand(Index index, Optional<ReadingAssessment1> ra1, Optional<ReadingAssessment2> ra2,
                                 Optional<MidTerms> midTerms, Optional<Finals> finals, Optional<PracticalExam> pe) {
        requireAllNonNull(index, ra1, ra2, midTerms, finals, pe);
        this.index = index;
        this.ra1 = ra1;
        this.ra2 = ra2;
        this.midTerms = midTerms;
        this.finals = finals;
        this.pe = pe;
    }

    /**
     * Executes the EditGradedTestCommand to update a person's graded test in the address book.
     *
     * @param model The current model representing the address book.
     * @return A CommandResult representing the result of the command execution.
     * @throws CommandException If there is an error during command execution,
     *      such as an invalid index or duplicate person.
     */
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedGradedTestPerson(personToEdit);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_GRADEDTEST_SUCCESS,
                Messages.format(editedPerson)), COMMAND_TYPE);
    }

    @Override
    public CommandType getCommandType() {
        return COMMAND_TYPE;
    }

    /**
     * Creates a new Person with the newly graded assignment.
     *
     * @param reference The person to be graded.
     * @return New person with a graded assignment.
     */
    public Person createEditedGradedTestPerson(Person reference) {
        Name name = reference.getName();
        Phone phone = reference.getPhone();
        Email email = reference.getEmail();
        TelegramHandle telegramHandle = reference.getTelegramHandle();
        Set<Tag> tags = reference.getTags();
        AssignmentMap assignmentMap = reference.getAllAssignments();
        Set<GradedTest> gradedTest = new HashSet<>(reference.getGradedTest());

        GradedTest existingGradedTest = null;
        for (GradedTest gt : gradedTest) {
            existingGradedTest = gt;
            break;
        }

        if (existingGradedTest == null) {
            existingGradedTest = new GradedTest(
                    new ReadingAssessment1(DEFAULT_VALUE),
                    new ReadingAssessment2(DEFAULT_VALUE),
                    new MidTerms(DEFAULT_VALUE),
                    new Finals(DEFAULT_VALUE),
                    new PracticalExam(DEFAULT_VALUE)
            );
        }

        // Check and update each component if provided, retain the existing value otherwise
        ReadingAssessment1 editedRA1 = ra1.orElse(existingGradedTest.getRA1());
        ReadingAssessment2 editedRA2 = ra2.orElse(existingGradedTest.getRA2());
        MidTerms editedMidTerms = midTerms.orElse(existingGradedTest.getMidTerms());
        Finals editedFinals = finals.orElse(existingGradedTest.getFinals());
        PracticalExam editedPE = pe.orElse(existingGradedTest.getPracticalExam());

        GradedTest editedGradedTest = new GradedTest(editedRA1, editedRA2, editedMidTerms, editedFinals, editedPE);

        gradedTest.remove(existingGradedTest);
        gradedTest.add(editedGradedTest);

        return new Person(name, phone, email, telegramHandle, tags, assignmentMap, gradedTest);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditGradedTestCommand)) {
            return false;
        }

        EditGradedTestCommand e = (EditGradedTestCommand) other;
        return index.equals(e.index)
                && ra1.equals(e.ra1)
                && ra2.equals(e.ra2)
                && midTerms.equals(e.midTerms)
                && finals.equals(e.finals)
                && pe.equals(e.pe);
    }
}
