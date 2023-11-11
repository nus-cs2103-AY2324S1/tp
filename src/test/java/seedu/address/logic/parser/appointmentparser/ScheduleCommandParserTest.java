package seedu.address.logic.parser.appointmentparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.DESCRIPTION_DESC_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.END_DESC_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.PATIENT_ONE_DESC;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.PRIORITY_TAG_DESC_HIGH;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.START_DESC_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_DESCRIPTION_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_END_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_PATIENT_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_PRIORITY_TAG_HIGH;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_START_ONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointmentcommands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDescription;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Name;
import seedu.address.model.tag.PriorityTag;
import seedu.address.testutil.AppointmentTimeBuilder;

public class ScheduleCommandParserTest {
    private ScheduleCommandParser parser = new ScheduleCommandParser();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {

        String patient = VALID_PATIENT_ONE;
        Name patientName = new Name(patient);
        String userInput = PATIENT_ONE_DESC + START_DESC_ONE + END_DESC_ONE + DESCRIPTION_DESC_ONE
                + PRIORITY_TAG_DESC_HIGH;
        AppointmentTime appointmentTime = new AppointmentTimeBuilder()
                .withStart(VALID_START_ONE)
                .withEnd(VALID_END_ONE).build();
        String appointmentDescription = VALID_DESCRIPTION_ONE;
        AppointmentDescription appointmentDescription1 = new AppointmentDescription(appointmentDescription);
        String priority = VALID_PRIORITY_TAG_HIGH;
        PriorityTag priorityTag = new PriorityTag(priority);

        Appointment expectedAppointment = new Appointment(patientName, appointmentTime, appointmentDescription1,
                priorityTag);

        ScheduleCommand expectedCommand = new ScheduleCommand(expectedAppointment, patientName);

        assertEquals(parser.parse(userInput).toString(), expectedCommand.toString());
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
