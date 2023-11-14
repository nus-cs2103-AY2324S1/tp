package seedu.address.ui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * A custom UI component that displays a colored text entry.
 */
public class ColoredTextEntry extends StackPane {
    /**
     * Creates a colored text entry.
     * @param text The text content to display.
     * @param color The color of the background fillout.
     */
    public ColoredTextEntry(String text, String color) {
        Text textNode = new Text(text);
        textNode.setFont(Font.font("Segoe UI Semibold", FontWeight.BOLD, 10));
        textNode.setFill(Color.WHITE);
        Rectangle rectangle = new Rectangle(textNode.getLayoutBounds().getWidth() + 15,
                textNode.getLayoutBounds().getHeight() + 10);
        rectangle.setArcWidth(20); // Customize the arc width to make it curved.
        rectangle.setArcHeight(20); // Customize the arc height to make it curved.
        rectangle.setFill(Color.web(color));
        getChildren().addAll(rectangle, textNode);
    }

    public static ColoredTextEntry build(String text, String color) {
        return new ColoredTextEntry(text, color);
    }
}
