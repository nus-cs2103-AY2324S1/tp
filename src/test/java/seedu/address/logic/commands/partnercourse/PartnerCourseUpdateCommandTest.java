package seedu.address.logic.commands.partnercourse;

import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalObjects.COMP1000;
import static seedu.address.testutil.TypicalObjects.COMP3000;
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
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.partnercourse.PartnerCourseAttribute;
import seedu.address.testutil.PartnerCourseBuilder;

public class PartnerCourseUpdateCommandTest {

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
    public void execute_updatePartnerCoursePartnerCode_success() {
        model.addPartnerCourse(COMP3000);
        PartnerCourse updatedPartnerCourse =
                new PartnerCourseBuilder()
                        .withPartnerUniversity(COMP3000.getPartnerUniversity().getUniversityName())
                        .withPartnerCode("COMP3001")
                        .withPartnerName(COMP3000.getPartnerName().toString())
                        .withPartnerUnit(COMP3000.getPartnerUnit().getValue())
                        .withPartnerDescription(COMP3000.getPartnerDescription().toString())
                        .build();

        expectedModel.addPartnerCourse(updatedPartnerCourse);

        assertSeplendidCommandSuccess(
                new PartnerCourseUpdateCommand(
                        COMP3000.getPartnerUniversity().getUniversityName(),
                        COMP3000.getPartnerCode(),
                        PartnerCourseAttribute.PARTNERCODE,
                        "COMP3001"),
                model,
                String.format(PartnerCourseUpdateCommand.MESSAGE_SUCCESS,
                        Messages.format(COMP3000), Messages.format(updatedPartnerCourse)),
                expectedModel);
    }

    @Test
    public void execute_updatePartnerCoursePartnerName_success() {
        model.addPartnerCourse(COMP3000);
        PartnerCourse updatedPartnerCourse =
                new PartnerCourseBuilder()
                        .withPartnerUniversity(COMP3000.getPartnerUniversity().getUniversityName())
                        .withPartnerCode(COMP3000.getPartnerCode().toString())
                        .withPartnerName("Advanced Networks")
                        .withPartnerUnit(COMP3000.getPartnerUnit().getValue())
                        .withPartnerDescription(COMP3000.getPartnerDescription().toString())
                        .build();

        expectedModel.addPartnerCourse(updatedPartnerCourse);

        assertSeplendidCommandSuccess(
                new PartnerCourseUpdateCommand(
                        COMP3000.getPartnerUniversity().getUniversityName(),
                        COMP3000.getPartnerCode(),
                        PartnerCourseAttribute.PARTNERNAME,
                        "Advanced Networks"),
                model,
                String.format(PartnerCourseUpdateCommand.MESSAGE_SUCCESS,
                        Messages.format(COMP3000), Messages.format(updatedPartnerCourse)),
                expectedModel);
    }

    @Test
    public void execute_updatePartnerCoursePartnerUnit_success() {
        model.addPartnerCourse(COMP3000);
        PartnerCourse updatedPartnerCourse =
                new PartnerCourseBuilder()
                        .withPartnerUniversity(COMP3000.getPartnerUniversity().getUniversityName())
                        .withPartnerCode(COMP3000.getPartnerCode().toString())
                        .withPartnerName(COMP3000.getPartnerName().toString())
                        .withPartnerUnit(10.0)
                        .withPartnerDescription(COMP3000.getPartnerDescription().toString())
                        .build();

        expectedModel.addPartnerCourse(updatedPartnerCourse);

        assertSeplendidCommandSuccess(
                new PartnerCourseUpdateCommand(
                        COMP3000.getPartnerUniversity().getUniversityName(),
                        COMP3000.getPartnerCode(),
                        PartnerCourseAttribute.PARTNERUNIT,
                        "10.0"),
                model,
                String.format(PartnerCourseUpdateCommand.MESSAGE_SUCCESS,
                        Messages.format(COMP3000), Messages.format(updatedPartnerCourse)),
                expectedModel);
    }

    @Test
    public void execute_updatePartnerCoursePartnerDescription_success() {
        model.addPartnerCourse(COMP3000);
        PartnerCourse updatedPartnerCourse =
                new PartnerCourseBuilder()
                        .withPartnerUniversity(COMP3000.getPartnerUniversity().getUniversityName())
                        .withPartnerCode(COMP3000.getPartnerCode().toString())
                        .withPartnerName(COMP3000.getPartnerName().toString())
                        .withPartnerUnit(COMP3000.getPartnerUnit().getValue())
                        .withPartnerDescription("Fun course about networks!")
                        .build();

        expectedModel.addPartnerCourse(updatedPartnerCourse);

        assertSeplendidCommandSuccess(
                new PartnerCourseUpdateCommand(
                        COMP3000.getPartnerUniversity().getUniversityName(),
                        COMP3000.getPartnerCode(),
                        PartnerCourseAttribute.PARTNERDESCRIPTION,
                        "Fun course about networks!"),
                model,
                String.format(PartnerCourseUpdateCommand.MESSAGE_SUCCESS,
                        Messages.format(COMP3000), Messages.format(updatedPartnerCourse)),
                expectedModel);
    }

    @Test
    public void execute_nonExistingPartnerCourse_throwsCommandException() {
        assertSeplendidCommandFailure(
                new PartnerCourseUpdateCommand(
                        COMP3000.getPartnerUniversity().getUniversityName(),
                        COMP3000.getPartnerCode(),
                        PartnerCourseAttribute.PARTNERDESCRIPTION,
                        "Fun course about networks!"),
                model,
                PartnerCourseUpdateCommand.MESSAGE_NONEXISTENT_PARTNER_COURSE);
    }

    @Test
    public void execute_duplicatePartnerCourse_throwsCommandException() {
        model.addPartnerCourse(COMP3000);
        PartnerCourse anotherPartnerCourse =
                new PartnerCourseBuilder()
                        .withPartnerUniversity(COMP3000.getPartnerUniversity().getUniversityName())
                        .withPartnerCode(COMP1000.getPartnerCode().toString())
                        .withPartnerName(COMP1000.getPartnerName().toString())
                        .withPartnerUnit(COMP1000.getPartnerUnit().getValue())
                        .withPartnerDescription(COMP1000.getPartnerDescription().toString())
                        .build();
        model.addPartnerCourse(anotherPartnerCourse);
        assertSeplendidCommandFailure(
                new PartnerCourseUpdateCommand(
                        COMP3000.getPartnerUniversity().getUniversityName(),
                        COMP1000.getPartnerCode(),
                        PartnerCourseAttribute.PARTNERCODE,
                        "comp3000"),
                model,
                PartnerCourseUpdateCommand.MESSAGE_DUPLICATE_PARTNER_COURSE);
    }

}
