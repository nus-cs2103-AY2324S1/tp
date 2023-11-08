package seedu.address.logic.commands;



/**
 * Represents the types of commands, which are used to detect them and set appropriate handlers for teach type in
 * MainWindow.
 */
public enum CommandType {
    ADD("Add Command"),
    CLEAR("Clear Command"),
    DELETE("Delete Command"),
    EDIT("Edit Command"),
    EDIT_FIELD("Edit Field Command"),
    EXIT("Exit Command"),
    FIND("Find Command"),
    HELP("Help Command"),
    LIST("List Command"),
    SAVE("Save Command"),
    SORT("Sort Command"),
    VIEW("View Command"),
    VIEW_EXIT("View Exit Command");

    private String commandName;

    CommandType(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public String toString() {
        return commandName;
    }
}
