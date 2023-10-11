package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class WelcomeCommandTest {

    @Test
    public void execute_welcomeMessageGenerated_success() {
        // Create a new WelcomeCommand and execute it
        WelcomeCommand welcomeCommand = new WelcomeCommand();
        Model model = new ModelManager(); // Assuming a no-arg constructor exists for ModelManager
        CommandResult result = welcomeCommand.execute(model);

        // Generate expected welcome message
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String strDate = formatter.format(date);
        String expectedMessage = "Welcome from Linktree, Current date and time: " + strDate;

        // Assert that the welcome message is as expected
        assertEquals(expectedMessage, result.getFeedbackToUser());
    }
}
