package seedu.address.commons.core;

import java.io.Serializable;
import java.util.LinkedHashMap;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.DeleteCommand;

/**
 * A Serializable class that contains the shortcut settings.
 * A shortcut is an alias that a user can assign to an existing command word so that the alias will be parsed as if
 * it is the command word.
 */
public class ShortcutSettings implements Serializable {
    private final LinkedHashMap<String, String> shortcutMap;
    /**
     * Constructs a {@code GuiSettings} with a default empty shortcut mapping.
     */
    public ShortcutSettings() {
        shortcutMap = new LinkedHashMap<>();
        shortcutMap.put("del", DeleteCommand.COMMAND_WORD);
    }

    /**
     * Constructs a {@code ShortcutSettings} with the specified shortcut mapping.
     */
    public ShortcutSettings(LinkedHashMap<String, String> shortcutMap) {
        this.shortcutMap = shortcutMap;
    }

    /**
     * Registers the new mapping.
     * @param alias New alias for an existing command word
     * @param commandWord The command word to be mapped to the alias
     * @return The alias' previously mapped command word if any, else returns null.
     */
    public String registerShortcut(String alias, String commandWord) {
        return shortcutMap.put(alias, commandWord);
    }

    /**
     * Checks if the alias has a mapping registered and returns it if found.
     * @param alias Query alias
     * @return Registered command word if found, else given alias
     */
    public String getShortcut(String alias) {
        return shortcutMap.getOrDefault(alias, alias);
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("shortcutMappings", shortcutMap)
                .toString();
    }
}
