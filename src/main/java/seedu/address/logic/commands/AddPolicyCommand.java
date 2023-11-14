package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_VALUE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PreferredContact;
import seedu.address.model.person.PreferredMeetingRegion;
import seedu.address.model.policy.Policy;
import seedu.address.model.tag.Tag;

/**
 * Adds a policy to an existing person in the address book.
 */
public class AddPolicyCommand extends Command {
    public static final String COMMAND_WORD = "addPolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds to the policy of the person identified "
            + "by the index number used in the displayed person list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_POLICY_NAME + "POLICY NAME "
            + " " + PREFIX_POLICY_DESCRIPTION + "POLICY DESCRIPTION "
            + " " + PREFIX_POLICY_VALUE + "POLICY VALUE "
            + " " + PREFIX_POLICY_START_DATE + "POLICY START DATE "
            + " " + PREFIX_POLICY_END_DATE + "POLICY END DATE" + "] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_POLICY_NAME + "Health Insurance "
            + PREFIX_POLICY_DESCRIPTION + "Cancer Plan "
            + PREFIX_POLICY_VALUE + "2000.00 "
            + PREFIX_POLICY_START_DATE + "2023-01-01 "
            + PREFIX_POLICY_END_DATE + "2024-12-12 ";

    public static final String MESSAGE_ADD_POLICY_SUCCESS = "Added policies successfully for person %1$s";

    public static final String MESSAGE_NOT_EDITED = "One policy must be provided.";

    private final Index index;
    private final AddPolicyCommand.AddPolicyDescriptor addPolicyDescriptor;

    /**
     * @param index               of the person in the filtered person list to edit
     * @param addPolicyDescriptor details to edit the person with
     */

    public AddPolicyCommand(Index index, AddPolicyCommand.AddPolicyDescriptor addPolicyDescriptor) {
        requireNonNull(index);
        requireNonNull(addPolicyDescriptor);
        this.index = index;
        this.addPolicyDescriptor = new AddPolicyCommand.AddPolicyDescriptor(addPolicyDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person personWithAddedPolicy = createPersonWithAddedPolicy(personToEdit, addPolicyDescriptor);
        personWithAddedPolicy.addPolicies(personToEdit.getPolicies());

        model.setPerson(personToEdit, personWithAddedPolicy);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_POLICY_SUCCESS, Messages.format(personWithAddedPolicy)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code addPolicyDescriptor}.
     */
    private static Person createPersonWithAddedPolicy(Person personToEdit, AddPolicyDescriptor addPolicyDescriptor) {
        assert personToEdit != null;

        Name updatedName = addPolicyDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = addPolicyDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = addPolicyDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = addPolicyDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = addPolicyDescriptor.getTags().orElse(personToEdit.getTags());
        PreferredContact updatedPreferredContact = addPolicyDescriptor.getPreferredContact()
                .orElse(personToEdit.getPreferredContact());
        PreferredMeetingRegion updatedPreferredMeetingRegion = addPolicyDescriptor.getPreferredMeetingRegion()
                .orElse(personToEdit.getPreferredMeetingRegion());
        Set<Policy> updatedPolicies = addPolicyDescriptor.getPolicies().orElse(personToEdit.getPolicies());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                updatedPreferredMeetingRegion, updatedPreferredContact, updatedPolicies);
    }

    /**
     * Checks for duplicates in the set
     * @param set set to be checked
     * @param <T> this describes my type parameter
     * @return true if at least one field is duplicated
     */
    public static <T> boolean hasDuplicates(Set<T> set) {
        Set<T> tempSet = new HashSet<>();
        for (T element : set) {
            if (tempSet.contains(element)) {
                return true;
            }
            tempSet.add(element);
        }
        return false;
    }

    /**
     * Compares this object with another object
     * @param other object to be compared
     * @return true if other is equal to this
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddPolicyCommand)) {
            return false;
        }

        AddPolicyCommand otherAddPolicyCommand = (AddPolicyCommand) other;
        return index.equals(otherAddPolicyCommand.index)
                && addPolicyDescriptor.equals(otherAddPolicyCommand.addPolicyDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .toString();
    }

    /**
     * Stores the details to add the policy with. Added policy will be added to current
     * list of policy.
     */

    public static class AddPolicyDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private PreferredMeetingRegion preferredMeetingRegion;
        private PreferredContact preferredContact;

        private Set<Policy> policies;


        public AddPolicyDescriptor() {
        }

        /**
         * Copy constructor.s
         * A defensive copy of {@code tags} is used internally.
         */
        public AddPolicyDescriptor(AddPolicyDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setPreferredMeetingRegion(toCopy.preferredMeetingRegion);
            setPreferredContact(toCopy.preferredContact);
            setPolicies(toCopy.policies);
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

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }


        public void setPreferredContact(PreferredContact preferredContact) {
            this.preferredContact = preferredContact;
        }
        public void setPreferredMeetingRegion(PreferredMeetingRegion preferredMeetingRegion) {
            this.preferredMeetingRegion = preferredMeetingRegion;
        }
        public Optional<PreferredMeetingRegion> getPreferredMeetingRegion() {
            return Optional.ofNullable(preferredMeetingRegion);
        }


        public Optional<PreferredContact> getPreferredContact() {
            return Optional.ofNullable(preferredContact);
        }

        /**
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code policies} to this object's {@code policies}.
         * A defensive copy of {@code policies} is used internally.
         */
        public void setPolicies(Set<Policy> policies) {
            this.policies = (policies != null) ? new HashSet<>(policies) : null;
        }

        /**
         * Returns an unmodifiable policy set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code policies} is null.
         */
        public Optional<Set<Policy>> getPolicies() {
            return (policies != null) ? Optional.of(Collections.unmodifiableSet(policies)) : Optional.empty();
        }


        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AddPolicyCommand.AddPolicyDescriptor)) {
                return false;
            }

            AddPolicyCommand.AddPolicyDescriptor otherAddPolicyDescriptor =
                    (AddPolicyCommand.AddPolicyDescriptor) other;
            return Objects.equals(name, otherAddPolicyDescriptor.name)
                    && Objects.equals(phone, otherAddPolicyDescriptor.phone)
                    && Objects.equals(email, otherAddPolicyDescriptor.email)
                    && Objects.equals(address, otherAddPolicyDescriptor.address)
                    && Objects.equals(tags, otherAddPolicyDescriptor.tags)
                    && Objects.equals(policies, otherAddPolicyDescriptor.policies);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .add("policies", policies)
                    .toString();
        }
    }

}
