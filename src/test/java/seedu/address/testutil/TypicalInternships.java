package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATIONSTATUS_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATIONSTATUS_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTDATE_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTDATE_OPTIVER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.InternshipBook;
import seedu.address.model.internship.Internship;

/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternships {

    public static final Internship JANESTREET = new InternshipBuilder().withCompanyName(VALID_COMPANY_NAME_JANESTREET)
            .withRole(VALID_ROLE_JANESTREET)
            .withApplicationStatus(VALID_APPLICATIONSTATUS_JANESTREET)
            .withDuration(VALID_DURATION_JANESTREET)
            .withDeadline(VALID_DEADLINE_JANESTREET, VALID_STARTDATE_JANESTREET)
            .withRequirements(new String[]{VALID_REQUIREMENT_JANESTREET})
            .withStartDate(VALID_STARTDATE_JANESTREET).build();

    public static final Internship OPTIVER = new InternshipBuilder().withCompanyName(VALID_COMPANY_NAME_OPTIVER)
            .withRole(VALID_ROLE_OPTIVER)
            .withApplicationStatus(VALID_APPLICATIONSTATUS_OPTIVER)
            .withDuration(VALID_DURATION_OPTIVER)
            .withDeadline(VALID_DEADLINE_OPTIVER, VALID_STARTDATE_OPTIVER)
            .withRequirements(new String[]{VALID_REQUIREMENT_OPTIVER})
            .withStartDate(VALID_STARTDATE_OPTIVER).build();

    public static final Internship CITADEL = new InternshipBuilder().withCompanyName("Citadel")
            .withRole("Software Engineer")
            .withDuration("3")
            .withDeadline("01/12/2021", "07/05/2023")
            .withRequirements(new String[]{"4 years of experience"})
            .withStartDate("07/05/2023").build();

    public static final Internship GOVTECH = new InternshipBuilder().withCompanyName("Govtech")
            .withRole("UI Designer")
            .withDuration("1")
            .withDeadline("05/05/2020", "01/05/2021")
            .withRequirements(new String[]{"40 years of experience"})
            .withStartDate("01/05/2021").build();

    private TypicalInternships() {} // prevents instantiation

    /**
     * Returns an {@code InternshipBook} with all the typical internships.
     */
    public static InternshipBook getTypicalInternshipBook() {
        InternshipBook ib = new InternshipBook();
        for (Internship internship : getTypicalInternships()) {
            ib.createInternship(internship);
        }
        return ib;
    }

    public static List<Internship> getTypicalInternships() {
        return new ArrayList<>(Arrays.asList(OPTIVER, CITADEL));
    }
}
