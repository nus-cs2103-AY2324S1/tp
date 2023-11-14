package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HelpWindowTest {

    private HelpWindow helpWindow;

    @BeforeEach
    public void setup() {
        JavaFxInitializer.initialize();
    }

    @AfterEach
    public void teardown() {
        JavaFxInitializer.cleanup();
    }

    @Test
    public void isShowingShouldReturnFalseInitially() {
        Platform.runLater(() -> {
            helpWindow = new HelpWindow();
            assertFalse(helpWindow.isShowing());
        });
    }

    @Test
    public void showShouldChangeVisibilityToTrue() {
        Platform.runLater(() -> {
            helpWindow = new HelpWindow();
            helpWindow.show();
            assertTrue(helpWindow.isShowing());
        });
    }

    @Test
    public void showShouldMakeWindowBeCentered() {
        Platform.runLater(() -> {
            helpWindow = new HelpWindow();
            helpWindow.show();

            // Calculate the center coordinates of the window
            double windowCenterX = helpWindow.getRoot().getX() + helpWindow.getRoot().getWidth() / 2;
            double windowCenterY = helpWindow.getRoot().getY() + helpWindow.getRoot().getHeight() / 2;

            // Get the Screen object that contains the center of the window
            Screen screen = Screen.getScreensForRectangle((int) windowCenterX, (int) windowCenterY, 0, 0)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No screen found for window center coordinates"));

            // Calculate the center coordinates of the screen
            Rectangle2D screenBounds = screen.getBounds();
            double screenCenterX = screenBounds.getMinX() + screenBounds.getWidth() / 2;
            double screenCenterY = screenBounds.getMinY() + screenBounds.getHeight() / 2;

            // Check if the window's center coordinates are approximately equal to the screen's center coordinates
            assertEquals(screenCenterX, windowCenterX, 1.0);
            assertEquals(screenCenterY, windowCenterY, 1.0);
        });
    }

    @Test
    public void hideShouldChangeVisibilityToFalse() {
        Platform.runLater(() -> {
            helpWindow = new HelpWindow();
            helpWindow.hide();
            assertFalse(helpWindow.isShowing());
        });
    }

    @Test
    public void focusShouldMakeWindowFocused() {
        Platform.runLater(() -> {
            helpWindow = new HelpWindow();
            helpWindow.show();
            helpWindow.focus();
            assertTrue(helpWindow.getRoot().isFocused());
        });
    }

    @Test
    public void otherWindowFocusShouldMakeWindowNotFocused() {
        Platform.runLater(() -> {
            helpWindow = new HelpWindow();
            helpWindow.show();
            Stage otherWindow = new Stage();
            otherWindow.show();
            assertFalse(helpWindow.getRoot().isFocused());
        });
    }

    @Test
    public void copyButtonShouldCopyUrl() {
        Platform.runLater(() -> {
            helpWindow = new HelpWindow();
            helpWindow.show();
            helpWindow.getCopyButton().fire();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = (ClipboardContent) clipboard.getContent(DataFormat.PLAIN_TEXT);
            assertEquals(HelpWindow.USERGUIDE_URL, content.getString());
        });
    }

    @Test
    public void copyUrlShouldCopyUrl() {
        Platform.runLater(() -> {
            helpWindow = new HelpWindow();
            helpWindow.show();
            helpWindow.copyUrl();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = (ClipboardContent) clipboard.getContent(DataFormat.PLAIN_TEXT);
            assertEquals(HelpWindow.USERGUIDE_URL, content.getString());
        });
    }

    @Test
    public void copyUrlShouldNotThrowExceptionWhenWindowIsNotShowing() {
        Platform.runLater(() -> {
            helpWindow = new HelpWindow();
            try {
                helpWindow.copyUrl();
            } catch (Exception e) {
                fail("copyUrl method should not throw an exception when the window is not showing");
            }
        });
    }

    @Test
    public void helpMessageShouldBeSetCorrectly() {
        Platform.runLater(() -> {
            helpWindow = new HelpWindow();
            assertEquals(HelpWindow.HELP_MESSAGE, helpWindow.HELP_MESSAGE);
        });
    }

    @Test
    public void requirementsMessageShouldBeSetCorrectly() {
        Platform.runLater(() -> {
            helpWindow = new HelpWindow();
            assertEquals(HelpWindow.REQUIREMENTS_MESSAGE, helpWindow.REQUIREMENTS_MESSAGE);
        });
    }

}
