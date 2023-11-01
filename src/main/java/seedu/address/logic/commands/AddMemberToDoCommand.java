package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Member;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Tasklist;
import seedu.address.model.person.fields.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.task.ToDo;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AddMemberToDoCommand extends Command{

    public static final String COMMAND_WORD = "addToDo";
    public static final String COMMAND_ALIAS = "addtd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a To Do task to the specified member "
            + "by the index number used in the displayed member list. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TODO + " {taskName} ";

    public static final String MESSAGE_ADD_TODO_SUCCESS = "Task added to member %1$s";

    private final Index index;
    private final AddMemberToDoDescriptor addMemberToDoDescriptor;


    /**
     * Creates an AddMemberCommand to add the specified {@code Member}
     */
    public AddMemberToDoCommand(Index index, AddMemberToDoDescriptor addMemberToDoDescriptor) {
        requireNonNull(addMemberToDoDescriptor);
        requireNonNull(index);

        this.index = index;
        this.addMemberToDoDescriptor = addMemberToDoDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getFilteredMemberList();
        System.out.println(lastShownList);

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        }

        Member memberToAssign = lastShownList.get(index.getZeroBased());
        Member taskAddedMember = assignTaskToMember(memberToAssign, addMemberToDoDescriptor);

        model.setMember(memberToAssign, taskAddedMember);
        model.updateFilteredMemberList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_TODO_SUCCESS, Messages.format(memberToAssign)));
    }

    private static Member assignTaskToMember(Member member, AddMemberToDoDescriptor addMemberToDoDescriptor) {
        assert member != null;

        Name updatedName = addMemberToDoDescriptor.getName().orElse(member.getName());
        Phone updatedPhone = addMemberToDoDescriptor.getPhone().orElse(member.getPhone());
        Email updatedEmail = addMemberToDoDescriptor.getEmail().orElse(member.getEmail());
        Telegram updatedTelegram = addMemberToDoDescriptor.getTelegram().orElse(member.getTelegram());
        Set<Tag> updatedTags = addMemberToDoDescriptor.getTags().orElse(member.getTags());
        Tasklist updatedTasks = addMemberToDoDescriptor.getTasks().orElse(member.getTasks());

        return new Member(updatedName, updatedPhone, updatedEmail, updatedTelegram, updatedTags, updatedTasks);

    }

    public static class AddMemberToDoDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Telegram telegram;
        private Set<Tag> tags;
        private Tasklist tasks;

        public AddMemberToDoDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public AddMemberToDoDescriptor(AddMemberToDoDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setTelegram(toCopy.telegram);
            setTags(toCopy.tags);
            setTasks(toCopy.tasks);
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

        public void setTasks(Tasklist tasks) {
            this.tasks = tasks;
            System.out.println(this.tasks);
        }

        public Optional<Tasklist> getTasks() {
            return Optional.ofNullable(this.tasks);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("telegram", telegram)
                    .add("tags", tags)
                    .add("tasks", tasks)
                    .toString();
        }
    }
}
