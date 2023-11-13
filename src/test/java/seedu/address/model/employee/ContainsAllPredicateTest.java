//@@author kenvynKwek
package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_AMY;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.CustomSet;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;
import seedu.address.testutil.EmployeeBuilder;


public class ContainsAllPredicateTest {
    public ContainsAllPredicate createContainsAllPredicateHelper(String prefixes, String keywords) {
        Set<EmployeeName> nameSet = new CustomSet<>();
        Set<Phone> phoneSet = new CustomSet<>();
        Set<Email> emailSet = new CustomSet<>();
        Set<Address> addressSet = new CustomSet<>();
        Set<Salary> salarySet = new CustomSet<>();
        Set<Leave> leaveSet = new CustomSet<>();
        Set<Role> roleSet = new CustomSet<>();
        Set<EmployeeName> supervisorSet = new CustomSet<>();
        Set<DepartmentName> departmentSet = new CustomSet<>();

        String[] prefix = prefixes.split("_");
        String[] keyword = keywords.split("_");
        for (int i = 0; i < prefix.length; i++) {
            switch (prefix[i]) {
            case "name":
                nameSet.add(new EmployeeName(keyword[i]));
                break;
            case "phone":
                phoneSet.add(new Phone(keyword[i]));
                break;
            case "email":
                emailSet.add(new Email(keyword[i]));
                break;
            case "address":
                addressSet.add(new Address(keyword[i]));
                break;
            case "salary":
                salarySet.add(new Salary(keyword[i]));
                break;
            case "leave":
                leaveSet.add(new Leave(keyword[i]));
                break;
            case "role":
                roleSet.add(new Role(keyword[i]));
                break;
            case "supervisor":
                supervisorSet.add(new EmployeeName(keyword[i]));
                break;
            case "department":
                departmentSet.add(new DepartmentName(keyword[i]));
                break;
            default:
                break;
            }
        }

        ContainsAllPredicate predicate = new ContainsAllPredicate(nameSet, phoneSet, emailSet, addressSet, salarySet,
                leaveSet, roleSet, supervisorSet, departmentSet);
        return predicate;
    }

    @Test
    public void test_containsName_returnsTrue() {
        String keyword = VALID_NAME_AMY;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("name", keyword);
        Employee employee = new EmployeeBuilder().withName(keyword).build();
        assertTrue(predicate.test(employee));
    }

    @Test
    public void test_doesNotContainName_returnsFalse() {
        String keyword = VALID_NAME_AMY;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("name", keyword);
        Employee employee = new EmployeeBuilder().withName(VALID_NAME_BOB).build();
        assertFalse(predicate.test(employee));
    }

    @Test
    public void test_containsSalary_returnsTrue() {
        String keyword = VALID_SALARY_AMY;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("salary", keyword);
        Employee employee = new EmployeeBuilder().withSalary(keyword).build();
        assertTrue(predicate.test(employee));
    }

    @Test
    public void test_containsAddress_returnsTrue() {
        String keyword = VALID_ADDRESS_BOB;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("address", keyword);
        Employee employee = new EmployeeBuilder().withAddress(keyword).build();
        assertTrue(predicate.test(employee));
    }

    @Test
    public void test_doesNotContainsAddress_returnsFalse() {
        String keyword = VALID_ADDRESS_BOB;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("address", keyword);
        Employee employee = new EmployeeBuilder().withAddress(VALID_ADDRESS_AMY).build();
        assertFalse(predicate.test(employee));
    }

    @Test
    public void test_emptyPredicate_returnsTrue() {
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("", "");
        Employee employee = new EmployeeBuilder().build();
        assertTrue(predicate.test(employee));
    }

    @Test
    public void test_doesNotContainSalary_returnsFalse() {
        // 4000 <= 2000 return false
        String salary2k = "2000";
        String salary4k = "4000";
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("salary", salary2k);
        assertFalse(predicate.test(new EmployeeBuilder().withSalary(salary4k).build()));
    }

    @Test
    public void test_containsDepartment_returnsTrue() {
        String keyword = VALID_DEPARTMENT_INVESTMENT;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("department", keyword);
        Employee employee = new EmployeeBuilder().withDepartments(keyword).build();
        assertTrue(predicate.test(employee));
    }

    @Test
    public void test_emptyDepartment_returnsTrue() {
        String keyword = VALID_DEPARTMENT_INVESTMENT;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("", "");
        Employee employee = new EmployeeBuilder().withDepartments(keyword).build();
        assertTrue(predicate.test(employee));
    }

    @Test
    public void test_multipleParameters_returnsTrue() {
        // name and department
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("name_department",
                VALID_NAME_AMY + "_" + VALID_DEPARTMENT_INVESTMENT);
        Employee employee = new EmployeeBuilder().withName(VALID_NAME_AMY).withDepartments(VALID_DEPARTMENT_INVESTMENT)
                .build();
        assertTrue(predicate.test(employee));

        // salary and leave
        predicate = createContainsAllPredicateHelper("salary_leave", VALID_SALARY_AMY + "_"
                + VALID_LEAVE_AMY);
        employee = new EmployeeBuilder().withSalary(VALID_SALARY_AMY).withLeave(VALID_LEAVE_AMY).build();
        assertTrue(predicate.test(employee));

        // address and phone
        predicate = createContainsAllPredicateHelper("address_phone", VALID_ADDRESS_AMY + "_"
                + VALID_PHONE_AMY);
        employee = new EmployeeBuilder().withAddress(VALID_ADDRESS_AMY).withPhone(VALID_PHONE_AMY).build();
        assertTrue(predicate.test(employee));

        // email and role and department
        predicate = createContainsAllPredicateHelper("email_role_department", VALID_EMAIL_AMY + "_"
                + VALID_ROLE_AMY + "_" + VALID_DEPARTMENT_INVESTMENT);
        employee = new EmployeeBuilder().withEmail(VALID_EMAIL_AMY).withRole(VALID_ROLE_AMY)
                .withDepartments(VALID_DEPARTMENT_INVESTMENT).build();
        assertTrue(predicate.test(employee));
    }
}
//@@author
