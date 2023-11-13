package swe.context.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class CommandBoxHistoryTest {
    private static final String FIRST_COMMAND = "list";
    private static final String SECOND_COMMAND = "clear";
    private CommandBoxHistory commandBoxHistory;

    @Test
    public void constructor_defensiveCopy() {
        List<String> list = new ArrayList<>();
        commandBoxHistory = new CommandBoxHistory(list);
        list.add(FIRST_COMMAND);

        CommandBoxHistory emptyHistory = new CommandBoxHistory(Collections.emptyList());
        assertEquals(emptyHistory, commandBoxHistory);
    }

    @Test
    public void emptyList() {
        commandBoxHistory = new CommandBoxHistory();
        assertCurrentSuccess("");
        assertPreviousFailure();
        assertNextFailure();

        commandBoxHistory.add(FIRST_COMMAND);
        assertNextSuccess("");

        assertPreviousSuccess(FIRST_COMMAND);
    }

    @Test
    public void singleCommandList() {
        List<String> list = new ArrayList<>();
        list.add(FIRST_COMMAND);
        commandBoxHistory = new CommandBoxHistory(list);

        assertCurrentSuccess("");
        assertPreviousSuccess(FIRST_COMMAND);
        assertPreviousFailure();
        assertCurrentSuccess(FIRST_COMMAND);
        assertNextSuccess("");
        assertNextFailure();

        // simulate adding new command
        commandBoxHistory.add(SECOND_COMMAND);
        commandBoxHistory.resetPointer();

        assertNextFailure();
        assertCurrentSuccess("");
        assertPreviousSuccess(SECOND_COMMAND);
        assertPreviousSuccess(FIRST_COMMAND);
        assertPreviousFailure();
    }

    @Test
    public void multipleCommandsList() {
        List<String> commandList = new ArrayList<>();
        commandList.add(FIRST_COMMAND);
        commandList.add(SECOND_COMMAND);

        commandBoxHistory = new CommandBoxHistory(commandList);
        String thirdElement = "add";
        // simulate adding new command
        commandBoxHistory.add(thirdElement);
        commandBoxHistory.resetPointer();

        assertNextFailure();
        assertCurrentSuccess("");
        assertPreviousSuccess(thirdElement);
        assertPreviousSuccess(SECOND_COMMAND);
        assertPreviousSuccess(FIRST_COMMAND);
        assertPreviousFailure();

        assertNextSuccess(SECOND_COMMAND);
        assertNextSuccess(thirdElement);
        assertNextSuccess("");
        assertNextFailure();
    }

    @Test
    public void equals() {
        List<String> commandList = new ArrayList<>();
        commandList.add(FIRST_COMMAND);
        commandList.add(SECOND_COMMAND);

        CommandBoxHistory firstHistory = new CommandBoxHistory(commandList);

        assertTrue(firstHistory.equals(firstHistory));

        // same values should return true
        CommandBoxHistory firstHistoryCopy = new CommandBoxHistory(commandList);
        assertTrue(firstHistory.equals(firstHistoryCopy));

        assertFalse(firstHistory.equals(1));

        assertFalse(firstHistory.equals(null));

        // different values should return false
        CommandBoxHistory differentHistory = new CommandBoxHistory(Collections.singletonList(SECOND_COMMAND));
        assertFalse(firstHistory.equals(differentHistory));

        // different pointer should return false
        firstHistoryCopy.previous();
        assertFalse(firstHistory.equals(firstHistoryCopy));
    }

    /**
     * Asserts that {@code commandBoxHistory#next()} succeeds without throwing NoSuchElementException,
     * and the return value of {@code commandBoxHistory#next()} equals {@code command}.
     */
    private void assertNextSuccess(String command) {
        try {
            assertEquals(command, commandBoxHistory.next());
        } catch (NoSuchElementException e) {
            throw new AssertionError("NoSuchElementException was thrown.");
        }
    }

    /**
     * Asserts that {@code commandBoxHistory#previous()} succeeds without throwing NoSuchElementException,
     * and the return value of {@code commandBoxHistory#previous()} equals {@code command}.
     */
    private void assertPreviousSuccess(String command) {
        try {
            assertEquals(command, commandBoxHistory.previous());
        } catch (NoSuchElementException e) {
            throw new AssertionError("NoSuchElementException was thrown.");
        }
    }

    /**
     * Asserts that {@code commandBoxHistory#current()} succeeds without throwing NoSuchElementException,
     * and the return value of {@code commandBoxHistory#current()} equals {@code command}.
     */
    private void assertCurrentSuccess(String command) {
        try {
            assertEquals(command, commandBoxHistory.current());
        } catch (NoSuchElementException e) {
            throw new AssertionError("NoSuchElementException was thrown.");
        }
    }

    /**
     * Asserts that {@code commandBoxHistory#next()} fails, throwing {@code NoSuchElementException}.
     */
    private void assertNextFailure() {
        try {
            commandBoxHistory.next();
            throw new AssertionError("The expected NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            // expected NoSuchElementException thrown
        }
    }

    /**
     * Asserts that {@code commandBoxHistory#previous()} fails, throwing {@code NoSuchElementException}.
     */
    private void assertPreviousFailure() {
        try {
            commandBoxHistory.previous();
            throw new AssertionError("The expected NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            // expected NoSuchElementException thrown
        }
    }

    /**
     * Asserts that {@code commandBoxHistory#current()} fails, throwing {@code NoSuchElementException}.
     */
    private void assertCurrentFailure() {
        try {
            commandBoxHistory.current();
            throw new AssertionError("The expected NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            // expected NoSuchElementException thrown
        }
    }
}
