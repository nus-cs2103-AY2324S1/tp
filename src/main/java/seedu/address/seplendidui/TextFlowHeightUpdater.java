package seedu.address.seplendidui;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.text.TextFlow;


/**
 * A utility class for managing the height of a JavaFX TextFlow.
 */
public class TextFlowHeightUpdater {

    /**
     * Initializes auto-updating behavior for the provided TextFlow.
     * This method sets up listeners for accessibleTextProperty and widthProperty to automatically update the height.
     *
     * @param textFlow The TextFlow to be auto-updated.
     */
    public static void setAutoFitText(TextFlow textFlow) {
        updateHeight(textFlow);
        textFlow.accessibleTextProperty().addListener((observable, oldValue, newValue) -> updateHeight(textFlow));
        textFlow.widthProperty().addListener((observable, oldValue, newValue) -> updateHeight(textFlow));
    }

    /**
     * Updates the preferred height of the provided TextFlow based on its content.
     *
     * @param textFlow The TextFlow to have its height updated.
     */
    public static void updateHeight(TextFlow textFlow) {
        double totalHeight = calculateTotalHeight(textFlow.getChildren());
        textFlow.setPrefHeight(totalHeight);
    }

    /**
     * Calculates the total height of a list of Nodes.
     *
     * @param nodes The list of Nodes to calculate the total height.
     * @return The total height of the Nodes.
     */
    private static double calculateTotalHeight(List<Node> nodes) {
        double totalHeight = 0;
        for (Node node : nodes) {
            totalHeight += Math.ceil(node.getBoundsInLocal().getHeight());
        }
        return totalHeight;
    }
}
