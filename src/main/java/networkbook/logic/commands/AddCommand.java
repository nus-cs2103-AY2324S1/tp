package networkbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static networkbook.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import networkbook.commons.core.index.Index;
import networkbook.commons.util.ToStringBuilder;
import networkbook.logic.Messages;
import networkbook.logic.commands.EditCommand.EditPersonDescriptor;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.logic.parser.CliSyntax;
import networkbook.model.Model;
import networkbook.model.person.Course;
import networkbook.model.person.Email;
import networkbook.model.person.GraduatingYear;
import networkbook.model.person.Link;
import networkbook.model.person.Name;
import networkbook.model.person.Person;
import networkbook.model.person.Phone;
import networkbook.model.person.Priority;
import networkbook.model.person.Specialisation;
import networkbook.model.tag.Tag;
import networkbook.model.util.UniqueList;

/**
 * Adds new information about a contact.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds new information about a contact. "
            + "Parameters: "
            + "[" + CliSyntax.PREFIX_PHONE + " PHONE] "
            + "[" + CliSyntax.PREFIX_EMAIL + " EMAIL] "
            + "[" + CliSyntax.PREFIX_LINK + "LINK] "
            + "[" + CliSyntax.PREFIX_GRADUATING_YEAR + "GRADUATING YEAR] "
            + "[" + CliSyntax.PREFIX_COURSE + "COURSE OF STUDY] "
            + "[" + CliSyntax.PREFIX_SPECIALISATION + "SPECIALISATION] "
            + "[" + CliSyntax.PREFIX_TAG + " TAG] "
            + "[" + CliSyntax.PREFIX_PRIORITY + " PRIORITY]...\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_PHONE + " 98765432 "
            + CliSyntax.PREFIX_EMAIL + " johnd@example.com "
            + CliSyntax.PREFIX_TAG + " friends "
            + CliSyntax.PREFIX_TAG + " owesMoney";

    public static final String MESSAGE_NO_INFO = "At least one field to add must be provided.";
    public static final String MESSAGE_MULTIPLE_NAMES = "Contact cannot have multiple names.\n"
            + "Please use the 'update' command instead.";
    public static final String MESSAGE_MULTIPLE_UNIQUE_FIELD = "Some fields provided cannot have multiple values.\n"
            + "Please use the 'update' command instead.";
    public static final String MESSAGE_ADD_INFO_SUCCESS = "Added information to this contact: %1$s";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * Creates an AddCommand to add information about the contact at {@code Index}
     *
     * @param index of the contact to add information about
     * @param editPersonDescriptor details to add to the contact
     */
    public AddCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = editPersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToAddInfo = lastShownList.get(index.getZeroBased());
        if (!Objects.isNull(editPersonDescriptor.getName().orElse(null))) {
            throw new CommandException(MESSAGE_MULTIPLE_NAMES);
        }
        Person personAfterAddingInfo = addInfoToPerson(personToAddInfo, editPersonDescriptor);
        model.setItem(personToAddInfo, personAfterAddingInfo);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_INFO_SUCCESS, Messages.format(personAfterAddingInfo)));
    }

    /**
     * Adds details from {@code editPersonDescriptor} and returns the {@code Person} after adding details.
     */
    private Person addInfoToPerson(Person personToAddInfo, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        assert personToAddInfo != null;
        Name currentName = personToAddInfo.getName(); // name cannot be added
        UniqueList<Phone> updatedPhones = addPhones(personToAddInfo, editPersonDescriptor);
        UniqueList<Email> updatedEmails = addEmails(personToAddInfo, editPersonDescriptor);
        UniqueList<Link> updatedLinks = addLinks(personToAddInfo, editPersonDescriptor);
        GraduatingYear updatedGraduatingYear = addGraduatingYear(personToAddInfo, editPersonDescriptor);
        Course updatedCourse = addCourse(personToAddInfo, editPersonDescriptor);
        UniqueList<Specialisation> updatedSpecialisations = addSpecialisations(personToAddInfo, editPersonDescriptor);
        UniqueList<Tag> updatedTags = addTags(personToAddInfo, editPersonDescriptor);
        Priority updatedPriority = addPriority(personToAddInfo, editPersonDescriptor);

        return new Person(currentName, updatedPhones, updatedEmails, updatedLinks, updatedGraduatingYear,
                updatedCourse, updatedSpecialisations, updatedTags, updatedPriority);
    }

    // TODO: for non-unique fields, change respective model to use list and append to the list
    // TODO: now it just replaces the old value
    private UniqueList<Phone> addPhones(Person personToAddInfo, EditPersonDescriptor editPersonDescriptor) {
        UniqueList<Phone> phones = personToAddInfo.getPhones();
        editPersonDescriptor.getPhones().ifPresent(phones::addAllFromList);
        return phones;
    }
    private UniqueList<Email> addEmails(Person personToAddInfo, EditPersonDescriptor editPersonDescriptor) {
        UniqueList<Email> emails = personToAddInfo.getEmails();
        editPersonDescriptor.getEmails().ifPresent(emails::addAllFromList);
        return emails;
    }
    private UniqueList<Link> addLinks(Person personToAddInfo, EditPersonDescriptor editPersonDescriptor) {
        UniqueList<Link> links = personToAddInfo.getLinks();
        editPersonDescriptor.getLinks().ifPresent(links::addAllFromList);
        return links;
    }
    private GraduatingYear addGraduatingYear(Person personToAddInfo, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        Optional<GraduatingYear> oldGraduatingYear = personToAddInfo.getGraduatingYear();
        Optional<GraduatingYear> newGraduatingYear = editPersonDescriptor.getGraduatingYear();
        if (oldGraduatingYear.isPresent() && newGraduatingYear.isPresent()) {
            throw new CommandException(MESSAGE_MULTIPLE_UNIQUE_FIELD);
        }
        return newGraduatingYear.orElse(oldGraduatingYear.orElse(null));
    }
    private Course addCourse(Person personToAddInfo, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        Optional<Course> oldCourse = personToAddInfo.getCourse();
        Optional<Course> newCourse = editPersonDescriptor.getCourse();
        if (oldCourse.isPresent() && newCourse.isPresent()) {
            throw new CommandException(MESSAGE_MULTIPLE_UNIQUE_FIELD);
        }
        return newCourse.orElse(oldCourse.orElse(null));
    }
    private UniqueList<Specialisation> addSpecialisations(Person personToAddInfo,
                                                           EditPersonDescriptor editPersonDescriptor) {
        UniqueList<Specialisation> specs = personToAddInfo.getSpecialisations();
        editPersonDescriptor.getSpecialisations().ifPresent(specs::addAllFromList);
        return specs;

    }
    private UniqueList<Tag> addTags(Person personToAddInfo, EditPersonDescriptor editPersonDescriptor) {
        UniqueList<Tag> tags = personToAddInfo.getTags();
        editPersonDescriptor.getTags().ifPresent(tags::addAllFromList);
        return tags;
    }

    private Priority addPriority(Person personToAddInfo, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        Optional<Priority> oldPriority = personToAddInfo.getPriority();
        Optional<Priority> newPriority = editPersonDescriptor.getPriority();
        if (oldPriority.isPresent() && newPriority.isPresent()) {
            throw new CommandException(MESSAGE_MULTIPLE_UNIQUE_FIELD);
        }
        return newPriority.orElse(oldPriority.orElse(null));
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherCreateCommand = (AddCommand) other;
        return index.equals(otherCreateCommand.index)
                && editPersonDescriptor.equals(otherCreateCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }
}
