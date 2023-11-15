//@@author ruiyangzh-unused
//This class was meant to be representative of a houseCard in the final application. Not enough time was available
//to implement the automatching buyers-with-houses feature, so we decided to cut this class and
//focus on core features instead.
package seedu.address.ui;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.displayable.house.House;

/**
 * An  UI component that displays information of a {@code House} according to a provided
 * FXML scheme.
 */
public class HouseCard extends UiPart<Region> {
    private static final String FXML_CONFIG = "HouseListCard.fxml";
    @FXML
    private HBox cardPanel;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label address;
    @FXML
    private Label price;
    @FXML
    private Label info;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code HouseCard} with the given {@code House} and index to display.
     */
    public HouseCard(House house, int displayedIndex) {
        super(FXML_CONFIG);
        requireAllNonNull(displayedIndex, house);
        id.setText(displayedIndex + ". ");
        name.setText(house.getName().fullName);
        price.setText(house.getPrice().value);
        address.setText(house.getAddress().value);
        info.setText(house.getHouseInfo().toString());
        house.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
