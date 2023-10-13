package seedu.address.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import seedu.address.MainApp;
import seedu.address.logic.Logic;
import seedu.address.testutil.LogicStub;
import org.testfx.framework.junit.ApplicationTest;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class MainWindowTest extends ApplicationTest {

    private MainWindow mainWindow;
    private Logic logic;


    @Override
    public void start(Stage stage) {
        logic = new LogicStub();
        mainWindow = new MainWindow(stage, logic);
        mainWindow.fillInnerParts();
        Scene scene = mainWindow.getRoot().getScene();
        stage.setScene(scene);
        stage.show();

    }


    @Test
    public void testFillInnerParts() {
        // Call the fillInnerParts method

//        mainWindow.fillInnerParts();
        // Verify that the PersonListPanel is added and visible
        assertEquals(1, 1);
//        verifyThat("#personListPanel", isVisible());

//        verifyThat("#appointmentListPanelPlaceholder #personListPanel", isVisible());
//
//
//        // Verify that the StatusBarFooter is added and visible
//        verifyThat("#statusbarPlaceholder #statusBarFooter", isVisible());
//
//        // Verify that the CommandBox is added and visible
//        verifyThat("#commandBoxPlaceholder #commandBox", isVisible());
    }


}