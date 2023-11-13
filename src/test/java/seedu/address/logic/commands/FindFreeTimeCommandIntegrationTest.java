package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.FindFreeTimeCommand.MESSAGE_NOT_ALL_FREE;
import static seedu.address.logic.commands.FindFreeTimeCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Duration;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalTimeIntervals;


public class FindFreeTimeCommandIntegrationTest {

    // success -> no changes to model and expected message
    // failure -> command on actualModel throws expected error message


    // group don't exist
    @Test
    public void execute_groupNotExist_unSuccessful() {
        AddressBook ab = new AddressBook();
        Group group = new GroupBuilder().withName("CS2100").build();
        Person person = new PersonBuilder().withName("Nicholas").build();
        ab.addGroup(group);
        ab.addPerson(person);

        Model actualModel = new ModelManager(ab, new UserPrefs());

        FindFreeTimeCommand command = new FindFreeTimeCommand("CS", new Duration(60));

        assertCommandFailure(command, actualModel, Messages.MESSAGE_NO_GROUP_WITH_NAME_FOUND);
    }

    // group exist but no people
    @Test
    public void execute_groupExistIsEmpty_unSuccessful() {
        AddressBook ab = new AddressBook();
        Group group = new GroupBuilder().withName("CS2100").build();
        Person person = new PersonBuilder().withName("Nicholas").build();
        ab.addGroup(group);
        ab.addPerson(person);

        Model actualModel = new ModelManager(ab, new UserPrefs());

        FindFreeTimeCommand command = new FindFreeTimeCommand("CS2100", new Duration(60));

        assertCommandFailure(command, actualModel, "Group is empty");
    }

    // group exist got people but no time
    @Test
    public void execute_groupExistContactNotFree_unSuccessful() {
        AddressBook ab = new AddressBook();
        Group group = new GroupBuilder().withName("CS2100").build();
        Person person = new PersonBuilder().withName("Nicholas").build();
        try {
            group.addPerson(person);
            person.addGroup(group);
        } catch (CommandException e) {
            fail();
        }
        ab.addGroup(group);
        ab.addPerson(person);

        Model actualModel = new ModelManager(ab, new UserPrefs());

        FindFreeTimeCommand command = new FindFreeTimeCommand("CS2100", new Duration(60));
        String message = String.format(MESSAGE_NOT_ALL_FREE, person.getName().toString());

        assertCommandFailure(command, actualModel, message);
    }

