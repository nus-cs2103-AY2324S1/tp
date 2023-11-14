package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalTasks.AGENDA;
import static seedu.address.testutil.TypicalTasks.BUDGET;

import org.junit.jupiter.api.Test;

public class MessagesTest {

    @Test
    public void format_person_alice() {
        String expected = "Alice Pauline; Phone: 94351253; Email: alice@example.com;"
                + " Address: 123, Jurong West Ave 6, #08-111; Tags: [friends]";
        assertEquals(expected, Messages.format(ALICE));
    }

    @Test
    public void format_person_benson() {
        String expected = "Benson Meier; Phone: 98765432; Email: johnd@example.com;"
                + " Address: 311, Clementi Ave 2, #02-25; Tags: [owesMoney][friends]";
        assertEquals(expected, Messages.format(BENSON));
    }

    @Test
    public void format_task_agenda() {
        String expected = "Prepare Agenda; Note: To book venue; Status: Not Done; Tags: [location]";
        assertEquals(expected, Messages.format(AGENDA));
    }

    @Test
    public void format_task_budget() {
        String expected = "Prepare Budget; Note: For CS2102; Status: Done; Tags: [class][finance]";
        assertEquals(expected, Messages.format(BUDGET));
    }

}
