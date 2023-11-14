package seedu.address.logic.commands.localcourse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalObjects.CS2042S;
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
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.localcourse.LocalCourseAttribute;
import seedu.address.model.localcourse.LocalCourseContainsKeywordsPredicate;
import seedu.address.testutil.LocalCourseBuilder;

public class LocalCourseSearchCommandTest {
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
        model.addLocalCourse(CS2042S);
        expectedModel.addLocalCourse(CS2042S);
    }

    @Test
    public void execute_validLocalCode_success() throws CommandException {
        LocalCourse courseToSearch = new LocalCourseBuilder().withLocalCode("CS2042S").build();

        LocalCourseSearchCommand searchCommand = new LocalCourseSearchCommand(
                LocalCourseAttribute.LOCALCODE,
                new LocalCourseContainsKeywordsPredicate("CS2042S", LocalCourseAttribute.LOCALCODE),
                "2042S"
        );

        searchCommand.execute(model);

        expectedModel.searchLocalCourses(LocalCourseAttribute.LOCALCODE,
                new LocalCourseContainsKeywordsPredicate("CS2042S", LocalCourseAttribute.LOCALCODE));

        assertSeplendidCommandSuccess(searchCommand,
                model,
                String.format(LocalCourseSearchCommand.MESSAGE_SUCCESS,
                        Messages.format(model.getFilteredLocalCourseList())),
                expectedModel);
    }

    @Test
    public void execute_validLocalName_success() throws CommandException {
        LocalCourse courseToSearch = new LocalCourseBuilder().withLocalName("Data Structures & Algorithms").build();

        LocalCourseSearchCommand searchCommand = new LocalCourseSearchCommand(
                LocalCourseAttribute.LOCALNAME,
                new LocalCourseContainsKeywordsPredicate("Data Structures & Algorithms",
                        LocalCourseAttribute.LOCALNAME),
                "Data Structures"
        );

        searchCommand.execute(model);

        expectedModel.searchLocalCourses(LocalCourseAttribute.LOCALNAME,
                new LocalCourseContainsKeywordsPredicate("Data Structures & Algorithms",
                        LocalCourseAttribute.LOCALNAME));

        assertSeplendidCommandSuccess(searchCommand,
                model,
                String.format(LocalCourseSearchCommand.MESSAGE_SUCCESS,
                        Messages.format(model.getFilteredLocalCourseList())),
                expectedModel);
    }

    @Test
    public void execute_validLocalUnit_success() throws CommandException {
        LocalCourse courseToSearch = new LocalCourseBuilder().withLocalName("4.0").build();

        LocalCourseSearchCommand searchCommand = new LocalCourseSearchCommand(
                LocalCourseAttribute.LOCALUNIT,
                new LocalCourseContainsKeywordsPredicate("4.0",
                        LocalCourseAttribute.LOCALUNIT),
                "4"
        );

        searchCommand.execute(model);

        expectedModel.searchLocalCourses(LocalCourseAttribute.LOCALUNIT,
                new LocalCourseContainsKeywordsPredicate("4.0",
                        LocalCourseAttribute.LOCALUNIT));

        assertSeplendidCommandSuccess(searchCommand,
                model,
                String.format(LocalCourseSearchCommand.MESSAGE_SUCCESS,
                        Messages.format(model.getFilteredLocalCourseList())),
                expectedModel);
    }

    @Test
    public void execute_validLocalDescription_success() throws CommandException {
        LocalCourse courseToSearch = new LocalCourseBuilder().withLocalDescription(
                "This course introduces students to the "
                + "design and implementation of "
                + "fundamental data structures and "
                + "algorithms. The course covers basic "
                + "data structures (linked lists, "
                + "stacks, queues, hash tables, binary "
                + "heaps, trees, and graphs), searching"
                + " and sorting algorithms, and basic "
                + "analysis of algorithms.").build();

        LocalCourseSearchCommand searchCommand = new LocalCourseSearchCommand(
                LocalCourseAttribute.LOCALNAME,
                new LocalCourseContainsKeywordsPredicate(
                        "This course introduces students to the "
                        + "design and implementation of "
                        + "fundamental data structures and "
                        + "algorithms. The course covers basic "
                        + "data structures (linked lists, "
                        + "stacks, queues, hash tables, binary "
                        + "heaps, trees, and graphs), searching"
                        + " and sorting algorithms, and basic "
                        + "analysis of algorithms.",
                        LocalCourseAttribute.LOCALNAME),
                "This course introduces students to the "
                        + "design and implementation of "
                        + "fundamental data structures and "
                        + "algorithms. The course covers basic "
                        + "data structures (linked lists, "
                        + "stacks, queues, hash tables, binary "
                        + "heaps, trees, and graphs), searching"
                        + " and sorting algorithms, and basic "
                        + "analysis."
        );

        searchCommand.execute(model);

        expectedModel.searchLocalCourses(LocalCourseAttribute.LOCALNAME,
                new LocalCourseContainsKeywordsPredicate(
                        "This course introduces students to the "
                        + "design and implementation of "
                        + "fundamental data structures and "
                        + "algorithms. The course covers basic "
                        + "data structures (linked lists, "
                        + "stacks, queues, hash tables, binary "
                        + "heaps, trees, and graphs), searching"
                        + " and sorting algorithms, and basic "
                        + "analysis of algorithms.",
                        LocalCourseAttribute.LOCALNAME));

        assertSeplendidCommandSuccess(searchCommand,
                model,
                String.format(LocalCourseSearchCommand.MESSAGE_SUCCESS,
                        Messages.format(model.getFilteredLocalCourseList())),
                expectedModel);
    }

    @Test
    public void equals() {
        LocalCourseContainsKeywordsPredicate firstPredicate = new LocalCourseContainsKeywordsPredicate(
                "CS2042S",
                LocalCourseAttribute.LOCALCODE);
        LocalCourseContainsKeywordsPredicate secondPredicate = new LocalCourseContainsKeywordsPredicate(
                "CS2041S",
                LocalCourseAttribute.LOCALCODE);
        LocalCourseSearchCommand searchCommandWithFirstPredicate = new LocalCourseSearchCommand(
                LocalCourseAttribute.LOCALCODE, firstPredicate, "CS2042S");
        LocalCourseSearchCommand searchCommandWithSecondPredicate = new LocalCourseSearchCommand(
                LocalCourseAttribute.LOCALCODE, secondPredicate, "CS2041S");

        // same object -> returns true
        assertTrue(
                searchCommandWithFirstPredicate.equals(searchCommandWithFirstPredicate));

        // same values -> returns true
        LocalCourseSearchCommand searchCommandWithFirstPredicateCopy = new LocalCourseSearchCommand(
                LocalCourseAttribute.LOCALCODE, firstPredicate, "CS2042S");
        assertTrue(searchCommandWithFirstPredicate.equals(searchCommandWithFirstPredicateCopy));

        // different types -> returns false
        assertFalse(searchCommandWithFirstPredicate.equals(1));

        // null -> returns false
        assertFalse(searchCommandWithFirstPredicate.equals(null));

        // different note -> returns false
        assertFalse(searchCommandWithFirstPredicate.equals(searchCommandWithSecondPredicate));
    }


}
