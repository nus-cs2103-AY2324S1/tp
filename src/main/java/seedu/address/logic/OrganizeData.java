package seedu.address.logic;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.person.Gender;
import seedu.address.model.person.SecLevel;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentIsGenderPredicate;
import seedu.address.model.person.StudentIsSecLevelPredicate;
import seedu.address.model.person.StudentTakesSubjectPredicate;
import seedu.address.model.tag.EnrolDate;
import seedu.address.model.tag.Subject;

/**
 * Class for organizing data by different attributes.
 */
public class OrganizeData {
    /**
     * Maps gender with the number of students
     * @param model instance of Model subclass, e.g. ModelManager instance
     * @return a map of gender with the number of students
     */
    public static Map<String, Integer> byGender(Model model) {
        Map<String, Integer> columnValueMapping = new HashMap<>();

        String[] titles = new String[]{Gender.MALE, Gender.FEMALE};

        ObservableList<Student> studentList = model.getFilteredPersonList();

        StudentIsGenderPredicate isMalePredicate = new StudentIsGenderPredicate(new Gender("M"));
        StudentIsGenderPredicate isFemalePredicate = new StudentIsGenderPredicate(new Gender("F"));
        Stream<StudentIsGenderPredicate> predicateStream = Stream.of(isMalePredicate, isFemalePredicate);

        int[] values = predicateStream
                .map(p -> studentList.stream().filter(p).count())
                .mapToInt(Long::intValue)
                .toArray();

        for (int i = 0; i < titles.length; i++) {
            columnValueMapping.put(titles[i], values[i]);
        }

        return columnValueMapping;
    }

    /**
     * Maps sec level with the number of students
     * @param model instance of Model subclass, e.g. ModelManager instance
     * @return a map of sec level with the number of students
     */
    public static Map<String, Integer> bySecLevel(Model model) {
        Map<String, Integer> columnValueMapping = new HashMap<>();
        String[] titles = new String[] {SecLevel.SEC1, SecLevel.SEC2, SecLevel.SEC3, SecLevel.SEC4};

        ObservableList<Student> studentList = model.getFilteredPersonList();

        StudentIsSecLevelPredicate isSec1Predicate = new StudentIsSecLevelPredicate(new SecLevel("1"));
        StudentIsSecLevelPredicate isSec2Predicate = new StudentIsSecLevelPredicate(new SecLevel("2"));
        StudentIsSecLevelPredicate isSec3Predicate = new StudentIsSecLevelPredicate(new SecLevel("3"));
        StudentIsSecLevelPredicate isSec4Predicate = new StudentIsSecLevelPredicate(new SecLevel("4"));

        Stream<StudentIsSecLevelPredicate> predicateStream = Stream.of(
                isSec1Predicate,
                isSec2Predicate,
                isSec3Predicate,
                isSec4Predicate
        );

        int[] values = predicateStream
                .map(p -> studentList.stream().filter(p).count())
                .mapToInt(Long::intValue)
                .toArray();

        for (int i = 0; i < titles.length; i++) {
            columnValueMapping.put(titles[i], values[i]);
        }
        return columnValueMapping;
    }

    /**
     * Maps subject with the number of students
     * @param model instance of Model subclass, e.g. ModelManager instance
     * @return a map of subject with the number of students
     */
    public static Map<String, Integer> bySubject(Model model) {
        Map<String, Integer> columnValueMapping = new HashMap<>();

        String[] titles = new String[] {Subject.ENG, Subject.CHI, Subject.EMATH, Subject.AMATH,
            Subject.PHY, Subject.CHEMI, Subject.BIO, Subject.GEOG,
            Subject.HIST, Subject.SOC};

        ObservableList<Student> studentList = model.getFilteredPersonList();

        StudentTakesSubjectPredicate takeEngPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.ENG));
        StudentTakesSubjectPredicate takeChiPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.CHI));
        StudentTakesSubjectPredicate takeEMathPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.EMATH));
        StudentTakesSubjectPredicate takeAMathPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.AMATH));
        StudentTakesSubjectPredicate takePhyPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.PHY));
        StudentTakesSubjectPredicate takeChemiPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.CHEMI));
        StudentTakesSubjectPredicate takeBioPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.BIO));
        StudentTakesSubjectPredicate takeGeogPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.GEOG));
        StudentTakesSubjectPredicate takeHistPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.HIST));
        StudentTakesSubjectPredicate takeSocPredicate = new StudentTakesSubjectPredicate(new Subject(Subject.SOC));

        Stream<StudentTakesSubjectPredicate> predicateStream = Stream.of(
                takeEngPredicate,
                takeChiPredicate,
                takeEMathPredicate,
                takeAMathPredicate,
                takePhyPredicate,
                takeChemiPredicate,
                takeBioPredicate,
                takeGeogPredicate,
                takeHistPredicate,
                takeSocPredicate
        );

        int[] values = predicateStream
                .map(p -> studentList.stream().filter(p).count())
                .mapToInt(Long::intValue)
                .toArray();

        for (int i = 0; i < titles.length; i++) {
            columnValueMapping.put(titles[i], values[i]);
        }

        return columnValueMapping;
    }

    /**
     * Maps Month with the number of students
     * @param model instance of Model subclass, e.g. ModelManager instance
     * @return a map of Month in String with the number of students
     */
    public static Map<String, Integer> byEnrolDate(Model model, int year) {
        Student[] students = model.getFilteredPersonList().toArray(new Student[0]);
        Map<String, Integer> columnValueMapping = new HashMap<>();
        String[] titles = new String[] {EnrolDate.JAN, EnrolDate.FEB, EnrolDate.MAR, EnrolDate.APR, EnrolDate.MAY,
            EnrolDate.JUN, EnrolDate.JUL, EnrolDate.AUG, EnrolDate.SEP, EnrolDate.OCT, EnrolDate.NOV, EnrolDate.DEC};
        int[] counts = new int[13];

        for (Student student : students) {
            Set<Subject> subjects = student.getSubjects();
            int earliestMonthValue = 13;
            int earliestYearValue = Integer.MAX_VALUE;
            for (Subject subject : subjects) {
                YearMonth yearMonth = subject.getYearMonth();
                int monthValue = yearMonth.getMonthValue();
                int yearValue = yearMonth.getYear();
                if (yearValue <= earliestYearValue && monthValue <= earliestMonthValue) {
                    earliestMonthValue = monthValue;
                    earliestYearValue = yearValue;
                }
            }
            if (earliestYearValue == year) {
                counts[earliestMonthValue]++;
            }
        }

        for (int i = 1; i < counts.length; i++) {
            columnValueMapping.put(titles[i - 1], counts[i]);
        }
        return columnValueMapping;
    }

}
