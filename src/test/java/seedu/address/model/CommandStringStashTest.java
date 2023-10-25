package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
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
        CommandStringStash commandStringStash = new CommandStringStash( createCmdStringStackIntegers(0, 9), 5);
        CommandStringStash expectedCommandStringStash = new CommandStringStash(createCmdStringStackIntegers(0, 10), 11);

        commandStringStash.addCommandString("10");

        assertEquals(expectedCommandStringStash, commandStringStash);
    }

    @Test
    public void getPrevCommandString_afterAdd_getsCorrectly() {
        CommandStringStash commandStringStash = new CommandStringStash(createCmdStringStackIntegers(0, 15), 9 );

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
    public void getPassedCommandString_afterAdd_getsCorrectly() {
        CommandStringStash commandStringStash = new CommandStringStash(createCmdStringStackIntegers(0, 15), 9 );

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
