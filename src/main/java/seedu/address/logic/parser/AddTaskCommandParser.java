package seedu.address.logic.parser;

import static seedu.address.logic.parser.TypeParsingUtil.parseFlag;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Task;


/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    @Override
    public AddTaskCommand parse(String args) throws ParseException {
        int index;
        Task task = new Task(parseFlag("description", args));
        try {
            index = TypeParsingUtil.parseNum(TypeParsingUtil
                    .getValueImmediatelyAfterCommandName(AddTaskCommand.COMMAND_WORD, "index", args));
        } catch (ParseException e) {
            throw new ParseException("Missing lesson with index " + e.getMessage());
        }
        return new AddTaskCommand(index, task);
    }
}
