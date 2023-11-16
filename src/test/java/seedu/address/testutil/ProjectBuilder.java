package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.commons.Name;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Description;
import seedu.address.model.project.Project;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {
    public static final String DEFAULT_NAME = "AppleApp";
    public static final String DEFAULT_DESCRIPTION = "Developing the AppleApp";
    public static final String DEFAULT_DEADLINE = "31-12-2019,Develop front end interface,HIGH,0";

    private Name name;
    private Description description;
    private List<Deadline> deadlineList;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        deadlineList = new ArrayList<>();
        deadlineList.add(new Deadline(DEFAULT_DEADLINE, 1));
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     * @param projectToCopy
     */
    public ProjectBuilder(Project projectToCopy) {
        name = projectToCopy.getProjectName();
        description = projectToCopy.getProjectDescription();
        deadlineList = projectToCopy.getProjectDeadlines();
    }

    /** Sets the {@code Name} of the {@code Project} that we are building. */
    public ProjectBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /** Sets the {@code Description} of the {@code Project} that we are building. */
    public ProjectBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /** Sets the {@code Deadline} of the {@code Project} that we are building. */
    public ProjectBuilder withDeadline(String deadline) {
        this.deadlineList = new ArrayList<>();
        this.deadlineList.add(new Deadline(deadline, 1));
        return this;
    }

    /** Sets the {@code Deadline} of the {@code Project} that we are building. */
    public ProjectBuilder withDeadlines(List<Deadline> deadlines) {
        this.deadlineList = new ArrayList<>();
        int index = 1;
        for (Deadline d : deadlines) {
            this.deadlineList.add(new Deadline(d.getStringRepresentation(), index));
            index += 1;
        }
        return this;
    }

    /** Sets the {@code Deadline} of the {@code Project} that we are building. */
    public ProjectBuilder withDeadlinesString(List<String> deadlines) {
        this.deadlineList = new ArrayList<>();
        int index = 1;
        for (String d : deadlines) {
            this.deadlineList.add(new Deadline(d, index));
            index += 1;
        }
        return this;
    }

    /** Builds a Project object. */
    public Project build() {
        return new Project(name, description, deadlineList);
    }
}
