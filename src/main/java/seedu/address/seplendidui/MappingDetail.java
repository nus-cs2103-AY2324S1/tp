package seedu.address.seplendidui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.model.mapping.Mapping;

/**
 * An UI component that displays information of a {@code Mapping}.
 */
public class MappingDetail extends UiPart<Region> {

    private static final String FXML = "MappingDetailCard.fxml";

    public final Mapping mapping;

    @FXML
    private HBox mappingDetailPane;
    @FXML
    private Label title;
    @FXML
    private Label localCode;
    @FXML
    private TextFlow localName;
    @FXML
    private Label localUnit;
    @FXML
    private Label partnerCode;
    @FXML
    private TextFlow partnerName;
    @FXML
    private Label partnerUnit;
    @FXML
    private TextFlow universityName;
    @FXML
    private TextFlow mappingMiscInformation;

    /**
     * Creates a {@code MappingDetail} with the given {@code Mapping}
     */
    public MappingDetail(Mapping mapping,
                         String localNameInput,
                         String localUnitInput,
                         String partnerNameInput,
                         String partnerUnitInput) {
        super(FXML);
        this.mapping = mapping;
        TextFlowHeightUpdater.setAutoFitText(universityName);
        TextFlowHeightUpdater.setAutoFitText(localName);
        TextFlowHeightUpdater.setAutoFitText(partnerName);
        TextFlowHeightUpdater.setAutoFitText(mappingMiscInformation);

        String localCodeInput = mapping.getLocalCode().toString();
        String partnerCodeInput = mapping.getPartnerCode().toString();

        universityName.getChildren().add(new Text(mapping.getUniversityName().toString()));
        title.setText(localCodeInput + " maps to " + partnerCodeInput);

        localCode.setText(localCodeInput);
        localName.getChildren().add(new Text(localNameInput));
        localUnit.setText("Units: " + localUnitInput);

        partnerCode.setText(partnerCodeInput);
        partnerName.getChildren().add(new Text(partnerNameInput));
        partnerUnit.setText("Units: " + partnerUnitInput);

        mappingMiscInformation.getChildren().add(new Text(mapping.getMappingMiscInformation().toString()));
    }
}
