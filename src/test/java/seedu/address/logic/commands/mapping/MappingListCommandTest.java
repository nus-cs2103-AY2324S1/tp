package seedu.address.logic.commands.mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalMappingCatalogueForMappingTests;
import static seedu.address.testutil.TypicalObjects.getTypicalNoteCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalPartnerCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalUniversityCatalogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.SeplendidModel;
import seedu.address.model.SeplendidModelManager;
import seedu.address.model.UserPrefs;

//@@author lamchenghou

/**
 * Contains integration tests (interaction with the SeplendidModel) and unit tests for MappingListCommand.
 */
public class MappingListCommandTest {

    private SeplendidModel model;
    private SeplendidModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new SeplendidModelManager(new UserPrefs(),
                getTypicalLocalCourseCatalogue(),
                getTypicalPartnerCourseCatalogue(),
                getTypicalUniversityCatalogue(),
                getTypicalMappingCatalogueForMappingTests(),
                getTypicalNoteCatalogue());
        expectedModel = new SeplendidModelManager(new UserPrefs(),
                model.getLocalCourseCatalogue(),
                getTypicalPartnerCourseCatalogue(),
                getTypicalUniversityCatalogue(),
                getTypicalMappingCatalogueForMappingTests(),
                getTypicalNoteCatalogue());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertSeplendidCommandSuccess(new MappingListCommand(),
                model,
                MappingListCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        model.updateFilteredMappingList(m -> false);
        assertSeplendidCommandSuccess(new MappingListCommand(),
                model,
                MappingListCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void equal() {
        MappingListCommand mappingListCommand = new MappingListCommand();
        // same object -> returns true
        assertTrue(mappingListCommand.equals(mappingListCommand));

        // different types -> returns false
        assertFalse(mappingListCommand.equals(1));

        // null -> returns false
        assertFalse(mappingListCommand.equals(null));
    }

    @Test
    public void toStringMethod() {
        assertEquals(MappingListCommand.class.getCanonicalName() + "{}", new MappingListCommand().toString());
    }
}
