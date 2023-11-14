package seedu.address.logic.commands.localcourse;

import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showLocalCourseAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalMappingCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalNoteCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalPartnerCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalUniversityCatalogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.SeplendidModel;
import seedu.address.model.SeplendidModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class LocalCourseListCommandTest {

    private SeplendidModel model;
    private SeplendidModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new SeplendidModelManager(new UserPrefs(),
                                          getTypicalLocalCourseCatalogue(),
                                          getTypicalPartnerCourseCatalogue(),
                                          getTypicalUniversityCatalogue(),
                                          getTypicalMappingCatalogue(),
                                          getTypicalNoteCatalogue());
        expectedModel = new SeplendidModelManager(new UserPrefs(),
                                                  model.getLocalCourseCatalogue(),
                                                  getTypicalPartnerCourseCatalogue(),
                                                  getTypicalUniversityCatalogue(),
                                                  getTypicalMappingCatalogue(),
                                                  getTypicalNoteCatalogue());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertSeplendidCommandSuccess(new LocalCourseListCommand(),
                                      model,
                                      LocalCourseListCommand.MESSAGE_SUCCESS,
                                      expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showLocalCourseAtIndex(model, INDEX_FIRST_OBJECT);
        assertSeplendidCommandSuccess(new LocalCourseListCommand(),
                                      model,
                                      LocalCourseListCommand.MESSAGE_SUCCESS,
                                      expectedModel);
    }
}
