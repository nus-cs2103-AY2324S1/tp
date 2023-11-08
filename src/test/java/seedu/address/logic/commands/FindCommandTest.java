package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICALHISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.predicates.AgeContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.LocationContainsKeywordsPredicate;
import seedu.address.model.person.predicates.MedHistoryContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.SpecialtyContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagsContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {

    private static final String WHITESPACE_REGEX = "\\s+";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void findCommandEquals() {
        FindPredicateMap findPredicateMap1 = setupPatientFindPredicateMap();
        FindPredicateMap findPredicateMap2 = setupSpecialistFindPredicateMap();

        FindCommand findCommand1 = new FindCommand(findPredicateMap1, PersonType.PATIENT);
        FindCommand findCommand2 = new FindCommand(findPredicateMap2, PersonType.SPECIALIST);

        // same object -> returns true
        assertTrue(findCommand1.equals(findCommand1));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(findPredicateMap1, PersonType.PATIENT);
        assertTrue(findCommand1.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findCommand1.equals(1));

        // null -> returns false
        assertFalse(findCommand1.equals(null));

        // different person type -> returns false
        assertFalse(findCommand1.equals(findCommand2));
    }

    @Test
    public void findPredicateMapEquals() {
        FindPredicateMap findPredicateMap = setupPatientFindPredicateMap();
        FindPredicateMap smallerFindPredicateMap = new FindPredicateMap();
        smallerFindPredicateMap.put(PREFIX_NAME,
                new NameContainsKeywordsPredicate(Collections.singletonList("Lopez")));

        // same object -> returns true
        assertTrue(findPredicateMap.equals(findPredicateMap));

        // same values -> return true;
        FindPredicateMap findPredicateMapCopy = setupPatientFindPredicateMap();
        assertTrue(findPredicateMap.equals(findPredicateMapCopy));

        // different types -> returns false
        assertFalse(findPredicateMap.equals(1));

        // null -> returns false
        assertFalse(findPredicateMap.equals(null));

        // different size -> short circuits and returns false
        assertFalse(findPredicateMap.equals(smallerFindPredicateMap));

    }

    private FindPredicateMap setupPatientFindPredicateMap() {
        // Find Predicates for a Patient
        NameContainsKeywordsPredicate nameContainsKeywordsPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Fabio"));
        PhoneContainsKeywordsPredicate phoneContainsKeywordsPredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("89934991"));
        EmailContainsKeywordsPredicate emailContainsKeywordsPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("patient@gmail.com"));
        AgeContainsKeywordsPredicate ageContainsKeywordsPredicate =
                new AgeContainsKeywordsPredicate(Arrays.asList("35", "40"));
        TagsContainsKeywordsPredicate tagsContainsKeywordsPredicate =
                new TagsContainsKeywordsPredicate(Arrays.asList("tag1", "tag2"));
        MedHistoryContainsKeywordsPredicate medHistoryContainsKeywordsPredicate =
                new MedHistoryContainsKeywordsPredicate(Arrays.asList("Osteoporosis", "Bronchitis"));

        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_NAME, nameContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_PHONE, phoneContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_EMAIL, emailContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_AGE, ageContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_TAG, tagsContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_MEDICALHISTORY, medHistoryContainsKeywordsPredicate);
        return findPredicateMap;
    }

    private FindPredicateMap setupSpecialistFindPredicateMap() {
        // Find Predicates for a Specialist
        NameContainsKeywordsPredicate nameContainsKeywordsPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Selena"));
        PhoneContainsKeywordsPredicate phoneContainsKeywordsPredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("90237753"));
        EmailContainsKeywordsPredicate emailContainsKeywordsPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("specialist@gmail.com"));
        LocationContainsKeywordsPredicate locationContainsKeywordsPredicate =
                new LocationContainsKeywordsPredicate(Collections.singletonList("311, Clementi Ave 2, #02-25"));
        TagsContainsKeywordsPredicate tagsContainsKeywordsPredicate =
                new TagsContainsKeywordsPredicate(Arrays.asList("tag2", "tag3"));
        SpecialtyContainsKeywordsPredicate specialtyContainsKeywordsPredicate =
                new SpecialtyContainsKeywordsPredicate(Arrays.asList("Surgery", "Haematology"));

        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_NAME, nameContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_PHONE, phoneContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_EMAIL, emailContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_LOCATION, locationContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_TAG, tagsContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_SPECIALTY, specialtyContainsKeywordsPredicate);
        return findPredicateMap;
    }

    @Test
    public void execute_zeroKeywords_allPatientsListed() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FindPredicateMap findPredicateMap = setupPersonZeroKeywordsPredicateMap();
        findPredicateMap.put(PREFIX_AGE, prepareAgePredicate(" "));
        findPredicateMap.put(PREFIX_MEDICALHISTORY, prepareMedHistPredicate(" "));
        Predicate<Person> predicate = combinePersonPredicates(findPredicateMap, PersonType.PATIENT);
        FindCommand command = new FindCommand(findPredicateMap, PersonType.PATIENT);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroKeywords_allSpecialistsListed() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FindPredicateMap findPredicateMap = setupPersonZeroKeywordsPredicateMap();
        findPredicateMap.put(PREFIX_LOCATION, prepareLocationPredicate(" "));
        findPredicateMap.put(PREFIX_SPECIALTY, prepareSpecialtyPredicate(" "));
        Predicate<Person> predicate = combinePersonPredicates(findPredicateMap, PersonType.SPECIALIST);
        FindCommand command = new FindCommand(findPredicateMap, PersonType.SPECIALIST);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleSpecialtyKeywordsOnly_multipleSpecialistFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_SPECIALTY, prepareSpecialtyPredicate("Orthopaedic Physiotherapy"));
        Predicate<Person> predicate = combinePersonPredicates(findPredicateMap, PersonType.SPECIALIST);
        FindCommand command = new FindCommand(findPredicateMap, PersonType.SPECIALIST);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameKeywordsOnly_multiplePatientsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_NAME, prepareNamePredicate("Alice Kunz Daniel"));
        Predicate<Person> predicate = combinePersonPredicates(findPredicateMap, PersonType.PATIENT);
        FindCommand command = new FindCommand(findPredicateMap, PersonType.PATIENT);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void nameContainsKeywordsPredicateToStringMethod() {
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_NAME, prepareNamePredicate("keyword"));
        FindCommand findCommand = new FindCommand(findPredicateMap, PersonType.PATIENT);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + findPredicateMap
                + ", personType=" + PersonType.PATIENT + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void phoneContainsKeywordsPredicateToStringMethod() {
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_PHONE, preparePhonePredicate("keyword"));
        FindCommand findCommand = new FindCommand(findPredicateMap, PersonType.PATIENT);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + findPredicateMap
                + ", personType=" + PersonType.PATIENT + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void emailContainsKeywordsPredicateToStringMethod() {
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_EMAIL, prepareEmailPredicate("keyword"));
        FindCommand findCommand = new FindCommand(findPredicateMap, PersonType.PATIENT);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + findPredicateMap
                + ", personType=" + PersonType.PATIENT + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void tagsContainsKeywordsPredicateToStringMethod() {
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_TAG, prepareTagsPredicate("keyword"));
        FindCommand findCommand = new FindCommand(findPredicateMap, PersonType.PATIENT);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + findPredicateMap
                + ", personType=" + PersonType.PATIENT + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void medHistContainsKeywordsPredicateToStringMethod() {
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_MEDICALHISTORY, prepareMedHistPredicate("keyword"));
        FindCommand findCommand = new FindCommand(findPredicateMap, PersonType.PATIENT);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + findPredicateMap
                + ", personType=" + PersonType.PATIENT + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void specialtyContainsKeywordsPredicateToStringMethod() {
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_SPECIALTY, prepareSpecialtyPredicate("keyword"));
        FindCommand findCommand = new FindCommand(findPredicateMap, PersonType.SPECIALIST);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + findPredicateMap
                + ", personType=" + PersonType.SPECIALIST + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split(WHITESPACE_REGEX)));
    }

    /**
     * Parses {@code userInput} into a {@code PhoneContainsKeywordsPredicate}.
     */
    private PhoneContainsKeywordsPredicate preparePhonePredicate(String userInput) {
        return new PhoneContainsKeywordsPredicate(Arrays.asList(userInput.split(WHITESPACE_REGEX)));
    }

    /**
     * Parses {@code userInput} into a {@code EmailContainsKeywordsPredicate}.
     */
    private EmailContainsKeywordsPredicate prepareEmailPredicate(String userInput) {
        return new EmailContainsKeywordsPredicate(Arrays.asList(userInput.split(WHITESPACE_REGEX)));
    }

    /**
     * Parses {@code userInput} into a {@code TagsContainsKeywordsPredicate}.
     */
    private TagsContainsKeywordsPredicate prepareTagsPredicate(String userInput) {
        return new TagsContainsKeywordsPredicate(Arrays.asList(userInput.split(WHITESPACE_REGEX)));
    }

    /**
     * Parses {@code userInput} into a {@code MedHistoryContainsKeywordsPredicate}.
     */
    private MedHistoryContainsKeywordsPredicate prepareMedHistPredicate(String userInput) {
        return new MedHistoryContainsKeywordsPredicate(Arrays.asList(userInput.split(WHITESPACE_REGEX)));
    }


    /**
     * Parses {@code userInput} into a {@code SpecialtyContainsKeywordsPredicate}.
     */
    private SpecialtyContainsKeywordsPredicate prepareSpecialtyPredicate(String userInput) {
        return new SpecialtyContainsKeywordsPredicate(Arrays.asList(userInput.split(WHITESPACE_REGEX)));
    }

    /**
     * Parses {@code userInput} into a {@code AgeContainsKeywordsPredicate}.
     */
    private AgeContainsKeywordsPredicate prepareAgePredicate(String userInput) {
        return new AgeContainsKeywordsPredicate(Arrays.asList(userInput.split(WHITESPACE_REGEX)));
    }

    /**
     * Parses {@code userInput} into a {@code LocationContainsKeywordsPredicate}.
     */
    private LocationContainsKeywordsPredicate prepareLocationPredicate(String userInput) {
        return new LocationContainsKeywordsPredicate(Arrays.asList(userInput.split(WHITESPACE_REGEX)));
    }

    private Predicate<Person> combinePersonPredicates(FindPredicateMap findPredicateMap, PersonType personType) {
        Predicate<Person> predicate = person -> findPredicateMap.getAllPredicates().stream()
                .map(pred -> pred.test(person))
                .reduce(true, (x, y) -> x && y);
        return predicate.and(personType.getSearchPredicate());
    }

    private FindPredicateMap setupPersonZeroKeywordsPredicateMap() {
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_NAME, prepareNamePredicate(" "));
        findPredicateMap.put(PREFIX_PHONE, preparePhonePredicate(" "));
        findPredicateMap.put(PREFIX_EMAIL, prepareEmailPredicate(" "));
        findPredicateMap.put(PREFIX_TAG, prepareTagsPredicate(" "));
        return findPredicateMap;
    }
}
