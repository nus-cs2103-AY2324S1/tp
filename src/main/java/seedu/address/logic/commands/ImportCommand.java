package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FIELD_EMAIL;
import static seedu.address.logic.parser.CliSyntax.FIELD_GPA;
import static seedu.address.logic.parser.CliSyntax.FIELD_NAME;
import static seedu.address.logic.parser.CliSyntax.FIELD_PHONE;
import static seedu.address.logic.parser.CliSyntax.FIELD_PREVIOUS_GRADE;
import static seedu.address.logic.parser.CliSyntax.FIELD_STUDENT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.FIELD_TAGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attachment.Attachment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gpa;
import seedu.address.model.person.IsBookmarked;
import seedu.address.model.person.IsHidden;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PreviousGrade;
import seedu.address.model.person.StudentNumber;
import seedu.address.model.tag.Tag;

/**
 * Imports new applicants from the given file.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports applicants from the file "
            + "specified, reading it in CSV format. The CSV header row is required, any\n "
            + "additional fields other than those supported will be ignored, and any missing "
            + "fields will be reported as an error.\n"
            + "Supported fields: studentNo, name, phone, email, gpa, previousGrade, tags.\n"
            + "Parameters: " + PREFIX_FILE + "FILE\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_FILE + "samples/applicants.csv";
    public static final String MESSAGE_IMPORT_SUCCESS = "Imported %1$s applicants successfully!";
    public static final String MESSAGE_IMPORT_NONE = "No applicants were imported.";
    public static final String MESSAGE_IMPORT_FAILURE = "Failed to import %1$s applicants, on line %2$s.";
    public static final String MESSAGE_FAILED_TO_OPEN = "Failed to open and load applicant file.";
    public static final String MESSAGE_INVALID_FILE_FORMAT = "File format is invalid.";
    public static final String MESSAGE_MISSING_FIELDS = "Missing fields from file: %1$s.";

    private static final String CELL_DELIM = ",";
    private static final String DATA_ARRAY_DELIM = ";";
    private static final Set<String> REQUIRED_FIELDS = Set.of(
            FIELD_STUDENT_NUMBER, FIELD_NAME,
            FIELD_PHONE, FIELD_EMAIL, FIELD_GPA,
            FIELD_PREVIOUS_GRADE, FIELD_TAGS);

    private final Attachment attachment;

    /**
     * Creates a new import command, which will import applicants listed in the
     * given
     * file (read in using CSV format).
     *
     * @param attachment The path of the file to be imported
     */
    public ImportCommand(Attachment attachment) {
        this.attachment = attachment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> applicants = new ArrayList<>();
        List<Integer> failureLineNos = new ArrayList<>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(attachment.file);

            if (!scanner.hasNextLine()) {
                throw new CommandException(MESSAGE_INVALID_FILE_FORMAT);
            }
            String header = scanner.nextLine();
            Map<String, Integer> fieldIndices = buildFieldIndices(header);

            int lineNo = 1;
            while (scanner.hasNextLine()) {
                lineNo++;
                String line = scanner.nextLine();
                try {
                    Person applicant = parseLine(fieldIndices, line);
                    applicants.add(applicant);
                } catch (ParsePersonException e) {
                    failureLineNos.add(lineNo);
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            throw new CommandException(MESSAGE_FAILED_TO_OPEN, e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        int newApplicantCount = applicants.size();
        for (Person applicant : applicants) {
            if (model.hasPerson(applicant)) {
                newApplicantCount--;
                continue;
            }
            model.addPerson(applicant);
        }

        String successMessage = null;
        if (!applicants.isEmpty()) {
            successMessage = String.format(MESSAGE_IMPORT_SUCCESS, newApplicantCount);
        }

        String failureMessage = null;
        if (!failureLineNos.isEmpty()) {
            List<String> lineNos = failureLineNos.stream().map(Object::toString).collect(Collectors.toList());
            failureMessage = String.format(
                    MESSAGE_IMPORT_FAILURE,
                    failureLineNos.size(),
                    String.join(", ", lineNos));
        }

        String message;
        if (successMessage != null && failureMessage != null) {
            message = successMessage + " " + failureMessage;
        } else if (successMessage != null) {
            message = successMessage;
        } else if (failureMessage != null) {
            message = failureMessage;
        } else {
            message = MESSAGE_IMPORT_NONE;
        }

        return new CommandResult(message);
    }

    private Map<String, Integer> buildFieldIndices(String header) throws CommandException {
        String[] fields = header.split(CELL_DELIM);

        Set<String> fieldSet = Set.of(fields);
        if (!fieldSet.containsAll(REQUIRED_FIELDS)) {
            Set<String> requiredFieldsCopy = new HashSet<>(REQUIRED_FIELDS);
            requiredFieldsCopy.removeAll(fieldSet);
            throw new CommandException(
                    String.format(MESSAGE_MISSING_FIELDS, String.join(", ", requiredFieldsCopy)));
        }

        Map<String, Integer> fieldIndices = new HashMap<>(fields.length);
        for (int i = 0; i < fields.length; i++) {
            fieldIndices.put(fields[i], i);
        }

        return fieldIndices;
    }

    private Person parseLine(Map<String, Integer> fieldIndices, String line) throws ParsePersonException {
        String[] data = line.split(CELL_DELIM, -1);

        String studentNoString = data[fieldIndices.get(FIELD_STUDENT_NUMBER)];
        if (!StudentNumber.isValidStudentNumber(studentNoString)) {
            throw new ParsePersonException("Invalid student number");
        }
        StudentNumber studentNo = new StudentNumber(studentNoString);

        String nameString = data[fieldIndices.get(FIELD_NAME)];
        if (!Name.isValidName(nameString)) {
            throw new ParsePersonException("Invalid name");
        }
        Name name = new Name(nameString);

        String phoneString = data[fieldIndices.get(FIELD_PHONE)];
        if (!Phone.isValidPhone(phoneString)) {
            throw new ParsePersonException("Invalid phone");
        }
        Phone phone = new Phone(phoneString);

        String emailString = data[fieldIndices.get(FIELD_EMAIL)];
        if (!Email.isValidEmail(emailString)) {
            throw new ParsePersonException("Invalid email");
        }
        Email email = new Email(emailString);

        String gpaString = data[fieldIndices.get(FIELD_GPA)];
        double gpaDouble;
        try {
            gpaDouble = Double.parseDouble(gpaString);
        } catch (NumberFormatException e) {
            throw new ParsePersonException("Invalid GPA");
        }
        if (!Gpa.isValidGpa(gpaDouble)) {
            throw new ParsePersonException("Invalid GPA");
        }
        Gpa gpa = new Gpa(gpaDouble);

        String previousGradeString = data[fieldIndices.get(FIELD_PREVIOUS_GRADE)];
        if (!PreviousGrade.isValidGrade(previousGradeString)) {
            throw new ParsePersonException("Invalid previous grade");
        }
        PreviousGrade previousGrade = new PreviousGrade(previousGradeString);

        String tagsString = data[fieldIndices.get(FIELD_TAGS)];
        String[] tagStrings = tagsString.split(DATA_ARRAY_DELIM);
        Set<Tag> tags = Set.of();
        if (tagStrings.length > 1 || tagStrings.length == 0 || !tagStrings[0].trim().equals("")) {
            boolean isValid = List.of(tagStrings).stream().map(Tag::isValidTagName).allMatch(v -> v);
            if (!isValid) {
                throw new ParsePersonException("Invalid tag");
            }
            tags = List.of(tagStrings).stream().map(Tag::new).collect(Collectors.toSet());
        }

        Person applicant = new Person(
                studentNo, name, phone, email, gpa, previousGrade,
                Optional.empty(), Optional.empty(), tags, List.of(),
                new IsHidden(false), new IsBookmarked(false));

        return applicant;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ImportCommand)) {
            return false;
        }

        ImportCommand otherImportCommand = (ImportCommand) other;
        return attachment.equals(otherImportCommand.attachment);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("attachment", attachment)
                .toString();
    }

    private static class ParsePersonException extends IllegalValueException {

        public ParsePersonException(String message) {
            super(message);
        }

        public ParsePersonException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
