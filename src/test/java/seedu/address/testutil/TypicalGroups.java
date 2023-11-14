package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.group.tasks.TaskList;
import seedu.address.model.person.Person;
import seedu.address.model.tutorial.Tutorial;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {

    public static final Group GROUP1 = new Group(1, new Tutorial("01"),
            Set.of(ALICE, BENSON, CARL), new TaskList());
    public static final Group GROUP2 = new Group(2, new Tutorial("02"),
            Set.of(DANIEL, ELLE, FIONA), new TaskList());
    public static final Group GROUP3 = new Group(3, new Tutorial("03"),
            new HashSet<>(Set.of(GEORGE)), new TaskList());
    public static final Group GROUP4 = new Group(4, new Tutorial("02"),
            Set.of(HOON), new TaskList());

    // test groups for CheckCommand
    public static final Group GROUP5 = new Group(5, new Tutorial("01"), Collections.emptySet(), new TaskList());
    public static final Group GROUP6 = new Group(6, new Tutorial("01"), Set.of(BENSON, CARL), new TaskList());
    public static final Group GROUP7 = new Group(7, new Tutorial("01"), Set.of(BENSON, HOON), new TaskList());
    public static final Group GROUP8 = new Group(8, new Tutorial("01"), Set.of(BENSON, IDA), new TaskList());
    public static final Group GROUP9 = new Group(9, new Tutorial("01"),
            Set.of(new PersonBuilder().withEmail("first@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("second@u.nus.edu").withTutorials("01").build()), new TaskList());
    public static final Group GROUP10 = new Group(10, new Tutorial("01"),
            Set.of(new PersonBuilder().withEmail("first@u.nus.edu").withTutorials("02").build(),
                    new PersonBuilder().withEmail("second@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("third@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("fourth@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("fifth@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("sixth@u.nus.edu").withTutorials("01").build()), new TaskList());
    public static final Group GROUP11 = new Group(11, new Tutorial("01"),
            Set.of(new PersonBuilder().withEmail("first@u.nus.edu").withTutorials("02").build(),
                    new PersonBuilder().withEmail("second@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("third@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("fourth@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("fifth@u.nus.edu").withTutorials("01").build()), new TaskList());
    public static final Group GROUP12 = new Group(12, new Tutorial("01"),
            Set.of(new PersonBuilder().withEmail("first@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("second@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("third@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("fourth@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("fifth@u.nus.edu").withTutorials("01").build()), new TaskList());
    public static final Group GROUP13 = new Group(13, new Tutorial("01"),
            Set.of(new PersonBuilder().withEmail("first@u.nus.edu").withTutorials("01").withNationality("foreigner")
                            .build(),
                    new PersonBuilder().withEmail("second@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("third@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("fourth@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("fifth@u.nus.edu").withTutorials("01").build()), new TaskList());
    public static final Group GROUP14 = new Group(14, new Tutorial("01"),
            Set.of(new PersonBuilder().withEmail("first@u.nus.edu").withTutorials("01").withGender("m").build(),
                    new PersonBuilder().withEmail("second@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("third@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("fourth@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("fifth@u.nus.edu").withTutorials("01").build()), new TaskList());
    public static final Group GROUP15 = new Group(15, new Tutorial("01"),
            Set.of(new PersonBuilder().withEmail("first@u.nus.edu").withTutorials("01").withGender("m").build(),
                    new PersonBuilder().withEmail("second@u.nus.edu").withTutorials("01").withNationality("foreigner")
                            .build(),
                    new PersonBuilder().withEmail("third@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("fourth@u.nus.edu").withTutorials("01").build(),
                    new PersonBuilder().withEmail("fifth@u.nus.edu").withTutorials("01").build()), new TaskList());

    private TypicalGroups() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical groups.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = TypicalPersons.getTypicalAddressBook();
        for (Person person : ab.getPersonList()) {
            ab.setPerson(person, new PersonBuilder(person).build());
        }
        for (Group group : getTypicalGroups()) {
            ab.addGroup(new GroupBuilder(group).build());
        }
        return ab;
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(GROUP1, GROUP2, GROUP3, GROUP4, GROUP5, GROUP6, GROUP7, GROUP8, GROUP9,
                GROUP10, GROUP11, GROUP12, GROUP13, GROUP14, GROUP15));
    }
}
