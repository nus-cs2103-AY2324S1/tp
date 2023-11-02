package swe.context.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandBoxHistoryTest {
    private static final String FIRST_COMMAND = "list";
    private static final String SECOND_COMMAND = "clear";
    private List<String> commandList;
    private CommandBoxHistory commandBoxHistory;

    @BeforeEach
    public void setUp() {
        commandList = new ArrayList<>();
        commandList.add(FIRST_COMMAND);
        commandList.add(SECOND_COMMAND);
    }

    @Test
    public void constructor_defensiveCopy_backingListUnmodified() {
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
     * Asserts that {@code commandBoxHistory#hasNext()} returns true and the return value
     * of {@code commandBoxHistory#next()} equals to {@code element}.
     */
    private void assertNextSuccess(String element) {
        assertTrue(commandBoxHistory.hasNext());
        assertEquals(element, commandBoxHistory.next());
    }

    /**
     * Asserts that {@code commandBoxHistory#hasPrevious()} returns true and the return value
     * of {@code commandBoxHistory#previous()} equals to {@code element}.
     */
    private void assertPreviousSuccess(String element) {
        assertTrue(commandBoxHistory.hasPrevious());
        assertEquals(element, commandBoxHistory.previous());
    }

    /**
     * Asserts that {@code commandBoxHistory#hasCurrent()} returns true and the return value
     * of {@code commandBoxHistory#current()} equals to {@code element}.
     */
    private void assertCurrentSuccess(String element) {
        assertTrue(commandBoxHistory.hasCurrent());
        assertEquals(element, commandBoxHistory.current());
    }

    /**
     * Asserts that {@code commandBoxHistory#hasNext()} returns false and the following
     * {@code commandBoxHistory#next()} call throws {@code NoSuchElementException}.
     */
    private void assertNextFailure() {
        assertFalse(commandBoxHistory.hasNext());
        try {
            commandBoxHistory.next();
            throw new AssertionError("The expected NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            // expected exception thrown
        }
    }

    /**
     * Asserts that {@code commandBoxHistory#hasPrevious()} returns false and the following
     * {@code commandBoxHistory#previous()} call throws {@code NoSuchElementException}.
     */
    private void assertPreviousFailure() {
        assertFalse(commandBoxHistory.hasPrevious());
        try {
            commandBoxHistory.previous();
            throw new AssertionError("The expected NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            // expected exception thrown
        }
    }

    /**
     * Asserts that {@code commandBoxHistory#hasCurrent()} returns false and the following
     * {@code commandBoxHistory#current()} call throws {@code NoSuchElementException}.
     */
    private void assertCurrentFailure() {
        assertFalse(commandBoxHistory.hasCurrent());
        try {
            commandBoxHistory.current();
            throw new AssertionError("The expected NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            // expected exception thrown
        }
    }
}
