package seedu.address.logic.commands.localcourse;

import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourses;
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
import seedu.address.model.localcourse.LocalCourse;

//@@author lamchenghou

/**
 * Contains integration tests (interaction with the SeplendidModel) for {@code LocalCourseDeleteCommand}.
 */
public class LocalCourseDeleteCommandIntegrationTest {

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
    public void execute_deleteLocalCourse_success() throws CommandException {
        LocalCourse validExistingLocalCourse = getTypicalLocalCourses().get(0);

        SeplendidModel expectedModel = new SeplendidModelManager(new UserPrefs(),
                model.getLocalCourseCatalogue(),
                model.getPartnerCourseCatalogue(),
                model.getUniversityCatalogue(),
                getTypicalMappingCatalogue(),
                model.getNoteCatalogue());
        expectedModel.deleteLocalCourse(validExistingLocalCourse);

        assertSeplendidCommandSuccess(new LocalCourseDeleteCommand(validExistingLocalCourse.getLocalCode()),
                model,
                String.format(
                        LocalCourseDeleteCommand.MESSAGE_SUCCESS,
                        Messages.format(validExistingLocalCourse)),
                expectedModel);
    }

    @Test
    public void execute_nonExistingLocalCourse_throwsCommandException() {
        assertSeplendidCommandFailure(new LocalCourseDeleteCommand(TYPICAL_LOCAL_COURSE.getLocalCode()),
                model,
                LocalCourseDeleteCommand.MESSAGE_NONEXISTENT_LOCAL_COURSE);
    }

}
