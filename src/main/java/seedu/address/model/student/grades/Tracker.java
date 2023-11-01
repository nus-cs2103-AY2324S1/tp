package seedu.address.model.student.grades;

import java.util.List;

/**
 * Represents a tracker for a particular class grade.
 */
public interface Tracker {

    /**
     * Returns the average result of objects in the tracker.
     *
     * @return Average result of objects in the tracker.
     */
    public double getPercentage();

    /**
     * Returns a Json Friendly representation of the tracker.
     *
     * @return Json Friendly representation of the tracker.
     */
    public List getJson();
}
