package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.animal.Animal;
import seedu.address.testutil.AnimalBuilder;

public class AnimalMessagesTest {

    @Test
    public void getFormattedHelpMessage_emptyStringsFilteredOut() {
        String message1 = "This is a valid message.";
        String message2 = ""; // should be ignored
        String message3 = "Another valid message.";

        // joins message 1 with message 2, separated by a new line.
        String expected = "This is a valid message.\nAnother valid message.";

        // actual
        String formattedMessage = AnimalMessages.getFormattedHelpMessage(message1, message2, message3);

        assertEquals(expected, formattedMessage);
    }

    @Test
    public void getErrorMessageForDuplicatePrefixes_singleDuplicatePrefix_returnErrorMessage() {
        Prefix nSlash = new Prefix("n/");
        Prefix pSlash = new Prefix("p/");

        String expected = AnimalMessages.MESSAGE_DUPLICATE_FIELDS + "n/" + " " + "p/";
        String errorMessage = AnimalMessages.getErrorMessageForDuplicatePrefixes(nSlash, pSlash);

        assertEquals(expected, errorMessage);
    }

    @Test
    public void format_validAnimal_returnsFormattedString() {
        Animal animal = new AnimalBuilder().build(); // Builds an Animal with default values.

        String expectedOutput = animal.getName() + "; ID: " + animal.getPetId()
            + "; Sex: " + animal.getSex()
            + "; Species: " + animal.getSpecies()
            + "; Breed: " + animal.getBreed()
            + "; Date of Birth: " + animal.getDateOfBirth() // Remove repeat Date of Birth prefix
            + "; Date of Admission: " + animal.getAdmissionDate();

        String formattedAnimal = AnimalMessages.format(animal);

        assertEquals(expectedOutput, formattedAnimal);
    }
}
