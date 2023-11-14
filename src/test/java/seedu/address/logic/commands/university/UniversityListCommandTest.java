package seedu.address.logic.commands.university;

import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showUniversityAtIndex;
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

public class UniversityListCommandTest {
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
                                                  getTypicalLocalCourseCatalogue(),
                                                  getTypicalPartnerCourseCatalogue(),
                                                  model.getUniversityCatalogue(),
                                                  getTypicalMappingCatalogue(),
                                                  getTypicalNoteCatalogue());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertSeplendidCommandSuccess(new UniversityListCommand(),
                                      model,
                                      UniversityListCommand.MESSAGE_SUCCESS,
                                      expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showUniversityAtIndex(model, INDEX_FIRST_OBJECT);
        assertSeplendidCommandSuccess(new UniversityListCommand(),
                                      model,
                                      UniversityListCommand.MESSAGE_SUCCESS,
                                      expectedModel);

    }
}
