package seedu.address.commons.core;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandWord;
import seedu.address.logic.commands.ShortcutAlias;

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
    }

    /**
     * Constructs a {@code ShortcutSettings} with the specified shortcut mapping.
     */
    public ShortcutSettings(LinkedHashMap<String, String> shortcutMap) {
        this.shortcutMap = shortcutMap;
    }

    /**
     * Registers the new mapping.
     * @param shortcutAlias New alias for an existing command word
     * @param commandWord The command word to be mapped to the alias
     * @return The alias' previously mapped command word if any, else returns null.
     */
    public String registerShortcut(ShortcutAlias shortcutAlias, CommandWord commandWord) {
        return shortcutMap.put(shortcutAlias.alias, commandWord.keyword);
    }

    /**
     * Removes the shortcut mapping.
     * @param shortcutAlias Alias for an existing command word
     * @return The alias' previously mapped command word if any, else returns null.
     */
    public String removeShortcut(ShortcutAlias shortcutAlias) {
        return shortcutMap.remove(shortcutAlias.alias);
    }

    /**
     * Checks if the alias has a mapping registered and returns it if found.
     * @param alias Query alias
     * @return Registered command word if found, else given alias
     */
    public String getShortcut(String alias) {
        return shortcutMap.getOrDefault(alias, alias);
    }

    /**
     * Cleans up bad mappings from the shortcutMap.
     * Duplicate keys are handled automatically.
     */
    public ShortcutSettings removeBadMappings() {
        // Use of Iterator in solution inspired by AI tool output
        Iterator<Map.Entry<String, String>> iterator = shortcutMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> mapping = iterator.next();
            String shortcut = mapping.getKey();
            String keyword = mapping.getValue();
            if (!ShortcutAlias.isValidShortcutAlias(shortcut)
                    || !CommandWord.isValidCommandWord(keyword)) {
                iterator.remove();
            }
        }
        return this;
    }

    public ShortcutSettings getCopy() {
        return new ShortcutSettings(new LinkedHashMap<>(shortcutMap));
    }

    public void setShortcutSettings(ShortcutSettings shortcutSettings) {
        this.shortcutMap.clear();
        this.shortcutMap.putAll(shortcutSettings.shortcutMap);
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof ShortcutSettings)) {
            return false;
        }
        ShortcutSettings otherShortcutSettings = (ShortcutSettings) other;
        return Objects.equals(shortcutMap, otherShortcutSettings.shortcutMap);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("shortcutMappings", shortcutMap)
                .toString();
    }
}
