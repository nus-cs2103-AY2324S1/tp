package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.group.tasks.TaskList;
import seedu.address.model.person.Person;
import seedu.address.model.tutorial.Tutorial;

/**
 * Jackson-friendly version of {@link Group}.
 */
public class JsonAdaptedGroup {

    public static final String INVALID_NUMBER_MESSAGE = "Group number is invalid!";

    private final int number;
    private final JsonAdaptedTutorial tutorial;
    private final List<JsonAdaptedPerson> members = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>(); // add a list of JsonAdaptedTask

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given group details.
     */
    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("number") int number,
                            @JsonProperty("tutorial") JsonAdaptedTutorial tutorial,
                            @JsonProperty("members") List<JsonAdaptedPerson> members,
                            @JsonProperty("tasks") List<JsonAdaptedTask> tasks) { // add tasks parameter

        this.number = number;
        this.tutorial = tutorial;
        if (members != null) {
            this.members.addAll(members);
        }
        if (tasks != null) {
            this.tasks.addAll(tasks);
        }
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        number = source.getNumber();
        tutorial = new JsonAdaptedTutorial(source.getTutorial());
        members.addAll(source.getMembers().stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toList()));
        tasks.addAll(source.getTasks().getTaskList().stream()
            .map(JsonAdaptedTask::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted social media object into the model's {@code Group} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted group.
     */
    public Group toModelType() throws IllegalValueException {
        final List<Person> groupMembers = new ArrayList<>();
        final TaskList groupTasks = new TaskList();

        for (JsonAdaptedPerson member : members) {
            groupMembers.add(member.toModelType());
        }
        for (JsonAdaptedTask task : tasks) {
            groupTasks.addTask(task.toModelType());
        }

        if (number < 1) {
            throw new IllegalValueException(INVALID_NUMBER_MESSAGE);
        }

        if (tutorial == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Tutorial.class.getSimpleName()));
        }
        if (!Tutorial.isValidTutorial(tutorial.getTutorial())) {
            throw new IllegalValueException(Tutorial.MESSAGE_CONSTRAINTS);
        }
        final Tutorial modelTutorial = tutorial.toModelType();

        final Set<Person> modelMembers = new HashSet<>(groupMembers);

        return new Group(number, modelTutorial, modelMembers, groupTasks);
    }
}
