package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.AnnualLeave;
import seedu.address.model.person.BankAccount;
import seedu.address.model.person.Email;
import seedu.address.model.person.JoinDate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;

/**
 * A utility class to help with building EditEmployeeDescriptor objects.
 */
public class EditEmployeeDescriptorBuilder {

    private EditEmployeeDescriptor descriptor;

    public EditEmployeeDescriptorBuilder() {
        descriptor = new EditEmployeeDescriptor();
    }

    public EditEmployeeDescriptorBuilder(EditEmployeeDescriptor descriptor) {
        this.descriptor = new EditEmployeeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEmployeeDescriptor} with fields containing {@code person}'s details
     */
    public EditEmployeeDescriptorBuilder(Person person) {
        descriptor = new EditEmployeeDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setBankAccount(person.getBankAccount());
        descriptor.setJoinDate(person.getJoinDate());
        descriptor.setSalary(person.getSalary());
        descriptor.setAnnualLeave(person.getAnnualLeave());
    }

    /**
     * Sets the {@code Name} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code BankAccount} of the {@code EditEmployeeDescriptor} that we are
     * building.
     */
    public EditEmployeeDescriptorBuilder withBankAccount(String bankAccount) {
        descriptor.setBankAccount(new BankAccount(bankAccount));
        return this;
    }

    /**
     * Sets the {@code JoinDate} of the {@code EditEmployeeDescriptor} that we are
     * building.
     */
    public EditEmployeeDescriptorBuilder withJoinDate(String joinDate) {
        descriptor.setJoinDate(new JoinDate(joinDate));
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code EditEmployeeDescriptor} that we are
     * building.
     */
    public EditEmployeeDescriptorBuilder withSalary(String salary) {
        descriptor.setSalary(new Salary(salary));
        return this;
    }

    /**
     * Sets the {@code AnnualLeave} of the {@code EditEmployeeDescriptor} that we are
     * building.
     */
    public EditEmployeeDescriptorBuilder withAnnualLeave(String annualLeave) {
        descriptor.setAnnualLeave(new AnnualLeave(annualLeave));
        return this;
    }

    public EditEmployeeDescriptor build() {
        return descriptor;
    }
}
