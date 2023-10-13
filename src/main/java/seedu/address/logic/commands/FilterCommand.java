package seedu.address.logic.commands;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Filters contact list to display contacts matching specified criteria.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose attributes contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Hugh "
            + PREFIX_PHONE + "90 "
            + PREFIX_EMAIL + "@u.nus.edu "
            + PREFIX_ADDRESS + "123, Tengah Ave 6, #69-420 "
            + PREFIX_TAG + "CS2103 "
            + PREFIX_TAG + "CSGod";
    private PersonFilter personFilter;

    public FilterCommand(PersonFilter personFilter) {
        requireNonNull(personFilter);
        this.personFilter = personFilter;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Filtering");
    }

    /**
     * Stores the details to filter the contacts by. Contacts will be filtered by each non-empty field .
     */
    public static class PersonFilter {
        private String name;
        private String phone;
        private String email;
        private String address;
        private Set<Tag> tags;

        public PersonFilter() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public PersonFilter(PersonFilter toCopy) {
            name = toCopy.name;
            phone = toCopy.phone;
            email = toCopy.email;
            address = toCopy.address;
            tags = toCopy.tags;
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhone() {
            return phone;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddress() {
            return address;
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

        public boolean test(Person person) {
            if (!person.getName().fullName.contains(name)) {
                return false;
            }
            if (!person.getPhone().value.contains(phone)) {
                return false;
            }
            if (!person.getEmail().value.contains(email)) {
                return false;
            }
            if (!person.getAddress().value.contains(address)) {
                return false;
            }
            if (!tags.isEmpty()) {
                Tag[] tagsToCheck = tags.toArray(new Tag[1]);
                Tag[] currentTags = person.getTags().toArray(new Tag[0]);
                for (Tag tag:tagsToCheck) {
                    for (int i = 0; i <= currentTags.length; i++) {
                        if (i == currentTags.length) {
                            return false;
                        } else if (currentTags[i].tagName.contains(tag.tagName)) {
                            break;
                        }
                    }
                }
            }
            return true;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof PersonFilter)) {
                return false;
            }

            PersonFilter otherPersonFilterDescriptor = (PersonFilter) other;
            return Objects.equals(name, otherPersonFilterDescriptor.name)
                    && Objects.equals(phone, otherPersonFilterDescriptor.phone)
                    && Objects.equals(email, otherPersonFilterDescriptor.email)
                    && Objects.equals(address, otherPersonFilterDescriptor.address)
                    && Objects.equals(tags, otherPersonFilterDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .toString();
        }
    }
}
