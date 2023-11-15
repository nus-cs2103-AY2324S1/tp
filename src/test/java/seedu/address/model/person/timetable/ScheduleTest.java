package seedu.address.model.person.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;

public class ScheduleTest {

    @Test
    public void getScheduleForDayOfWeek_success() {
        Schedule schedule = new Schedule();
        schedule.addCca(new Cca("Basketball", "monday 1200 1300"));
        schedule.addModule(new Module("CS2103", "monday 1400 1500"));
        List<TimeBlock> expected = new ArrayList<>();
        expected.add(new Cca("Basketball", "monday 1200 1300"));
        expected.add(new Module("CS2103", "monday 1400 1500"));
        assertEquals(schedule.getScheduleForDayOfWeek(1), expected);
    }

    @Test
    public void addModule_success() {
        Schedule schedule = new Schedule();
        schedule.addModule(new Module("CS2103", "monday 1400 1500"));
        List<Module> expected = new ArrayList<>();
        expected.add(new Module("CS2103", "monday 1400 1500"));
        assertEquals(schedule.getModulesList(), expected);
    }

    @Test
    public void addModule_failure() {
        Schedule schedule = new Schedule();
        assertThrows(IllegalValueException.class, () -> schedule.addModule("CS2 monday 1400 1500"));
    }

    @Test
    public void editModule_success() throws IllegalValueException {
        Schedule schedule = new Schedule();
        schedule.addModule(new Module("CS2103", "monday 1400 1500"));
        schedule.addModule(new Module("CS2101", "monday 1900 2000"));
        schedule.editModule("CS2103", "CS2100 monday 1600 1700");
        List<Module> expected = new ArrayList<>();
        expected.add(new Module("CS2101", "monday 1900 2000"));
        expected.add(new Module("CS2100", "monday 1600 1700"));
        assertEquals(schedule.getModulesList(), expected);
    }

    @Test
    public void editModule_failure() {
        Schedule schedule = new Schedule();
        schedule.addModule(new Module("CS2103", "monday 1400 1500"));
        assertThrows(IllegalArgumentException.class, () -> schedule.editModule("CS2",
                "monday 1600 1400"));
    }

    @Test
    public void deleteModule_success() throws CommandException {
        Schedule schedule = new Schedule();
        schedule.addModule(new Module("CS2103", "monday 1400 1500"));
        schedule.addModule(new Module("CS2101", "monday 1600 1700"));
        schedule.deleteModule("CS2103");
        List<Module> expected = new ArrayList<>();
        expected.add(new Module("CS2101", "monday 1600 1700"));
        assertEquals(schedule.getModulesList(), expected);
    }

    @Test
    public void deleteModule_failure() {
        Schedule schedule = new Schedule();
        schedule.addModule(new Module("CS2103", "monday 1400 1500"));
        schedule.addModule(new Module("CS2101", "monday 1600 1700"));
        assertThrows(CommandException.class, () -> schedule.deleteModule("CS2100"));
    }

    @Test
    public void addCca_success() {
        Schedule schedule = new Schedule();
        schedule.addCca(new Cca("Basketball", "monday 1400 1500"));
        List<Cca> expected = new ArrayList<>();
        expected.add(new Cca("Basketball", "monday 1400 1500"));
        assertEquals(schedule.getCcasList(), expected);
    }

    @Test
    public void addCca_failure() {
        Schedule schedule = new Schedule();
        assertThrows(IllegalValueException.class, () -> schedule.addCca("Basketball* monday 1400 1500"));
    }

    @Test
    public void editCca_success() throws IllegalValueException {
        Schedule schedule = new Schedule();
        schedule.addCca(new Cca("Basketball", "monday 1400 1500"));
        schedule.addCca(new Cca("Football", "monday 1900 2000"));
        schedule.editCca("Basketball", "Tennis monday 1600 1700");
        List<Cca> expected = new ArrayList<>();
        expected.add(new Cca("Football", "monday 1900 2000"));
        expected.add(new Cca("Tennis", "monday 1600 1700"));
        assertEquals(schedule.getCcasList(), expected);
    }

    @Test
    public void editCca_failure() {
        Schedule schedule = new Schedule();
        schedule.addCca(new Cca("Baketball", "monday 1400 1500"));
        assertThrows(IllegalArgumentException.class, () -> schedule.editCca("Basketball*",
                "monday 1600 1400"));
    }

    @Test
    public void deleteCca_success() throws CommandException {
        Schedule schedule = new Schedule();
        schedule.addCca(new Cca("Basketball", "monday 1400 1500"));
        schedule.addCca(new Cca("Football", "monday 1600 1700"));
        schedule.deleteCca("Basketball");
        List<Cca> expected = new ArrayList<>();
        expected.add(new Cca("Football", "monday 1600 1700"));
        assertEquals(schedule.getCcasList(), expected);
    }

    @Test
    public void deleteCca_failure() {
        Schedule schedule = new Schedule();
        schedule.addCca(new Cca("Basketball", "monday 1400 1500"));
        schedule.addCca(new Cca("Football", "monday 1600 1700"));
        assertThrows(CommandException.class, () -> schedule.deleteCca("CS2100"));
    }

    @Test
    public void addDatedEvent_success() {
        Schedule schedule = new Schedule();
        schedule.addDatedEvent("Walk Dog 2023-10-30 1400 1500 y");
        List<DatedEvent> expected = new ArrayList<>();
        expected.add(new DatedEvent("Walk Dog", "monday 1400 1500",
                "2023-10-30", true));
        assertEquals(schedule.getDatedEventsList(), expected);
    }

    @Test
    public void editDatedEvent_success() {
        Schedule schedule = new Schedule();
        schedule.addDatedEvent("Walk Dog 2023-10-30 1400 1500 y");
        schedule.addDatedEvent("Sleep 2023-10-31 1400 1500 y");
        schedule.editDatedEvent("Walk Dog", "Walk Cat 2023-10-30 1400 1500 y");
        List<DatedEvent> expected = new ArrayList<>();
        expected.add(new DatedEvent("Sleep", "tuesday 1400 1500",
                "2023-10-31", true));
        expected.add(new DatedEvent("Walk Cat", "monday 1400 1500",
                "2023-10-30", true));
        assertEquals(schedule.getDatedEventsList(), expected);
    }

    @Test
    public void deleteDatedEvent_success() throws Exception {
        Schedule schedule = new Schedule();
        schedule.addDatedEvent("Walk Dog 2023-10-30 1400 1500 y");
        schedule.addDatedEvent("Sleep 2023-10-31 1400 1500 y");
        schedule.deleteDatedEvent("Walk Dog");
        List<DatedEvent> expected = new ArrayList<>();
        expected.add(new DatedEvent("Sleep", "tuesday 1400 1500",
                "2023-10-31", true));
        assertEquals(schedule.getDatedEventsList(), expected);
    }

    @Test
    public void deleteDatedEvent_failure() {
        Schedule schedule = new Schedule();
        schedule.addDatedEvent("Walk Dog 2023-10-30 1400 1500 y");
        schedule.addDatedEvent("Sleep 2023-10-31 1400 1500 y");
        assertThrows(Exception.class, () -> schedule.deleteDatedEvent("CS2100"));
    }

    @Test
    public void equals() {
        Schedule schedule = new Schedule();
        schedule.addCca(new Cca("Basketball", "monday 1400 1500"));
        schedule.addDatedEvent("Sleep 2023-10-31 1400 1500 y");
        Schedule schedule2 = new Schedule();

        assertEquals(schedule, schedule);
        assertNotEquals(null, schedule);
        assertNotEquals(schedule, schedule2);
    }


}
