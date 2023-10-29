package seedu.address.model.course;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import seedu.address.model.availability.TimeInterval;

/**
 * Contains a list of SOC courses.
 */
public class CourseData {
    private static final List<Course> courseList = List.of(
            new Course("Discrete Structures", "CS1231S",
                    Set.of(
                            new Lesson("Lecture", "CS1231S", MONDAY,
                                    new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0))),
                            new Lesson("Tutorial", "CS1231S", TUESDAY,
                                    new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0))),
                            new Lesson("Laboratory", "CS1231S", WEDNESDAY,
                                    new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0)))
                    )
            ),
            new Course("Programming Methodology", "CS1101S",
                    Set.of(
                            new Lesson("Lecture", "CS1101S", MONDAY,
                                    new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0))),
                            new Lesson("Tutorial", "CS1101S", TUESDAY,
                                    new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0))),
                            new Lesson("Laboratory", "CS1101S", WEDNESDAY,
                                    new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0)))
                    )
            ),
            new Course("Data Structures and Algorithms", "CS2040S",
                    Set.of(
                            new Lesson("Lecture", "CS2040S", MONDAY,
                                    new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0))),
                            new Lesson("Tutorial", "CS2040S", TUESDAY,
                                    new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0))),
                            new Lesson("Laboratory", "CS2040S", WEDNESDAY,
                                    new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0)))
                    )
            ),
            new Course("Computer Organisation", "CS2100",
                    Set.of(
                            new Lesson("Lecture", "CS2100", MONDAY,
                                    new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0))),
                            new Lesson("Tutorial", "CS2100", TUESDAY,
                                    new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0))),
                            new Lesson("Laboratory", "CS2100", WEDNESDAY,
                                    new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0)))
                    )
            ),
            new Course("Software Engineering", "CS2103T",
                    Set.of(
                            new Lesson("Lecture", "CS2103T", MONDAY,
                                    new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0))),
                            new Lesson("Tutorial", "CS2103T", TUESDAY,
                                    new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0))),
                            new Lesson("Laboratory", "CS2103T", WEDNESDAY,
                                    new TimeInterval(LocalTime.of(10, 0), LocalTime.of(12, 0)))
                    )
            )
    );

    public static List<Course> getCourseList() {
        return courseList;
    }

    public static String getCourseListString() {
        return "Available courses: " + courseList.stream()
                .map(Course::getCourseCode).reduce((courseCode1, courseCode2) -> courseCode1 + ", "
                        + courseCode2).orElse("");
    }
}
