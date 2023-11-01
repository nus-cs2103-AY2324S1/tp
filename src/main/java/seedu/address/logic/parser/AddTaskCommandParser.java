package seedu.address.logic.parser;

import static seedu.address.logic.parser.TypeParsingUtil.parseFlag;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Task;


/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    @Override
    public AddTaskCommand parse(String args) throws ParseException {
        Index index;
        if (!Task.isValidTask(parseFlag("description", args))) {
            throw new ParseException(Task.MESSAGE_CONSTRAINTS);
        }
        Task task = new Task(parseFlag("description", args));
        try {
            index = Index.fromOneBased(TypeParsingUtil.parseNum(TypeParsingUtil
                    .getValueImmediatelyAfterCommandName(AddTaskCommand.COMMAND_WORD, "index", args),
                    1, Integer.MAX_VALUE));
        } catch (ParseException e) {
            throw new ParseException("Missing lesson with specified index!");
        }
        return new AddTaskCommand(index, task);
    }
}
