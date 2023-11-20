package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEY_MILESTONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.KeyMilestone;
import seedu.address.model.person.Lead;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing lead in the address book.
 */
public class EditLeadCommand extends EditCommand {

    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_KEY_MILESTONE + "KEY_MILESTONE] "
            + "[" + PREFIX_MEETING_TIME + "MEETING_TIME] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";
    public static final String INVALID_USAGE_FOR_CLIENT = "Type client has no key milestone.";


    private final Index index;
    private final EditLeadDescriptor editLeadDescriptor;

    /**
     * @param index                of the person in the filtered person list to edit
     * @param editLeadDescriptor details to edit the lead with
     */
    public EditLeadCommand(Index index, EditLeadDescriptor editLeadDescriptor) {
        super(index, editLeadDescriptor);
        requireNonNull(index);
        requireNonNull(editLeadDescriptor);

        this.index = index;
        this.editLeadDescriptor = new EditLeadDescriptor(editLeadDescriptor);
    }


    private static Lead createEditedLead(Lead leadToEdit, EditLeadDescriptor editLeadDescriptor) {
        assert leadToEdit != null;

        Name updatedName = editLeadDescriptor.getName().orElse(leadToEdit.getName());
        Phone updatedPhone = editLeadDescriptor.getPhone().orElse(leadToEdit.getPhone());
        Email updatedEmail = editLeadDescriptor.getEmail().orElse(leadToEdit.getEmail());
        Address updatedAddress = editLeadDescriptor.getAddress().orElse(leadToEdit.getAddress());
        Set<Tag> updatedTags = editLeadDescriptor.getTags().orElse(leadToEdit.getTags());
        KeyMilestone updatedKeyMilestone = editLeadDescriptor.getKeyMilestone()
                .orElse(leadToEdit.getKeyMilestone());
        Optional<MeetingTime> updatedMeetingTime = editLeadDescriptor.getMeetingTime()
                .or(leadToEdit::getMeetingTime);

        return new Lead(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedKeyMilestone,
                updatedMeetingTime, updatedTags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (personToEdit.isClient()) {
            //if "edit index k/" is used on client
            throw new CommandException(INVALID_USAGE_FOR_CLIENT);
        }
        assert(personToEdit.isLead());
        Lead leadToEdit = (Lead) lastShownList.get(index.getZeroBased());
        Lead editedLead = createEditedLead(leadToEdit, editLeadDescriptor);

        if (!leadToEdit.isSamePerson(editedLead) && model.hasPerson(editedLead)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(leadToEdit, editedLead);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_LEAD_SUCCESS, Messages.format(editedLead)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditLeadCommand)) {
            return false;
        }

        EditLeadCommand otherEditCommand = (EditLeadCommand) other;
        return index.equals(otherEditCommand.index)
                && editLeadDescriptor.equals(otherEditCommand.editLeadDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editLeadDescriptor", editLeadDescriptor)
                .toString();
    }


}
