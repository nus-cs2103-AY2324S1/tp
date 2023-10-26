package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CommandStringStashTest {

    @Test
    public void addCommandString_withValidCommandString_addsCorrectly() {
        CommandStringStash commandStringStash = new CommandStringStash();
        CommandStringStash expectedCommandStringStash = new CommandStringStash(
                new LinkedList<>(Arrays.asList("0")),
                new LinkedList<>(),
                true
        );

        commandStringStash.addCommandString("0");

        assertEquals(expectedCommandStringStash, commandStringStash);
    }

    @Test
    public void addCommandString_withValidCommandString_evictsCorrectly() {
        String[] prevCmdStringsStack = createCmdStringStackIntegers(0, 19, false);
        CommandStringStash commandStringStash = new CommandStringStash(
                new LinkedList<>(Arrays.asList(prevCmdStringsStack)),
                new LinkedList<>(),
                true);

        String[] expectedCmdStringsStack = createCmdStringStackIntegers(1, 20, false);
        CommandStringStash expectedCommandStringStash = new CommandStringStash(
                new LinkedList<>(Arrays.asList(expectedCmdStringsStack)),
                new LinkedList<>(),
                true);

        commandStringStash.addCommandString("20");

        assertEquals(expectedCommandStringStash, commandStringStash);
    }

    @Test
    public void addCommandString_withValidCommandString_resetsStash() {
        String[] prevCmdStringsStack = createCmdStringStackIntegers(1, 10, false);
        String[] expectedCmdStringsStack = createCmdStringStackIntegers(0, 10, true);
        CommandStringStash commandStringStash = new CommandStringStash(
                new LinkedList<>(),
                new LinkedList<>(Arrays.asList(prevCmdStringsStack)),
                false);
        CommandStringStash expectedCommandStringStash = new CommandStringStash(
                new LinkedList<>(Arrays.asList(expectedCmdStringsStack)),
                new LinkedList<>(),
                true);

        commandStringStash.addCommandString("0");

        assertEquals(expectedCommandStringStash, commandStringStash);
    }

    @Test
    public void getPrevCommandString_afterAdd_getsCorrectly() {
        String[] prevCmdStringsStack = createCmdStringStackIntegers(1, 9, false);
        String[] passedCmdStringsStack = createCmdStringStackIntegers(10, 15, true);
        CommandStringStash commandStringStash = new CommandStringStash(
                new LinkedList<>(Arrays.asList(prevCmdStringsStack)),
                new LinkedList<>(Arrays.asList(passedCmdStringsStack)),
                true
        );

        commandStringStash.addCommandString("16");

        assertEquals("16", commandStringStash.getPrevCommandString("0"));
    }

    @Test
    public void getPrevCommandString_afterGetPassed_getsCorrectly() {
        String[] prevCmdStringsStack = createCmdStringStackIntegers(1, 9, false);
        String[] passedCmdStringsStack = createCmdStringStackIntegers(10, 15, true);
        CommandStringStash commandStringStash = new CommandStringStash(
                new LinkedList<>(Arrays.asList(prevCmdStringsStack)),
                new LinkedList<>(Arrays.asList(passedCmdStringsStack)),
                false
        );

        commandStringStash.getPassedCommandString("0");

        assertEquals("9", commandStringStash.getPrevCommandString("0"));
    }

    @Test
    public void getPrevCommandString_afterGetPrev_getsCorrectly() {
        String[] prevCmdStringsStack = createCmdStringStackIntegers(1, 9, false);
        String[] passedCmdStringsStack = createCmdStringStackIntegers(10, 15, true);
        CommandStringStash commandStringStash = new CommandStringStash(
                new LinkedList<>(Arrays.asList(prevCmdStringsStack)),
                new LinkedList<>(Arrays.asList(passedCmdStringsStack)),
                true
        );

        commandStringStash.getPrevCommandString("0");

        assertEquals("8", commandStringStash.getPrevCommandString("0"));
    }

    @Test
    public void getPassedCommandString_afterAdd_getsCorrectly() {
        String[] prevCmdStringsStack = createCmdStringStackIntegers(1, 9, false);
        String[] passedCmdStringsStack = createCmdStringStackIntegers(10, 15, true);
        CommandStringStash commandStringStash = new CommandStringStash(
                new LinkedList<>(Arrays.asList(prevCmdStringsStack)),
                new LinkedList<>(Arrays.asList(passedCmdStringsStack)),
                true
        );

        commandStringStash.addCommandString("16");

        assertEquals("0", commandStringStash.getPassedCommandString("0"));
    }

    @Test
    public void getPassedCommandString_afterGetPassed_getsCorrectly() {
        String[] prevCmdStringsStack = createCmdStringStackIntegers(1, 9, false);
        String[] passedCmdStringsStack = createCmdStringStackIntegers(10, 15, true);
        CommandStringStash commandStringStash = new CommandStringStash(
                new LinkedList<>(Arrays.asList(prevCmdStringsStack)),
                new LinkedList<>(Arrays.asList(passedCmdStringsStack)),
                false
        );

        commandStringStash.getPassedCommandString("0");

        assertEquals("11", commandStringStash.getPassedCommandString("0"));
    }

    @Test
    public void getPassedCommandString_afterGetPrev_getsCorrectly() {
        String[] prevCmdStringsStack = createCmdStringStackIntegers(1, 9, false);
        String[] passedCmdStringsStack = createCmdStringStackIntegers(10, 15, true);
        CommandStringStash commandStringStash = new CommandStringStash(
                new LinkedList<>(Arrays.asList(prevCmdStringsStack)),
                new LinkedList<>(Arrays.asList(passedCmdStringsStack)),
                true
        );

        commandStringStash.getPrevCommandString("0");

        assertEquals("10", commandStringStash.getPassedCommandString("0"));
    }

    /**
     * Helper function to create an array of integers in increasing or decreasing order
     * from {@code start} to {@code end}
     */
    public String[] createCmdStringStackIntegers(int start, int end, boolean increasing) {
        int length = end - start + 1;
        String[] cmdStringStack = new String[length];
        for (int i = 0; i < length; i++) {
            cmdStringStack[i] = String.valueOf(end - i);
        }
        if (increasing) {
            List<String> list = Arrays.asList(cmdStringStack);
            Collections.reverse(list);
            cmdStringStack = list.toArray(cmdStringStack);
        }
        return cmdStringStack;
    }

}
