package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.testutil.MeetingBuilder;

public class CommandUtilTest {
    @Test
    public void verifyValidEventTime() throws ParseException {
        Event validEvent = new MeetingBuilder()
                .withEventName("valid event")
                .withEventDate("2024-10-10")
                .withEventStartTime("1000")
                .withEventEndTime("1200")
                .build();

        try {
            CommandUtil.verifyEventTimes(validEvent);
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void verifyValidEventWithoutTime() throws ParseException {
        Event validEvent = new MeetingBuilder().withEventName("valid event")
                .withEventDate("2024-10-10")
                .build();

        try {
            CommandUtil.verifyEventTimes(validEvent);
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void verifyValidEventWithoutStartTime() throws ParseException {
        Event validEvent = new MeetingBuilder().withEventName("valid event")
                .withEventDate("2024-10-10")
                .withEventEndTime("1200")
                .build();

        try {
            CommandUtil.verifyEventTimes(validEvent);
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void verifyValidEventWithoutEndTime() throws ParseException {
        Event validEvent = new MeetingBuilder().withEventName("valid event")
                .withEventDate("2024-10-10")
                .withEventStartTime("1000")
                .build();

        try {
            CommandUtil.verifyEventTimes(validEvent);
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void verifyInvalidEventTime() throws ParseException {
        Event invalidEvent = new MeetingBuilder().withEventName("invalid event")
                .withEventDate("2020-10-10")
                .withEventStartTime("1000")
                .withEventEndTime("1200")
                .build();

        assertThrows(CommandException.class, () -> CommandUtil.verifyEventTimes(invalidEvent));
    }

    @Test
    public void verifyInvalidEventEndTime() throws ParseException {
        Event invalidEvent = new MeetingBuilder().withEventName("invalid event")
                .withEventDate("2024-10-10")
                .withEventStartTime("1000")
                .withEventEndTime("0900")
                .build();

        assertThrows(CommandException.class, () -> CommandUtil.verifyEventTimes(invalidEvent));
    }

    @Test
    public void verifyInvalidEventWithoutTime() throws ParseException {
        Event invalidEvent = new MeetingBuilder().withEventName("invalid event")
                .withEventDate("2020-10-10")
                .build();

        assertThrows(CommandException.class, () -> CommandUtil.verifyEventTimes(invalidEvent));
    }

}
