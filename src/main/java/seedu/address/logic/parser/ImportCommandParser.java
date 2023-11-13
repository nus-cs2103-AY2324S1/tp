package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_ADDRESS;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_EMAIL;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_ENROL_DATE;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_GENDER;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_MRT_STATION;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_NAME;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_PHONE;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_SEC_LEVEL;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_SUBJECT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.MrtStation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SecLevel;
import seedu.address.model.person.Student;
import seedu.address.model.tag.EnrolDate;
import seedu.address.model.tag.Subject;

/**
 * Parses input arguments and creates a new ImportCommand object
 */
public class ImportCommandParser implements Parser<ImportCommand> {

    public static final String MESSAGE_ERROR_READING_FILE = "Error reading CSV file: ";

    /**
     * Parses the given {@code String} of arguments in the context of the ImportCommand
     * and returns a ImportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ImportCommand.MESSAGE_USAGE));
        }

        List<Student> students = new ArrayList<>();

        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/" + args.trim();

        File csvFile = new File(path);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            String header = reader.readLine();
            String[] expectedHeaders = {MESSAGE_NAME, MESSAGE_PHONE, MESSAGE_EMAIL, MESSAGE_ADDRESS, MESSAGE_GENDER,
                MESSAGE_SEC_LEVEL, MESSAGE_MRT_STATION, MESSAGE_SUBJECT, MESSAGE_ENROL_DATE};

            if (header == null) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    "Header Error!\n" + ImportCommand.MESSAGE_USAGE));
            }
            String[] actualHeaders = header.split(",");

            for (int i = 0; i < expectedHeaders.length; i++) {
                if (actualHeaders.length == 0
                    || !actualHeaders[i].trim().toLowerCase().equals(expectedHeaders[i].toLowerCase())) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        "Header Error!\n" + ImportCommand.MESSAGE_USAGE));
                }
            }

            if (actualHeaders.length > expectedHeaders.length) {
                int count = 7;
                for (int i = 9; i < actualHeaders.length; i++) {
                    if (!actualHeaders[i].trim().toLowerCase().equals(expectedHeaders[count].toLowerCase())) {
                        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                "Header Error!\n" + ImportCommand.MESSAGE_USAGE));
                    }
                    count = 7 + i % 2;
                }
            }

            int count = 1;
            String line = reader.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Student student = null;
                try {
                    student = parseStudentFromCsv(attributes);
                } catch (ParseException e) {
                    throw new ParseException("For student at index " + count + ": " + e.getMessage());
                }
                if (student == null) {
                    break;
                }
                students.add(student);
                line = reader.readLine();
                count++;
            }
        } catch (IOException e) {
            throw new ParseException(MESSAGE_ERROR_READING_FILE + args);
        }

        return new ImportCommand(students, args);
    }

    private Student parseStudentFromCsv(String[] attributes) throws ParseException {
        if (attributes.length == 0) {
            return null;
        }
        Name name = ParserUtil.parseName(attributes[0]);
        Phone phone = ParserUtil.parsePhone(attributes[1]);
        Email email = ParserUtil.parseEmail(attributes[2]);

        int count = 4;
        StringBuilder addr = new StringBuilder(attributes[3]);
        while (!(attributes[count - 1].endsWith("\""))) {
            addr.append(", ").append(attributes[count]);
            count++;
        }
        Address address = ParserUtil.parseAddress(addr.toString().replace("\"", ""));

        Gender gender = ParserUtil.parseGender(attributes[count]);
        count++;
        SecLevel secLevel = ParserUtil.parseSecLevel(attributes[count]);
        count++;
        MrtStation nearestMrtStation = ParserUtil.parseMrtStation(attributes[count]);
        count++;
        List<String> remaining = new ArrayList<>(Arrays.asList(attributes).subList(count, attributes.length));

        int disp = count % 2;
        Iterator<String> remainingIterator = remaining.iterator();
        List<String> subjects = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        while (remainingIterator.hasNext()) {
            String next = remainingIterator.next().trim();
            if (count % 2 == disp) {
                subjects.add(next);
                count++;
            } else {
                if (next.isEmpty() && subjects.size() > 0) {
                    dates.add(YearMonth.now().format(DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH)));
                } else {
                    dates.add(next);
                }
                count++;
            }
        }

        Collection<EnrolDate> parsedDates = ParserUtil.parseDates(dates);
        Set<Subject> parsedSubjects = ParserUtil.parseTags(subjects, parsedDates);

        return new Student(name, phone, email, address,
                gender, secLevel, nearestMrtStation, parsedSubjects);
    }


}
