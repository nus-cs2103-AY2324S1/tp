package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.company.Company;

/**
 * Panel containing the detail of company.
 */
public class CompanyDetailPanel extends UiPart<Region> {
    private static final String FXML = "CompanyDetailPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CompanyDetailPanel.class);

    @FXML
    private ListView<Company> companyDetailView;

    /**
     * Creates a {@code CompanyDetailPanel} with the given {@code ObservableList}.
     */
    public CompanyDetailPanel(ObservableList<Company> company) {
        super(FXML);
        companyDetailView.setItems(company);
        companyDetailView.setCellFactory(listView -> new CompanyDetailViewCell());
    }

    public ListView<Company> getCompanyDetailView() {
        return companyDetailView;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Company} using a {@code CompanyDetailCard}.
     */
    class CompanyDetailViewCell extends ListCell<Company> {
        @Override
        protected void updateItem(Company company, boolean empty) {
            super.updateItem(company, empty);

            if (empty || company == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CompanyDetailCard(company).getRoot());
            }
        }
    }

}
