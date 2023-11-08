package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.state.State;

class LinkCommandParserTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalScheduleList());
    private LinkCommandParser staticParser = new LinkCommandParser();
    private LinkCommandParser stateFullParser = new LinkCommandParser(model);
    //todo make it clear in UG the behaviour of extra flag and extra texts.
    //todo, explain partition in test?
    @Test
    void testStaticParser_validNames_inValidNames() throws ParseException {
        assertEquals(Name.of("lesson name"),
                staticParser.parse("-lesson lesson name -student student name").getLessonName());
        assertEquals(Name.of("student name"),
                staticParser.parse("-lesson lesson name -student student name").getStudentName());
        assertThrows(ParseException.class, () -> staticParser.parse("-lesson lesson name"));
        assertThrows(ParseException.class, () -> staticParser.parse("-student student name"));
        assertThrows(ParseException.class, () -> staticParser.parse("-lesson lesson name -student"));
        assertThrows(ParseException.class, () -> staticParser.parse("-lesson -student student name"));
        assertThrows(ParseException.class, () -> staticParser.parse(""));
    }
    //todo make clear the behaviour, no flag needed
    @Test
    void testStateFullParser_validNames_inValidNames() throws ParseException {
        model.setState(State.SCHEDULE);
        model.showLesson(model.getFilteredScheduleList().get(0));
        assertEquals(model.getCurrentlyDisplayedLesson().getName(),
                stateFullParser.parse("student name").getLessonName());
        assertEquals(Name.of("student name"),
                stateFullParser.parse("student name").getStudentName());

        model.setState(State.STUDENT);
        model.showPerson(model.getFilteredPersonList().get(0));
        assertEquals(model.getCurrentlyDisplayedPerson().getName(),
                stateFullParser.parse("lesson name").getStudentName());
        assertEquals(Name.of("lesson name"),
                stateFullParser.parse("lesson name").getLessonName());

        model.resetAllShowFields();
        assertThrows(ParseException.class, () -> stateFullParser.parse("lesson name"));

        model.setState(State.SCHEDULE);
        assertThrows(ParseException.class, () -> stateFullParser.parse("student name"));

        model.showPerson(model.getFilteredPersonList().get(0));
        model.setState(State.SCHEDULE);
        assertThrows(ParseException.class, () -> stateFullParser.parse("lesson name"));

        model.resetAllShowFields();
        model.showLesson(model.getFilteredScheduleList().get(0));
        model.setState(State.STUDENT);
        assertThrows(ParseException.class, () -> stateFullParser.parse("student name"));
    }

}
