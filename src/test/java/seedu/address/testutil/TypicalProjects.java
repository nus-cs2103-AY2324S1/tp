package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DEADLINE_ANDROIDAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DEADLINE_APPLEAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DEADLINE_GOOGLEAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_ANDROIDAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_APPLEAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_GOOGLEAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_ANDROIDAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_APPLEAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_GOOGLEAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_PRIORITY_ANDROIDAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_PRIORITY_APPLEAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_PRIORITY_GOOGLEAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_BACKEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.project.Project;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalProjects {

    public static final Project PROJECT_A = new ProjectBuilder().withName("Project A")
            .withDescription("This is Project A").withDeadline("20-12-2023,Design frontend,MEDIUM,0").build();
    public static final Project PROJECT_B = new ProjectBuilder().withName("Project B")
            .withDescription("This is Project B").withDeadline("22-12-2023,Design backend,HIGH,0").build();
    public static final Project PROJECT_C = new ProjectBuilder().withName("Project C")
            .withDescription("This is Project C").withDeadline("25-12-2023,Testing,LOW,1").build();

    public static final Project PROJECT_A_NO_SPACING = new ProjectBuilder().withName("ProjectA")
            .withDescription("This is Project A").withDeadline("20-12-2023,Design frontend,MEDIUM,0").build();

    private TypicalProjects() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical projects.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Project project : getTypicalProjects()) {
            ab.addProject(project);
        }
        return ab;
    }

    public static List<Project> getTypicalProjects() {
        return new ArrayList<>(Arrays.asList(PROJECT_A, PROJECT_B, PROJECT_C, PROJECT_A_NO_SPACING));
    }

    /**
     * Returns a list of typical project names.
     */
    public static List<String> getTypicalProjectNames() {
        return new ArrayList<>(Arrays.asList(VALID_PROJECT_NAME_APPLEAPP, VALID_PROJECT_NAME_GOOGLEAPP,
                VALID_PROJECT_NAME_ANDROIDAPP));
    }

    /**
     * Returns a list of typical project descriptions.
     */
    public static List<String> getTypicalProjectDescriptions() {
        return new ArrayList<>(Arrays.asList(VALID_PROJECT_DESCRIPTION_APPLEAPP, VALID_PROJECT_DESCRIPTION_GOOGLEAPP,
                VALID_PROJECT_DESCRIPTION_ANDROIDAPP));
    }

    /**
     * Returns a list of typical project deadlines.
     */
    public static List<String> getTypicalProjectDeadlines() {
        return new ArrayList<>(Arrays.asList(VALID_PROJECT_DEADLINE_APPLEAPP, VALID_PROJECT_DEADLINE_GOOGLEAPP,
                VALID_PROJECT_DEADLINE_ANDROIDAPP));
    }

    /**
     * Returns a list of typical project priorities.
     */
    public static List<String> getTypicalProjectPriorities() {
        return new ArrayList<>(Arrays.asList(VALID_PROJECT_PRIORITY_APPLEAPP, VALID_PROJECT_PRIORITY_GOOGLEAPP,
                VALID_PROJECT_PRIORITY_ANDROIDAPP));
    }

    /**
     * Returns a list of typical project tags.
     */
    public static List<String> getTypicalProjectTags() {
        return new ArrayList<>(Arrays.asList(VALID_PROJECT_TAG_UI, VALID_PROJECT_TAG_BACKEND));
    }
}
