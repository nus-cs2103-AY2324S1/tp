package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.person.Contact;
import seedu.address.model.person.Course;
import seedu.address.model.person.Role;
import seedu.address.model.person.Tutorial;



/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {
    /**
     * Message displayed upon successful execution of the ListCommand if there are no persons.
     */
    public static final String MESSAGE_SUCCESS = "You have 0 persons in your list\n";

    /**
     * Keyword to trigger the 'list' command.
     */
    public static final String COMMAND_WORD = "list";

    /**
     * Usage instructions for the 'list' command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display all the users stored\n"
            + "Example: " + COMMAND_WORD;
    private static final String enter = "\n   ";
    private static int numPeople = 0;
    private static String s = "";
    private ArrayList<Object> nameList = new ArrayList<>();
    private ArrayList<Set<Role>> roleList = new ArrayList<>();
    private ArrayList<Set<Contact>> contactList = new ArrayList<>();
    private ArrayList<Set<Course>> courseList = new ArrayList<>();
    private ArrayList<Set<Tutorial>> tutorialList = new ArrayList<>();

    /**
     * Converts a set of roles into a formatted string.
     *
     * @param roleList A set of roles to be converted.
     * @return A formatted string representation of the roles, e.g., "Role: Student, TA".
     */
    public static String roleToString(Set<Role> roleList) {
        String begin = "Role: ";
        StringBuilder lst = new StringBuilder(begin);
        roleList.forEach(role -> lst.append(role.toString()).append(", "));
        if (lst.length() > begin.length()) {
            lst.setLength(lst.length() - 2);
        }
        return lst.toString();
    }

    /**
     * Converts a set of contacts into a formatted string.
     *
     * @param contactList A set of contacts to be converted.
     * @return A formatted string representation of the contacts, e.g., "Contact: [email@example.com], [123456789]".
     */
    public static String contactToString(Set<Contact> contactList) {
        String begin = "Contact: ";
        StringBuilder lst = new StringBuilder(begin);
        contactList.forEach(contact -> lst.append(contact.toString()).append(", "));
        if (lst.length() > begin.length()) {
            lst.setLength(lst.length() - 2);
        }
        return lst.toString();
    }

    /**
     * Converts a set of courses into a formatted string.
     *
     * @param courseList A set of courses to be converted.
     * @return A formatted string representation of the courses, e.g., "Courses: CS2100, CS2103T".
     */
    public static String courseToString(Set<Course> courseList) {
        String begin = "Courses: ";
        StringBuilder lst = new StringBuilder(begin);
        courseList.forEach(course -> lst.append(course.toString()).append(", "));
        if (lst.length() > begin.length()) {
            lst.setLength(lst.length() - 2);
        }
        return lst.toString();
    }

    /**
     * Converts a set of tutorials into a formatted string.
     *
     * @param tutorialList A set of tutorials to be converted.
     * @return A formatted string representation of the tutorials, e.g., "Tutorials: CS2100/T32, CS2103T/F08".
     */
    public static String tutorialToString(Set<Tutorial> tutorialList) {
        String begin = "Tutorials: ";
        StringBuilder lst = new StringBuilder(begin);
        tutorialList.forEach(tutorial-> lst.append(tutorial.toString()).append(", "));
        if (lst.length() > begin.length()) {
            lst.setLength(lst.length() - 2);
        }
        return lst.toString();
    }

    /**
     * Executes the ListCommand to display the list of all persons.
     *
     * @param model The model this command should operate on.
     * @return The CommandResult displaying the list of all persons in the address book.
     */
    @Override
    public CommandResult execute(Model model) {
        String peopleList = "";
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        numPeople = model.getFilteredPersonList().size();
        s = numPeople == 1 ? "" : "s";
        model.getFilteredPersonList().forEach((people) -> {
            nameList.add(people.getName());
            roleList.add(people.getRoles());
            contactList.add(people.getContacts());
            courseList.add(people.getCourses());
            tutorialList.add(people.getTutorials());
        });;

        for (int i = 0; i < nameList.size(); i++) {
            peopleList += (i + 1) + ". Name: " + nameList.get(i) + enter
                    + roleToString(roleList.get(i)) + enter + contactToString(contactList.get(i)) + enter
                    + courseToString(courseList.get(i)) + enter + tutorialToString(tutorialList.get(i)) + "\n";
        }
        return new CommandResult("You have " + numPeople + " person" + s + " in your list\n" + peopleList);
    }
}
