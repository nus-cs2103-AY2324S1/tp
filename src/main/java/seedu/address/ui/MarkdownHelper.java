package seedu.address.ui;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Helper class for the markdown functionality
 */
public class MarkdownHelper {

    public static void setTextWithMarkdown(TextFlow textFlowControl, String content, Color color) {
        textFlowControl.getChildren().clear(); // clear existing children

        // Initialize flags for formatting
        boolean isBold = false;
        boolean isItalic = false;
        boolean isUnderlined = false;
        boolean awaitingBoldContent = false;
        boolean awaitingItalicContent = false;
        boolean awaitingUnderlineContent = false;

        StringBuilder currentSegment = new StringBuilder();

        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);

            // Check for underline tags
            if (c == '<' && content.startsWith("<u>", i)) {
                if (!awaitingUnderlineContent && currentSegment.length() == 0) {
                    awaitingUnderlineContent = true;
                } else {
                    addTextSegment(textFlowControl, currentSegment.toString(), isBold, isItalic, isUnderlined, color);
                    currentSegment.setLength(0);
                    isUnderlined = !isUnderlined;
                    awaitingUnderlineContent = false;
                }
                i += 2; // Skip over the <u> tag
            } else if (c == '<' && content.startsWith("</u>", i)) {
                if (awaitingUnderlineContent) {
                    awaitingUnderlineContent = false;
                } else {
                    addTextSegment(textFlowControl, currentSegment.toString(), isBold, isItalic, isUnderlined, color);
                    currentSegment.setLength(0);
                    isUnderlined = false;
                }
                i += 3; // Skip over the </u> tag
            } else if (c == '*') {
                if (i < content.length() - 1 && content.charAt(i + 1) == '*') {
                    if (!awaitingBoldContent && currentSegment.length() == 0) {
                        awaitingBoldContent = true;
                    } else {
                        addTextSegment(textFlowControl, currentSegment.toString(), isBold, isItalic,
                                isUnderlined, color);
                        currentSegment.setLength(0);
                        isBold = !isBold;
                        awaitingBoldContent = false;
                    }
                    i++; // Skip the next asterisk
                } else {
                    if (!awaitingItalicContent && currentSegment.length() == 0) {
                        awaitingItalicContent = true;
                    } else {
                        addTextSegment(textFlowControl, currentSegment.toString(), isBold, isItalic,
                                isUnderlined, color);
                        currentSegment.setLength(0);
                        isItalic = !isItalic;
                        awaitingItalicContent = false;
                    }
                }
            } else {
                if (awaitingBoldContent && isBold) {
                    awaitingBoldContent = false;
                }
                if (awaitingItalicContent && isItalic) {
                    awaitingItalicContent = false;
                }
                if (awaitingUnderlineContent && isUnderlined) {
                    awaitingUnderlineContent = false;
                }
                currentSegment.append(c);
            }
        }

        // Handle any remaining text
        if (currentSegment.length() > 0) {
            addTextSegment(textFlowControl, currentSegment.toString(), isBold, isItalic, isUnderlined, color);
        }
    }

    /**
     * Takes in a textflow reference object and its content and updates the markdown accordingly
     * @param textFlowControl reference object
     * @param content actual string content to be displayed
     * @param isBold boolean to see if there is bold
     * @param isItalic boolean to see if there is italics
     * @param isUnderlined boolean to see if there is underline
     * @param color color of the font
     */
    public static void addTextSegment(TextFlow textFlowControl, String content,
                                boolean isBold, boolean isItalic, boolean isUnderlined, Color color) {
        Text textSegment = new Text(content);

        if (isBold && isItalic) {
            textSegment.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 15));
        } else if (isBold) {
            textSegment.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        } else if (isItalic) {
            textSegment.setFont(Font.font("Arial", FontPosture.ITALIC, 15));
        } else {
            textSegment.setFont(Font.font("Arial", 15));
        }

        if (isUnderlined) {
            textSegment.setUnderline(true);
        }

        textSegment.setFill(color); // Set text color to black

        textFlowControl.getChildren().add(textSegment);
    }
}
