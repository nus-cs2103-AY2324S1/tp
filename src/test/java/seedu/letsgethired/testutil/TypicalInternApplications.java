package seedu.letsgethired.testutil;

import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_BYTEDANCE;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_JANE_STREET;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_CYCLE_SUMMER;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_CYCLE_WINTER;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_DEADLINE;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_BACK_END;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_FULL_STACK;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_STATUS_ACCEPTED;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_STATUS_REJECTED;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import seedu.letsgethired.commons.exceptions.DataLoadingException;
import seedu.letsgethired.model.ReadOnlyInternTracker;
import seedu.letsgethired.model.VersionedInternTracker;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.storage.JsonInternTrackerStorage;

/**
 * A utility class containing a list of {@code InternApplication} objects to be used in tests.
 */
public class TypicalInternApplications {

    public static final InternApplication JANE_STREET = new InternApplicationBuilder()
            .withCompany("Jane Street")
            .withStatus("Pending")
            .withCycle("Summer 2024")
            .withNotes("Jane Street is the leading market maker in the APAC region")
            .withRole("Full Stack Developer")
            .withDeadline("24 Oct 2023")
            .build();
    public static final InternApplication OPTIVER = new InternApplicationBuilder()
            .withCompany("Optiver")
            .withStatus("Pending")
            .withNotes("Optiver needs very good mental math skills")
            .withCycle("Summer 2023")
            .withRole("Data Engineering Intern")
            .withDeadline("24 Oct 2023")
            .build();
    public static final InternApplication GOOGLE = new InternApplicationBuilder()
            .withCompany("Google")
            .withRole("Full Stack Intern")
            .withNotes("Need to revise Rust before the Google interview")
            .withCycle("Summer 2021")
            .withStatus("Pending")
            .withDeadline("24 Oct 2023")
            .build();
    public static final InternApplication META = new InternApplicationBuilder()
            .withCompany("Meta")
            .withRole("Back End Intern")
            .withNotes("Need to revise Rust before the Meta interview")
            .withCycle("Off-cycle 2020")
            .withStatus("Pending")
            .withDeadline("24 Oct 2023")
            .build();
    public static final InternApplication BYTEDANCE = new InternApplicationBuilder()
            .withCompany("Bytedance")
            .withRole("Front End Intern")
            .withNotes("Bytedance requires back end developers to know the MERN stack")
            .withCycle("Summer 2021")
            .withStatus("Pending")
            .withDeadline("24 Oct 2023")
            .build();
    public static final InternApplication GRAB = new InternApplicationBuilder()
            .withCompany("Grab")
            .withRole("Web Dev Intern")
            .withNotes("Grab online assessment has a difficult mental math section")
            .withCycle("Summer 2021")
            .withStatus("Pending")
            .withDeadline("24 Oct 2023")
            .build();
    public static final InternApplication STRIPE = new InternApplicationBuilder()
            .withCompany("Stripe")
            .withRole("DevOps Intern")
            .withNotes("Stripe requires previous experience with SQL")
            .withCycle("Summer 2021")
            .withStatus("Pending")
            .withDeadline("24 Oct 2023")
            .build();

    // Manually added
    public static final InternApplication BOSCH = new InternApplicationBuilder().withCompany("Bosch")
            .withRole("AI Research")
            .withCycle("Summer 2021").withStatus("pending").build();
    public static final InternApplication SCHNEIDER = new InternApplicationBuilder().withCompany("Schneider")
            .withRole("IoT Engineer")
            .withCycle("Summer 2021").withStatus("accepted").build();

    // Manually added - Intern Application's details found in {@code CommandTestUtil}
    public static final InternApplication A = new InternApplicationBuilder()
            .withCompany(VALID_COMPANY_JANE_STREET)
            .withRole(VALID_ROLE_FULL_STACK)
            .withCycle(VALID_CYCLE_SUMMER)
            .withStatus(VALID_STATUS_ACCEPTED)
            .withDeadline(VALID_DEADLINE)
            .build();
    public static final InternApplication B = new InternApplicationBuilder()
            .withCompany(VALID_COMPANY_BYTEDANCE)
            .withRole(VALID_ROLE_BACK_END)
            .withCycle(VALID_CYCLE_WINTER)
            .withStatus(VALID_STATUS_REJECTED)
            .withDeadline(VALID_DEADLINE)
            .build();

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableInternTrackerTest");
    private static final Path TYPICAL_INTERN_APPLICATIONS_FILE =
            TEST_DATA_FOLDER.resolve("typicalInternApplicationInternTracker.json");

    private TypicalInternApplications() {
    } // prevents instantiation

    /**
     * Returns an {@code InternTracker} with all the typical intern applications.
     */
    public static VersionedInternTracker getTypicalInternTracker() {
        VersionedInternTracker ab = new VersionedInternTracker();
        for (InternApplication internApplication : getTypicalInternApplications()) {
            ab.addApplication(internApplication);
        }
        return ab;
    }

    public static List<InternApplication> getTypicalInternApplications() {
        JsonInternTrackerStorage jsonInternTrackerStorage =
                new JsonInternTrackerStorage(TYPICAL_INTERN_APPLICATIONS_FILE);



        try {
            Optional<ReadOnlyInternTracker> internTracker = jsonInternTrackerStorage.readInternTracker();
            return internTracker.get().getApplicationList();
        } catch (DataLoadingException dle) {
            // do nothing
        }

        return new ArrayList<>(Arrays.asList(JANE_STREET, OPTIVER, GOOGLE, META, BYTEDANCE, GRAB, STRIPE));
    }
}
