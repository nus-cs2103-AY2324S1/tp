package networkbook.ui;

import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

/**
 * Utility class containing useful shared functions related to keyboard shortcuts.
 */
public class KeyboardShortcutUtil {

    /**
     * Checks if a shortcut represented by a {@code KeyCharacterCombination} matches a keyboard input event.
     * This is used when the default KeyCombination#match does not work properly on macOS.
     * @param combination is the shortcut.
     * @param event is the new {@code KeyEvent} to be checked with.
     * @return boolean result of the match.
     */
    public static boolean shortcutMatchEvent(KeyCharacterCombination combination, KeyEvent event) {
        boolean result = event.getCode().getChar().equals(combination.getCharacter());
        if (!combination.getShortcut().equals(KeyCombination.ModifierValue.ANY)) {
            result = result && event.isShortcutDown()
                    == combination.getShortcut().equals(KeyCombination.ModifierValue.DOWN);
        }
        if (!combination.getShift().equals(KeyCombination.ModifierValue.ANY)) {
            result = result && event.isShiftDown()
                    == combination.getShift().equals(KeyCombination.ModifierValue.DOWN);
        }
        if (!combination.getAlt().equals(KeyCombination.ModifierValue.ANY)) {
            result = result && event.isAltDown()
                    == combination.getAlt().equals(KeyCombination.ModifierValue.DOWN);
        }
        return result;
    }
}

