package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICALHISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseComplexSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindPredicateMap;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseComplexFailure;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.predicates.AgeContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.LocationContainsKeywordsPredicate;
import seedu.address.model.person.predicates.MedHistoryContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.SpecialtyContainsKeywordsPredicate;


public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseComplexFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE_PATIENT), PersonType.PATIENT);
        assertParseComplexFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE_SPECIALIST), PersonType.SPECIALIST);
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        List<String> keywords = Arrays.asList("Alice", "Bob");
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_NAME, new NameContainsKeywordsPredicate(keywords));

        FindCommand expectedCommand = new FindCommand(findPredicateMap, PersonType.PATIENT);
        // no leading and trailing whitespaces
        assertParseComplexSuccess(parser, " " + PREFIX_NAME + " Alice Bob", expectedCommand, PersonType.PATIENT);
        // multiple whitespaces between keywords
        assertParseComplexSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t",
                expectedCommand, PersonType.PATIENT);
    }

    @Test
    public void parse_validPhoneArgs_returnsFindCommand() {
        List<String> keywords = Arrays.asList("92773291", "88765321");
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_PHONE, new PhoneContainsKeywordsPredicate(keywords));

        FindCommand expectedCommand = new FindCommand(findPredicateMap, PersonType.PATIENT);
        // no leading and trailing whitespaces
        assertParseComplexSuccess(parser, " " + PREFIX_PHONE + " 92773291 88765321",
                expectedCommand, PersonType.PATIENT);
        // multiple whitespaces between keywords
        assertParseComplexSuccess(parser, " " + PREFIX_PHONE + " \n 92773291 \n \t 88765321  \t",
                expectedCommand, PersonType.PATIENT);
    }

    @Test
    public void parse_validEmailArgs_returnsFindCommand() {
        List<String> keywords = Arrays.asList("timothy@gmail.com", "neumann@hotmail.com");
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_EMAIL, new EmailContainsKeywordsPredicate(keywords));
        FindCommand expectedCommand = new FindCommand(findPredicateMap, PersonType.PATIENT);
        // no leading and trailing whitespaces
        assertParseComplexSuccess(parser, " " + PREFIX_EMAIL + " timothy@gmail.com neumann@hotmail.com",
                expectedCommand, PersonType.PATIENT);
        // multiple whitespaces between keywords
        assertParseComplexSuccess(parser, " " + PREFIX_EMAIL + " \n timothy@gmail.com \n \t neumann@hotmail.com  \t",
                expectedCommand, PersonType.PATIENT);
    }

    @Test
    public void parse_validAgeAndMedHistArgs_returnsFindCommand() {
        List<String> ageKeywords = Arrays.asList("21", "37");
        List<String> medHistKeywords = Arrays.asList("Bronchitis", "ADHD", "Diabetes");
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_AGE, new AgeContainsKeywordsPredicate(ageKeywords));
        findPredicateMap.put(PREFIX_MEDICALHISTORY, new MedHistoryContainsKeywordsPredicate(medHistKeywords));

        String userInputArgs1 = String.format(" %s 21 37 %s Bronchitis ADHD Diabetes",
                PREFIX_AGE, PREFIX_MEDICALHISTORY);

        String userInputArgs2 = String.format(" %s 21 \n 37 \t %s Bronchitis \n ADHD \t Diabetes \t",
                PREFIX_AGE, PREFIX_MEDICALHISTORY);

        FindCommand expectedCommand = new FindCommand(findPredicateMap, PersonType.PATIENT);

        // no leading and trailing whitespaces
        assertParseComplexSuccess(parser, userInputArgs1, expectedCommand, PersonType.PATIENT);
        // multiple whitespaces between keywords
        assertParseComplexSuccess(parser, userInputArgs2, expectedCommand, PersonType.PATIENT);
    }

    @Test
    public void parse_validSpecialtyAndLocationArgs_returnsFindCommand() {
        List<String> specialtyKeywords = Arrays.asList("Orthopaedic", "Neurology");
        List<String> locationKeywords = Arrays.asList("Clementi", "Bukit", "Timah");
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_SPECIALTY, new SpecialtyContainsKeywordsPredicate(specialtyKeywords));
        findPredicateMap.put(PREFIX_LOCATION, new LocationContainsKeywordsPredicate(locationKeywords));

        String userInputArgs1 = String.format(" %s Orthopaedic Neurology %s Clementi Bukit Timah",
                PREFIX_SPECIALTY, PREFIX_LOCATION);

        String userInputArgs2 = String.format(" %s Orthopaedic \n Neurology \t %s Clementi \n Bukit \t Timah \t",
                PREFIX_SPECIALTY, PREFIX_LOCATION);

        FindCommand expectedCommand = new FindCommand(findPredicateMap, PersonType.SPECIALIST);

        // no leading and trailing whitespaces
        assertParseComplexSuccess(parser, userInputArgs1, expectedCommand, PersonType.SPECIALIST);
        // multiple whitespaces between keywords
        assertParseComplexSuccess(parser, userInputArgs2, expectedCommand, PersonType.SPECIALIST);
    }
}
