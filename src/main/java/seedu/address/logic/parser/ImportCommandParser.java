package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
        String path = projectPath + "\\data\\" + args.trim();

        File csvFile = new File(path);
        if (!csvFile.exists()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            String header = reader.readLine();
            String[] headers = header.split(",");

            String line = reader.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Student student = parseStudentFromCSV(headers, attributes);
                students.add(student);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new ParseException("Error reading CSV file: " + e.getMessage());
        }

        return new ImportCommand(students, args);
    }

    private Student parseStudentFromCSV(String[] headers, String[] attributes) throws ParseException{
//        int[] index = new int[headers.length];
//        for (int i = 0; i < headers.length; i++) {
//            String keyword = headers[i];
//            switch (keyword) {
//            case "Name":
//                index[0] = i;
//                break;
//            case "Phone Number":
//                index[1] = i;
//                break;
//            case "Email":
//                index[2] = i;
//                break;
//            case "Address":
//                index[3] = i;
//                break;
//            case "Gender":
//                index[4] = i;
//                break;
//            case "Sec Level":
//                index[5] = i;
//                break;
//            case "Nearest Mrt Station":
//                index[6] = i;
//                break;
//            case "Subject":
//                index[7] = 7;
//                break;
//            default:
//                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
//            }
//        }

//        Name name = ParserUtil.parseName(attributes[index[0]]);
//        Phone phone = ParserUtil.parsePhone(attributes[index[1]]);
//        Email email = ParserUtil.parseEmail(attributes[index[2]]);
//        Address address = ParserUtil.parseAddress(attributes[index[3]]);
//        Gender gender = ParserUtil.parseGender(attributes[index[4]]);
//        SecLevel secLevel = ParserUtil.parseSecLevel(attributes[index[5]]);
//        MrtStation nearestMrtStation = ParserUtil.parseMrtStation(attributes[index[6]]);
        Name name = ParserUtil.parseName(attributes[0]);
        Phone phone = ParserUtil.parsePhone(attributes[1]);
        Email email = ParserUtil.parseEmail(attributes[2]);

        int count = 3;
        StringBuilder addr = new StringBuilder(attributes[3]);
        while (!(attributes[count].equals("M") || attributes[count].equals("F"))) {
            addr.append(attributes[count]);
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
