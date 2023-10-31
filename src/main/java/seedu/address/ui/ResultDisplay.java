package seedu.address.ui;


import static java.util.Objects.requireNonNull;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;


/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private WebView resultDisplay;

    /**
     * Constructs a ResultDisplay and sets the text to wrap.
     */
    public ResultDisplay() {
        super(FXML);
    }


    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        // Convert the Markdown string to HTML
        Parser parser = Parser.builder().build();
        Document document = parser.parse(feedbackToUser);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String htmlContent = renderer.render(document);

        // Load the HTML content into the WebView
        String styledContent = "<!DOCTYPE html><html><head>"
                + "<style>"
                + "body { "
                + "background-color: transparent; "
                + "font-family: Arial; "
                + "font-size: 18px; "
                + "color: white; "
                + "}"
                + "</style>"
                + "</head><body>"
                + htmlContent
                + "</body></html>";

        resultDisplay.setPageFill(Color.web("#383838"));
        resultDisplay.getEngine().loadContent(styledContent);
    }

}
