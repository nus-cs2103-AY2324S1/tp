package seedu.address.seplendidui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.mapping.Mapping;

/**
 * An UI component that displays information of a {@code Mapping}.
 */
public class MappingItem extends UiPart<Region> {

    private static final String FXML = "MappingItemCard.fxml";

    public final Mapping mapping;

    @FXML
    private HBox mappingItemPane;
    @FXML
    private Label id;
    @FXML
    private Label localCode;
    @FXML
    private Label universityName;
    @FXML
    private Label partnerCode;

    /**
     * Creates a {@code MappingItem} with the given {@code Mapping}
     */
    public MappingItem(Mapping mapping, int displayedIndex) {
        super(FXML);
        this.mapping = mapping;
        id.setText(displayedIndex + ".");
        localCode.setText(mapping.getLocalCode().toString());
        universityName.setText(mapping.getUniversityName().toString());
        partnerCode.setText(mapping.getPartnerCode().toString());
    }
}
