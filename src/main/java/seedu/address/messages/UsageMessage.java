package seedu.address.messages;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.localcourse.LocalCourseAddCommand;
import seedu.address.logic.commands.localcourse.LocalCourseCommand;
import seedu.address.logic.commands.localcourse.LocalCourseDeleteCommand;
import seedu.address.logic.commands.localcourse.LocalCourseListCommand;
import seedu.address.logic.commands.localcourse.LocalCourseSearchCommand;
import seedu.address.logic.commands.localcourse.LocalCourseSortCommand;
import seedu.address.logic.commands.localcourse.LocalCourseUpdateCommand;
import seedu.address.logic.commands.mapping.MappingAddCommand;
import seedu.address.logic.commands.mapping.MappingCommand;
import seedu.address.logic.commands.mapping.MappingDeleteCommand;
import seedu.address.logic.commands.mapping.MappingListCommand;
import seedu.address.logic.commands.mapping.MappingSearchCommand;
import seedu.address.logic.commands.mapping.MappingSortCommand;
import seedu.address.logic.commands.note.NoteAddCommand;
import seedu.address.logic.commands.note.NoteClearTagCommand;
import seedu.address.logic.commands.note.NoteCommand;
import seedu.address.logic.commands.note.NoteDeleteCommand;
import seedu.address.logic.commands.note.NoteListCommand;
import seedu.address.logic.commands.note.NoteSearchCommand;
import seedu.address.logic.commands.note.NoteTagCommand;
import seedu.address.logic.commands.note.NoteUpdateCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseAddCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseDeleteCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseListCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseSearchCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseSortCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseUpdateCommand;
import seedu.address.logic.commands.university.UniversityCommand;
import seedu.address.logic.commands.university.UniversityListCommand;
import seedu.address.logic.commands.university.UniversitySearchCommand;
import seedu.address.logic.commands.university.UniversitySortCommand;

/**
 * Enum for command usage messages.
 */
