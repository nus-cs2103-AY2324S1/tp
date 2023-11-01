package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Task;

import static seedu.address.logic.parser.TypeParsingUtil.*;


/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    @Override
    public AddTaskCommand parse(String args) throws ParseException {
        Index index;
        String indexStr = getNumberImmediatelyAfterCommandName(AddTaskCommand.COMMAND_WORD
                ,"lesson index", args,true);
        if (indexStr != null) {
            args = args.replace(indexStr, "");
        }
        String description =getValueImmediatelyAfterCommandName(AddTaskCommand.COMMAND_WORD
                ,"description", args);
        if (!Task.isValidTask(description)) {
            throw new ParseException(Task.MESSAGE_CONSTRAINTS);
        }
        Task task = new Task(description);
        if (null == indexStr) {
            return new AddTaskCommand(task);
        }
        try {
            index = Index.fromOneBased(Integer.parseInt(indexStr));
        } catch (NumberFormatException e) {
            throw new ParseException(indexStr + " is not a valid index!");
        }
        return new AddTaskCommand(index, task);
    }
}
