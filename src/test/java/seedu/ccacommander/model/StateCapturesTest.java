package seedu.ccacommander.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StateCapturesTest {
    @Test
    public void constructor_validInputs_success() {
        int currentIndex = 0;
        List<String> stateCaptures = Arrays.asList("State1", "State2");
        StateCaptures state = new StateCaptures(currentIndex, stateCaptures);

        assertEquals(currentIndex, state.getCurrentIndex());
        assertEquals(stateCaptures, state.getStateCaptures());
    }

    @Test
    public void equals_equalObjects_true() {
        List<String> stateCaptures1 = Arrays.asList("State1", "State2");
        StateCaptures state1 = new StateCaptures(0, stateCaptures1);
        StateCaptures state2 = new StateCaptures(0, stateCaptures1);

        assertTrue(state1.equals(state2));
    }

    @Test
    public void equals_differentStateCaptures_false() {
        StateCaptures state1 = new StateCaptures(0, Arrays.asList("State1", "State2"));
        StateCaptures state2 = new StateCaptures(0, Arrays.asList("State1", "DifferentState"));

        assertFalse(state1.equals(state2));
    }

    @Test
    public void equals_differentCurrentIndex_false() {
        StateCaptures state1 = new StateCaptures(0, Arrays.asList("State1", "State2"));
        StateCaptures state2 = new StateCaptures(1, Arrays.asList("State1", "State2"));

        assertFalse(state1.equals(state2));
    }

    @Test
    public void equals_differentType_false() {
        StateCaptures state = new StateCaptures(0, Arrays.asList("State1", "State2"));
        Object otherObject = new Object();

        assertFalse(state.equals(otherObject));
    }
}
