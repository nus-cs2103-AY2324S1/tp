package seedu.address.logic.commands.partnercourse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalObjects.COMP3000;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;
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
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.partnercourse.PartnerCourseAttribute;
import seedu.address.model.partnercourse.PartnerCourseContainsKeywordsPredicate;
import seedu.address.testutil.PartnerCourseBuilder;

public class PartnerCourseSearchCommandTest {
    private SeplendidModel model;
    private SeplendidModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new SeplendidModelManager(new UserPrefs(), getTypicalLocalCourseCatalogue(),
                getTypicalPartnerCourseCatalogue(), getTypicalUniversityCatalogue(),
                getTypicalMappingCatalogue(), getTypicalNoteCatalogue());
        expectedModel = new SeplendidModelManager(new UserPrefs(), model.getLocalCourseCatalogue(),
                getTypicalPartnerCourseCatalogue(), getTypicalUniversityCatalogue(),
                getTypicalMappingCatalogue(), getTypicalNoteCatalogue());
        model.addPartnerCourse(COMP3000);
        expectedModel.addPartnerCourse(COMP3000);
    }

    @Test
    public void execute_validPartnerCode_success() throws CommandException {
        PartnerCourse courseToSearch = new PartnerCourseBuilder().withPartnerCode("COMP3000").build();

        PartnerCourseSearchCommand searchCommand = new PartnerCourseSearchCommand(
                PartnerCourseAttribute.PARTNERCODE,
                new PartnerCourseContainsKeywordsPredicate("COMP3000", PartnerCourseAttribute.PARTNERCODE),
                "3000"
        );

        searchCommand.execute(model);

        expectedModel.searchPartnerCourses(PartnerCourseAttribute.PARTNERCODE,
                new PartnerCourseContainsKeywordsPredicate("COMP3000", PartnerCourseAttribute.PARTNERCODE));

        assertSeplendidCommandSuccess(searchCommand,
                model,
                String.format(PartnerCourseSearchCommand.MESSAGE_SUCCESS,
                        Messages.format(model.getFilteredPartnerCourseList())),
                expectedModel);
    }

    @Test
    public void execute_validPartnerName_success() throws CommandException {
        PartnerCourse courseToSearch = new PartnerCourseBuilder().withPartnerName("Introduction to Networks").build();

        PartnerCourseSearchCommand searchCommand = new PartnerCourseSearchCommand(
                PartnerCourseAttribute.PARTNERNAME,
                new PartnerCourseContainsKeywordsPredicate("Introduction to Networks",
                        PartnerCourseAttribute.PARTNERNAME),
                "Introduction to Networks"
        );

        searchCommand.execute(model);

        expectedModel.searchPartnerCourses(PartnerCourseAttribute.PARTNERNAME,
                new PartnerCourseContainsKeywordsPredicate("Introduction to Networks",
                        PartnerCourseAttribute.PARTNERNAME));

        assertSeplendidCommandSuccess(searchCommand,
                model,
                String.format(PartnerCourseSearchCommand.MESSAGE_SUCCESS,
                        Messages.format(model.getFilteredPartnerCourseList())),
                expectedModel);
    }

    @Test
    public void execute_validPartnerUnit_success() throws CommandException {
        PartnerCourse courseToSearch = new PartnerCourseBuilder().withPartnerUnit(4.0).build();

        PartnerCourseSearchCommand searchCommand = new PartnerCourseSearchCommand(
                PartnerCourseAttribute.PARTNERUNIT,
                new PartnerCourseContainsKeywordsPredicate("4.0",
                        PartnerCourseAttribute.PARTNERUNIT),
                "4"
        );

        searchCommand.execute(model);

        expectedModel.searchPartnerCourses(PartnerCourseAttribute.PARTNERUNIT,
                new PartnerCourseContainsKeywordsPredicate("4.0",
                        PartnerCourseAttribute.PARTNERUNIT));

        assertSeplendidCommandSuccess(searchCommand,
                model,
                String.format(PartnerCourseSearchCommand.MESSAGE_SUCCESS,
                        Messages.format(model.getFilteredPartnerCourseList())),
                expectedModel);
    }

    @Test
    public void execute_validPartnerDescription_success() throws CommandException {
        PartnerCourse courseToSearch = new PartnerCourseBuilder().withPartnerDescription(
                "COMP3000 is a "
                        + "level 3000 course.").build();

        PartnerCourseSearchCommand searchCommand = new PartnerCourseSearchCommand(
                PartnerCourseAttribute.PARTNERDESCRIPTION,
                new PartnerCourseContainsKeywordsPredicate(
                        "COMP3000 is a "
                                + "level 3000 course.",
                        PartnerCourseAttribute.PARTNERNAME),
                "COMP3000 is a "
                        + "level 3000 course."
        );

        searchCommand.execute(model);

        expectedModel.searchPartnerCourses(PartnerCourseAttribute.PARTNERNAME,
                new PartnerCourseContainsKeywordsPredicate(
                        "COMP3000 is a "
                                + "level 3000 course.",
                        PartnerCourseAttribute.PARTNERNAME));

        assertSeplendidCommandSuccess(searchCommand,
                model,
                String.format(PartnerCourseSearchCommand.MESSAGE_SUCCESS,
                        Messages.format(model.getFilteredPartnerCourseList())),
                expectedModel);
    }

    @Test
    public void equals() {

        PartnerCourseContainsKeywordsPredicate firstPredicate = new PartnerCourseContainsKeywordsPredicate(
                "COMP3000",
                PartnerCourseAttribute.PARTNERCODE);

        PartnerCourseContainsKeywordsPredicate secondPredicate = new PartnerCourseContainsKeywordsPredicate(
                "COMP1000",
                PartnerCourseAttribute.PARTNERCODE);

        PartnerCourseSearchCommand searchCommandWithFirstPredicate =
                new PartnerCourseSearchCommand(PartnerCourseAttribute.PARTNERCODE, firstPredicate, "COMP3000");
        PartnerCourseSearchCommand searchCommandWithSecondPredicate =
                new PartnerCourseSearchCommand(PartnerCourseAttribute.PARTNERCODE, secondPredicate, "COMP1000");

        // same object -> returns true
        assertTrue(
                searchCommandWithFirstPredicate.equals(searchCommandWithFirstPredicate));

        // same values -> returns true
        PartnerCourseSearchCommand searchCommandWithFirstPredicateCopy = new PartnerCourseSearchCommand(
                PartnerCourseAttribute.PARTNERCODE, firstPredicate, "COMP3000");
        assertTrue(searchCommandWithFirstPredicate.equals(searchCommandWithFirstPredicateCopy));

        // different types -> returns false
        assertFalse(searchCommandWithFirstPredicate.equals(1));

        // null -> returns false
        assertFalse(searchCommandWithFirstPredicate.equals(null));

        // different note -> returns false
        assertFalse(searchCommandWithFirstPredicate.equals(searchCommandWithSecondPredicate));
    }
}
