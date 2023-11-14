package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task AGENDA = new TaskBuilder()
            .withTitle("Prepare Agenda")
            .withNote("To book venue")
            .withStatus(Status.STATUS_NOT_DONE)
            .withTags("location")
            .build();

    public static final Task BUDGET = new TaskBuilder()
            .withTitle("Prepare Budget")
            .withNote("For CS2102")
            .withStatus(Status.STATUS_DONE)
            .withTags("finance", "class")
            .build();

    public static final Task CATERING = new TaskBuilder()
            .withTitle("Book Catering")
            .withNote("For CS2101")
            .withStatus(Status.STATUS_NOT_DONE)
            .withTags("caterer", "class")
            .build();

    public static final Task DRAFT = new TaskBuilder()
            .withTitle("Send Budget and Draft")
            .withNote("For CS2100")
            .withStatus(Status.STATUS_NOT_DONE)
            .withTags("finance", "class")
            .build();

    public static final Task ENTERTAINMENT = new TaskBuilder()
            .withTitle("Book Entertainment")
            .withNote("For CS1101")
            .withStatus(Status.STATUS_DONE)
            .withTags("class")
            .build();

    public static final Task FUNDING = new TaskBuilder()
            .withTitle("Prepare Funding")
            .withNote("For MA2001")
            .withStatus(Status.STATUS_DONE)
            .withTags("finance", "class")
            .build();

    public static final Task GUESTLIST = new TaskBuilder()
            .withTitle("Prepare Guestlist")
            .withNote("For CS2103T")
            .withStatus(Status.STATUS_DONE)
            .withTags("admin", "class")
            .build();

    public static final Task HOSPITALITY = new TaskBuilder()
            .withTitle("Ensure Hospitality")
            .withNote("For CS2103T")
            .withStatus(Status.STATUS_DONE)
            .withTags("admin", "class")
            .build();

    public static final Task INVITATION = new TaskBuilder()
            .withTitle("Send Invitations and Guestlist")
            .withNote("For CS2103T")
            .withStatus(Status.STATUS_NOT_DONE)
            .withTags("admin", "class")
            .build();

    public static final Task JOURNAL = new TaskBuilder()
            .withTitle("Journal Review")
            .withNote("Review journals for Syndicate A updates")
            .withStatus(Status.STATUS_NOT_DONE)
            .withTags("colleague", "syndicateA")
            .build();

    public static final Task KPI = new TaskBuilder()
            .withTitle("KPI Setting")
            .withNote("Set KPIs for Syndicate B this year")
            .withStatus(Status.STATUS_NOT_DONE)
            .withTags("colleague", "syndicateB")
            .build();

    public static final Task LECTURE = new TaskBuilder()
            .withTitle("Lecture Preparation")
            .withNote("Redo content for syndicateA's lecture next month")
            .withStatus(Status.STATUS_NOT_DONE)
            .withTags("colleague", "syndicateA")
            .build();

    // Keywords
    public static final String KEYWORD_MATCHING_BOOK = "Book";
    public static final String KEYWORD_MATCHING_PREPARE = "Prepare";
    public static final String KEYWORD_MATCHING_SEND = "Send";
    public static final String KEYWORD_MATCHING_FOR = "For";
    public static final String KEYWORD_MATCHING_CS2103T = "CS2103T";

    private TypicalTasks() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(
                AGENDA, BUDGET, CATERING, DRAFT, ENTERTAINMENT, FUNDING, GUESTLIST, HOSPITALITY, INVITATION,
                JOURNAL, KPI, LECTURE));
    }
}
