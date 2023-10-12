package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.employee.Employee;

/**
 * A utility class containing a list of {@code Employee} objects to be used in tests.
 */
public class TypicalEmployees {

    public static final Employee ALICE = new EmployeeBuilder().withName("Alice Pauline")
                .withPosition("Manager")
                .withId("EID1234-5678")
                .withEmail("alice@example.com")
                .withPhone("94351253")
                .withDepartments("friends").build();
    public static final Employee BENSON = new EmployeeBuilder().withName("Benson Meier")
                .withPosition("Assistant Manager")
                .withId("EID5678-1234")
                .withEmail("johnd@example.com").withPhone("98765432")
                .withDepartments("owesMoney", "friends").build();
    public static final Employee CARL = new EmployeeBuilder().withName("Carl Kurz")
                .withPosition("Software Engineer")
                .withId("EID1234-8765")
                .withPhone("95352563")
                .withEmail("heinz@example.com").build();
    public static final Employee DANIEL = new EmployeeBuilder().withName("Daniel Meier")
                .withPosition("Tester")
                .withId("EID4321-5678")
                .withPhone("87652533")
                .withEmail("cornelia@example.com").withDepartments("friends").build();
    public static final Employee ELLE = new EmployeeBuilder().withName("Elle Meyer")
                .withPosition("Junior Software Engineer")
                .withId("EID2023-1234")
                .withPhone("9482224")
                .withEmail("werner@example.com").build();
    public static final Employee FIONA = new EmployeeBuilder().withName("Fiona Kunz")
                .withPosition("Senior Software Engineer")
                .withId("EID2023-5678")
                .withPhone("9482427")
                .withEmail("lydia@example.com").build();
    public static final Employee GEORGE = new EmployeeBuilder().withName("George Best")
                .withPosition("Intern")
                .withId("EID2023-9876")
                .withPhone("9482442")
                .withEmail("anna@example.com").build();

    // Manually added
    public static final Employee HOON = new EmployeeBuilder().withName("Hoon Meier")
                .withPosition("Intern").withId("EID2023-2024").withPhone("8482424")
                .withEmail("stefan@example.com").build();
    public static final Employee IDA = new EmployeeBuilder().withName("Ida Mueller")
                .withPosition("CEO").withId("2021-2022").withPhone("8482131")
                .withEmail("hans@example.com").build();

    // Manually added - Employee's details found in {@code CommandTestUtil}
    public static final Employee AMY = new EmployeeBuilder().withName(VALID_NAME_AMY)
                .withPosition(VALID_POSITION_AMY)
                .withId(VALID_ID_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).withDepartments(VALID_DEPARTMENT_FRIEND).build();
    public static final Employee BOB = new EmployeeBuilder().withName(VALID_NAME_BOB)
                .withPosition(VALID_POSITION_BOB)
                .withId(VALID_ID_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withDepartments(VALID_DEPARTMENT_HUSBAND, VALID_DEPARTMENT_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEmployees() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical employees.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Employee employee : getTypicalEmployees()) {
            ab.addEmployee(employee);
        }
        return ab;
    }

    public static List<Employee> getTypicalEmployees() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
