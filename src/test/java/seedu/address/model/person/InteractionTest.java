package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Interaction.Outcome;

public class InteractionTest {
    public static final LocalDate EXAMPLE_DATE = LocalDate.of(2023, 10, 27);
    public static final Interaction INTERACTION_ONE =
        new Interaction("Met up for lunch", Outcome.INTERESTED, InteractionTest.EXAMPLE_DATE);
    public static final Interaction CLOSED_INTERACTION_ONE =
        new Interaction("Bought after lunch", Outcome.CLOSED, InteractionTest.EXAMPLE_DATE);

    @Test
    public void sole_interactionNoteConstructor() {
        Interaction interaction = new Interaction("This is a test interaction note.");
        Interaction expectedInteraction =
            new Interaction("This is a test interaction note.", Interaction.Outcome.UNKNOWN);
        assertEquals(expectedInteraction, interaction);
    }

    @Test
    public void isOutcome() {
        assertEquals(true, INTERACTION_ONE.isOutcome(Interaction.Outcome.INTERESTED));
        assertEquals(false, INTERACTION_ONE.isOutcome(Interaction.Outcome.CLOSED));

        //Tests again if default outcome is UNKNOWN
        Interaction interaction = new Interaction("This is a test interaction note.");
        assertEquals(true, interaction.isOutcome(Interaction.Outcome.UNKNOWN));
    }

    @Test
    public void testToString() {
        assertEquals("Met up for lunch\nThe outcome of this interaction is: INTERESTED\nDate: 27-Oct-2023",
            INTERACTION_ONE.toString());
        assertEquals("Bought after lunch\nThe outcome of this interaction is: CLOSED\nDate: 27-Oct-2023",
            CLOSED_INTERACTION_ONE.toString());
    }

    @Test
    public void testEquals() {
        assertEquals(INTERACTION_ONE, INTERACTION_ONE);
        assertNotEquals(INTERACTION_ONE, CLOSED_INTERACTION_ONE);
        assertEquals(INTERACTION_ONE, new Interaction("Met up for lunch", Outcome.INTERESTED,
            InteractionTest.EXAMPLE_DATE));
    }
}
