package swe.context.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Stores the history of entered commands and a pointer indicating the current position in the list.
 * The list always behaves externally as if its last element is the empty string.
 */
public class CommandBoxHistory {
    private List<String> commandList;
    private int commandIndex;

    /**
     * Constructs {@code CommandBoxHistory} with empty command history.
     * The pointer is set to the last element in {@code commandList}.
     */
    public CommandBoxHistory() {
        this.commandList = new ArrayList<>();
        this.commandIndex = this.commandList.size();
    }

    /**
     * Constructs {@code CommandBoxHistory} defensively.
     * The pointer is set to the last element in {@code commandList}.
     */
    public CommandBoxHistory(List<String> list) {
        this.commandList = new ArrayList<>(list);
        this.commandIndex = this.commandList.size();
    }

    /**
     * Appends {@code element} to the end of the commandList.
     */
    public void add(String element) {
        commandList.add(element);
    }

    /**
     * Resets the command history pointer to the end of the list.
     */
    public void resetPointer() {
        this.commandIndex = this.commandList.size();
    }

    /**
     * Returns true if calling {@code #next()} does not throw an {@code NoSuchElementException}.
     */
    public boolean hasNext() {
        int nextIndex = commandIndex + 1;
        return isWithinBounds(nextIndex);
    }

    /**
     * Returns true if calling {@code #previous()} does not throw an {@code NoSuchElementException}.
     */
    public boolean hasPrevious() {
        int previousIndex = commandIndex - 1;
        return isWithinBounds(previousIndex);
    }

    /**
     * Returns true if calling {@code #current()} does not throw an {@code NoSuchElementException}.
     */
    public boolean hasCurrent() {
        return isWithinBounds(commandIndex);
    }

    private boolean isWithinBounds(int index) {
        return index >= 0 && index <= commandList.size();
    }

    /**
     * Returns the next command in the commandList and advances the pointer position.
     * @throws NoSuchElementException if there is no more next command in the commandList.
     */
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        commandIndex++;
        if (commandIndex == commandList.size()) {
            return "";
        } else {
            return commandList.get(commandIndex);
        }
    }

    /**
     * Returns the previous command in the commandList and decrements the pointer position.
     * @throws NoSuchElementException if there is no more previous command in the commandList.
     */
    public String previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        commandIndex--;
        if (commandIndex == commandList.size()) {
            return "";
        } else {
            return commandList.get(commandIndex);
        }
    }

    /**
     * Returns the current command in the commandList.
     * @throws NoSuchElementException if the commandList is empty.
     */
    public String current() {
        if (!hasCurrent()) {
            throw new NoSuchElementException();
        }
        if (commandIndex == commandList.size()) {
            return "";
        } else {
            return commandList.get(commandIndex);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CommandBoxHistory)) {
            return false;
        }

        CommandBoxHistory iterator = (CommandBoxHistory) other;
        return commandList.equals(iterator.commandList) && commandIndex == iterator.commandIndex;
    }
}
