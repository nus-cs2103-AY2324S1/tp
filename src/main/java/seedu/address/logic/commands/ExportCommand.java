package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.FileWriter;
import java.io.IOException;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Exports the current dataset into Excel (.csv) format -- into the /data folder
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the current address book to Excel format "
            + "Parameters: "
            + "export";

    public static final String MESSAGE_SUCCESS = "Sucessfully Exported";

    private String defaulltPath = "data/export.csv";

    /**
     * Empty constructor,
     */
    public ExportCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try (FileWriter writer = new FileWriter(defaulltPath)) {
            writer.append(StringUtil.appendPersons(model));
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (IOException e) {
            throw new CommandException("Error exporting data: " + e.getMessage());
        }
    }

    /**
     * Checks if the other command is an equivalent ExportCommand
     *
     * @param other the other object to be compared
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof ExportCommand;
    }

    public String toString() {
        return "ExportCommand";
    }
}
