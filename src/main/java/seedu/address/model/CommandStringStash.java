package seedu.address.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a stash that stores the history of the command String of the 20 most recent
 * commands executed.
 */
public class CommandStringStash {
    private List<String> cmdStringStack;
    private int currentCmdIndex;

    /**
     * Basic empty constructor for the CommandStringStash.
     */
    public CommandStringStash() {
        this.cmdStringStack = new ArrayList<>();
        this.currentCmdIndex = 0;
    }

    /**
     * Creates the CommandStringStash using the initial values provided.
     */
    public CommandStringStash(List<String> cmdStringStack, int currentCmd) {
        this.cmdStringStack = cmdStringStack;
        this.currentCmdIndex = currentCmd;
    }

    /**
     * Returns the previous command string input entered by the user.
     * Remembers the state, so it passes previous command string inputs already gotten.
     * Returns {@code commandInputString} if already at the first command string input entered.
     */
    public String getPrevCommandString(String commandInputString) {
        assert commandInputString != null;
        if (cmdStringStack.isEmpty() || currentCmdIndex == 0) {
            return commandInputString;
        }

        currentCmdIndex -= 1;
        String prevCommandString = cmdStringStack.get(currentCmdIndex);
        return prevCommandString;
    }

    /**
     * Returns the command string input passed over by the user acting as an "undo" of {@code getPrevCommandString}.
     * Remember the state, so it passes over command string inputs already "undone".
     * Returns {@code commandInputString} if the user has not passed any commands yet.
     */
    public String getPassedCommandString(String commandInputString) {
        assert commandInputString != null;
        if (cmdStringStack.isEmpty() || currentCmdIndex >= cmdStringStack.size() - 1) {
            return commandInputString;
        }

        currentCmdIndex += 1;
        String passedCommandString = cmdStringStack.get(currentCmdIndex);
        return passedCommandString;
    }

    /**
     * Adds the {@code commandString} to the stash of command Strings.
     * If the stash has size > 20, the least recently added command string is evicted.
     * Also resets the state of the stash.
     * @param commandInputString A non-null command input String.
     */
    public void addCommandString(String commandInputString) {
        assert commandInputString != null;

        // remove existing command String from stack if it already exists as it will be replaced by new command
        if (cmdStringStack.contains(commandInputString)) {
            cmdStringStack.remove(commandInputString);
        }

        // evict least recently added command string if necessary
        if (cmdStringStack.size() == 20) {
            cmdStringStack.remove(0);
        }

        // add command string
        cmdStringStack.add(commandInputString);

        // reset the command string index pointer
        currentCmdIndex = cmdStringStack.size();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CommandStringStash)) {
            return false;
        }
        CommandStringStash commandStringStash = (CommandStringStash) object;
        return Objects.equals(cmdStringStack, commandStringStash.cmdStringStack)
                && currentCmdIndex == commandStringStash.currentCmdIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cmdStringStack, currentCmdIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("command string stack", cmdStringStack)
                .add("command string stack current index", currentCmdIndex)
                .toString();
    }
}
