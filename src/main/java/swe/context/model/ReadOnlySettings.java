package swe.context.model;

import java.nio.file.Path;

import swe.context.commons.core.GuiSettings;



/**
 * Read-only view of settings.
 */
public interface ReadOnlySettings {
    public Path getContactsPath();

    public GuiSettings getGuiSettings();
}
