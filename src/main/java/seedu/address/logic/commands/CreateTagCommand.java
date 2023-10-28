package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Represents a command to create tags with specific categories.
 */
public class CreateTagCommand extends Command {
    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_SUCCESS = "Tags created!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates tags with specific categories. "
            + "Parameters: "
            + PREFIX_TAG + "ROLE TAG "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "dept "
            + "Tech "
            + PREFIX_TAG + "role "
            + "Analyst";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists in the address book!";

    private final String[] tagParams;

    /**
     * Constructs a CreateTagCommand with the specified tag parameters.
     *
     * @param tagParams An array of tag parameters, where each element represents a tag's category and name.
     */
    public CreateTagCommand(String[] tagParams) {
        this.tagParams = tagParams;
    }

    /**
     * Executes the CreateTagCommand to create tags with specific categories in the model.
     *
     * @param model The model in which the tags are created.
     * @return A CommandResult indicating the success of the command.
     * @throws CommandException If there is an error executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        for (String tagParam : tagParams) {
            String[] categoryTag = tagParam.split("\\s+");
            try {
                String tagCategory = categoryTag[0];
                String tagName = categoryTag[1];
                model.addTag(new Tag(tagName, tagCategory));
            } catch (IllegalValueException e) {
                throw new RuntimeException(e);
            }
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
