package networkbook.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;



public class KeyboardShortcutUtilTest {
    private static final KeyCharacterCombination combiF = new KeyCharacterCombination("F");
    private static final KeyCharacterCombination combiShortcutF = new KeyCharacterCombination("F",
            KeyCombination.SHORTCUT_DOWN);
    private static final KeyCharacterCombination combiShiftF = new KeyCharacterCombination("F",
            KeyCombination.SHIFT_DOWN);
    private static final KeyCharacterCombination combiAltF = new KeyCharacterCombination("F",
            KeyCombination.ALT_DOWN);

    private static final KeyEvent eventF = new KeyEvent(KeyEvent.KEY_PRESSED,
            "f", "f", KeyCode.F, false, false, false, false);
    private static final KeyEvent eventN = new KeyEvent(KeyEvent.KEY_PRESSED,
            "n", "n", KeyCode.N, false, false, false, false);
    private static final KeyEvent eventShortcutF = new KeyEvent(KeyEvent.KEY_PRESSED,
            "f", "f", KeyCode.F, false, true, false, true);
    private static final KeyEvent eventShortcutN = new KeyEvent(KeyEvent.KEY_PRESSED,
            "n", "n", KeyCode.N, false, true, false, true);
    private static final KeyEvent eventShiftF = new KeyEvent(KeyEvent.KEY_PRESSED,
            "f", "f", KeyCode.F, true, false, false, false);
    private static final KeyEvent eventAltF = new KeyEvent(KeyEvent.KEY_PRESSED,
            "f", "f", KeyCode.F, false, false, true, false);
    private static final KeyEvent eventShiftAltF = new KeyEvent(KeyEvent.KEY_PRESSED,
            "f", "f", KeyCode.F, true, false, true, false);

    @Test
    public void shortcutMatchEvent_match_true() {
        assertTrue(KeyboardShortcutUtil.shortcutMatchEvent(combiF, eventF));
        assertTrue(KeyboardShortcutUtil.shortcutMatchEvent(combiShortcutF, eventShortcutF));
        assertTrue(KeyboardShortcutUtil.shortcutMatchEvent(combiShiftF, eventShiftF));
        assertTrue(KeyboardShortcutUtil.shortcutMatchEvent(combiAltF, eventAltF));
    }

    @Test
    public void shortcutMatchEvent_codeDoesNotMatch_false() {
        assertFalse(KeyboardShortcutUtil.shortcutMatchEvent(combiF, eventN));
        assertFalse(KeyboardShortcutUtil.shortcutMatchEvent(combiShortcutF, eventShortcutN));
    }

    @Test
    public void shortcutMatchEvent_modifierDoesNotMatch_false() {
        assertFalse(KeyboardShortcutUtil.shortcutMatchEvent(combiF, eventShortcutF));
        assertFalse(KeyboardShortcutUtil.shortcutMatchEvent(combiF, eventShiftF));
        assertFalse(KeyboardShortcutUtil.shortcutMatchEvent(combiF, eventAltF));
        assertFalse(KeyboardShortcutUtil.shortcutMatchEvent(combiShortcutF, eventShiftF));
        assertFalse(KeyboardShortcutUtil.shortcutMatchEvent(combiAltF, eventShiftF));
        assertFalse(KeyboardShortcutUtil.shortcutMatchEvent(combiShortcutF, eventAltF));
        assertFalse(KeyboardShortcutUtil.shortcutMatchEvent(combiShiftF, eventShiftAltF));
        assertFalse(KeyboardShortcutUtil.shortcutMatchEvent(combiAltF, eventShiftAltF));
    }
}
