package seedu.address.logic.parser;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Task;

import static seedu.address.logic.parser.RegularExpressionUtil.STARTING_WITH_ONE_TO_FIVE_DIGITS;
import static seedu.address.logic.parser.TypeParsingUtil.*;

/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    @Override
    public AddTaskCommand parse(String args) throws ParseException {
        Integer index = parseIndex(args, true);
        String description = index == null ? args : args.replaceFirst(index.toString(), "");
        if (!Task.isValidTask(description)) {
            throw new ParseException(Task.MESSAGE_CONSTRAINTS);
        }
        Task task = new Task(description);
        return new AddTaskCommand(index, task);
    }
}
