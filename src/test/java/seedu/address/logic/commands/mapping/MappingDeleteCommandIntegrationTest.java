package seedu.address.logic.commands.mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalObjects.CS2040S_TO_NTU_S0402SC;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_UNIVERSITY_NAME;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalMappingCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalMappings;
import static seedu.address.testutil.TypicalObjects.getTypicalNoteCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalPartnerCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalUniversityCatalogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.SeplendidModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.mapping.Mapping;

//@@author lamchenghou

/**
 * Contains integration tests (interaction with the SeplendidModel) for {@code MappingDeleteCommand}.
 */
public class MappingDeleteCommandIntegrationTest {

    private SeplendidModel model;

    @BeforeEach
    public void setUp() {
        model = new SeplendidModelManager(new UserPrefs(),
                getTypicalLocalCourseCatalogue(),
                getTypicalPartnerCourseCatalogue(),
                getTypicalUniversityCatalogue(),
                getTypicalMappingCatalogue(),
                getTypicalNoteCatalogue());
    }

    @Test
    public void execute_deleteMapping_success() {
        Mapping validExistingMapping = getTypicalMappings().get(0);

        SeplendidModel expectedModel = new SeplendidModelManager(new UserPrefs(),
                model.getLocalCourseCatalogue(),
                model.getPartnerCourseCatalogue(),
                model.getUniversityCatalogue(),
                getTypicalMappingCatalogue(),
                model.getNoteCatalogue());
        expectedModel.deleteMapping(validExistingMapping);

        assertSeplendidCommandSuccess(new MappingDeleteCommand(validExistingMapping.getLocalCode(),
                        validExistingMapping.getUniversityName(), validExistingMapping.getPartnerCode()),
                model,
                String.format(
                        MappingDeleteCommand.MESSAGE_SUCCESS,
                        Messages.format(validExistingMapping)),
                expectedModel);
    }

    @Test
    public void execute_nonExistingMapping_throwsCommandException() {
        assertSeplendidCommandFailure(new MappingDeleteCommand(TYPICAL_LOCAL_COURSE.getLocalCode(),
                        TYPICAL_PARTNER_UNIVERSITY_NAME, TYPICAL_PARTNER_COURSE.getPartnerCode()),
                model,
                MappingDeleteCommand.MESSAGE_NONEXISTENT_MAPPING);
    }

    @Test
    public void equals() {
        MappingDeleteCommand firstDeleteCommand = new MappingDeleteCommand(TYPICAL_LOCAL_COURSE.getLocalCode(),
                TYPICAL_PARTNER_COURSE.getPartnerUniversity().getUniversityName(),
                TYPICAL_PARTNER_COURSE.getPartnerCode());
        MappingDeleteCommand secondDeleteCommand = new MappingDeleteCommand(CS2040S_TO_NTU_S0402SC.getLocalCode(),
                CS2040S_TO_NTU_S0402SC.getUniversityName(), CS2040S_TO_NTU_S0402SC.getPartnerCode());

        // same object -> returns true
        assertTrue(firstDeleteCommand.equals(firstDeleteCommand));

        // same values -> returns true
        MappingDeleteCommand firstDeleteCommandCopy = new MappingDeleteCommand(TYPICAL_LOCAL_COURSE.getLocalCode(),
                TYPICAL_PARTNER_UNIVERSITY_NAME, TYPICAL_PARTNER_COURSE.getPartnerCode());
        assertTrue(firstDeleteCommand.equals(firstDeleteCommandCopy));

        // different types -> returns false
        assertFalse(firstDeleteCommand.equals(1));

        // null -> returns false
        assertFalse(firstDeleteCommand.equals(null));

        // different command -> returns false
        assertFalse(firstDeleteCommand.equals(secondDeleteCommand));
    }

    @Test
    public void toStringMethod() {
        MappingDeleteCommand mappingDeleteCommand = new MappingDeleteCommand(TYPICAL_LOCAL_COURSE.getLocalCode(),
                TYPICAL_PARTNER_COURSE.getPartnerUniversity().getUniversityName(),
                TYPICAL_PARTNER_COURSE.getPartnerCode());
        String expected = MappingDeleteCommand.class.getCanonicalName()
                + "{" + "localCodeToDelete=" + TYPICAL_LOCAL_COURSE.getLocalCode() + ", "
                + "universityNameToDelete=" + TYPICAL_PARTNER_COURSE.getPartnerUniversity().getUniversityName() + ", "
                + "partnerCodeToDelete=" + TYPICAL_PARTNER_COURSE.getPartnerCode() + "}";
        assertEquals(expected, mappingDeleteCommand.toString());
    }

}
