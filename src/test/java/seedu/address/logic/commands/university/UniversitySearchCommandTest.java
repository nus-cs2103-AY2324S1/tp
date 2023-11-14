package seedu.address.logic.commands.university;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalObjects.HYUN;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalMappingCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalNoteCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalPartnerCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalUniversityCatalogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.SeplendidModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.university.University;
import seedu.address.model.university.UniversityNameContainsKeywordsPredicate;
import seedu.address.testutil.UniversityBuilder;

//Test cases for university search command
public class UniversitySearchCommandTest {
    private SeplendidModel model;
    private SeplendidModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new SeplendidModelManager(new UserPrefs(), getTypicalLocalCourseCatalogue(),
                getTypicalPartnerCourseCatalogue(), getTypicalUniversityCatalogue(),
                getTypicalMappingCatalogue(), getTypicalNoteCatalogue());
        expectedModel = new SeplendidModelManager(new UserPrefs(), model.getLocalCourseCatalogue(),
                getTypicalPartnerCourseCatalogue(), getTypicalUniversityCatalogue(),
                getTypicalMappingCatalogue(), getTypicalNoteCatalogue());
        model.addUniversity(HYUN);
        expectedModel.addUniversity(HYUN);
    }

    @Test
    public void execute_validUniversity_success() throws CommandException {
        University universityToSearch = new UniversityBuilder().withUniversityName("HYUN").build();

        UniversitySearchCommand searchCommand = new UniversitySearchCommand(
                new UniversityNameContainsKeywordsPredicate("HYUN")
        );

        searchCommand.execute(model);

        expectedModel.getSearchUniversityIfExists(new UniversityNameContainsKeywordsPredicate("HYUN"));

        assertSeplendidCommandSuccess(searchCommand,
                model,
                String.format(UniversitySearchCommand.MESSAGE_SUCCESS,
                        Messages.format(model.getFilteredUniversityList())),
                expectedModel);
    }

    @Test
    public void equals() {

        UniversityNameContainsKeywordsPredicate firstPredicate = new UniversityNameContainsKeywordsPredicate(
                "HYUN");

        UniversityNameContainsKeywordsPredicate secondPredicate = new UniversityNameContainsKeywordsPredicate(
                "HYUNNIE");

        UniversitySearchCommand searchCommandWithFirstPredicate =
                new UniversitySearchCommand(firstPredicate);
        UniversitySearchCommand searchCommandWithSecondPredicate =
                new UniversitySearchCommand(secondPredicate);

        // same object -> returns true
        assertTrue(
                searchCommandWithFirstPredicate.equals(searchCommandWithFirstPredicate));

        // same values -> returns true
        UniversitySearchCommand searchCommandWithFirstPredicateCopy = new UniversitySearchCommand(
                firstPredicate);
        assertTrue(searchCommandWithFirstPredicate.equals(searchCommandWithFirstPredicateCopy));

        // different types -> returns false
        assertFalse(searchCommandWithFirstPredicate.equals(1));

        // null -> returns false
        assertFalse(searchCommandWithFirstPredicate.equals(null));

        // different note -> returns false
        assertFalse(searchCommandWithFirstPredicate.equals(searchCommandWithSecondPredicate));
    }
}
