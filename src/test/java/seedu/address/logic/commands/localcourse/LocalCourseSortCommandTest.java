package seedu.address.logic.commands.localcourse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalObjects.CS1231S;
import static seedu.address.testutil.TypicalObjects.CS2040S;
import static seedu.address.testutil.TypicalObjects.CS3230;
import static seedu.address.testutil.TypicalObjects.MA2001;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalMappingCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalNoteCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalPartnerCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalUniversityCatalogue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.SeplendidModel;
import seedu.address.model.SeplendidModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.localcourse.comparator.LocalCourseComparatorByLocalCode;
import seedu.address.model.localcourse.comparator.LocalCourseComparatorByLocalName;

public class LocalCourseSortCommandTest {
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
    public void execute_sortByLocalCode_success() {
        LocalCourseComparatorByLocalCode comparator = new LocalCourseComparatorByLocalCode();
        LocalCourseSortCommand localCourseSortCommand = new LocalCourseSortCommand(comparator);
        expectedModel.updateSortedLocalList(comparator);

        assertSeplendidCommandSuccess(localCourseSortCommand,
                                      model,
                                      LocalCourseSortCommand.MESSAGE_SUCCESS,
                                      expectedModel);

        List<LocalCourse> sortedList = new ArrayList<>();
        sortedList.add(CS1231S);
        sortedList.add(CS2040S);
        sortedList.add(CS3230);
        sortedList.add(MA2001);

        assertEquals(sortedList, model.getSortedLocalCourseList());
    }

    @Test
    public void execute_sortByLocalName_success() {
        LocalCourseComparatorByLocalName comparator = new LocalCourseComparatorByLocalName();
        LocalCourseSortCommand localCourseSortCommand = new LocalCourseSortCommand(comparator);
        expectedModel.updateSortedLocalList(comparator);

        assertSeplendidCommandSuccess(localCourseSortCommand,
                                      model,
                                      LocalCourseSortCommand.MESSAGE_SUCCESS,
                                      expectedModel);

        List<LocalCourse> sortedList = new ArrayList<>();
        sortedList.add(CS2040S);
        sortedList.add(CS3230);
        sortedList.add(CS1231S);
        sortedList.add(MA2001);

        assertEquals(sortedList, model.getSortedLocalCourseList());
    }

    @Test
    public void equals() {
        Comparator<LocalCourse> localCourseComparatorByLocalCode = new LocalCourseComparatorByLocalCode();
        Comparator<LocalCourse> localCourseComparatorByLocalName = new LocalCourseComparatorByLocalName();

        LocalCourseSortCommand localCourseSortCommandByLocalCode = new LocalCourseSortCommand(
            localCourseComparatorByLocalCode);
        LocalCourseSortCommand localCourseSortCommandByLocalName = new LocalCourseSortCommand(
            localCourseComparatorByLocalName);

        // same object -> returns true
        assertEquals(localCourseSortCommandByLocalCode, localCourseSortCommandByLocalCode);
        assertEquals(localCourseComparatorByLocalName, localCourseComparatorByLocalName);

        // same values -> returns true
        LocalCourseSortCommand localCourseSortCommandByLocalCodeCopy = new LocalCourseSortCommand(
            localCourseComparatorByLocalCode);
        assertEquals(localCourseSortCommandByLocalCode, localCourseSortCommandByLocalCodeCopy);

        LocalCourseSortCommand localCourseSortCommandByLocalNameCopy = new LocalCourseSortCommand(
                localCourseComparatorByLocalName);
        assertEquals(localCourseSortCommandByLocalName, localCourseSortCommandByLocalNameCopy);

        // different types -> returns false
        assertNotEquals(1, localCourseSortCommandByLocalCode);
        assertNotEquals(4.12, localCourseSortCommandByLocalName);

        // null -> returns false
        assertNotEquals(null, localCourseSortCommandByLocalCode);
        assertNotEquals(null, localCourseSortCommandByLocalName);

        // different task -> returns false
        assertNotEquals(localCourseSortCommandByLocalCode, localCourseSortCommandByLocalName);
    }
}
