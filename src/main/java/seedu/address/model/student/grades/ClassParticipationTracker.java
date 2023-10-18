package seedu.address.model.student.grades;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import seedu.address.commons.core.index.Index;

/**
 * Represents a Student's class participation grades
 * in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassParticipationTracker(int)}
 */
public class ClassParticipationTracker {

    public static final String MESSAGE_CONSTRAINTS = "Class Participation needs to be a positive integer";

    private final ClassParticipation[] classPartList;

    /**
     * Constructs an {@code ClassParticipationTracker}.
     *
     * @param numOfTut A valid integer for the number of tutorials in a class.
     *
     */
    public ClassParticipationTracker(int numOfTut) {
        requireNonNull(numOfTut);
        checkArgument(isValidClassPart(numOfTut), MESSAGE_CONSTRAINTS);
        classPartList = new ClassParticipation[numOfTut];
        IntStream.range(0, numOfTut).forEach(i -> classPartList[i] = new ClassParticipation());
    }

    /**
     * Constructs an {@code ClassParticipationTracker}. With a given class participation tracker list.
     *
     * @param classParticipationTracker A list of booleans representing the class participation.
     */
    public ClassParticipationTracker(List<Boolean> classParticipationTracker) {
        requireNonNull(classParticipationTracker);
        classPartList = new ClassParticipation[classParticipationTracker.size()];
        IntStream.range(0, classParticipationTracker.size())
                .forEach(i -> classPartList[i] = new ClassParticipation(classParticipationTracker.get(i)));
    }

    /**
     * Returns true if a given int is a valid number of tutorials.
     */
    public static boolean isValidClassPart(int numOfTut) {
        return numOfTut >= 0;
    }

    /**
     * Marks the class participation of a student.
     *
     * @param tutNum The tutorial number.
     */
    public void markParticipated(Index tutNum) {
        classPartList[tutNum.getZeroBased()].mark();
    }

    /**
     * Unmarks the class participation of a student.
     *
     * @param tutNum The tutorial number.
     */
    public void markDidNotParticipate(Index tutNum) {
        classPartList[tutNum.getZeroBased()].unmark();
    }

    /**
     * Marks the class participation of a student.
     *
     * @param tutNum The tutorial number.
     * @param participated Whether the student participated.
     */
    public void markParticipation(Index tutNum, boolean participated) {
        if (participated) {
            markParticipated(tutNum);
        } else {
            markDidNotParticipate(tutNum);
        }
    }

    /**
     * Returns a Json friendly version of the classParticipationTracker.
     */
    public List<Boolean> getJsonClassParticipationTracker() {
        List<Boolean> classParticipationTracker = new ArrayList<>();
        for (ClassParticipation classParticipation : classPartList) {
            classParticipationTracker.add(classParticipation.getParticipated());
        }
        return classParticipationTracker;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("Class Part Tracker:\n");
        for (int i = 0; i < classPartList.length; i++) {
            ret.append("Tutorial " + (i + 1) + ": " + classPartList[i].toString() + "\n");
        }
        return ret.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassParticipationTracker)) {
            return false;
        }

        ClassParticipationTracker otherClassParticipationTracker = (ClassParticipationTracker) other;
        return Arrays.equals(classPartList, otherClassParticipationTracker.classPartList);
    }

    @Override
    public int hashCode() {
        return classPartList.hashCode();
    }

}
