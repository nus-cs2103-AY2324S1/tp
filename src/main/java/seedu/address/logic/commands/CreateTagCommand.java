package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.UniqueTagList;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class CreateTagCommand extends Command {
    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_SUCCESS = "Tags created!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates tags with specific categories."
            + "Parameters: "
            + PREFIX_TAG + "ROLE TAG"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "dept "
            + "Tech"
            + PREFIX_TAG + "role "
            + "Analyst";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists in the address book!";

    private final String[] tagParams;

    public CreateTagCommand(String[] tagParams) {
        this.tagParams = tagParams;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        for (String tagParam : tagParams) {
            String[] categoryTag = tagParam.split("\\s+");
            try {
                model.addTagToCategory(categoryTag[0], categoryTag[1]);
            } catch (IllegalValueException e) {
                throw new RuntimeException(e);
            }
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

}

// -> note: can only add tags belonging to a single category
// create t/dept cs t/dept cde t/role swe t/role analyst
// note => first input is category, next is tag name
