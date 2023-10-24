package seedu.address.model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

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

    public String getNextCommandString(String commandInputString) {
        assert commandInputString != null;
        if (prevCmdStringsStack.isEmpty()) {
            return commandInputString;
        }

        String nextCommandString = prevCmdStringsStack.removeFirst();
        this.passedCmdStringsStack.addFirst(nextCommandString);

        if (!isNext) {
            // skip over this string as it was just added to the stack
            isNext = true;
            return getNextCommandString(commandInputString);
        }
        return nextCommandString;
    }

    public String getPrevCommandString(String commandInputString) {
        assert commandInputString != null;
        if (passedCmdStringsStack.isEmpty()) {
            return commandInputString;
        }

        String prevCommandString = passedCmdStringsStack.removeFirst();
        this.prevCmdStringsStack.addFirst(prevCommandString);

        if (isNext) {
            // skip over this string as it was just added to the stack
            isNext = false;
            return getPrevCommandString(commandInputString);
        }
        return prevCommandString;
    }

    /**
     * Adds the {@code commandString} to the stash of command Strings.
     * If the stash has size > 20, the least recently added command string is evicted.
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
        if (object == null || !(object instanceof CommandStringStash)) {
            return false;
        }
        CommandStringStash commandStringStash = (CommandStringStash) object;
        return isNext == commandStringStash.isNext
                && Objects.equals(prevCmdStringsStack, commandStringStash.prevCmdStringsStack)
                && Objects.equals(passedCmdStringsStack, commandStringStash.passedCmdStringsStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prevCmdStringsStack, passedCmdStringsStack, isNext);
    }
}
