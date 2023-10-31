package networkbook.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import networkbook.MainApp;

@ExtendWith(ApplicationExtension.class)
public class CommandBoxTest {
    @BeforeAll
    public static void setupForHeadlessTesting() {
        HeadlessHelper.setupForHeadlessTesting();
    }

    @BeforeEach
    public void setup() throws Exception {
        ApplicationTest.launch(MainApp.class);
    }

    @Test
    public void shortcut_robotPressesCtrlN_create(FxRobot robot) {
        TextField textField = robot.lookup("#commandTextField").query();
        assertNotNull(textField);
        robot.clickOn(textField);
        robot.press(KeyCode.SHORTCUT, KeyCode.N);
        assertEquals(textField.getText(), "create ");
    }
    @Test
    public void shortcut_robotPressesCtrlG_edit(FxRobot robot) {
        TextField textField = robot.lookup("#commandTextField").query();
        assertNotNull(textField);
        robot.clickOn(textField);
        robot.press(KeyCode.SHORTCUT, KeyCode.G);
        assertEquals(textField.getText(), "edit ");
    }

    @Test
    public void shortcut_robotPressesCtrlF_find(FxRobot robot) {
        TextField textField = robot.lookup("#commandTextField").query();
        assertNotNull(textField);
        robot.clickOn(textField);
        robot.press(KeyCode.SHORTCUT, KeyCode.F);
        assertEquals(textField.getText(), "find ");
    }

    @Test
    public void shortcut_robotPressesCtrlU_undo(FxRobot robot) {
        TextField textField = robot.lookup("#commandTextField").query();
        assertNotNull(textField);
        robot.clickOn(textField);
        robot.press(KeyCode.SHORTCUT, KeyCode.U);
        assertEquals(textField.getText(), "undo");
    }

    @Test
    public void shortcut_robotPressesCtrlR_redo(FxRobot robot) {
        TextField textField = robot.lookup("#commandTextField").query();
        assertNotNull(textField);
        robot.clickOn(textField);
        robot.press(KeyCode.SHORTCUT, KeyCode.R);
        assertEquals(textField.getText(), "redo");
    }

    @Test
    public void shortcut_robotPressesUpAndDown_navigateCommandHistory(FxRobot robot) {
        // NOTE: Ensure model contains no person with name "a" before running this test
        TextField textField = robot.lookup("#commandTextField").query();
        assertNotNull(textField);
        robot.clickOn(textField);
        robot.write("create /name a").press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.write("undo").press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.write("input 3").press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.press(KeyCode.UP).release(KeyCode.UP).press(KeyCode.UP).release(KeyCode.UP);
        assertEquals(textField.getText(), "undo");
        robot.press(KeyCode.UP).release(KeyCode.UP);
        assertEquals(textField.getText(), "create /name a");
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        assertEquals(textField.getText(), "undo");
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN).press(KeyCode.DOWN).release(KeyCode.DOWN)
                .press(KeyCode.DOWN).release(KeyCode.DOWN);
        assertEquals(textField.getText(), "input 3");
    }

    @Test
    public void style_validAndInvalidCommands_changeStyle(FxRobot robot) {
        // NOTE: Ensure model contains no person with name "a" before running this test
        TextField textField = robot.lookup("#commandTextField").query();
        assertNotNull(textField);
        robot.clickOn(textField);
        robot.write("create /name ").press(KeyCode.ENTER).release(KeyCode.ENTER);
        assertTrue(textField.getStyleClass().contains(CommandBox.ERROR_STYLE_CLASS));
        robot.write("a").press(KeyCode.ENTER).release(KeyCode.ENTER);
        assertFalse(textField.getStyleClass().contains(CommandBox.ERROR_STYLE_CLASS));
        robot.write("undo").press(KeyCode.ENTER).release(KeyCode.ENTER);
        assertFalse(textField.getStyleClass().contains(CommandBox.ERROR_STYLE_CLASS));
    }
}
