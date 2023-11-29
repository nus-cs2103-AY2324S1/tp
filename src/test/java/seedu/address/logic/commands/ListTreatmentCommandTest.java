package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.treatment.Treatment;
import seedu.address.testutil.TreatmentBuilder;

class ListTreatmentCommandTest {
    private Model model;
    private Model emptyModel;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        emptyModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // Creating sample treatments
        Treatment treatmentOne = new TreatmentBuilder()
                .withName("Root Canal")
                .withCost("1080")
                .withTime("03:00")
                .build();

        Treatment treatmentTwo = new TreatmentBuilder()
                .withName("Cleaning")
                .withCost("250")
                .withTime("01:00")
                .build();

        // Adding treatments to the model
        model.addTreatment(treatmentOne);
        model.addTreatment(treatmentTwo);
        expectedModel.addTreatment(treatmentOne);
        expectedModel.addTreatment(treatmentTwo);
    }

    @Test
    public void execute_emptyTreatmentList_showsEmptyList() {
        assertTrue(emptyModel.getFilteredTreatmentList().isEmpty());
        assertCommandSuccess(new ListTreatmentCommand(), emptyModel, ListTreatmentCommand.EMPTY_WARNING, emptyModel);
    }

    @Test
    public void execute_showTreatmentList_showsEverything() {
        StringBuilder expectedResultBuilder = new StringBuilder("treatments: ");
        model.getFilteredTreatmentList()
                .forEach(treatment -> expectedResultBuilder.append(treatment.getName()).append(", "));
        String expectedResult = expectedResultBuilder.toString();
        assertCommandSuccess(new ListTreatmentCommand(), model, expectedResult, expectedModel);
    }
}
