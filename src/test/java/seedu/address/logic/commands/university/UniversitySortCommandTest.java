package seedu.address.logic.commands.university;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalObjects.NTU;
import static seedu.address.testutil.TypicalObjects.SNU;
import static seedu.address.testutil.TypicalObjects.STANFORD;
import static seedu.address.testutil.TypicalObjects.WATERLOO;
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
import seedu.address.model.university.University;
import seedu.address.model.university.comparator.UniversityComparatorByUniversityName;

public class UniversitySortCommandTest {

    private SeplendidModel model;
    private SeplendidModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new SeplendidModelManager(new UserPrefs(), getTypicalLocalCourseCatalogue(),
                getTypicalPartnerCourseCatalogue(), getTypicalUniversityCatalogue(),
                getTypicalMappingCatalogue(), getTypicalNoteCatalogue());
        expectedModel = new SeplendidModelManager(new UserPrefs(), getTypicalLocalCourseCatalogue(),
                getTypicalPartnerCourseCatalogue(), model.getUniversityCatalogue(),
                getTypicalMappingCatalogue(), getTypicalNoteCatalogue());
    }

    @Test
    public void execute_sortByUniversityName_success() {
        UniversityComparatorByUniversityName comparator = new UniversityComparatorByUniversityName();
        UniversitySortCommand universitySortCommand = new UniversitySortCommand(comparator);
        expectedModel.updateSortedUniversityList(comparator);

        assertSeplendidCommandSuccess(universitySortCommand, model,
                UniversitySortCommand.MESSAGE_SUCCESS,
                expectedModel);

        List<University> sortedList = new ArrayList<>();
        sortedList.add(NTU);
        sortedList.add(SNU);
        sortedList.add(STANFORD);
        sortedList.add(WATERLOO);
    }

    @Test
    public void equals() {
        Comparator<University> universityComparatorByUniversityName = new UniversityComparatorByUniversityName();

        UniversitySortCommand universitySortCommandByUniversityName =
                new UniversitySortCommand(universityComparatorByUniversityName);

        // same object -> returns true
        assertTrue(universitySortCommandByUniversityName.equals(universitySortCommandByUniversityName));

        // same values -> returns true
        UniversitySortCommand universitySortCommandByUniversityNameCopy =
                new UniversitySortCommand(universityComparatorByUniversityName);
        assertTrue(universitySortCommandByUniversityName.equals(universitySortCommandByUniversityNameCopy));

        // different types -> returns false
        assertFalse(universitySortCommandByUniversityName.equals(2));

        // null -> returns false
        assertFalse(universitySortCommandByUniversityName.equals(null));

    }
}
