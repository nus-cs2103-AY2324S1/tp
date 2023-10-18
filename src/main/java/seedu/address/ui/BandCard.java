package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.band.Band;

import java.util.Comparator;

/**
 * An UI component that displays information of a {@code Band}.
 */
public class BandCard extends UiPart<Region> {

    private static final String FXML = "BandListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Band band;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane genres;


    /**
     * Creates a {@code BandCode} with the given {@code Band} and index to display.
     */
    public BandCard(Band band, int displayedIndex) {
        super(FXML);
        this.band = band;
        id.setText(displayedIndex + ". ");
        name.setText(band.getName().fullName);
        band.getGenres().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> genres.getChildren().add(new Label(tag.tagName)));
    }
}
