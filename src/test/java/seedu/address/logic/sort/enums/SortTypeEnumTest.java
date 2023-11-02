package seedu.address.logic.sort.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SortTypeEnumTest {
    @Test
    public void of_validTaskName_success() {
        assertEquals(SortTypeEnum.of("t"), SortTypeEnum.TASK_DESCRIPTION);
        assertEquals(SortTypeEnum.of("td"), SortTypeEnum.TASK_DESCRIPTION);
        assertEquals(SortTypeEnum.of("task"), SortTypeEnum.TASK_DESCRIPTION);
        assertEquals(SortTypeEnum.of("tsk dsc"), SortTypeEnum.TASK_DESCRIPTION);
        assertEquals(SortTypeEnum.of("task description"), SortTypeEnum.TASK_DESCRIPTION);
    }

    @Test
    public void of_validPriority_success() {
        assertEquals(SortTypeEnum.of("p"), SortTypeEnum.PRIORITY);
        assertEquals(SortTypeEnum.of("pr"), SortTypeEnum.PRIORITY);
        assertEquals(SortTypeEnum.of("pri"), SortTypeEnum.PRIORITY);
        assertEquals(SortTypeEnum.of("prior"), SortTypeEnum.PRIORITY);
        assertEquals(SortTypeEnum.of("priority"), SortTypeEnum.PRIORITY);
    }

    @Test
    public void of_validDeadline_success() {
        assertEquals(SortTypeEnum.of("d"), SortTypeEnum.DEADLINE);
        assertEquals(SortTypeEnum.of("dl"), SortTypeEnum.DEADLINE);
        assertEquals(SortTypeEnum.of("deadln"), SortTypeEnum.DEADLINE);
        assertEquals(SortTypeEnum.of("deadline"), SortTypeEnum.DEADLINE);
    }

    @Test
    public void of_invalidTaskName_failure() {
        assertThrows(IllegalArgumentException.class, () -> SortTypeEnum.of("taskdescription"));
    }

    @Test
    public void of_invalidPriority_failure() {
        assertThrows(IllegalArgumentException.class, () -> SortTypeEnum.of("piority"));
    }

    @Test
    public void of_invalidDeadline_failure() {
        assertThrows(IllegalArgumentException.class, () -> SortTypeEnum.of("dealdine"));
    }

    @Test
    public void toString_taskName_success() {
        assertEquals(SortTypeEnum.TASK_DESCRIPTION.toString(), "task description");
    }

    @Test
    public void toString_priority_success() {
        assertEquals(SortTypeEnum.PRIORITY.toString(), "priority");
    }

    @Test
    public void toString_deadline_success() {
        assertEquals(SortTypeEnum.DEADLINE.toString(), "deadline");
    }
}
