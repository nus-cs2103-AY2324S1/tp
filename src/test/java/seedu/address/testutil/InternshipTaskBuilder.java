package seedu.address.testutil;

import static seedu.address.testutil.TypicalInternshipRoles.getTypicalInternshipRole2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.fields.IsoDate;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.Status;
import seedu.address.model.fields.TaskOutcome;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;


/**
 * A class to create new InternshipTask objects
 */
public class InternshipTaskBuilder {
    private final InternshipRole defaultRole = getTypicalInternshipRole2();
    private final String defaultTaskName = "Grind Leetcode";
    private final LocalDateTime defaultDate = LocalDateTime.parse("2024-04-01 23:59",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    private final boolean defaultStatus = false;
    private final Outcome defaultOutcome = Outcome.FOLLOW_UP;

    private InternshipRole role;
    private Name taskName;
    private IsoDate deadline;
    private Status status;
    private TaskOutcome outcome;
    private Set<Tag> tags;

    /**
     * Creates an InternshipTaskBuilder with default fields
     */
    public InternshipTaskBuilder() {
        role = defaultRole;
        taskName = new Name(defaultTaskName);
        deadline = new IsoDate(defaultDate);
        status = new Status(defaultStatus);
        outcome = new TaskOutcome(defaultOutcome);
        tags = new HashSet<>();
    }

    /**
     * Creates an InternshipTaskBuilder with specified role and other default fields
     * @param role {@code InternshipRole} to use
     */
    public InternshipTaskBuilder(InternshipRole role) {
        this.role = role;
        taskName = new Name(defaultTaskName);
        deadline = new IsoDate(defaultDate);
        status = new Status(defaultStatus);
        outcome = new TaskOutcome(defaultOutcome);
        tags = new HashSet<>();
    }

    /**
     * Creates an InternshipTaskBuilder with the given input
     * @param taskToCopy over to the builder
     */
    public InternshipTaskBuilder(InternshipTask taskToCopy) {
        role = taskToCopy.getInternshipRole();
        taskName = taskToCopy.getTaskName();
        deadline = taskToCopy.getDeadline();
        status = taskToCopy.getStatus();
        outcome = taskToCopy.getOutcome();
        tags = new HashSet<>();
    }

    /**
     * @param role that this task is related to
     * @return InternshipBuilder with the given name
     */
    public InternshipTaskBuilder withInternshipRole(InternshipRole role) {
        this.role = role;
        return this;
    }

    /**
     * @param name of the task
     * @return InternshipBuilder with the given name
     */
    public InternshipTaskBuilder withTaskName(String name) {
        this.taskName = new Name(name);
        return this;
    }

    /**
     * @param date of the task
     * @return InternshipBuilder with the given date
     */
    public InternshipTaskBuilder withDeadline(LocalDateTime date) {
        this.deadline = new IsoDate(date);
        return this;
    }

    /**
     * @param status of task
     * @return InternshipBuilder with the given status
     */
    public InternshipTaskBuilder withStatus(boolean status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * @param outcome of task
     * @return InternshipBuilder with the given outcome
     */
    public InternshipTaskBuilder withOutcome(Outcome outcome) {
        this.outcome = new TaskOutcome(outcome);
        return this;
    }

    /**
     * @param tags of task
     * @return InternshipBuilder with the given tags
     */
    public InternshipTaskBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }


    /**
     * @return an InternshipTask with the current fields
     */
    public InternshipTask build() {
        return new InternshipTask(role, taskName, deadline, status, outcome, tags);
    }

}
