package seedu.address.model.displayable;

import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * Represents a displayable that has an ability to display as a UIPart and can do two lesser equality check
 * with methods named isSameDisplayable and isSimilarDisplayable. All displayables must be able to furnish a main name.
 */
public interface Displayable {
    Name getName();
    UiPart<Region> display(int index);
    boolean isSameDisplayable(Displayable displayable);
    boolean isSimilarDisplayable(Displayable displayable);
}
