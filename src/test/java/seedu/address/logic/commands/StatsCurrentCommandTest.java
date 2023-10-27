package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AnimalType;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPersons;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class StatsCurrentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private StatsCurrentCommand currentCommand = new StatsCurrentCommand();

    @Test
    public void getCurrentFosterers_unfilteredList() {
        List<Person> result = currentCommand.getCurrentFosterers(model.getFilteredPersonList());
        assertEquals(TypicalPersons.getCurrent(), result);
    }

    @Test
    public void getCurrentFosterers_filteredList_Available() {
        model.updateFilteredPersonList(fosterer -> fosterer.getAvailability().equals(Availability.AVAILABLE));
        List<Person> result = currentCommand.getCurrentFosterers(model.getFilteredPersonList());
        assertTrue(result.isEmpty());
    }

    @Test
    public void getCurrentDogCount_unfilteredList() {
        model.updateFilteredPersonList(fosterer -> fosterer.getAnimalType().equals(AnimalType.CURRENT_DOG));
        int result = currentCommand.getCurrentDogCount(model.getFilteredPersonList());
        assertEquals(model.getFilteredPersonList().size(), result);
    }

    @Test
    public void getCurrentDogCount_filteredList_currentCat() {
        //can only be able.Dog or able.Cat
        model.updateFilteredPersonList(fosterer -> fosterer.getAnimalType().equals(AnimalType.CURRENT_CAT));
        int result = currentCommand.getCurrentDogCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void getCurrentDogCount_filteredList_available() {
        //when NotAvailable, can only have nil or current.Cat/Dog in as animal type
        model.updateFilteredPersonList(fosterer -> fosterer.getAvailability().equals(Availability.AVAILABLE));
        int result = currentCommand.getCurrentDogCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void getCurrentCatCount_unfilteredList() {
        model.updateFilteredPersonList(fosterer -> fosterer.getAnimalType().equals(AnimalType.CURRENT_CAT));
        int result = currentCommand.getCurrentCatCount(model.getFilteredPersonList());
        assertEquals(model.getFilteredPersonList().size(), result);
    }

    @Test
    public void getCurrentCatCount_filteredList_currentDog() {
        //can only be current.Dog or current.Cat
        model.updateFilteredPersonList(fosterer -> fosterer.getAnimalType().equals(AnimalType.CURRENT_DOG));
        int result = currentCommand.getCurrentCatCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void getCurrentCatCount_filteredList_available() {
        //when NotAvailable, can only have nil or current.Cat/Dog in as animal type
        model.updateFilteredPersonList(fosterer -> fosterer.getAvailability().equals(Availability.AVAILABLE));
        int result = currentCommand.getCurrentCatCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void execute_unfilteredList_success() throws CommandException {
        List<Person> fosterers = model.getFilteredPersonList();
        int total = fosterers.size();
        int currCount = currentCommand.getCurrentFosterers(fosterers).size();
        int currDogCount = currentCommand.getCurrentDogCount(fosterers);
        int currCatCount = currentCommand.getCurrentCatCount(fosterers);
        int unknown = currCount - currCatCount - currDogCount;

        String summary = String.format(StatsCurrentCommand.MESSAGE_CURRENT_SUMMARY, currCount,
                total, StatsAvailCommand.calculatePercentage(currCatCount, total));

        String details = String.format(StatsCurrentCommand.MESSAGE_CURRENT_DETAILS, currDogCount,
                StatsAvailCommand.calculatePercentage(currDogCount, currCount), currCatCount,
                StatsAvailCommand.calculatePercentage(currCatCount, currCount), unknown,
                StatsAvailCommand.calculatePercentage(unknown, currCount));
        assertEquals(summary + "\n" + details, currentCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_noFosterers_throwsCommandException() {
        model.updateFilteredPersonList(Objects::isNull);
        assertCommandFailure(currentCommand, model, StatsCommand.MESSAGE_NO_FOSTERERS);
    }

    @Test
    public void execute_filteredList_success() throws CommandException {
        model.updateFilteredPersonList(fosterer -> fosterer.getAvailability().equals(Availability.AVAILABLE));
        List<Person> fosterers = model.getFilteredPersonList();
        int total = fosterers.size();
        int currCount = currentCommand.getCurrentFosterers(fosterers).size();

        String summary = String.format(StatsCurrentCommand.MESSAGE_CURRENT_SUMMARY, currCount,
                total, StatsAvailCommand.calculatePercentage(currCount, total));
        assertEquals(summary, currentCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void equals() {
        StatsCurrentCommand currentSecondCommand = new StatsCurrentCommand();
        // same object -> returns true
        assertTrue(currentCommand.equals(currentCommand));

        // different object -> returns true
        assertTrue(currentCommand.equals(currentSecondCommand));

        // different types -> returns false
        assertFalse(currentCommand.equals(1));
        assertFalse(currentCommand.equals(new StatsAvailCommand()));
        assertFalse(currentCommand.equals(new StatsHousingCommand()));

        // null -> returns false
        assertFalse(currentCommand.equals(null));
    }
}
