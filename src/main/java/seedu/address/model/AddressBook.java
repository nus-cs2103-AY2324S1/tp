package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.UniquePlanList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniquePlanList plans;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        plans = new UniquePlanList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }
    public void setPlans(List<Plan> plans) {
        this.plans.setPlans(plans);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setPlans(newData.getPlanList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if another person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasOtherPerson(Person person, Person originalPerson) {
        requireNonNull(person);
        return persons.otherContains(person, originalPerson);
    }


    /**
     * Returns true if a plan with the same identity as {@code plan} exists in the address book.
     */
    public boolean hasPlan(Plan plan) {
        requireNonNull(plan);
        return plans.contains(plan);
    }

    /**
     * Gets the Person that has the same {@code name} from the list.
     * A Person with the same name must exist in the list, else Exception is thrown.
     */
    public Person getPersonByName(Name name) {
        requireNonNull(name);
        return persons.getPersonByName(name);
    }

    /**
     * Gets the Person that has the same {@code name} (case-insensitive) from the list.
     * A Person with the same name must exist in the list, else Exception is thrown.
     */
    public Person getPersonByName(String name) {
        requireNonNull(name);
        return persons.getPersonByName(name);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a plan to the FriendBook.
     * The plan must not already exist in the FriendBook.
     */
    public void addPlan(Plan p) {
        plans.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    public void setPlan(Plan target, Plan editedPlan) {
        requireNonNull(editedPlan);

        plans.setPlan(target, editedPlan);
    }

    /**
     * Replaces the given person (@code target} with {@code editedPerson} in plans which involved {@code target}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void updatePlansWithPerson(ObservableList<Plan> plans, Person editedPerson) {
        requireAllNonNull(plans, editedPerson);
        for (Plan plan: plans) {
            plan.setFriend(editedPerson);
        }
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    public void removePlan(Plan key) {
        plans.remove(key);
    }

    /**
     * Marks {@code key} as completed in this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void completePlan(Plan key) {
        plans.complete(key);
    }

    /**
     * Unmarks {@code key} as uncompleted in this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void uncompletePlan(Plan key) {
        plans.uncomplete(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Plan> getPlanList() {
        return plans.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons)
                && plans.equals(otherAddressBook.plans);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
