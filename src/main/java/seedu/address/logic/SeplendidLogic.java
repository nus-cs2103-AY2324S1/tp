package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyLocalCourseCatalogue;
import seedu.address.model.ReadOnlyMappingCatalogue;
import seedu.address.model.ReadOnlyPartnerCourseCatalogue;
import seedu.address.model.ReadOnlyUniversityCatalogue;
import seedu.address.model.SeplendidModel;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.mapping.Mapping;
import seedu.address.model.note.Note;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.university.University;
import seedu.address.seplendidui.Ui;

/**
 * API of the SeplendidLogic component.
 */
public interface SeplendidLogic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    void setUi(Ui ui);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    //=========== LocalCourseCatalogue ================================================================================

    /**
     * Returns the LocalCourseCatalogue.
     *
     * @see SeplendidModel#getLocalCourseCatalogue()
     */
    ReadOnlyLocalCourseCatalogue getLocalCourseCatalogue();

    /**
     * Returns an unmodifiable view of the filtered list of persons
     */
    ObservableList<LocalCourse> getFilteredLocalCourseCatalogue();

    /**
     * Returns the user prefs' LocalCourseCatalogue file path.
     */
    Path getLocalCourseCatalogueFilePath();

    ObservableList<LocalCourse> getSortedLocalCourseCatalogue();

    //=========== PartnerCourseCatalouge ============================================================================
    ReadOnlyPartnerCourseCatalogue getPartnerCourseCatalogue();

    ObservableList<PartnerCourse> getFilteredPartnerCourseCatalogue();

    Path getPartnerCourseCatalogueFilePath();

    ObservableList<PartnerCourse> getSortedPartnerCourseCatalogue();


    //=========== UniversityCatalogue ============================================================================
    ReadOnlyUniversityCatalogue getUniversityCatalogue();

    ObservableList<University> getFilteredUniversityCatalogue();

    Path getUniversityeCatalogueFilePath();

    ObservableList<University> getSortedUniversityCatalogue();

    //=========== NoteCatalogue ============================================================================
    ObservableList<Note> getFilteredNoteCatalogue();

    //=========== MappingCatalogue ============================================================================
    ReadOnlyMappingCatalogue getMappingCatalogue();

    ObservableList<Mapping> getFilteredMappingCatalogue();

    Path getMappingCatalogueFilePath();

    ObservableList<Mapping> getSortedMappingCatalogue();
}

