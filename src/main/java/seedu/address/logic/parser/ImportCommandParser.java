package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import seedu.address.model.tag.Subject;

/**
 * Parses input arguments and creates a new ImportCommand object
 */
public class ImportCommandParser implements Parser<ImportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ImportCommand
     * and returns a ImportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportCommand parse(String args) throws ParseException {
        List<Student> students = new ArrayList<>();

        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "\\" + args.trim();

        File csvFile = new File(path);
        if (!csvFile.exists()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            String header = reader.readLine();
            String[] expectedHeaders = {"Name", "Phone Number", "Email", "Address",
                "Gender", "Sec level", "Nearest Mrt station", "Subject"};

            String[] actualHeaders = header.split(",");

            if (!(actualHeaders.length == expectedHeaders.length
                    || actualHeaders.length == expectedHeaders.length - 1)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
            }

            String line = reader.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Student student = parseStudentFromCsv(attributes);
                students.add(student);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new ParseException("Error reading CSV file: " + e.getMessage());
        }

        return new ImportCommand(students, args);
    }

    private Student parseStudentFromCsv(String[] attributes) throws ParseException {
        Name name = ParserUtil.parseName(attributes[0]);
        Phone phone = ParserUtil.parsePhone(attributes[1]);
        Email email = ParserUtil.parseEmail(attributes[2]);

        int count = 3;
        StringBuilder addr = new StringBuilder();
        while (!(attributes[count].equalsIgnoreCase("M")
                || attributes[count].equalsIgnoreCase("F"))) {
            addr.append(", ").append(attributes[count]);
            count++;
        }
        Address address = ParserUtil.parseAddress(addr.toString());

        Gender gender = ParserUtil.parseGender(attributes[count]);
        count++;
        SecLevel secLevel = ParserUtil.parseSecLevel(attributes[count]);
        count++;
        MrtStation nearestMrtStation = ParserUtil.parseMrtStation(attributes[count]);
        count++;
        List<String> subjects = new ArrayList<>(Arrays.asList(attributes).subList(count, attributes.length));
        Set<Subject> subjectList = ParserUtil.parseTags(subjects);

        return new Student(name, phone, email, address,
                gender, secLevel, nearestMrtStation, subjectList);
    }


}
