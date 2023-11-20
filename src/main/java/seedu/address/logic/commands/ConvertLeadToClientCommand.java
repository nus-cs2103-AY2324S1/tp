package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Email;
import seedu.address.model.person.Lead;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Converts a Lead to a Client in the address book.
 */
public class ConvertLeadToClientCommand extends Command {
    public static final String COMMAND_WORD = "converttoclient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Converts a lead to a client by the index "
            + "number used in the displayed person list. \n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CONVERT_SUCCESS = "Converted Lead to Client: %1$s";
    public static final String MESSAGE_NOT_LEAD = "The person at the specified index is not a Lead.";

    private final Index index;

    /**
     * Creates an ConvertLeadToClientCommand to convert the specified {@code Lead} to {@code Client}
     */
    public ConvertLeadToClientCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        System.out.println(lastShownList);
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToConvert = lastShownList.get(index.getZeroBased());

        if (!(personToConvert instanceof Lead)) {
            throw new CommandException("The person at the specified index is not a Lead.");
        }

        Name name = personToConvert.getName();
        Phone phone = personToConvert.getPhone();
        Email email = personToConvert.getEmail();
        Address address = personToConvert.getAddress();
        Set<Tag> tags = new HashSet<>(personToConvert.getTags());
        Optional<MeetingTime> meetingTime = personToConvert.getMeetingTime();

        // TODO: Add more fields from lead to client

        Client convertedClient = new Client(name, phone, email, address, meetingTime, tags);

        model.setPerson(personToConvert, convertedClient);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_CONVERT_SUCCESS, Messages.format(convertedClient)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ConvertLeadToClientCommand)) {
            return false;
        }

        ConvertLeadToClientCommand otherConvertCommand = (ConvertLeadToClientCommand) other;
        return index.equals(otherConvertCommand.index);
    }
}

