package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {
    public static final Module FIRST_MODULE = new Module("CS2030S");
    public static final Module SECOND_MODULE = new Module("CS2040S");
    public static final Module THIRD_MODULE = new Module("CS2100");
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withStudentNumber("A0203221J")
            .withTelegram("@bruh")
            .withModules(FIRST_MODULE)
            .withTutorials(TypicalTutorials.TUTORIAL_TUT1_MON9PM)
            .withTags("owesMoney", "friends").build();

    private TypicalModules() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all persons, modules, tutorials.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        ab.addTutorial(TypicalTutorials.TUTORIAL_TUT1_MON9PM);
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with modules only.
     */
    public static AddressBook getTypicalModuleAddressBook() {
        AddressBook ab = new AddressBook();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(FIRST_MODULE, SECOND_MODULE, THIRD_MODULE));
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(BENSON));
    }
}
