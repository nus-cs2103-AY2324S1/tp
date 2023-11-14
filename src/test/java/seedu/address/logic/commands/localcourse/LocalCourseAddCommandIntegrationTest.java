package seedu.address.logic.commands.localcourse;

import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
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
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.testutil.LocalCourseBuilder;

//@@author lamchenghou

/**
 * Contains integration tests (interaction with the SeplendidModel) for {@code LocalCourseAddCommand}.
 */
public class LocalCourseAddCommandIntegrationTest {

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
    public void execute_newLocalCourse_success() {
        LocalCourse validLocalCourse = new LocalCourseBuilder().build();

        SeplendidModel expectedModel = new SeplendidModelManager(new UserPrefs(),
                model.getLocalCourseCatalogue(),
                model.getPartnerCourseCatalogue(),
                model.getUniversityCatalogue(),
                model.getMappingCatalogue(),
                model.getNoteCatalogue());
        expectedModel.addLocalCourse(validLocalCourse);

        assertSeplendidCommandSuccess(new LocalCourseAddCommand(validLocalCourse),
                model,
                String.format(LocalCourseAddCommand.MESSAGE_SUCCESS,
                        Messages.format(validLocalCourse)),
                expectedModel);
    }

    @Test
    public void execute_duplicateLocalCourse_throwsCommandException() {
        LocalCourse localCourseInList = model.getLocalCourseCatalogue().getLocalCourseList().get(0);
        assertSeplendidCommandFailure(new LocalCourseAddCommand(localCourseInList),
                model,
                LocalCourseAddCommand.MESSAGE_DUPLICATE_LOCAL_COURSE);
    }

}
