package seedu.address.model.person.assignment.initialise;

import static javafx.collections.FXCollections.observableArrayList;

import javafx.collections.ObservableList;
import seedu.address.model.person.assignment.AssignmentName;

/**
 * Represents an initializer to create names for each assignment.
 */
public class AssignmentNameInitialise {
    private static ObservableList<AssignmentName> assignmentNames = observableArrayList();

    /**
     * Initialises mission names.
     */
    public void initMissions() {
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Rune Trials"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Rune Reading"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Beyond the Second Dimension"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Curve Introduction"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Curve Manipulation"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Beyond the First Dimension"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Premorseal Communications"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("POTS and Pans"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Musical Diversions"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Search and Rescue"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Sorting Things Out"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Robotic Trials"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Moving about on Planet Y"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Finding ELDRIC"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Know Your Environment"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Corrective Sky Surgery"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Reuse Your Pairs"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Streaming the Anomaly"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("The Anomaly in Focus"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("The Essence of the Source"));
    }

    /**
     * Initialises quest names.
     */
    public void initQuests() {
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Runic Carpets"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Colorful Carpets"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Functional Expressionism"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Cardioid Arrest"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Curvaceous Wizardry"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("The Magical Tone Matrix"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Echoes of the Past"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Grading a Sort"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Stellar Motion Detector"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("ELDROW - The Riddle"));
        AssignmentNameInitialise.assignmentNames.add(new AssignmentName("Rescue the Space Lizard"));
    }

    public AssignmentName getName(int index) {
        return assignmentNames.get(index);
    }

    public static ObservableList<AssignmentName> getAllNames() {
        return assignmentNames;
    }

    public boolean contains(AssignmentName assignmentName) {
        return assignmentNames.contains(assignmentName);
    }

    public int size() {
        return assignmentNames.size();
    }
}
