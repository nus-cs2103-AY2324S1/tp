package seedu.address.logic.commands;

import java.io.FileWriter;
import java.io.IOException;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;

/**
 * Exports the current dataset into Excel (.csv) format -- into the /data folder
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the current address book to Excel format "
            + "Parameters: "
            + "export";

    public static final String MESSAGE_SUCCESS = "Sucessfully Exported";

    /**
     * Empty constructor,
     */
    public ExportCommand() {}

    public String treatAsString(String str) {
        return "\"" + str + "\"";
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String fileName = "data/export.csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("Name,Phone,Email,Address,Tags,LinkedIn,Github,Remark,Status\n"); // Write the header

            for (Person p : model.getAddressBook().getPersonList()) {
                String name = treatAsString(p.getName().toString());
                String phone = treatAsString(p.getPhone().toString());
                String email = treatAsString(p.getEmail().toString());
                String address = treatAsString(p.getAddress().toString());
                String tags = treatAsString(p.getTags().toString());
                String linkedIn = treatAsString(p.getLinkedIn().toString());
                String github = treatAsString(p.getGithub().toString());
                String remark = treatAsString(p.getRemark().toString());
                String status = treatAsString(p.getStatus().toString());

                writer.append(name)
                        .append(",").append(phone)
                        .append(",").append(email)
                        .append(",").append(address)
                        .append(",").append(tags)
                        .append(",").append(linkedIn)
                        .append(",").append(github)
                        .append(",").append(remark)
                        .append(",").append(status)
                        .append("\n");
            }
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
}
