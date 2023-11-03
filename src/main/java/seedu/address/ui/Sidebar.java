package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * A UI component representing a sidebar in the application interface. The sidebar typically
 * contains user-related information, such as the username.
 */
public class Sidebar extends UiPart<Region> {
    private static final String FXML = "Sidebar.fxml";

    @FXML
    private Label username;

    @FXML
    private ToggleButton toggleEmployees;

    @FXML
    private ToggleButton toggleDepartments;

    @FXML
    private StackPane listPanelContainer;
    @FXML
    private StackPane detailsContainer;

    private ToggleGroup toggleGroup;
    private EmployeeListPanel employeeListPanel;
    private DepartmentListPanel departmentListPanel;
    private ProfileDetails profileDetails;
    private DepartmentDetails departmentDetails;

    /**
     * Creates a new {@code Sidebar} instance with the specified FXML resource.
     */
    public Sidebar(StackPane listPanelContainer, EmployeeListPanel employeeListPanel,
                   DepartmentListPanel departmentListPanel, StackPane detailsContainer,
                   ProfileDetails profileDetails, DepartmentDetails departmentDetails) {

        super(FXML);
        this.employeeListPanel = employeeListPanel;
        this.departmentListPanel = departmentListPanel;
        this.listPanelContainer = listPanelContainer;
        this.detailsContainer = detailsContainer;
        this.profileDetails = profileDetails;
        this.departmentDetails = departmentDetails;
        toggleDepartments.setOnAction(event -> onClickToggleDepartments());
        toggleEmployees.setOnAction(event -> onClickToggleEmployees());
        toggleGroup = new ToggleGroup();
        toggleDepartments.setToggleGroup(toggleGroup);
        toggleEmployees.setToggleGroup(toggleGroup);
        toggleEmployees.setSelected(true);

        toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal == null) {
                if (observable.getValue() != null) {
                    ToggleButton originalButton = (ToggleButton) observable.getValue();
                    originalButton.setSelected(true);
                }
                return;
            }
            String toggledButton = ((ToggleButton) newVal).getText();
            switch (toggledButton) {
            case ("Employees"):
                if (listPanelContainer.getChildren().contains(departmentListPanel.getRoot())) {
                    listPanelContainer.getChildren().remove(departmentListPanel.getRoot());
                }
                if (!listPanelContainer.getChildren().contains(employeeListPanel.getRoot())) {
                    listPanelContainer.getChildren().add(employeeListPanel.getRoot());
                }
                if (detailsContainer.getChildren().contains(departmentDetails.getRoot())) {
                    detailsContainer.getChildren().remove(departmentDetails.getRoot());
                }
                if (!detailsContainer.getChildren().contains(profileDetails.getRoot())) {
                    detailsContainer.getChildren().add(profileDetails.getRoot());
                }
                break;
            case ("Departments"):
                if (listPanelContainer.getChildren().contains(employeeListPanel.getRoot())) {
                    listPanelContainer.getChildren().remove(employeeListPanel.getRoot());
                }
                if (!listPanelContainer.getChildren().contains(departmentListPanel.getRoot())) {
                    listPanelContainer.getChildren().add(departmentListPanel.getRoot());
                }
                if (detailsContainer.getChildren().contains(profileDetails.getRoot())) {
                    detailsContainer.getChildren().remove(profileDetails.getRoot());
                }
                if (!detailsContainer.getChildren().contains(departmentDetails.getRoot())) {
                    detailsContainer.getChildren().add(departmentDetails.getRoot());
                }
                break;
            default:
                break;
            }
        });
    }

    /**
     * Sets the username to be displayed in the sidebar.
     *
     * @param name The username to be displayed.
     */
    public void setUsername(String name) {
        username.setText(name);
    }

    private void onClickToggleDepartments() {
        if (listPanelContainer.getChildren().contains(employeeListPanel.getRoot())) {
            listPanelContainer.getChildren().remove(employeeListPanel.getRoot());
        }
        if (!listPanelContainer.getChildren().contains(departmentListPanel.getRoot())) {
            listPanelContainer.getChildren().add(departmentListPanel.getRoot());
        }
        if (detailsContainer.getChildren().contains(profileDetails.getRoot())) {
            detailsContainer.getChildren().remove(profileDetails.getRoot());
        }
        if (!detailsContainer.getChildren().contains(departmentDetails.getRoot())) {
            detailsContainer.getChildren().add(departmentDetails.getRoot());
        }
    }

    private void onClickToggleEmployees() {
        if (listPanelContainer.getChildren().contains(departmentListPanel.getRoot())) {
            listPanelContainer.getChildren().remove(departmentListPanel.getRoot());
        }
        if (!listPanelContainer.getChildren().contains(employeeListPanel.getRoot())) {
            listPanelContainer.getChildren().add(employeeListPanel.getRoot());
        }
        if (detailsContainer.getChildren().contains(departmentDetails.getRoot())) {
            detailsContainer.getChildren().remove(departmentDetails.getRoot());
        }
        if (!detailsContainer.getChildren().contains(profileDetails.getRoot())) {
            detailsContainer.getChildren().add(profileDetails.getRoot());
        }
    }
}
