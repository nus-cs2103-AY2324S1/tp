package seedu.address.logic.commands.partnercourse;

import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalObjects.WATERLOO;
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
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.testutil.PartnerCourseBuilder;
import seedu.address.testutil.UniversityCatalogueBuilder;

/**
 * Contains integration tests (interaction with the SeplendidModel) for {@code PartnerCourseAddCommand}.
 */
public class PartnerCourseAddCommandIntegrationTest {
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
    public void execute_addPartnerCourse_success() {
        model.addUniversity(WATERLOO);
        PartnerCourse validPartnerCourse = new PartnerCourseBuilder().build();

        SeplendidModel expectedModel = new SeplendidModelManager(new UserPrefs(),
                                                                 getTypicalLocalCourseCatalogue(),
                                                                 model.getPartnerCourseCatalogue(),
                                                                 new UniversityCatalogueBuilder().withUniversity(
                                                                     WATERLOO).build(),
                                                                 getTypicalMappingCatalogue(),
                                                                 getTypicalNoteCatalogue());
        expectedModel.addPartnerCourse(validPartnerCourse);

        assertSeplendidCommandSuccess(new PartnerCourseAddCommand(validPartnerCourse),
                                      model,
                                      String.format(PartnerCourseAddCommand.MESSAGE_SUCCESS,
                                                    Messages.format(validPartnerCourse)),
                                      expectedModel);
    }

    @Test
    public void execute_nonExistingUniversity_throwsCommandException() {
        PartnerCourse validPartnerCourse = new PartnerCourseBuilder().build();
        assertSeplendidCommandFailure(new PartnerCourseAddCommand(validPartnerCourse),
                                      model,
                                      PartnerCourseAddCommand.MESSAGE_NONEXISTENT_UNIVERSITY);
    }

    @Test
    public void execute_duplicatePartnerCourse_throwsCommandException() {
        PartnerCourse partnerCourseInList = model.getPartnerCourseCatalogue().getPartnerCourseList().get(0);
        assertSeplendidCommandFailure(new PartnerCourseAddCommand(partnerCourseInList),
                                      model,
                                      PartnerCourseAddCommand.MESSAGE_DUPLICATE_PARTNER_COURSE);
    }
}
