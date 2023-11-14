package seedu.address.logic.commands.mapping;

import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalObjects.CS2040S_TO_NTU_S0402SC;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalMappingCatalogue;
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
import seedu.address.testutil.MappingBuilder;

//@@author lamchenghou

/**
 * Contains integration tests (interaction with the SeplendidModel) for {@code MappingAddCommand}.
 */
public class MappingAddCommandIntegrationTest {

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
    public void execute_newMapping_success() {
        Mapping validMapping = new MappingBuilder(CS2040S_TO_NTU_S0402SC).build();

        SeplendidModel expectedModel = new SeplendidModelManager(new UserPrefs(),
                model.getLocalCourseCatalogue(),
                model.getPartnerCourseCatalogue(),
                model.getUniversityCatalogue(),
                model.getMappingCatalogue(),
                model.getNoteCatalogue());

        expectedModel.addMapping(validMapping);

        assertSeplendidCommandSuccess(new MappingAddCommand(validMapping),
                model,
                String.format(MappingAddCommand.MESSAGE_SUCCESS, Messages.format(validMapping)),
                expectedModel);
    }

    @Test
    public void execute_duplicateMapping_throwsCommandException() {
        Mapping mappingInList = model.getMappingCatalogue().getMappingList().get(0);
        assertSeplendidCommandFailure(new MappingAddCommand(mappingInList),
                model,
                MappingAddCommand.MESSAGE_DUPLICATE_MAPPING);
    }

}
