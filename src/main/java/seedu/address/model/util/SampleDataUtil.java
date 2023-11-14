package seedu.address.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.fields.ApplicationOutcome;
import seedu.address.model.fields.Cycle;
import seedu.address.model.fields.Description;
import seedu.address.model.fields.IsoDate;
import seedu.address.model.fields.Location;
import seedu.address.model.fields.NoDate;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.Pay;
import seedu.address.model.fields.Role;
import seedu.address.model.fields.Status;
import seedu.address.model.fields.TaskOutcome;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static Assignment[] getSampleAssignments() {
        return new Assignment[] {
            new Assignment(new seedu.address.model.fields.Name("Task1"),
                    new IsoDate(LocalDateTime.parse("2023-10-18 23:59",
                            DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                    new Status(true), new Description("description"),
                    new IsoDate(LocalDateTime.parse("2023-11-11 11:11",
                            DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                    Set.of(new Tag("cs2100"))),
            new Assignment(new seedu.address.model.fields.Name("Task2"),
                    new IsoDate(LocalDateTime.parse("2023-10-31 23:59",
                            DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                    new Status(true), new Description("description"),
                    new NoDate(), Set.of(new Tag("cs2103"))),
        };
    }

    private static InternshipRole getSampleRole1() {
        return new InternshipRole(new seedu.address.model.fields.Name("Google"),
                new Role("SWE"), new Cycle("Summer"), new Description(""),
                new Pay(null), new ApplicationOutcome(Outcome.FOLLOW_UP),
                new Location(""), new HashSet<>());
    }

    private static InternshipRole getSampleRole2() {
        return new InternshipRole(new seedu.address.model.fields.Name("Tiktok"),
                new Role("SWE"), new Cycle("Summer"), new Description(""),
                new Pay(null), new ApplicationOutcome(Outcome.FOLLOW_UP),
                new Location(""), new HashSet<>());
    }

    private static InternshipRole[] getSampleInternshipRoles() {
        return new InternshipRole[] { getSampleRole1(), getSampleRole2() };
    }

    private static InternshipTask[] getSampleInternshipTasks() {
        return new InternshipTask[] { new InternshipTask(getSampleRole1(), new seedu.address.model.fields.Name("OA"),
                new IsoDate(LocalDateTime.parse("2023-11-11 23:59",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(false), new TaskOutcome(Outcome.AWAITING), new HashSet<>()) };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();

        for (Assignment assignment : getSampleAssignments()) {
            sampleAb.addAssignment(assignment);
        }

        for (InternshipRole role : getSampleInternshipRoles()) {
            sampleAb.addInternshipRole(role);
        }

        for (InternshipTask task : getSampleInternshipTasks()) {
            sampleAb.addInternshipTask(task);
        }

        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
