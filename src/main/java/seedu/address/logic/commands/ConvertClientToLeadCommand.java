package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.person.KeyMilestone.DATE_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import seedu.address.model.person.KeyMilestone;
import seedu.address.model.person.Lead;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


/**
 * Converts a Client to a Lead in the address book.
 */
public class ConvertClientToLeadCommand extends Command {
    public static final String COMMAND_WORD = "converttolead";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Converts a client to a lead by the index "
            + "number used in the displayed person list. \n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CONVERT_SUCCESS = "Converted Client to Lead: %1$s";
    public static final String MESSAGE_CONVERT_FAILURE = "The person at the specified index is not a Client.";

    private final Index index;

    /**
     * Creates an ConvertClientToLeadCommand to convert the specified {@code Client} to {@code Lead}
     */
    public ConvertClientToLeadCommand(Index index) {
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

        if (!(personToConvert instanceof Client)) {
            throw new CommandException("The person at the specified index is not a Client.");
        }

        Name name = personToConvert.getName();
        Phone phone = personToConvert.getPhone();
        Email email = personToConvert.getEmail();
        Address address = personToConvert.getAddress();
        //todo: temporary fix for keyMilestone
        LocalDate date = LocalDate.from(LocalDateTime.now());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String formattedDate = date.plusYears(1).format(formatter);
        KeyMilestone keyMilestone = new KeyMilestone(formattedDate);
        Set<Tag> tags = new HashSet<>(personToConvert.getTags());
        Optional<MeetingTime> meetingTime = personToConvert.getMeetingTime();


        Lead convertedLead = new Lead(name, phone, email, address, keyMilestone, meetingTime, tags);

        model.setPerson(personToConvert, convertedLead);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_CONVERT_SUCCESS, Messages.format(convertedLead)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ConvertClientToLeadCommand)) {
            return false;
        }

        ConvertClientToLeadCommand otherConvertCommand = (ConvertClientToLeadCommand) other;
        return index.equals(otherConvertCommand.index);
    }
}

