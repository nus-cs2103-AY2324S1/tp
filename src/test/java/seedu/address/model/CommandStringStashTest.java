package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CommandStringStashTest {

    @Test
    public void addCommandString_withValidCommandString_addsCorrectly() {
        CommandStringStash commandStringStash = new CommandStringStash();
        CommandStringStash expectedCommandStringStash = new CommandStringStash(createCmdStringStackIntegers(0, 0), 1);

        commandStringStash.addCommandString("0");

        assertEquals(expectedCommandStringStash, commandStringStash);
    }

    @Test
    public void addCommandString_withValidCommandString_evictsCorrectly() {
        CommandStringStash commandStringStash = new CommandStringStash(
                createCmdStringStackIntegers(0, 19),
                20
                );

        CommandStringStash expectedCommandStringStash = new CommandStringStash(
                createCmdStringStackIntegers(1, 20),
                20
        );

        commandStringStash.addCommandString("20");

        assertEquals(expectedCommandStringStash, commandStringStash);
    }

    @Test
    public void addCommandString_withValidCommandString_resetsCurrentCmd() {
        CommandStringStash commandStringStash = new CommandStringStash(createCmdStringStackIntegers(0, 9), 5);
        CommandStringStash expectedCommandStringStash = new CommandStringStash(createCmdStringStackIntegers(0, 10), 11);

        commandStringStash.addCommandString("10");

        assertEquals(expectedCommandStringStash, commandStringStash);
    }

    @Test
    public void addCommandString_withDuplicateCommandString_removesOldInstance() {
        CommandStringStash commandStringStash = new CommandStringStash(createCmdStringStackIntegers(0, 9), 6);
        List<String> expectedCommandStringStack = createCmdStringStackIntegers(0, 3);
        expectedCommandStringStack.addAll(createCmdStringStackIntegers(5, 9));
        expectedCommandStringStack.add("4");
        CommandStringStash expectedCommandStringStash = new CommandStringStash(expectedCommandStringStack, 10);

        commandStringStash.addCommandString("4");
        assertEquals(expectedCommandStringStash, commandStringStash);
    }

    @Test
    public void addCommandString_withNullCommandString_throwsAssertionError() {
        CommandStringStash commandStringStash = new CommandStringStash();
        assertThrows(AssertionError.class, () -> commandStringStash.addCommandString(null));
    }

    @Test
    public void getPrevCommandString_valid_getsCorrectly() {
        CommandStringStash commandStringStash = new CommandStringStash(createCmdStringStackIntegers(0, 15), 9);

        assertEquals("8", commandStringStash.getPrevCommandString("0"));
    }

    @Test
    public void getPrevCommandString_afterAdd_getsCorrectly() {
        CommandStringStash commandStringStash = new CommandStringStash(createCmdStringStackIntegers(0, 15), 9);

        commandStringStash.addCommandString("16");

        assertEquals("16", commandStringStash.getPrevCommandString("0"));
    }

    @Test
    public void getPrevCommandString_withEmptyStack_getsCorrectly() {
        CommandStringStash commandStringStash = new CommandStringStash(new ArrayList<>(), 0);

        assertEquals("0", commandStringStash.getPrevCommandString("0"));
    }

    @Test
    public void getPrevCommandString_withNoPrev_getsCorrectly() {
        CommandStringStash commandStringStash = new CommandStringStash(createCmdStringStackIntegers(1, 5), 0);

        assertEquals("0", commandStringStash.getPrevCommandString("0"));
    }

    @Test
    public void getPrevCommandString_withNullCommandString_throwsAssertionError() {
        CommandStringStash commandStringStash = new CommandStringStash();
        assertThrows(AssertionError.class, () -> commandStringStash.getPrevCommandString(null));
    }

    @Test
    public void getPassedCommandString_valid_getsCorrectly() {
        CommandStringStash commandStringStash = new CommandStringStash(createCmdStringStackIntegers(0, 15), 9);
        assertEquals("10", commandStringStash.getPassedCommandString("0"));
    }
    @Test
    public void getPassedCommandString_afterAdd_getsCorrectly() {
        CommandStringStash commandStringStash = new CommandStringStash(createCmdStringStackIntegers(0, 15), 9);

        commandStringStash.addCommandString("16");

        assertEquals("0", commandStringStash.getPassedCommandString("0"));
    }

    @Test
    public void getPassedCommandString_withEmptyStack_getsCorrectly() {
        CommandStringStash commandStringStash = new CommandStringStash(new ArrayList<>(), 0);

        assertEquals("0", commandStringStash.getPassedCommandString("0"));
    }

    @Test
    public void getPassedCommandString_withNoPassed_getsCorrectly() {
        CommandStringStash commandStringStash = new CommandStringStash(createCmdStringStackIntegers(1, 5), 4);

        assertEquals("0", commandStringStash.getPassedCommandString("0"));
    }

    @Test
    public void getPassedCommandString_withNullCommandString_throwsAssertionError() {
        CommandStringStash commandStringStash = new CommandStringStash();
        assertThrows(AssertionError.class, () -> commandStringStash.getPassedCommandString(null));
    }

    /**
     * Helper function to create an array of integers in increasing or decreasing order
     * from {@code start} to {@code end}
     */
    public List<String> createCmdStringStackIntegers(int start, int end) {
        int length = end - start + 1;
        List<String> cmdStringStack = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            cmdStringStack.add(String.valueOf(start + i));
        }
        return cmdStringStack;
    }

}
