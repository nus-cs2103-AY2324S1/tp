package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddPlanCommand.MESSAGE_FRIEND_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPlans.MEETING;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.plan.Plan;
import seedu.address.testutil.PlanBuilder;


public class AddPlanCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPlan_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPlanCommand(null, null, null));
    }

    @Test
    public void execute_planAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPlanAdded modelStub = new ModelStubAcceptingPlanAdded();
        Plan validPlan = new PlanBuilder().build();

        CommandResult commandResult = new AddPlanCommand(
                validPlan.getPlanName(), validPlan.getPlanDateTime(), validPlan.getPlanFriend().getName()
        ).execute(modelStub);

        assertEquals(String.format(AddPlanCommand.MESSAGE_SUCCESS, Messages.format(validPlan)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPlan), modelStub.plansAdded);
    }

    @Test
    public void execute_duplicatePlan_throwsCommandException() {
        Plan validPlan = new PlanBuilder().build();
        AddPlanCommand addPlanCommand = new AddPlanCommand(
                validPlan.getPlanName(), validPlan.getPlanDateTime(), validPlan.getPlanFriend().getName());
        ModelStub modelStub = new ModelStubWithPlan(validPlan);

        assertThrows(CommandException.class,
                AddPlanCommand.MESSAGE_DUPLICATE_PLAN, () -> addPlanCommand.execute(modelStub));
    }

    @Test
    public void execute_friendDoesNotExist_throwsCommandException() {
        Plan plan = new PlanBuilder().withPlanFriend(AMY).build();

        AddPlanCommand addPlanCommand = new AddPlanCommand(
                plan.getPlanName(), plan.getPlanDateTime(), plan.getPlanFriend().getName());
        assertCommandFailure(addPlanCommand, model, MESSAGE_FRIEND_NOT_FOUND);
    }

    @Test
    public void equals() {
        Plan meeting = new PlanBuilder().withPlanName("Meeting")
                .withPlanDateTime("2025-10-23-12:00").withPlanFriend(ALICE).build();
        Plan gaming = new PlanBuilder().withPlanName("Gaming")
                .withPlanDateTime("2025-10-28-16:00").withPlanFriend(ALICE).build();
        AddPlanCommand addMeetingCommand = new AddPlanCommand(
                meeting.getPlanName(), meeting.getPlanDateTime(), meeting.getPlanFriend().getName());
        AddPlanCommand addGamingCommand = new AddPlanCommand(
                gaming.getPlanName(), gaming.getPlanDateTime(), gaming.getPlanFriend().getName());

        assertTrue(addMeetingCommand.equals(addMeetingCommand));

        AddPlanCommand addMeetingCommandCopy = new AddPlanCommand(
                meeting.getPlanName(), meeting.getPlanDateTime(), meeting.getPlanFriend().getName());
        assertTrue(addMeetingCommand.equals(addMeetingCommandCopy));

        assertFalse(addMeetingCommand.equals(1));
        assertFalse(addMeetingCommand.equals(null));
        assertFalse(addMeetingCommand.equals(addGamingCommand));
    }

    @Test
    public void toStringMethod() {
        AddPlanCommand addPlanCommand = new AddPlanCommand(
                MEETING.getPlanName(), MEETING.getPlanDateTime(), MEETING.getPlanFriend().getName()
        );
        String expected = AddPlanCommand.class.getCanonicalName() + "{toAdd=" + MEETING + "}";
        assertEquals(expected, addPlanCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     * Only getPersonByName returns the DEFAULT_FRIEND in FriendBook testing.
     */
    private class ModelStub implements Model {

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPlan(Plan plan) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOtherPerson(Person person, Person originalPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPersonByName(Name name) {
            return ALICE;
        }

        @Override
        public Person getPersonByName(String name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPlan(Plan plan) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePlan(Plan target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void completePlan(Plan target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void uncompletePlan(Plan target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPlan(Plan target, Plan editedPlan) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePlansWithPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Plan> getFilteredPlanList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPlanList(Predicate<Plan> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single plan.
     */
    private class ModelStubWithPlan extends ModelStub {
        final FilteredList<Plan> filteredPlans = new FilteredList<>(getTypicalAddressBook().getPlanList());
        private final Plan plan;

        ModelStubWithPlan(Plan plan) {
            requireNonNull(plan);
            this.plan = plan;
        }

        @Override
        public boolean hasPlan(Plan plan) {
            requireNonNull(plan);
            return this.plan.equals(plan);
        }

        @Override
        public void updateFilteredPlanList(Predicate<Plan> predicate) {
            requireNonNull(predicate);
            filteredPlans.setPredicate(predicate);
        }
    }

    /**
     * A Model stub that always accept the plan being added.
     */
    private class ModelStubAcceptingPlanAdded extends ModelStub {
        final FilteredList<Plan> filteredPlans = new FilteredList<>(getTypicalAddressBook().getPlanList());
        final ArrayList<Plan> plansAdded = new ArrayList<>();

        @Override
        public boolean hasPlan(Plan plan) {
            requireNonNull(plan);
            return plansAdded.stream().anyMatch(plan::equals);
        }

        @Override
        public void addPlan(Plan plan) {
            requireNonNull(plan);
            plansAdded.add(plan);
        }

        @Override
        public void updateFilteredPlanList(Predicate<Plan> predicate) {
            requireNonNull(predicate);
            filteredPlans.setPredicate(predicate);
        }

    }
}
