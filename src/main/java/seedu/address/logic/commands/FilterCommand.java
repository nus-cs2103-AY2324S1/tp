package seedu.address.logic.commands;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

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
    public static final String CONTACTS_NOT_FILTERED = "At least one parameter is required to filter contacts.";
    private final PersonFilter personFilter;

    /**
     * Filters out all persons that do not match the filter criteria specified.
     * Field matching is case-insensitive
     *
     * @param personFilter Details to filter people with
     */
    public FilterCommand(PersonFilter personFilter) {
        requireNonNull(personFilter);
        this.personFilter = personFilter;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(personFilter::test);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return personFilter.equals(otherFilterCommand.personFilter);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("personFilter", personFilter)
                .toString();
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
         * Creates a copy of a filter with the same parameters as another filter.
         *
         * @param toCopy The other filter to create a copy of.
         */
        public PersonFilter(PersonFilter toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
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
        public Set<Tag> getTags() {
            return (tags != null) ? Collections.unmodifiableSet(tags) : null;
        }

        /**
         * Predicate to test if a person passes the filter by matching all parameters.
         *
         * @param person The person to be tested.
         * @return a boolean representing whether the person passed the filter.
         */
        public boolean test(Person person) {
            if (nonNull(name) && !person.getName().fullName.toLowerCase().contains(name.toLowerCase())) {
                return false;
            }
            if (nonNull(phone) && !person.getPhone().value.contains(phone)) {
                return false;
            }
            if (nonNull(email) && !person.getEmail().value.toLowerCase().contains(email.toLowerCase())) {
                return false;
            }
            if (nonNull(address) && !person.getAddress().value.toLowerCase().contains(address.toLowerCase())) {
                return false;
            }
            if (nonNull(tags) && !tags.isEmpty()) {
                Tag[] tagsToCheck = tags.toArray(new Tag[0]);
                Tag[] currentTags = person.getTags().toArray(new Tag[0]);
                for (Tag tag:tagsToCheck) {
                    for (int i = 0; i <= currentTags.length; i++) {
                        if (i == currentTags.length) {
                            return false;
                        } else if (currentTags[i].tagName.toLowerCase().contains(tag.tagName.toLowerCase())) {
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

            PersonFilter otherPersonFilter = (PersonFilter) other;
            return isEqual(name, otherPersonFilter.getName())
                    && isEqual(phone, otherPersonFilter.getPhone())
                    && isEqual(email, otherPersonFilter.getEmail())
                    && isEqual(address, otherPersonFilter.getAddress())
                    && isEqual(tags, otherPersonFilter.getTags());
        }

        private boolean isEqual(Object first, Object second) {
            if (Objects.isNull(first)) {
                return Objects.isNull(second);
            } else if (Objects.isNull(second)) {
                return false;
            } else {
                return first.equals(second);
            }
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