    // group exist got people got time but no overlap
    @Test
    public void execute_groupExistContactNoOverlap_unSuccessful() {
        AddressBook ab = new AddressBook();
        Group group = new GroupBuilder().withName("CS2100").build();
        Person personOne = new PersonBuilder().withName("Nicholas").build();
        Person personTwo = new PersonBuilder().withName("Zhen Dong").build();
        personOne.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_TWO_NO_OVERLAP); // 1100 - 1200
        personTwo.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A); // 1000 - 1100

        try {
            group.addPerson(personOne);
            group.addPerson(personTwo);
            personOne.addGroup(group);
            personTwo.addGroup(group);
        } catch (CommandException e) {
            fail();
        }
        ab.addGroup(group);
        ab.addPerson(personOne);

        Model actualModel = new ModelManager(ab, new UserPrefs());
        Model expectedModel = new ModelManager(ab, new UserPrefs());

        FindFreeTimeCommand command = new FindFreeTimeCommand("CS2100", new Duration(60));
        String message = MESSAGE_SUCCESS;

        assertCommandSuccess(command, actualModel,
            message, expectedModel);
    }

    // group exist got people got time got overlap but don't fit duration
    @Test
    public void execute_groupExistContactOverlapNoFitDuration_unSuccessful() {
        AddressBook ab = new AddressBook();
        Group group = new GroupBuilder().withName("CS2100").build();
        Person personOne = new PersonBuilder().withName("Nicholas").build();
        Person personTwo = new PersonBuilder().withName("Zhen Dong").build();
        personOne.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_ONE_NO_OVERLAP); // 1000 - 1100
        personTwo.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A); // 1000 - 1100

        try {
            group.addPerson(personOne);
            group.addPerson(personTwo);
            personOne.addGroup(group);
            personTwo.addGroup(group);
        } catch (CommandException e) {
            fail();
        }
        ab.addGroup(group);
        ab.addPerson(personOne);

        Model actualModel = new ModelManager(ab, new UserPrefs());
        Model expectedModel = new ModelManager(ab, new UserPrefs());

        FindFreeTimeCommand command = new FindFreeTimeCommand("CS2100", new Duration(61));
        String message = MESSAGE_SUCCESS;

        assertCommandSuccess(command, actualModel,
            message, expectedModel);
    }



    // group exist got people got time got overlap and fit duration
    @Test
    public void execute_groupExistContactOverlapFitDuration_successful() {
        AddressBook ab = new AddressBook();
        Group group = new GroupBuilder().withName("CS2100").build();
        Person personOne = new PersonBuilder().withName("Nicholas").build();
        Person personTwo = new PersonBuilder().withName("Zhen Dong").build();
        personOne.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_ONE_NO_OVERLAP); // 1000 - 1100
        personTwo.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_ONE_OVERLAP_A); // 1000 - 1100

        try {
            group.addPerson(personOne);
            group.addPerson(personTwo);
            personOne.addGroup(group);
            personTwo.addGroup(group);
        } catch (CommandException e) {
            fail();
        }
        ab.addGroup(group);
        ab.addPerson(personOne);

        Model actualModel = new ModelManager(ab, new UserPrefs());
        Model expectedModel = new ModelManager(ab, new UserPrefs());

        FindFreeTimeCommand command = new FindFreeTimeCommand("CS2100", new Duration(60));
        String message = MESSAGE_SUCCESS
            + "1. MON 1000 - MON 1100 \n";

        assertCommandSuccess(command, actualModel,
            message, expectedModel);
    }

    // multiple overlaps on the same day
    @Test
    public void execute_groupExistContactMultipleOverlapFitDuration_successful() {
        AddressBook ab = new AddressBook();
        Group group = new GroupBuilder().withName("CS2100").build();
        Person personOne = new PersonBuilder().withName("Nicholas").build();
        Person personTwo = new PersonBuilder().withName("Zhen Dong").build();
        personOne.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_TWO_OVERLAP_A); // 1030 - 1130
        personTwo.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_THREE_OVERLAP_A); // 1100 - 1200
        personOne.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_FIVE_OVERLAP_A); // 1200 - 1300
        personTwo.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_FIVE_HALFOVERLAP_A); // 1230 - 1330



        try {
            group.addPerson(personOne);
            group.addPerson(personTwo);
            personOne.addGroup(group);
            personTwo.addGroup(group);
        } catch (CommandException e) {
            fail();
        }
        ab.addGroup(group);
        ab.addPerson(personOne);

        Model actualModel = new ModelManager(ab, new UserPrefs());
        Model expectedModel = new ModelManager(ab, new UserPrefs());

        FindFreeTimeCommand command = new FindFreeTimeCommand("CS2100", new Duration(30));
        String message = MESSAGE_SUCCESS
                         + "1. MON 1100 - MON 1130 \n"
                         + "2. MON 1230 - MON 1300 \n";

        assertCommandSuccess(command, actualModel,
            message, expectedModel);
    }


    // multiple overlaps across days
    @Test
    public void execute_groupExistContactMultipleOverlapDifferentDaysFitDuration_successful() {
        AddressBook ab = new AddressBook();
        Group group = new GroupBuilder().withName("CS2100").build();
        Person personOne = new PersonBuilder().withName("Nicholas").build();
        Person personTwo = new PersonBuilder().withName("Zhen Dong").build();

        personOne.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_MONTUE_ONE); // MON 1100 - TUE 1400
        personTwo.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_MONWED_ONE); // MON 1200 - WED 1500
        personOne.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_THUSAT_TWO); // THU 1200 - SAT 1800
        personTwo.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_SATSUN_TWO); // SAT 1200 - SUN 1800



        try {
            group.addPerson(personOne);
            group.addPerson(personTwo);
            personOne.addGroup(group);
            personTwo.addGroup(group);
        } catch (CommandException e) {
            fail();
        }
        ab.addGroup(group);
        ab.addPerson(personOne);

        Model actualModel = new ModelManager(ab, new UserPrefs());
        Model expectedModel = new ModelManager(ab, new UserPrefs());

        FindFreeTimeCommand command = new FindFreeTimeCommand("CS2100", new Duration(360));
        String message = MESSAGE_SUCCESS
                         + "1. MON 1200 - TUE 1400 \n"
                         + "2. SAT 1200 - SAT 1800 \n";
        FindFreeTimeCommand commandAnother = new FindFreeTimeCommand("CS2100", new Duration(361));
        String messageAnother = MESSAGE_SUCCESS
                         + "1. MON 1200 - TUE 1400 \n";

        assertCommandSuccess(command, actualModel,
            message, expectedModel);

        assertCommandSuccess(commandAnother, actualModel,
            messageAnother, expectedModel);
    }

    // multiple overlaps across days and only the 14h timeslot
    @Test
    public void execute_groupExistContactOverlapDifferentDaysFitDuration_successful() {
        AddressBook ab = new AddressBook();
        Group group = new GroupBuilder().withName("CS2100").build();
        Person personOne = new PersonBuilder().withName("Nicholas").build();
        Person personTwo = new PersonBuilder().withName("Zhen Dong").build();

        personOne.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_MONTUE_ONE); // MON 1100 - TUE 1400
        personTwo.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_MONWED_ONE); // MON 1200 - WED 1500
        personOne.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_THUSAT_TWO); // THU 1200 - SAT 1800
        personTwo.addFreeTime(TypicalTimeIntervals.TIME_INTERVAL_SATSUN_TWO); // SAT 1200 - SUN 1800

        try {
            group.addPerson(personOne);
            group.addPerson(personTwo);
            personOne.addGroup(group);
            personTwo.addGroup(group);
        } catch (CommandException e) {
            fail();
        }
        ab.addGroup(group);
        ab.addPerson(personOne);

        Model actualModel = new ModelManager(ab, new UserPrefs());
        Model expectedModel = new ModelManager(ab, new UserPrefs());

        FindFreeTimeCommand command = new FindFreeTimeCommand("CS2100", new Duration(840));
        String message = MESSAGE_SUCCESS
                         + "1. MON 1200 - TUE 1400 \n";

        // inbetween 6h and 26h
        FindFreeTimeCommand commandAnother = new FindFreeTimeCommand("CS2100", new Duration(1559));
        String messageAnother = MESSAGE_SUCCESS
                         + "1. MON 1200 - TUE 1400 \n";

        FindFreeTimeCommand commandExceed = new FindFreeTimeCommand("CS2100", new Duration(1561));
        String messageExceed = MESSAGE_SUCCESS;

        assertCommandSuccess(command, actualModel,
            message, expectedModel);

        assertCommandSuccess(commandAnother, actualModel,
            messageAnother, expectedModel);

        assertCommandSuccess(commandExceed, actualModel,
            messageExceed, expectedModel);

    }




}
