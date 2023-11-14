package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

class LinkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalScheduleList());
    @Test
    public void test_linkValidNames() throws CommandException {
        Person p = model.getFilteredPersonList().get(0);
        Lesson l = model.getFilteredScheduleList().get(0);
        assertEquals(0, model.getLinkedWith(p).length);
        assertEquals(0, model.getLinkedWith(l).length);
        LinkCommand linkCommand = new LinkCommand(l.getName(), p.getName());
        linkCommand.execute(model);
        assertEquals(model.getLinkedWith(p)[0], l.getName());
        assertEquals(model.getLinkedWith(l)[0], p.getName());
    }
    @Test
    public void test_linkInvalidNames_linkExistingLink() throws CommandException, ParseException {
        Person p = model.getFilteredPersonList().get(0);
        Lesson l = model.getFilteredScheduleList().get(0);
        LinkCommand linkCommand = new LinkCommand(l.getName(), p.getName());
        linkCommand.execute(model);
        assertThrows(CommandException.class, () -> linkCommand.execute(model));
        Name name = new Name("invalid");
        assertThrows(CommandException.class, () -> new LinkCommand(name, p.getName()).execute(model));
    }
}
