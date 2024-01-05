package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ViewAttendanceCommandParserTest {

    private ViewAttendanceCommandParser parser = new ViewAttendanceCommandParser();

    @Test
    public void parse_validArguments_success() {

        Name name = new Name("Alex Yeoh");
        Set<Name> names = new HashSet<>();
        names.add(name);

        ViewAttendanceCommand expectedCommand = new ViewAttendanceCommand(names);

        assertParseSuccess(parser, " n/Alex Yeoh", expectedCommand);
    }

    @Test
    public void parse_validArgumentsWithMultipleNames_success() throws ParseException {
        String validArgs = " n/Alex Yeoh n/Bernice Yu";

        Set<Name> names = new HashSet<>();
        Person alex = new PersonBuilder().withName("Alex Yeoh").build();
        Person bernice = new PersonBuilder().withName("Bernice Yu").build();
        names.add(alex.getName());
        names.add(bernice.getName());
        ViewAttendanceCommand expectedCommand = new ViewAttendanceCommand(names);


        assertParseSuccess(parser, validArgs, expectedCommand);
    }

    @Test
    public void parse_emptyArg_success() {
        ViewAttendanceCommand expectedCommand = new ViewAttendanceCommand(new HashSet<>());
        assertParseSuccess(parser, "     ", expectedCommand);
    }
}
