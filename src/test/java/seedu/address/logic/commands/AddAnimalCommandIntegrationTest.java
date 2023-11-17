package seedu.address.logic.commands;

import seedu.address.model.AnimalModel;

/**
 * Contains integration tests (interaction with the Model) for {@code AddAnimalCommand}.
 */
public class AddAnimalCommandIntegrationTest {

    private AnimalModel model;

    //    @BeforeEach
    //    public void setUp() {
    //        model = new AnimalModelManager(getTypicalCatalog(), new AnimalUserPrefs());
    //    }
    //
    //    @Test
    //    public void execute_newPerson_success() {
    //        Animal validAnimal = new AnimalBuilder().build();
    //
    //        AnimalModel expectedModel = new AnimalModelManager(model.getAnimalCatalog(), new AnimalUserPrefs());
    //        expectedModel.addAnimal(validAnimal);
    //
    //        assertCommandSuccess(new AddAnimalCommand(validAnimal), model,
    //                String.format(AddAnimalCommand.MESSAGE_SUCCESS, AnimalMessages.format(validAnimal)),
    //                expectedModel);
    //    }
    //
    //    @Test
    //    public void execute_duplicatePerson_throwsCommandException() {
    //        Animal animalInList = model.getAnimalCatalog().getAnimalList().get(0);
    //        assertCommandFailure(new AddAnimalCommand(animalInList), model,
    //                AddAnimalCommand.MESSAGE_DUPLICATE_ANIMAL);
    //    }

}
