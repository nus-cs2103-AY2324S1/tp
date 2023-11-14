package seedu.address.logic.commands.localcourse;

import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalObjects.CS2041S;
import static seedu.address.testutil.TypicalObjects.CS2042S;
import static seedu.address.testutil.TypicalObjects.CS3230;
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
import seedu.address.model.localcourse.LocalCourseAttribute;
import seedu.address.testutil.LocalCourseBuilder;

public class LocalCourseUpdateCommandTest {

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
    }

    @Test
    public void execute_updateLocalCourseLocalCode_success() {
        model.addLocalCourse(CS2041S);
        LocalCourse updatedLocalCourse =
                new LocalCourseBuilder()
                        .withLocalCode("CS2042S")
                        .withLocalName(CS2041S.getLocalName().toString())
                        .withLocalUnit(CS2041S.getLocalUnit().getValue())
                        .withLocalDescription(CS2041S.getLocalDescription().toString())
                        .build();

        expectedModel.addLocalCourse(updatedLocalCourse);

        assertSeplendidCommandSuccess(
                new LocalCourseUpdateCommand(
                        CS2041S.getLocalCode(),
                        LocalCourseAttribute.LOCALCODE,
                        "CS2042S"),
                model,
                String.format(LocalCourseUpdateCommand.MESSAGE_SUCCESS,
                        Messages.format(CS2041S), Messages.format(updatedLocalCourse)),
                expectedModel);
    }

    @Test
    public void execute_updateLocalCourseLocalName_success() {
        model.addLocalCourse(CS2041S);
        LocalCourse updatedLocalCourse =
                new LocalCourseBuilder()
                        .withLocalCode(CS2041S.getLocalCode().toString())
                        .withLocalName("Data Structures")
                        .withLocalUnit(CS2041S.getLocalUnit().getValue())
                        .withLocalDescription(CS2041S.getLocalDescription().toString())
                        .build();

        expectedModel.addLocalCourse(updatedLocalCourse);

        assertSeplendidCommandSuccess(
                new LocalCourseUpdateCommand(
                        CS2041S.getLocalCode(),
                        LocalCourseAttribute.LOCALNAME,
                        "Data Structures"),
                model,
                String.format(LocalCourseUpdateCommand.MESSAGE_SUCCESS,
                        Messages.format(CS2041S), Messages.format(updatedLocalCourse)),
                expectedModel);
    }

    @Test
    public void execute_updateLocalCourseLocalUnit_success() {
        model.addLocalCourse(CS2041S);
        LocalCourse updatedLocalCourse =
                new LocalCourseBuilder()
                        .withLocalCode(CS2041S.getLocalCode().toString())
                        .withLocalName(CS2041S.getLocalName().toString())
                        .withLocalUnit(10.0)
                        .withLocalDescription(CS2041S.getLocalDescription().toString())
                        .build();

        expectedModel.addLocalCourse(updatedLocalCourse);

        assertSeplendidCommandSuccess(
                new LocalCourseUpdateCommand(
                        CS2041S.getLocalCode(),
                        LocalCourseAttribute.LOCALUNIT,
                        "10.0"),
                model,
                String.format(LocalCourseUpdateCommand.MESSAGE_SUCCESS,
                        Messages.format(CS2041S), Messages.format(updatedLocalCourse)),
                expectedModel);
    }

    @Test
    public void execute_updateLocalCourseLocalDescription_success() {
        model.addLocalCourse(CS2041S);
        LocalCourse updatedLocalCourse =
                new LocalCourseBuilder()
                        .withLocalCode(CS2041S.getLocalCode().toString())
                        .withLocalName(CS2041S.getLocalName().toString())
                        .withLocalUnit(CS2041S.getLocalUnit().getValue())
                        .withLocalDescription("This course introduces students to the "
                                + "design and implementation of "
                                + "fundamental data structures and "
                                + "algorithms. The course covers basic "
                                + "data structures (linked lists, "
                                + "stacks, queues, hash tables, binary "
                                + "heaps, trees, and graphs), searching"
                                + " and sorting algorithms, and basic "
                                + "analysis of algos.")
                        .build();

        expectedModel.addLocalCourse(updatedLocalCourse);

        assertSeplendidCommandSuccess(
                new LocalCourseUpdateCommand(
                        CS2041S.getLocalCode(),
                        LocalCourseAttribute.LOCALDESCRIPTION,
                        "This course introduces students to the "
                                + "design and implementation of "
                                + "fundamental data structures and "
                                + "algorithms. The course covers basic "
                                + "data structures (linked lists, "
                                + "stacks, queues, hash tables, binary "
                                + "heaps, trees, and graphs), searching"
                                + " and sorting algorithms, and basic "
                                + "analysis of algos."),
                model,
                String.format(LocalCourseUpdateCommand.MESSAGE_SUCCESS,
                        Messages.format(CS2041S), Messages.format(updatedLocalCourse)),
                expectedModel);
    }

    @Test
    public void execute_nonExistingLocalCourse_throwsCommandException() {
        assertSeplendidCommandFailure(
                new LocalCourseUpdateCommand(
                        CS2041S.getLocalCode(),
                        LocalCourseAttribute.LOCALDESCRIPTION,
                        "This course introduces students to the "
                                + "design and implementation of "
                                + "fundamental data structures "
                                + ". The course covers basic "
                                + "data structures (linked lists, "
                                + "stacks, queues, hash tables, binary "
                                + "heaps, trees, and graphs), searching"
                                + " and sorting algorithms, and basic "
                                + "analysis of algorithms."),
                model,
                LocalCourseUpdateCommand.MESSAGE_NONEXISTENT_LOCAL_COURSE);
    }

    @Test
    public void execute_duplicateLocalCourse_throwsCommandException() {
        model.addLocalCourse(CS2041S);
        LocalCourse anotherLocalCourse =
                new LocalCourseBuilder()
                        .withLocalCode(CS2042S.getLocalCode().toString())
                        .withLocalName(CS3230.getLocalName().toString())
                        .withLocalUnit(CS3230.getLocalUnit().getValue())
                        .withLocalDescription(CS3230.getLocalDescription().toString())
                        .build();
        model.addLocalCourse(anotherLocalCourse);
        assertSeplendidCommandFailure(
                new LocalCourseUpdateCommand(
                        CS2042S.getLocalCode(),
                        LocalCourseAttribute.LOCALCODE,
                        "cs2041s"),
                model,
                LocalCourseUpdateCommand.MESSAGE_DUPLICATE_LOCAL_COURSE);
    }

}
