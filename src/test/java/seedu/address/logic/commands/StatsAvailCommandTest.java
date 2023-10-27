package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AnimalType;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.Objects;

public class StatsAvailCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private StatsAvailCommand availCommand = new StatsAvailCommand();


    @Test
    public void getAvailableFosterers_unfilteredList() {
        model.updateFilteredPersonList(fosterer -> fosterer.getAvailability().equals(Availability.AVAILABLE));
        List<Person> availableFosterers = model.getFilteredPersonList();
        List<Person> result = availCommand.getAvailableFosterers(model.getFilteredPersonList());
        assertEquals(availableFosterers, result);
    }

    @Test
    public void getAvailableFosterers_filteredList_NotAvailable() {
        model.updateFilteredPersonList(fosterer -> fosterer.getAvailability().equals(Availability.NOT_AVAILABLE));
        List<Person> result = availCommand.getAvailableFosterers(model.getFilteredPersonList());
        assertTrue(result.isEmpty());
    }

    @Test
    public void getAbleDogCount_unfilteredList() {
        model.updateFilteredPersonList(fosterer -> fosterer.getAnimalType().equals(AnimalType.ABLE_DOG));
        int result = availCommand.getAbleDogCount(model.getFilteredPersonList());
        assertEquals(model.getFilteredPersonList().size(), result);
    }

    @Test
    public void getAbleDogCount_filteredList_ableCat() {
        //can only be able.Dog or able.Cat
        model.updateFilteredPersonList(fosterer -> fosterer.getAnimalType().equals(AnimalType.ABLE_CAT));
        int result = availCommand.getAbleDogCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void getAbleDogCount_filteredList_notAvailable() {
        //when NotAvailable, can only have nil or current.Cat/Dog in as animal type
        model.updateFilteredPersonList(fosterer -> fosterer.getAvailability().equals(Availability.NOT_AVAILABLE));
        int result = availCommand.getAbleDogCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void getAbleCatCount_unfilteredList() {
        model.updateFilteredPersonList(fosterer -> fosterer.getAnimalType().equals(AnimalType.ABLE_CAT));
        int result = availCommand.getAbleCatCount(model.getFilteredPersonList());
        assertEquals(model.getFilteredPersonList().size(), result);
    }

    @Test
    public void getAbleCatCount_filteredList_ableDog() {
        //can only be able.Dog or able.Cat
        model.updateFilteredPersonList(fosterer -> fosterer.getAnimalType().equals(AnimalType.ABLE_DOG));
        int result = availCommand.getAbleCatCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void getAbleCatCount_filteredList_notAvailable() {
        //when NotAvailable, can only have nil or current.Cat/Dog in as animal type
        model.updateFilteredPersonList(fosterer -> fosterer.getAvailability().equals(Availability.NOT_AVAILABLE));
        int result = availCommand.getAbleCatCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void execute_unfilteredList_success() throws CommandException {
        List<Person> fosterers = model.getFilteredPersonList();
        int total = fosterers.size();
        int availableCount = availCommand.getAvailableFosterers(fosterers).size();
        int ableDogCount = availCommand.getAbleDogCount(fosterers);
        int ableCatCount = availCommand.getAbleCatCount(fosterers);
        int unknown = availableCount - ableDogCount - ableCatCount;

        String summary = String.format(StatsAvailCommand.MESSAGE_AVAIL_SUMMARY, availableCount,
                total, StatsAvailCommand.calculatePercentage(availableCount, total));

        String details = String.format(StatsAvailCommand.MESSAGE_AVAIL_DETAILS, ableDogCount,
                StatsAvailCommand.calculatePercentage(ableDogCount, availableCount), ableCatCount,
                StatsAvailCommand.calculatePercentage(ableCatCount, availableCount), unknown,
                StatsAvailCommand.calculatePercentage(unknown, availableCount));
        assertEquals(summary + "\n" + details, availCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_noFosterers_throwsCommandException() {
        model.updateFilteredPersonList(Objects::isNull);
        assertCommandFailure(availCommand, model, StatsCommand.MESSAGE_NO_FOSTERERS);
    }

    @Test
    public void execute_filteredList_success() throws CommandException {
        model.updateFilteredPersonList(fosterer -> fosterer.getAvailability().equals(Availability.NOT_AVAILABLE));
        List<Person> fosterers = model.getFilteredPersonList();
        int total = fosterers.size();
        int availableCount = availCommand.getAvailableFosterers(fosterers).size();

        String summary = String.format(StatsAvailCommand.MESSAGE_AVAIL_SUMMARY, availableCount,
                total, StatsAvailCommand.calculatePercentage(availableCount, total));
        assertEquals(summary, availCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void equals() {
        StatsAvailCommand availSecondCommand = new StatsAvailCommand();

        // same object -> returns true
        assertTrue(availCommand.equals(availCommand));

        // different object -> returns true
        assertTrue(availCommand.equals(availSecondCommand));

        // different types -> returns false
        assertFalse(availCommand.equals(1));
        assertFalse(availCommand.equals(new StatsCurrentCommand()));
        assertFalse(availCommand.equals(new StatsHousingCommand()));

        // null -> returns false
        assertFalse(availCommand.equals(null));
    }
}
