package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Member;
import seedu.address.model.person.fields.*;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditMemberCommand extends Command {

    public static final String COMMAND_WORD = "editMember";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the member identified "
            + "by the index number used in the displayed member list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + " {memberName} "
            + PREFIX_PHONE + " {phoneNumber} "
            + PREFIX_EMAIL + " {email} "
            + PREFIX_TELEGRAM + " {telegramHandle} "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + " 91234567 "
            + PREFIX_EMAIL + " johndoe@example.com";

    public static final String MESSAGE_EDIT_MEMBER_SUCCESS = "Edited member: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEMBER = "This member already exists in the address book.";

    private final Index index;
    private final EditMemberDescriptor editMemberDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editMemberDescriptor details to edit the person with
     */
    public EditMemberCommand(Index index, EditMemberDescriptor editMemberDescriptor) {
        requireNonNull(index);
        requireNonNull(editMemberDescriptor);

        this.index = index;
        this.editMemberDescriptor = new EditMemberDescriptor(editMemberDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getFilteredMemberList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        }

        Member memberToEdit = lastShownList.get(index.getZeroBased());
        Member editedMember = createEditedMember(memberToEdit, editMemberDescriptor);

        if (!memberToEdit.isSameMember(editedMember) && model.hasMember(editedMember)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEMBER);
        }

        model.setMember(memberToEdit, editedMember);
        model.updateFilteredMembersList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_MEMBER_SUCCESS, Messages.format(editedMember)));
    }

    /**
     * Creates and returns a {@code Member} with the details of {@code memberToEdit}
     * edited with {@code editMemberDescriptor}.
     */
    private static Member createEditedMember(Member memberToEdit, EditMemberDescriptor editMemberDescriptor) {
        assert memberToEdit != null;

        Name updatedName = editMemberDescriptor.getName().orElse(memberToEdit.getName());
        Phone updatedPhone = editMemberDescriptor.getPhone().orElse(memberToEdit.getPhone());
        Email updatedEmail = editMemberDescriptor.getEmail().orElse(memberToEdit.getEmail());
        Telegram updatedTelegram = editMemberDescriptor.getTelegram().orElse(memberToEdit.getTelegram());
        Set<Tag> updatedTags = editMemberDescriptor.getTags().orElse(memberToEdit.getTags());

        return new Member(updatedName, updatedPhone, updatedEmail, updatedTelegram, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMemberCommand)) {
            return false;
        }

        EditMemberCommand otherEditMemberCommand = (EditMemberCommand) other;
        return index.equals(otherEditMemberCommand.index)
                && editMemberDescriptor.equals(otherEditMemberCommand.editMemberDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editMemberDescriptor", editMemberDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditMemberDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Telegram telegram;
        private Set<Tag> tags;

        public EditMemberDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditMemberDescriptor(EditMemberDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setTelegram(toCopy.telegram);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, telegram, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setTelegram(Telegram telegram) {
            this.telegram = telegram;
        }

        public Optional<Telegram> getTelegram() {
            return Optional.ofNullable(telegram);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMemberDescriptor)) {
                return false;
            }

            EditMemberDescriptor otherEditMemberDescriptor = (EditMemberDescriptor) other;
            return Objects.equals(name, otherEditMemberDescriptor.name)
                    && Objects.equals(phone, otherEditMemberDescriptor.phone)
                    && Objects.equals(email, otherEditMemberDescriptor.email)
                    && Objects.equals(telegram, otherEditMemberDescriptor.telegram)
                    && Objects.equals(tags, otherEditMemberDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("telegram", telegram)
                    .add("tags", tags)
                    .toString();
        }
    }
}
