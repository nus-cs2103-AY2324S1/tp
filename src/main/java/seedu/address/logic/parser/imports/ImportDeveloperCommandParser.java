package seedu.address.logic.parser.imports;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_FILE;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import seedu.address.logic.commands.imports.ImportDeveloperCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Date;
import seedu.address.model.commons.Name;
import seedu.address.model.developer.Developer;
import seedu.address.model.developer.DeveloperRoles;
import seedu.address.model.developer.GithubId;
import seedu.address.model.developer.Rating;
import seedu.address.model.developer.Salary;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and
 * creates a new {@link ImportDeveloperCommand} object for importing developer data from a CSV file.
 * The CSV file should contain columns with specific column names and data in a certain format.
 */
public class ImportDeveloperCommandParser implements Parser<ImportDeveloperCommand> {

    /**
     * Parses the provided file name to import developer data from a CSV file.
     *
     * @param fileName The name of the CSV file to import developer data from.
     * @return A {@link ImportDeveloperCommand} for importing developer data.
     * @throws ParseException If there are issues with file handling or if the file format is invalid.
     */
    @Override
    public ImportDeveloperCommand parse(String fileName) throws ParseException {
        try {
            fileName = fileName.trim();
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = "";
            String splitBy = ",";
            boolean isValid = checkColumnNames(br.readLine());
            if (!isValid) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ImportDeveloperCommand.MESSAGE_USAGE));
            }
            ArrayList<Developer> toAddList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(splitBy); // use comma as separator
                Name name = ParserUtil.parseName(employee[0]);
                Phone phone = ParserUtil.parsePhone(employee[1]);
                Email email = ParserUtil.parseEmail(employee[2]);
                Address address = ParserUtil.parseAddress(employee[3]);
                Date dateJoined = ParserUtil.parseDateJoined(employee[4]);
                DeveloperRoles role = ParserUtil.parseDeveloperRole(employee[5]);
                Salary salary = ParserUtil.parseSalary(employee[6]);
                GithubId githubId = ParserUtil.parseGithubId(employee[7]);
                Rating rating = ParserUtil.parseRating(employee[8]);
                ArrayList<String> projects = new ArrayList<>();
                for (int i = 9; i < employee.length; i++) {
                    projects.add(employee[i]);
                }
                Set<String> projectList = ParserUtil.parseProjectsToSet(projects);

                Developer developer =
                        new Developer(name, phone, email,
                                address, role, projectList, salary, dateJoined, githubId, rating);
                toAddList.add(developer);

            }
            return new ImportDeveloperCommand(toAddList);
        } catch (FileNotFoundException ex) {
            throw new ParseException(MESSAGE_INVALID_FILE);
        } catch (IOException e) {
            throw new ParseException("Error reading line from file " + fileName);
        }
    }

    /**
     * Checks if the CSV file contains valid column names based on expected format.
     *
     * @param line The header line of the CSV file.
     * @return True if the column names match the expected format, false otherwise.
     */
    private boolean checkColumnNames(String line) {
        String[] columnNames = line.split(",");
        if (!columnNames[0].contains("Name")) {
            return false;
        }
        if (!columnNames[1].contains("Contact Number")) {
            return false;
        }
        if (!columnNames[2].contains("Email")) {
            return false;
        }
        if (!columnNames[3].contains("Address")) {
            return false;
        }
        if (!columnNames[4].contains("Date Joined")) {
            return false;
        }
        if (!columnNames[5].contains("Role")) {
            return false;
        }
        if (!columnNames[6].contains("Salary")) {
            return false;
        }
        if (!columnNames[7].contains("GithubId")) {
            return false;
        }
        if (!columnNames[8].contains("Rating")) {
            return false;
        }
        if (!columnNames[9].contains("Projects")) {
            return false;
        }
        return true;
    }
}
