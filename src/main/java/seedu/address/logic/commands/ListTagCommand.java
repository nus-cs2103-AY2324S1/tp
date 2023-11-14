package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.tag.TagFrequencyTable;

/**
 * Lists all tags in CoordiMate to the user.
 */
public class ListTagCommand extends Command {

    public static final String COMMAND_WORD = "listTag";
    public static final String SHORTENED_COMMAND_WORD = "ltag";

    public static final String MESSAGE_NO_TAGS_FOUND = "No tags found!";
    public static final String MESSAGE_SUCCESS = "Listed all tags: \n%s\n";

    @Override
    public CommandResult execute(Model model) {
        TagFrequencyTable tagFrequencyTable = model.getTagFrequencyTable();

        if (tagFrequencyTable.isEmpty()) {
            return new CommandResult(MESSAGE_NO_TAGS_FOUND);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, tagFrequencyTable.format()));
    }
}
