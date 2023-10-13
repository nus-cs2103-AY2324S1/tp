package networkbook.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.control.Label;
import networkbook.MainApp;
import networkbook.model.person.Person;
import networkbook.testutil.PersonBuilder;

public class PersonCardTest {

    @BeforeAll
    public static void setupForHeadlessTesting() {
        HeadlessHelper.setupForHeadlessTesting();
    }

    @BeforeEach
    public void setup() throws Exception {
        ApplicationTest.launch(MainApp.class);
    }

    @Test
    public void constructor_hasHighPriority_showsHighPriority() {
        Person person = new PersonBuilder().withName("Bob").withPriority("High").build();
        PersonCard personCard = new PersonCard(person, 1);
        Label priority = personCard.getPriority();
        assertEquals("Priority: High", priority.getText());
        assertTrue(priority.isVisible());
    }

    @Test
    public void constructor_noPriority_notVisible() {
        Person person = new PersonBuilder().withName("Bob").build();
        PersonCard personCard = new PersonCard(person, 1);
        Label priority = personCard.getPriority();
        assertNull(null, priority.getText());
        assertFalse(priority.isVisible());
    }

}
