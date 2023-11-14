package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ScheduleList;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Task;
import seedu.address.model.lessons.TaskList;


/**
 * A utility class containing a list of {@code Lesson} objects to be used in tests.
 */
public class TypicalLessons {
    private static TaskList taskList = new TaskList();


    private static List<Task> fillerTasks = TypicalTasks.getTypicalTasks();

    private TypicalLessons() {} // prevents instantiation

    /**
     * Returns an {@code ScheduleList} with all the typical lessons.
     */
    public static ScheduleList getTypicalScheduleList() {
        ScheduleList sc = new ScheduleList();
        for (Lesson lesson : getTypicalLessons()) {
            sc.addLesson(lesson.clone());
        }
        return sc;
    }

    public static List<Lesson> getTypicalLessons() {
        try {
            taskList.setTasks(fillerTasks);
            return new ArrayList<>(Arrays.asList(
                    new Lesson("lesson 1", "14:30", "16:30", "2022/10/10", "MATHEMATICS", taskList),
                    new Lesson("lesson 2", "14:30", "16:30", "2022/10/20", "BIOLOGY", taskList),
                    new Lesson("lesson 3", "10:30", "12:30", "2022/11/20", "MATHEMATICS", taskList),
                    new Lesson("some lesson", "10:30", "11:30", "2022/12/20", "BIOLOGY", taskList),
                    new Lesson("random name", "09:30", "10:30", "2022/11/21", "PHYSICS", taskList)
            ));
        } catch (ParseException e) {
            return new ArrayList<>();
        }
    }

    public static Lesson getSample1() throws ParseException {
        return new Lesson("SAMPLE 1", "10:00", "14:00", "2020/10/10", "MATHEMATICS", taskList);
    }

    public static Lesson getSample2() throws ParseException {
        return new Lesson("SAMPLE 2", "14:00", "16:00", "2020/10/10", "CHEMISTRY", taskList);
    }
    public static Lesson getSample3() throws ParseException {
        return new Lesson("SAMPLE 3", "13:00", "16:00", "2020/10/12", "MATHEMATICS", taskList);
    }
}
