package seedu.address.ui;

import java.util.concurrent.TimeoutException;

import static org.testfx.api.FxAssert.verifyThat;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import seedu.address.MainApp;


public class MainAppTest extends ApplicationTest {

    private MainApp mainApp;

    @Before
    public void setUp() throws Exception {
        ApplicationTest.launch(MainApp.class);
    }

    @Override
    public void start(Stage stage) {
        mainApp = new MainApp();
        mainApp.start(stage);
        stage.show();
    }

    @After
    public void afterEachTest() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    public <T extends Node> T find (String query) {
        return (T) lookup(query).queryAll().iterator().next();
    }

    @Test
    public void fillInnerPartsTest() {
        verifyThat("#personListPanel", NodeMatchers.isVisible());
        verifyThat("#resultDisplay", NodeMatchers.isVisible());
        verifyThat("#statusBarFooter", NodeMatchers.isVisible());
        verifyThat("#commandBox", NodeMatchers.isVisible());
    }
}
