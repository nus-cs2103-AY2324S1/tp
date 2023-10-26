package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.getPeople;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class StatsAvailCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void getAvailableFosterers_success() {
//        Person[] availableFosterers = getPeople(model.getFilteredPersonList(), )
    }

}
