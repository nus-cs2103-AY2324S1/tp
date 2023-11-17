package seedu.address.model;


import seedu.address.logic.commands.AddAnimalCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.DeleteAnimalCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditAnimalCommand;
import seedu.address.logic.commands.HelpAnimalCommand;
import seedu.address.logic.commands.ListAnimalCommand;
import seedu.address.logic.commands.MarkTaskCommand;
import seedu.address.logic.commands.ResetTaskCommand;
import seedu.address.logic.commands.SearchAnimalCommand;
import seedu.address.logic.commands.UnmarkTaskCommand;

/**
 * Encapsulates a Command's name and help message. Used primarily in {@link HelpAnimalCommand}.
 */
public enum CommandEnum {
    ADD_ANIMAL_COMMAND(AddAnimalCommand.COMMAND_WORD, AddAnimalCommand.getHelp()),
    ADD_TASK_COMMAND(AddTaskCommand.COMMAND_WORD, AddTaskCommand.getHelp()),
    DELETE_ANIMAL_COMMAND(DeleteAnimalCommand.COMMAND_WORD, DeleteAnimalCommand.getHelp()),
    DELETE_TASK_COMMAND(DeleteTaskCommand.COMMAND_WORD, DeleteTaskCommand.getHelp()),
    EDIT_ANIMAL_COMMAND(EditAnimalCommand.COMMAND_WORD, EditAnimalCommand.getHelp()),
    LIST_ANIMAL_COMMAND(ListAnimalCommand.COMMAND_WORD, ListAnimalCommand.getHelp()),
    MARK_TASK_COMMAND(MarkTaskCommand.COMMAND_WORD, MarkTaskCommand.getHelp()),
    RESET_TASK_COMMAND(ResetTaskCommand.COMMAND_WORD, ResetTaskCommand.getHelp()),
    SEARCH_ANIMAL_COMMAND(SearchAnimalCommand.COMMAND_WORD, SearchAnimalCommand.getHelp()),
    UNMARK_TASK_COMMAND(UnmarkTaskCommand.COMMAND_WORD, UnmarkTaskCommand.getHelp());

    private final String commandWord;
    private final String helpMessage;

    CommandEnum(String commandWord, String helpMessage) {
        this.commandWord = commandWord;
        this.helpMessage = helpMessage;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String getHelpMessage() {
        return helpMessage;
    }
}
