package seedu.address.logic.commands.partnercourse;

import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalMappingCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalNoteCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalPartnerCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalPartnerCourses;
import static seedu.address.testutil.TypicalObjects.getTypicalUniversityCatalogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.SeplendidModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.partnercourse.PartnerCourse;

/**
 * Contains integration tests (interaction with the SeplendidModel) for {@code PartnerCourseDeleteCommand}.
 */
public class PartnerCourseDeleteCommandIntegrationTest {

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
    public void execute_deletePartnerCourse_success() throws CommandException {
        PartnerCourse validExistingPartnerCourse = getTypicalPartnerCourses().get(0);

        SeplendidModel expectedModel = new SeplendidModelManager(new UserPrefs(),
                                                                 model.getLocalCourseCatalogue(),
                                                                 model.getPartnerCourseCatalogue(),
                                                                 model.getUniversityCatalogue(),
                                                                 getTypicalMappingCatalogue(),
                                                                 getTypicalNoteCatalogue());
        expectedModel.deletePartnerCourse(validExistingPartnerCourse);

        assertSeplendidCommandSuccess(
                new PartnerCourseDeleteCommand(
                        validExistingPartnerCourse.getPartnerUniversity().getUniversityName(),
                        validExistingPartnerCourse.getPartnerCode()
                ),
                model,
                String.format(PartnerCourseDeleteCommand.MESSAGE_SUCCESS, Messages.format(validExistingPartnerCourse)),
                expectedModel);
    }

    @Test
    public void execute_nonExistingPartnerCourse_throwsCommandException() {
        assertSeplendidCommandFailure(
                new PartnerCourseDeleteCommand(
                        TYPICAL_PARTNER_COURSE.getPartnerUniversity().getUniversityName(),
                        TYPICAL_PARTNER_COURSE.getPartnerCode()),
                model,
                PartnerCourseDeleteCommand.MESSAGE_NONEXISTENT_PARTNER_COURSE);
    }
}
