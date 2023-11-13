package networkbook.logic.commands.add;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import networkbook.commons.core.index.Index;
import networkbook.commons.util.ToStringBuilder;
import networkbook.logic.Messages;
import networkbook.logic.commands.Command;
import networkbook.logic.commands.CommandResult;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.logic.parser.CliSyntax;
import networkbook.model.Model;
import networkbook.model.person.Course;
import networkbook.model.person.Email;
import networkbook.model.person.Graduation;
import networkbook.model.person.Link;
import networkbook.model.person.Name;
import networkbook.model.person.Person;
import networkbook.model.person.Phone;
import networkbook.model.person.Priority;
import networkbook.model.person.Specialisation;
import networkbook.model.person.Tag;
import networkbook.model.util.UniqueList;

/**
 * Adds new information about a contact.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds new information about a contact. \n"
            + "Parameters: [LIST INDEX OF CONTACT] "
            + "[" + CliSyntax.PREFIX_PHONE + " PHONE] "
            + "[" + CliSyntax.PREFIX_EMAIL + " EMAIL] "
            + "[" + CliSyntax.PREFIX_LINK + " LINK] "
            + "[" + CliSyntax.PREFIX_GRADUATION + " GRADUATION DATE] "
            + "[" + CliSyntax.PREFIX_COURSE + " COURSE OF STUDY] "
            + "[" + CliSyntax.PREFIX_SPECIALISATION + " SPECIALISATION] "
            + "[" + CliSyntax.PREFIX_TAG + " TAG] "
            + "[" + CliSyntax.PREFIX_PRIORITY + " PRIORITY]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + CliSyntax.PREFIX_PHONE + " 98765432 "
            + CliSyntax.PREFIX_EMAIL + " johnd@example.com "
            + CliSyntax.PREFIX_TAG + " friends "
            + CliSyntax.PREFIX_TAG + " owesMoney";

    public static final String MESSAGE_NO_INFO = "At least one field to add must be provided.";
    public static final String MESSAGE_MULTIPLE_UNIQUE_FIELD = "Some fields provided cannot have multiple values.\n"
            + "Please use the 'edit' command instead.";
    public static final String MESSAGE_ADD_INFO_SUCCESS = "Added information to this contact: %1$s";

    private final Index index;
    private final AddPersonDescriptor addPersonDescriptor;

    /**
     * Creates an AddCommand to add information about the contact at {@code Index}
     * This command is data-changing, so parent constructor is called with true.
     *
     * @param index of the contact to add information about
     * @param addPersonDescriptor details to add to the contact
     */
    public AddCommand(Index index, AddPersonDescriptor addPersonDescriptor) {
        super(true);
        requireNonNull(index);
        requireNonNull(addPersonDescriptor);

        this.index = index;
        this.addPersonDescriptor = addPersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Model should not be null";
        List<Person> lastShownList = model.getDisplayedPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToAddInfo = lastShownList.get(index.getZeroBased());
        Person personAfterAddingInfo = addInfoToPerson(personToAddInfo, addPersonDescriptor);
        model.setItem(personToAddInfo, personAfterAddingInfo);
        return new CommandResult(String.format(MESSAGE_ADD_INFO_SUCCESS, Messages.format(personAfterAddingInfo)));

    }

    /**
     * Adds details from {@code addPersonDescriptor} and returns the {@code Person} after adding details.
     */
    private Person addInfoToPerson(Person personToAddInfo, AddPersonDescriptor addPersonDescriptor)
            throws CommandException {
        assert personToAddInfo != null;
        Name currentName = personToAddInfo.getName(); // name cannot be added
        UniqueList<Phone> updatedPhones = addPhones(personToAddInfo, addPersonDescriptor);
        UniqueList<Email> updatedEmails = addEmails(personToAddInfo, addPersonDescriptor);
        UniqueList<Link> updatedLinks = addLinks(personToAddInfo, addPersonDescriptor);
        Graduation updatedGraduatingYear = addGraduation(personToAddInfo, addPersonDescriptor);
        UniqueList<Course> updatedCourses = addCourses(personToAddInfo, addPersonDescriptor);
        UniqueList<Specialisation> updatedSpecialisations = addSpecialisations(personToAddInfo, addPersonDescriptor);
        UniqueList<Tag> updatedTags = addTags(personToAddInfo, addPersonDescriptor);
        Priority updatedPriority = addPriority(personToAddInfo, addPersonDescriptor);

        return new Person(currentName, updatedPhones, updatedEmails, updatedLinks, updatedGraduatingYear,
                updatedCourses, updatedSpecialisations, updatedTags, updatedPriority);
    }

    private UniqueList<Phone> addPhones(Person personToAddInfo, AddPersonDescriptor addPersonDescriptor) {
        UniqueList<Phone> phones = personToAddInfo.getPhones();
        addPersonDescriptor.getPhones().ifPresent(phones::addAllFromList);
        return phones;
    }
    private UniqueList<Email> addEmails(Person personToAddInfo, AddPersonDescriptor addPersonDescriptor) {
        UniqueList<Email> emails = personToAddInfo.getEmails();
        addPersonDescriptor.getEmails().ifPresent(emails::addAllFromList);
        return emails;
    }
    private UniqueList<Link> addLinks(Person personToAddInfo, AddPersonDescriptor addPersonDescriptor) {
        UniqueList<Link> links = personToAddInfo.getLinks();
        addPersonDescriptor.getLinks().ifPresent(links::addAllFromList);
        return links;
    }
    private Graduation addGraduation(Person personToAddInfo, AddPersonDescriptor addPersonDescriptor)
            throws CommandException {
        Optional<Graduation> oldGraduation = personToAddInfo.getGraduation();
        Optional<Graduation> newGraduation = addPersonDescriptor.getGraduation();
        if (oldGraduation.isPresent() && newGraduation.isPresent()) {
            throw new CommandException(MESSAGE_MULTIPLE_UNIQUE_FIELD);
        }
        return newGraduation.orElse(oldGraduation.orElse(null));
    }
    private UniqueList<Course> addCourses(Person personToAddInfo, AddPersonDescriptor addPersonDescriptor) {
        UniqueList<Course> courses = personToAddInfo.getCourses();
        addPersonDescriptor.getCourses().ifPresent(courses::addAllFromList);
        return courses;
    }
    private UniqueList<Specialisation> addSpecialisations(Person personToAddInfo,
                                                           AddPersonDescriptor addPersonDescriptor) {
        UniqueList<Specialisation> specs = personToAddInfo.getSpecialisations();
        addPersonDescriptor.getSpecialisations().ifPresent(specs::addAllFromList);
        return specs;

    }
    private UniqueList<Tag> addTags(Person personToAddInfo, AddPersonDescriptor addPersonDescriptor) {
        UniqueList<Tag> tags = personToAddInfo.getTags();
        addPersonDescriptor.getTags().ifPresent(tags::addAllFromList);
        return tags;
    }

    private Priority addPriority(Person personToAddInfo, AddPersonDescriptor addPersonDescriptor)
            throws CommandException {
        Optional<Priority> oldPriority = personToAddInfo.getPriority();
        Optional<Priority> newPriority = addPersonDescriptor.getPriority();
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
                && addPersonDescriptor.equals(otherCreateCommand.addPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("addPersonDescriptor", addPersonDescriptor)
                .toString();
    }

}
