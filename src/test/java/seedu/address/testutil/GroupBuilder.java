package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.TaskException;
import seedu.address.model.group.tasks.Task;
import seedu.address.model.group.tasks.TaskInitializer;
import seedu.address.model.group.tasks.TaskList;
import seedu.address.model.person.Person;
import seedu.address.model.tutorial.Tutorial;

/**
 * A utility class to help with building Group objects.
 */
public class GroupBuilder {

    public static final String DEFAULT_NUMBER = "1";
    public static final String DEFAULT_TUTORIAL = "01";

    private int number;
    private Tutorial tutorial;
    private Set<Person> members;
    private TaskList tasks;

    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder() {
        number = Integer.parseInt(DEFAULT_NUMBER);
        tutorial = new Tutorial(DEFAULT_TUTORIAL);
        members = new HashSet<>();
        try {
            tasks = TaskInitializer.initializeTasks();
        } catch (TaskException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the GroupBuilder with the data of {@code groupToCopy}.
     */
    public GroupBuilder(Group groupToCopy) {
        number = groupToCopy.getNumber();
        tutorial = groupToCopy.getTutorial();
        members = new HashSet<>(groupToCopy.getMembers());
        tasks = new TaskList(groupToCopy.getTasks().getTasks());
    }

    /**
     * Sets the {@code Number} of the {@code Group} that we are building.
     */
    public GroupBuilder withNumber(String number) {
        this.number = Integer.parseInt(number);
        return this;
    }

    /**
     * Sets the {@code Tutorial} of the {@code Group} that we are building.
     */
    public GroupBuilder withTutorial(String tutorial) {
        this.tutorial = new Tutorial(tutorial);
        return this;
    }

    /**
     * Parses the {@code members} into a {@code Set<Person>} and set it to the {@code Group} that
     * we are building.
     */
    public GroupBuilder withMembers(Person... members) {
        this.members = new HashSet<>();
        this.members.addAll(Arrays.stream(members).collect(Collectors.toSet()));
        return this;
    }

    /**
     * Parses the {@code tasks} into a {@code List<Task>} and set it to the {@code Group} that
     * we are building.
     */
    public GroupBuilder withTasks(Task... tasks) {
        this.tasks = new TaskList(Arrays.stream(tasks).collect(Collectors.toList()));
        return this;
    }

    public Group build() {
        return new Group(number, tutorial, members, tasks);
    }
}
