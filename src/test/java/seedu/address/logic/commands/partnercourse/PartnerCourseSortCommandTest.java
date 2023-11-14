package seedu.address.logic.commands.partnercourse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalObjects.COMP1000;
import static seedu.address.testutil.TypicalObjects.COMP2000;
import static seedu.address.testutil.TypicalObjects.S0402SC;
import static seedu.address.testutil.TypicalObjects.STAN3230;
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
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.partnercourse.comparator.PartnerCourseComparatorByPartnerCode;
import seedu.address.model.partnercourse.comparator.PartnerCourseComparatorByPartnerName;
import seedu.address.model.partnercourse.comparator.PartnerCourseComparatorByUniversity;

public class PartnerCourseSortCommandTest {
    private SeplendidModel model;
    private SeplendidModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new SeplendidModelManager(new UserPrefs(), getTypicalLocalCourseCatalogue(),
                getTypicalPartnerCourseCatalogue(), getTypicalUniversityCatalogue(),
                getTypicalMappingCatalogue(), getTypicalNoteCatalogue());
        expectedModel = new SeplendidModelManager(new UserPrefs(), getTypicalLocalCourseCatalogue(),
                model.getPartnerCourseCatalogue(), getTypicalUniversityCatalogue(),
                getTypicalMappingCatalogue(), getTypicalNoteCatalogue());

    }

    @Test
    public void execute_sortByPartnerCode_success() {
        PartnerCourseComparatorByPartnerCode comparator = new PartnerCourseComparatorByPartnerCode();
        PartnerCourseSortCommand partnerCourseSortCommand = new PartnerCourseSortCommand(comparator);
        expectedModel.updateSortedPartnerList(comparator);

        assertSeplendidCommandSuccess(partnerCourseSortCommand, model,
                PartnerCourseSortCommand.MESSAGE_SUCCESS,
                expectedModel);

        List<PartnerCourse> sortedList = new ArrayList<>();
        sortedList.add(COMP1000);
        sortedList.add(COMP2000);
        sortedList.add(S0402SC);
        sortedList.add(STAN3230);

        assertEquals(sortedList, model.getSortedPartnerCourseList());
    }

    @Test
    public void execute_sortByPartnerName_success() {
        PartnerCourseComparatorByPartnerName comparator = new PartnerCourseComparatorByPartnerName();
        PartnerCourseSortCommand partnerCourseSortCommand = new PartnerCourseSortCommand(comparator);
        expectedModel.updateSortedPartnerList(comparator);

        assertSeplendidCommandSuccess(partnerCourseSortCommand, model,
                PartnerCourseSortCommand.MESSAGE_SUCCESS,
                expectedModel);

        List<PartnerCourse> sortedList = new ArrayList<>();
        sortedList.add(S0402SC);
        sortedList.add(STAN3230);
        sortedList.add(COMP2000);
        sortedList.add(COMP1000);

        assertEquals(sortedList, model.getSortedPartnerCourseList());
    }

    @Test
    public void execute_sortedByUniversity_success() {
        PartnerCourseComparatorByUniversity comparator = new PartnerCourseComparatorByUniversity();
        PartnerCourseSortCommand partnerCourseSortCommand = new PartnerCourseSortCommand(comparator);
        expectedModel.updateSortedPartnerList(comparator);

        assertSeplendidCommandSuccess(partnerCourseSortCommand, model,
                PartnerCourseSortCommand.MESSAGE_SUCCESS,
                expectedModel);

        List<PartnerCourse> sortedList = new ArrayList<>();
        sortedList.add(S0402SC);
        sortedList.add(STAN3230);
        sortedList.add(COMP1000);
        sortedList.add(COMP2000);

        assertEquals(sortedList, model.getSortedPartnerCourseList());
    }

    @Test
    public void equals() {
        Comparator<PartnerCourse> partnerCourseComparatorByPartnerCode = new PartnerCourseComparatorByPartnerCode();
        Comparator<PartnerCourse> partnerCourseComparatorByPartnerName = new PartnerCourseComparatorByPartnerName();
        Comparator<PartnerCourse> partnerCourseComparatorByUniversity = new PartnerCourseComparatorByUniversity();

        PartnerCourseSortCommand partnerCourseSortCommandByPartnerCode =
                new PartnerCourseSortCommand(partnerCourseComparatorByPartnerCode);
        PartnerCourseSortCommand partnerCourseSortCommandByPartnerName =
                new PartnerCourseSortCommand(partnerCourseComparatorByPartnerName);
        PartnerCourseSortCommand partnerCourseSortCommandByUniversity =
                new PartnerCourseSortCommand(partnerCourseComparatorByUniversity);

        // same object -> returns true
        assertTrue(partnerCourseSortCommandByPartnerCode.equals(partnerCourseSortCommandByPartnerCode));
        assertTrue(partnerCourseSortCommandByPartnerName.equals(partnerCourseSortCommandByPartnerName));
        assertTrue(partnerCourseSortCommandByUniversity.equals(partnerCourseSortCommandByUniversity));

        // same values -> returns true
        PartnerCourseSortCommand partnerCourseSortCommandByPartnerCodeCopy =
                new PartnerCourseSortCommand(partnerCourseComparatorByPartnerCode);
        assertTrue(partnerCourseSortCommandByPartnerCode.equals(partnerCourseSortCommandByPartnerCodeCopy));
        PartnerCourseSortCommand partnerCourseSortCommandByPartnerNameCopy =
                new PartnerCourseSortCommand(partnerCourseComparatorByPartnerName);
        assertTrue(partnerCourseSortCommandByPartnerName.equals(partnerCourseSortCommandByPartnerNameCopy));
        PartnerCourseSortCommand partnerCourseSortCommandByUniversityCopy =
                new PartnerCourseSortCommand(partnerCourseComparatorByUniversity);
        assertTrue(partnerCourseSortCommandByUniversity.equals(partnerCourseSortCommandByUniversityCopy));

        // different types -> returns false
        assertFalse(partnerCourseSortCommandByPartnerCode.equals(2));
        assertFalse(partnerCourseSortCommandByPartnerName.equals("bah"));
        assertFalse(partnerCourseSortCommandByUniversity.equals("1.2"));

        // null -> returns false
        assertFalse(partnerCourseSortCommandByPartnerCode.equals(null));
        assertFalse(partnerCourseSortCommandByPartnerName.equals(null));
        assertFalse(partnerCourseSortCommandByUniversity.equals(null));

        // different task -> returns false
        assertFalse(partnerCourseSortCommandByPartnerCode.equals(partnerCourseSortCommandByPartnerName));
        assertFalse(partnerCourseSortCommandByPartnerName.equals(partnerCourseSortCommandByUniversity));
        assertFalse(partnerCourseSortCommandByPartnerCode.equals(partnerCourseSortCommandByUniversity));
    }
}
