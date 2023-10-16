package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PATIENT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICALHISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.SPECIALIST_TAG;
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
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
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

    //TODO: Change equals method, remember to do the tags.
    @Test
    public void equals() {
        FindPredicateMap findPredicateMap1 = setupFirstFindPredicateMap();
        FindPredicateMap findPredicateMap2 = setupSecondFindPredicateMap();

        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Fabio"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Test2"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate, PersonType.PATIENT);
        FindCommand findSecondCommand = new FindCommand(secondPredicate, PersonType.PATIENT);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate, PersonType.PATIENT);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // different person type -> return false
        FindCommand findFirstCommandSpecialist = new FindCommand(firstPredicate, PersonType.SPECIALIST);
        assertFalse(findFirstCommand.equals(findFirstCommandSpecialist));
    }

    private FindPredicateMap setupFirstFindPredicateMap() {
        // Find Predicates for a Patient
        NameContainsKeywordsPredicate nameContainsKeywordsPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Fabio"));
        PhoneContainsKeywordsPredicate phoneContainsKeywordsPredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("89934991"));
        EmailContainsKeywordsPredicate emailContainsKeywordsPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("patient@gmail.com"));
        TagsContainsKeywordsPredicate tagsContainsKeywordsPredicate =
                new TagsContainsKeywordsPredicate(Arrays.asList("tag1", "tag2"));
        MedHistoryContainsKeywordsPredicate medHistoryContainsKeywordsPredicate =
                new MedHistoryContainsKeywordsPredicate(Arrays.asList("Osteoporosis", "Bronchitis"));

        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PATIENT_TAG, PersonType.PATIENT.getSearchPredicate());
        findPredicateMap.put(PREFIX_NAME.toString(), nameContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_PHONE.toString(), phoneContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_EMAIL.toString(), emailContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_TAG.toString(), tagsContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_MEDICALHISTORY.toString(), medHistoryContainsKeywordsPredicate);
        return findPredicateMap;
    }

    private FindPredicateMap setupSecondFindPredicateMap() {
        // Find Predicates for a Specialist
        NameContainsKeywordsPredicate nameContainsKeywordsPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Selena"));
        PhoneContainsKeywordsPredicate phoneContainsKeywordsPredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("90237753"));
        EmailContainsKeywordsPredicate emailContainsKeywordsPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("specialist@gmail.com"));
        TagsContainsKeywordsPredicate tagsContainsKeywordsPredicate =
                new TagsContainsKeywordsPredicate(Arrays.asList("tag2", "tag3"));
        SpecialtyContainsKeywordsPredicate specialtyContainsKeywordsPredicate =
                new SpecialtyContainsKeywordsPredicate(Arrays.asList("Surgery", "Haematology"));

        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(SPECIALIST_TAG, PersonType.SPECIALIST.getSearchPredicate());
        findPredicateMap.put(PREFIX_NAME.toString(), nameContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_PHONE.toString(), phoneContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_EMAIL.toString(), emailContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_TAG.toString(), tagsContainsKeywordsPredicate);
        findPredicateMap.put(PREFIX_SPECIALTY.toString(), specialtyContainsKeywordsPredicate);
        return findPredicateMap;
    }

    @Test
    public void execute_zeroKeywords_allPatientsListed() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        PhoneContainsKeywordsPredicate phonePredicate = preparePhonePredicate(" ");
        // Add age predicate here
        EmailContainsKeywordsPredicate emailPredicate = prepareEmailPredicate(" ");
        TagsContainsKeywordsPredicate tagsPredicate = prepareTagsPredicate(" ");
        MedHistoryContainsKeywordsPredicate medHistPredicate = prepareMedHistPredicate(" ");

        Predicate<Person> predicate = combinePersonPredicates(namePredicate, phonePredicate, emailPredicate,
            tagsPredicate, medHistPredicate);

        FindCommand command = new FindCommand(predicate, PersonType.PATIENT);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroKeywords_allSpecialistsListed() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        PhoneContainsKeywordsPredicate phonePredicate = preparePhonePredicate(" ");
        // Add location predicate here
        EmailContainsKeywordsPredicate emailPredicate = prepareEmailPredicate(" ");
        TagsContainsKeywordsPredicate tagsPredicate = prepareTagsPredicate(" ");
        SpecialtyContainsKeywordsPredicate specialtyPredicate = prepareSpecialtyPredicate(" ");

        Predicate<Person> predicate = combinePersonPredicates(namePredicate, phonePredicate, emailPredicate,
                tagsPredicate, specialtyPredicate);

        FindCommand command = new FindCommand(predicate, PersonType.SPECIALIST);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameKeywordsOnly_multipleSpecialistFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        Predicate<Person> predicate = prepareNamePredicate("Alice Kunz George")
                .and(PersonType.SPECIALIST.getSearchPredicate());
        FindCommand command = new FindCommand(predicate, PersonType.SPECIALIST);
        expectedModel.updateFilteredPersonList(predicate.and(PersonType.SPECIALIST.getSearchPredicate()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameKeywordsOnly_multiplePatientsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        Predicate<Person> predicate = prepareNamePredicate("Alice Kunz Daniel")
                .and(PersonType.PATIENT.getSearchPredicate());
        FindCommand command = new FindCommand(predicate, PersonType.PATIENT);
        expectedModel.updateFilteredPersonList(predicate.and(PersonType.PATIENT.getSearchPredicate()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void nameContainsKeywordsPredicateToStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate, PersonType.PATIENT);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate
                + ", personType=" + PersonType.PATIENT + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void phoneContainsKeywordsPredicateToStringMethod() {
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate, PersonType.PATIENT);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate
                + ", personType=" + PersonType.PATIENT + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void emailContainsKeywordsPredicateToStringMethod() {
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate, PersonType.PATIENT);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate
                + ", personType=" + PersonType.PATIENT + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void tagsContainsKeywordsPredicateToStringMethod() {
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate, PersonType.PATIENT);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate
                + ", personType=" + PersonType.PATIENT + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void medHistContainsKeywordsPredicateToStringMethod() {
        MedHistoryContainsKeywordsPredicate predicate =
                new MedHistoryContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate, PersonType.PATIENT);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate
                + ", personType=" + PersonType.PATIENT + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void specialtyContainsKeywordsPredicateToStringMethod() {
        SpecialtyContainsKeywordsPredicate predicate =
                new SpecialtyContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate, PersonType.SPECIALIST);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate
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

    @SafeVarargs
    private Predicate<Person> combinePersonPredicates(Predicate<Person> ... predicates) {
        return person -> Arrays.stream(predicates).map(predicate -> predicate.test(person))
                .reduce(true, (x, y) -> x && y);
    }
}
