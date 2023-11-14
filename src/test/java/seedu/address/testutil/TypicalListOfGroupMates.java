package seedu.address.testutil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * People that are in the same group
 */
public class TypicalListOfGroupMates {
    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withGroupList("CS2103T").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withGroupList("CS2103T").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withGroupList("CS2103T").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withGroupList("CS2103T").build();

    // prevent instantiation
    public TypicalListOfGroupMates() {
    }

    public static ObservableList<Person> getTypicalListOfGroupMates() {
        ObservableList<Person> groupMates = FXCollections.observableArrayList();
        groupMates.addAll(ALICE, BENSON, CARL, DANIEL);
        return groupMates;
    }
}