public enum UsageMessage {
    LOCALCOURSE_ADD(
            LocalCourseCommand.COMMAND_WORD,
            LocalCourseAddCommand.ACTION_WORD,
            "[localcode] [localname] [localunit] [localdescription]",
            "Adds a local course"),
    LOCALCOURSE_DELETE(
            LocalCourseCommand.COMMAND_WORD,
            LocalCourseDeleteCommand.ACTION_WORD,
            "[localcode]",
            "Deletes a local course"),
    LOCALCOURSE_LIST(
            LocalCourseCommand.COMMAND_WORD,
            LocalCourseListCommand.ACTION_WORD,
            "",
            "Lists all local courses"),
    LOCALCOURSE_SEARCH(
            LocalCourseCommand.COMMAND_WORD,
            LocalCourseSearchCommand.ACTION_WORD,
            "[attribute] [query]",
            "Searches local courses by attributes.\nAttributes:\n"
                    + "localcode\nlocalname\nlocaldescription"),
    LOCALCOURSE_SORT(
            LocalCourseCommand.COMMAND_WORD,
            LocalCourseSortCommand.ACTION_WORD,
            "[attribute]",
            "Sorts all local courses by attributes\nAttributes:\n"
                    + "localcode\nlocalname"),
    LOCALCOURSE_UPDATE(
            LocalCourseCommand.COMMAND_WORD,
            LocalCourseUpdateCommand.ACTION_WORD,
            "[localcode] [attribute] [newvalue]",
            "Updates a local course for a particular attribute.\nAttributes:\n"
                    + "localcode\nlocalname\nunit\nlocaldescription'"),
    LOCALCOURSE(String.join("\n",
            "Local Courses Commands:\n",
            LOCALCOURSE_ADD.toString(),
            LOCALCOURSE_LIST.toString(),
            LOCALCOURSE_UPDATE.toString(),
            LOCALCOURSE_DELETE.toString(),
            LOCALCOURSE_SEARCH.toString(),
            LOCALCOURSE_SORT.toString())),
    MAPPING_ADD(
            MappingCommand.COMMAND_WORD,
            MappingAddCommand.ACTION_WORD,
            "[localcode] [university] [partnercode] [information]",
            "Adds a mapping to the mapping catalogue"),
    MAPPING_DELETE(
            MappingCommand.COMMAND_WORD,
            MappingDeleteCommand.ACTION_WORD,
            "[localcode] [university] [partnercode]",
            "Deletes a course mapping."),
    MAPPING_LIST(
            MappingCommand.COMMAND_WORD,
            MappingListCommand.ACTION_WORD,
            "",
            "Lists all mappings"),
    MAPPING_SEARCH(
            MappingCommand.COMMAND_WORD,
            MappingSearchCommand.ACTION_WORD,
            "[attribute] [query]",
            "Lists all mappings based on queried value for the specified attribute\nAttributes:\n"
                    + "localcode\nlocalname\npartnercode\npartnername\nuniversity\ninformation"),
    MAPPING_SORT(
            MappingCommand.COMMAND_WORD,
            MappingSortCommand.ACTION_WORD,
        "[attribute]",
            "Sorts all mappings based on the specified attribute\nAttributes:\n"
                    + "localcode\nlocalname\npartnercode\npartnername\nuniversity\ninformation"),
    MAPPING(String.join("\n",
            "Mapping Commands:\n",
            MAPPING_LIST.toString(),
            MAPPING_ADD.toString(),
            MAPPING_DELETE.toString(),
            MAPPING_SEARCH.toString(),
            MAPPING_SORT.toString())),
    NOTE_ADD(
            NoteCommand.COMMAND_WORD,
            NoteAddCommand.ACTION_WORD,
            "[content] [tag]",
            "Adds a note"),
    NOTE_DELETE(
            NoteCommand.COMMAND_WORD,
            NoteDeleteCommand.ACTION_WORD,
            "[index]",
            "Deletes a note"),
    NOTE_LIST(
            NoteCommand.COMMAND_WORD,
            NoteListCommand.ACTION_WORD,
            "",
            "Lists all notes"),
    NOTE_SEARCH(
            NoteCommand.COMMAND_WORD,
            NoteSearchCommand.ACTION_WORD,
            "[note_tag_keyword]",
            "Search notes with the same tag keyword"),
    NOTE_TAG(
            NoteCommand.COMMAND_WORD,
            NoteTagCommand.ACTION_WORD,
            "[index] [tag]",
            "Add a tag to a note"),
    NOTE_UPDATE(
            NoteCommand.COMMAND_WORD,
            NoteUpdateCommand.ACTION_WORD,
            "[index] [content]",
            "Updates a note to the specified content"),
    NOTE_CLEAR_TAG(
            NoteCommand.COMMAND_WORD,
            NoteClearTagCommand.ACTION_WORD,
            "[index]",
            "Removes all tags to a note"),
    NOTE(String.join("\n",
            "Note Commands:\n",
            NOTE_ADD.toString(),
            NOTE_CLEAR_TAG.toString(),
            NOTE_DELETE.toString(),
            NOTE_LIST.toString(),
            NOTE_SEARCH.toString(),
            NOTE_TAG.toString(),
            NOTE_UPDATE.toString())),
    PARTNERCOURSE_ADD(
            PartnerCourseCommand.COMMAND_WORD,
            PartnerCourseAddCommand.ACTION_WORD,
            "[university] [partnercode] [partnername] [partnerunit] [partnerdescription]",
            "Adds a partner course"),
    PARTNERCOURSE_DELETE(
            PartnerCourseCommand.COMMAND_WORD,
            PartnerCourseDeleteCommand.ACTION_WORD,
            "[university] [partnercode] ",
            "Deletes a partner course"),
    PARTNERCOURSE_LIST(
            PartnerCourseCommand.COMMAND_WORD,
            PartnerCourseListCommand.ACTION_WORD,
            "",
            "Lists all partner courses"),
    PARTNERCOURSE_SEARCH(
            PartnerCourseCommand.COMMAND_WORD,
            PartnerCourseSearchCommand.ACTION_WORD,
            "[attribute] [query]",
            "Searches partner courses by attributes\nAttributes:\n"
                    + "partnercode\npartnername\ndescription\nuniversity"),
    PARTNERCOURSE_SORT(
            PartnerCourseCommand.COMMAND_WORD,
            PartnerCourseSortCommand.ACTION_WORD,
            "[attribute]",
            "Sorts all partner courses by attributes.\nAttributes:\n"
                    + "partnercode\npartnername\nuniversity"),
    PARTNERCOURSE_UPDATE(
            PartnerCourseCommand.COMMAND_WORD,
            PartnerCourseUpdateCommand.ACTION_WORD,
            "[university] [partnercode] [attribute] [newvalue]",
            "Updates a partner course for a particular attribute.\nAttributes:\n"
                    + "partnercode\npartnername\nunit\ndescription"),
    PARTNERCOURSE(String.join("\n",
            "Partner Course Commands:\n",
            PARTNERCOURSE_LIST.toString(),
            PARTNERCOURSE_ADD.toString(),
            PARTNERCOURSE_UPDATE.toString(),
            PARTNERCOURSE_DELETE.toString(),
            PARTNERCOURSE_SEARCH.toString(),
            PARTNERCOURSE_SORT.toString())),
    UNIVERSITY_LIST(
            UniversityCommand.COMMAND_WORD,
            UniversityListCommand.ACTION_WORD,
            "",
            "Lists all universities"),
    UNIVERSITY_SEARCH(
            UniversityCommand.COMMAND_WORD,
            UniversitySearchCommand.ACTION_WORD,
            "[university_keyword]",
            "Search universities with the same keyword"),
    UNIVERSITY_SORT(
            UniversityCommand.COMMAND_WORD,
            UniversitySortCommand.ACTION_WORD,
            "",
            "Sorts all universities in alphabetical order"),
    UNIVERSITY(String.join("\n",
            "University Commands:\n",
            UNIVERSITY_LIST.toString(),
            UNIVERSITY_SEARCH.toString(),
            UNIVERSITY_SORT.toString())),
    HELP(
        HelpCommand.COMMAND_WORD,
        "",
        "",
        "Shows a help window for the program usage"),
    EXIT(
        ExitCommand.COMMAND_WORD,
        "",
        "",
        "Exit SEPlendid.");

    private final String value;

    /**
     * Constructs a UsageMessage with the specified value.
     *
     * @param description The usage message value.
     */
    UsageMessage(String description) {
        this.value = description;
    }

    /**
     * Constructs a UsageMessage with the specified values.
     *
     * @param commandWord The command word for the command.
     * @param actionWord  The action word for the command.
     * @param args        The arguments for the command.
     * @param description The description of the command.
     */
    UsageMessage(String commandWord, String actionWord, String args, String description) {
        this.value = String.format("%s %s %s: %s\n", commandWord, actionWord, args, description);
    }

    /**
     * Gets the string representation of the usage message.
     *
     * @return The usage message string.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Gets usage message with message that arguments cannot be empty.
     */
    public String getValueWithEmptyArgs() {
        return String.format("%s\n%s", value, "Arguments cannot be empty.\n");
    }
}
