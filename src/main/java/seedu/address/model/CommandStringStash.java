package seedu.address.model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a stash that contains the command String of the 20 most recent
 * command executed.
 */
public class CommandStringStash {
    private Deque<String> prevCmdStringsStack;
    private Deque<String> passedCmdStringsStack;
    private boolean isNext;

    /**
     * Basic empty constructor for the CommandStringStash.
     */
    public CommandStringStash() {
        this.prevCmdStringsStack = new LinkedList<>();
        this.passedCmdStringsStack = new LinkedList<>();
        this.isNext = true;
    }

    /**
     * Creates the CommandStringStash using the initial values provided.
     */
    public CommandStringStash(Deque<String> prevCmdStringsStack, Deque<String> passedCmdStringsStack, boolean isNext) {
        this.prevCmdStringsStack = prevCmdStringsStack;
        this.passedCmdStringsStack = passedCmdStringsStack;
        this.isNext = isNext;
    }

    /**
     * Returns the previous command string input entered by the user.
     * Remembers the state, so it passes previous command string inputs already gotten.
     * Returns {@code commandInputString} if already at the first command string input entered.
     */
    public String getPrevCommandString(String commandInputString) {
        assert commandInputString != null;
        if (prevCmdStringsStack.isEmpty()) {
            return commandInputString;
        }

        String nextCommandString = prevCmdStringsStack.removeFirst();
        this.passedCmdStringsStack.addFirst(nextCommandString);

        if (!isNext) {
            // skip over this string as it was just added to the stack
            isNext = true;
            return getPrevCommandString(commandInputString);
        }
        return nextCommandString;
    }

    /**
     * Returns the command string input passed by the user acting as an "undo" of {@code getPrevCommandString}.
     * Remember the state, so it passes command string inputs already "undone".
     * Returns {@code commandInputString} if the user has not passed any commands yet.
     */
    public String getPassedCommandString(String commandInputString) {
        assert commandInputString != null;
        if (passedCmdStringsStack.isEmpty()) {
            return commandInputString;
        }

        String prevCommandString = passedCmdStringsStack.removeFirst();
        this.prevCmdStringsStack.addFirst(prevCommandString);

        if (isNext) {
            // skip over this string as it was just added to the stack
            isNext = false;
            return getPassedCommandString(commandInputString);
        }
        return prevCommandString;
    }

    /**
     * Adds the {@code commandString} to the stash of command Strings.
     * If the stash has size > 20, the least recently added command string is evicted.
     * Also resets the state of the stash.
     * @param commandInputString A non-null command input String.
     */
    public void addCommandString(String commandInputString) {
        assert commandInputString != null;

        // evict least recently added command string if necessary
        while (this.passedCmdStringsStack.size() + prevCmdStringsStack.size() >= 20) {
            this.prevCmdStringsStack.removeLast();
        }

        // reset the command string stacks
        while (!this.passedCmdStringsStack.isEmpty()) {
            this.prevCmdStringsStack.addFirst(passedCmdStringsStack.removeFirst());
        }
        this.isNext = true;

        this.prevCmdStringsStack.addFirst(commandInputString);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CommandStringStash)) {
            return false;
        }
        CommandStringStash commandStringStash = (CommandStringStash) object;
        return Objects.equals(prevCmdStringsStack, commandStringStash.prevCmdStringsStack)
                && Objects.equals(passedCmdStringsStack, commandStringStash.passedCmdStringsStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prevCmdStringsStack, passedCmdStringsStack, isNext);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Previous Command Strings", prevCmdStringsStack)
                .add("Passed Command Strings", passedCmdStringsStack)
                .toString();
    }
}
