package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

public class ListEventCommand extends ListCommand {
    public static final String SECONDARY_COMMAND_WORD = "events";
    private static final String MESSAGE = "Here are all the events in this address book: ";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> persons = model.getAddressBook().getPersonList();
        StringBuilder str = new StringBuilder("All events:\n");
        persons.forEach(
                person -> person.getEvents().forEach(
                        event -> str.append("[").append(person.getName().toString()).append("] ").append(event.getUIText()).append("\n")
                )
        );
        return new CommandResult(str.toString());
    }
}
