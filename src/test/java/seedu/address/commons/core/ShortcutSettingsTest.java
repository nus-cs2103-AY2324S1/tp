package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.COMMAND_WORD_1;
import static seedu.address.logic.commands.CommandTestUtil.COMMAND_WORD_2;
import static seedu.address.logic.commands.CommandTestUtil.SHORTCUT_ALIAS_1;
import static seedu.address.logic.commands.CommandTestUtil.SHORTCUT_ALIAS_2;

import org.junit.jupiter.api.Test;

public class ShortcutSettingsTest {

    @Test
    public void equals() {
        ShortcutSettings shortcutSettings1 = new ShortcutSettings();
        shortcutSettings1.registerShortcut(SHORTCUT_ALIAS_1, COMMAND_WORD_1);
        ShortcutSettings shortcutSettings2 = new ShortcutSettings();
        shortcutSettings2.registerShortcut(SHORTCUT_ALIAS_2, COMMAND_WORD_2);

        // same object -> returns true
        assertTrue(shortcutSettings1.equals(shortcutSettings1));

        // same values -> returns true
        ShortcutSettings test = new ShortcutSettings();
        test.registerShortcut(SHORTCUT_ALIAS_1, COMMAND_WORD_1);
        assertTrue(shortcutSettings1.equals(test));

        // different types -> returns false
        assertFalse(shortcutSettings1.equals(1));

        // null -> returns false
        assertFalse(shortcutSettings1.equals(null));

        // different mappings -> returns false
        assertFalse(shortcutSettings1.equals(shortcutSettings2));
        assertFalse(shortcutSettings1.equals(new ShortcutSettings()));
    }
}
