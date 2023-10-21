package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEC_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Gender;
import seedu.address.model.person.SecLevel;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentIsGenderPredicate;
import seedu.address.model.person.StudentIsSecLevelPredicate;
import seedu.address.model.person.StudentTakesSubjectPredicate;
import seedu.address.model.tag.Subject;
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
            + "[" + PREFIX_SUBJECT + "]...\n"
            + "Example: " + COMMAND_WORD + PREFIX_GENDER;

    public static final String MESSAGE_INCORRECT_COMMAND = "To view a table, please do one of the following:\n"
                                                        + "table " + PREFIX_GENDER + " or\n"
                                                        + "table " + PREFIX_SEC_LEVEL + " or\n"
                                                        + "table " + PREFIX_SUBJECT;
    private final String args;

    /**
     * Constructor for TableCommand.
     * @param args represents the category for table, eg: s/, l/, g/
     */
    public TableCommand(String args) {
        this.args = args.trim();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch(this.args) {
        case "g/":
            return executeGender(model);
        case "l/":
            return executeSecLevel(model);
        case "s/":
            return executeSubject(model);
        default:
            throw new CommandException(MESSAGE_INCORRECT_COMMAND);

        }
    }

    /**
     * Generate GenderTableCommandResult instance.
     * @param model instance of Model subclass, e.g. ModelManager instance
     * @return GenderTableCommandResult instance containing the column titles and values.
     */
    private GenderTableCommandResult executeGender(Model model) {
        Map<String, Long> columnValueMapping = new HashMap<>();

        String[] titles = new String[]{"Male", "Female"};

        ObservableList<Student> studentList = model.getFilteredPersonList();

        StudentIsGenderPredicate isMalePredicate = new StudentIsGenderPredicate(new Gender("M"));
        StudentIsGenderPredicate isFemalePredicate = new StudentIsGenderPredicate(new Gender("F"));
        Stream<StudentIsGenderPredicate> predicateStream = Stream.of(isMalePredicate, isFemalePredicate);

        Long[] values = predicateStream
                .map(p -> studentList.stream().filter(p).count())
                .toArray(Long[]::new);

        for (int i = 0; i < titles.length; i++) {
            columnValueMapping.put(titles[i], values[i]);
        }

        return new GenderTableCommandResult(columnValueMapping);
    }

    /**
     * Generate SecLevelTableCommandResult instance
     * @param model instance of Model subclass, e.g. ModelManager instance
     * @return SecLevelTableCommandResult instance containing column titles and values.
     */
    private SecLevelTableCommandResult executeSecLevel(Model model) {
        Map<String, Long> columnValueMapping = new HashMap<>();
        String[] titles = new String[] {SecLevel.SEC1, SecLevel.SEC2, SecLevel.SEC3, SecLevel.SEC4};

        ObservableList<Student> studentList = model.getFilteredPersonList();

        StudentIsSecLevelPredicate isSec1Predicate = new StudentIsSecLevelPredicate(new SecLevel("1"));
        StudentIsSecLevelPredicate isSec2Predicate = new StudentIsSecLevelPredicate(new SecLevel("2"));
        StudentIsSecLevelPredicate isSec3Predicate = new StudentIsSecLevelPredicate(new SecLevel("3"));
        StudentIsSecLevelPredicate isSec4Predicate = new StudentIsSecLevelPredicate(new SecLevel("4"));

        Stream<StudentIsSecLevelPredicate> predicateStream = Stream.of(
                isSec1Predicate,
                isSec2Predicate,
                isSec3Predicate,
                isSec4Predicate
        );

        Long[] values = predicateStream
                .map(p -> studentList.stream().filter(p).count())
                .toArray(Long[]::new);

        for (int i = 0; i < titles.length; i++) {
            columnValueMapping.put(titles[i], values[i]);
        }

        return new SecLevelTableCommandResult(columnValueMapping);
    }

    /**
     * Generate SubjectTableCommandResult instance.
     * @param model instance of Model subclass, e.g. ModelManager instance.
     * @return SubjectTableCommandResult instance containing column titles and values.
     */
    private SubjectTableCommandResult executeSubject(Model model) {
        Map<String, Long> columnValueMapping = new HashMap<>();

        String[] titles = new String[] {Subject.MATHS, Subject.CS, Subject.CHEMI, Subject.BIO,
                                        Subject.ENG, Subject.PHY};

        ObservableList<Student> studentList = model.getFilteredPersonList();

        StudentTakesSubjectPredicate takeMathsPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.MATHS));
        StudentTakesSubjectPredicate takeCsPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.CS));
        StudentTakesSubjectPredicate takePhyPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.PHY));
        StudentTakesSubjectPredicate takeChemiPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.CHEMI));
        StudentTakesSubjectPredicate takeBioPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.BIO));
        StudentTakesSubjectPredicate takeEngPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.ENG));

        Stream<StudentTakesSubjectPredicate> predicateStream = Stream.of(
                takeMathsPredicate,
                takeCsPredicate,
                takePhyPredicate,
                takeChemiPredicate,
                takeBioPredicate,
                takeEngPredicate
        );

        Long[] values = predicateStream
                .map(p -> studentList.stream().filter(p).count())
                .toArray(Long[]::new);

        for (int i = 0; i < titles.length; i++) {
            columnValueMapping.put(titles[i], values[i]);
        }

        return new SubjectTableCommandResult(columnValueMapping);
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
        return otherCommand.args.equals(this.args);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("category: ", args)
                .toString();
    }

}
