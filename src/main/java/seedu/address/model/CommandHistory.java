package seedu.address.model;

import java.util.LinkedList;

/**
 * A command history stores a specified number of commands that the user has entered.
 */
public class CommandHistory {
    private final int maxCommands;
    private final LinkedList<String> commandHistory;
    private int currentCommandIndex = 0;

    /**
     * Create a command history with a default maximum number of commands to store.
     */
    public CommandHistory() {
        this.maxCommands = 100;
        this.commandHistory = new LinkedList<>();
    }

    /**
     * Create a command history with a specified maximum number of commands to store.
     * @param maxCommands The maximum number of commands to store.
     */
    public CommandHistory(int maxCommands) {
        this.maxCommands = maxCommands;
        this.commandHistory = new LinkedList<>();
    }

    /**
     * Add a command to the command history.
     * @param command The command to add.
     */
    public void addCommand(String command) {
        if (commandHistory.size() == maxCommands) {
            commandHistory.removeFirst();
        }

        // Do not add the same command twice in a row
        if (commandHistory.size() != 0 && commandHistory.getLast().equals(command)) {
            return;
        }

        // Do not add empty commands
        if (command.equals("")) {
            return;
        }

        removeHistoryAfterCurrentIndex();

        commandHistory.addLast(command);
        currentCommandIndex = commandHistory.size();
    }

    /**
     * Get the previous command in the command history.
     */
    public String getPreviousCommand() {
        if (currentCommandIndex == 0) {
            return commandHistory.isEmpty()
                ? ""
                : commandHistory.getFirst();
        }
        currentCommandIndex--;
        return commandHistory.get(currentCommandIndex);
    }

    /**
     * Get the next command in the command history.
     */
    public String getNextCommand() {
        if (currentCommandIndex >= commandHistory.size() - 1) {
            currentCommandIndex = commandHistory.size();
            return "";
        }
        currentCommandIndex++;
        return commandHistory.get(currentCommandIndex);
    }

    /**
     * Reset the current command index to the end of the command history.
     */
    public void resetCurrentCommandIndex() {
        currentCommandIndex = commandHistory.size();
    }

    private void removeHistoryAfterCurrentIndex() {
        while (commandHistory.size() > currentCommandIndex) {
            commandHistory.removeLast();
        }
    }
}
