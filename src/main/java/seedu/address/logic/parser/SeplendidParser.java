package seedu.address.logic.parser;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.SeplendidLogsCenter;
import seedu.address.logic.commands.Command;
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
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.localcourse.LocalCourseAddCommandParser;
import seedu.address.logic.parser.localcourse.LocalCourseDeleteCommandParser;
import seedu.address.logic.parser.localcourse.LocalCourseSearchCommandParser;
import seedu.address.logic.parser.localcourse.LocalCourseSortCommandParser;
import seedu.address.logic.parser.localcourse.LocalCourseUpdateCommandParser;
import seedu.address.logic.parser.mapping.MappingAddCommandParser;
import seedu.address.logic.parser.mapping.MappingDeleteCommandParser;
import seedu.address.logic.parser.mapping.MappingSearchCommandParser;
import seedu.address.logic.parser.mapping.MappingSortCommandParser;
import seedu.address.logic.parser.note.NoteAddCommandParser;
import seedu.address.logic.parser.note.NoteClearTagCommandParser;
import seedu.address.logic.parser.note.NoteDeleteCommandParser;
import seedu.address.logic.parser.note.NoteSearchCommandParser;
import seedu.address.logic.parser.note.NoteTagCommandParser;
import seedu.address.logic.parser.note.NoteUpdateCommandParser;
import seedu.address.logic.parser.partnercourse.PartnerCourseAddCommandParser;
import seedu.address.logic.parser.partnercourse.PartnerCourseDeleteCommandParser;
import seedu.address.logic.parser.partnercourse.PartnerCourseSearchCommandParser;
import seedu.address.logic.parser.partnercourse.PartnerCourseSortCommandParser;
import seedu.address.logic.parser.partnercourse.PartnerCourseUpdateCommandParser;
import seedu.address.logic.parser.university.UniversitySearchCommandParser;
import seedu.address.messages.UsageMessage;
import seedu.address.model.university.comparator.UniversityComparatorByUniversityName;

/**
 * Parses user input into the SEPlendid CLI.
 * <p>
 */
public class SeplendidParser {
    /**
     * Used for initial separation of command, action word and arguments.
     */
    private static final String REGEX_GROUP_COMMAND_WORD = "commandWord";
    private static final String REGEX_GROUP_ACTION_WORD = "actionWord";
    private static final String REGEX_GROUP_ARGUMENTS = "arguments";
    private static final Pattern COMMAND_FORMAT_COMMAND = Pattern.compile(String.format(
            "(?<%s>\\S+)",
            REGEX_GROUP_COMMAND_WORD));
    private static final Pattern COMMAND_FORMAT_COMMAND_ACTION = Pattern.compile(String.format(
            "(?<%s>\\S+)\\s(?<%s>\\S+)",
            REGEX_GROUP_COMMAND_WORD,
            REGEX_GROUP_ACTION_WORD));
    private static final Pattern COMMAND_FORMAT_COMMAND_ACTION_ARG = Pattern.compile(String.format(
            "(?<%s>\\S+)\\s(?<%s>\\S+)\\s(?<%s>.*)",
            REGEX_GROUP_COMMAND_WORD,
            REGEX_GROUP_ACTION_WORD,
            REGEX_GROUP_ARGUMENTS));
    private static final Logger logger = SeplendidLogsCenter.getLogger(SeplendidParser.class);

    /**
     * Parses user input into command for execution.
     * Determines the pattern of the command.
     *
     * @param userInput full user input string.
     * @return the command based on the user input.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public Command parseCommand(String userInput) throws ParseException {
        // Trim the user input
        userInput = userInput.trim();

        // Check which pattern the input matches
        if (COMMAND_FORMAT_COMMAND_ACTION_ARG.matcher(userInput).matches()) {
            return parseCommandActionArg(userInput);
        } else if (COMMAND_FORMAT_COMMAND_ACTION.matcher(userInput).matches()) {
            return parseCommandAction(userInput);
        } else if (COMMAND_FORMAT_COMMAND.matcher(userInput).matches()) {
            return parseCommandOnly(userInput);
        } else {
            throw new ParseException(UsageMessage.HELP.toString());
        }
    }

    /**
     * Parses a command with no action word and no arguments.
     *
     * @param userInput String of userInput
     * @return the command based on the user input.
     * @throws ParseException If the input is invalid.
     */
    public Command parseCommandOnly(String userInput) throws ParseException {
        final Matcher matcher = COMMAND_FORMAT_COMMAND.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(UsageMessage.HELP.toString());
        }

        final String commandWord = matcher.group(REGEX_GROUP_COMMAND_WORD).trim();

        switch (commandWord) {
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
        }

        logger.finer("This user input caused a ParseException: " + userInput);
        switch (commandWord) {
        case LocalCourseCommand.COMMAND_WORD:
            throw new ParseException(
                    UsageMessage.LOCALCOURSE.toString());
        case PartnerCourseCommand.COMMAND_WORD:
            throw new ParseException(
                    UsageMessage.PARTNERCOURSE.toString());
        case NoteCommand.COMMAND_WORD:
            throw new ParseException(
                    UsageMessage.NOTE.toString());
        case UniversityCommand.COMMAND_WORD:
            throw new ParseException(
                    UsageMessage.UNIVERSITY.toString());
        case MappingCommand.COMMAND_WORD:
            throw new ParseException(
                    UsageMessage.MAPPING.toString());
        default:
            throw new ParseException(
                    UsageMessage.HELP.toString());
        }
    }

    /**
     * Parses a command with an action word and no arguments.
     *
     * @param userInput String of userInput
     * @return the command based on the user input.
     * @throws ParseException If the input is invalid.
     */
    public Command parseCommandAction(String userInput) throws ParseException {
        final Matcher matcher = COMMAND_FORMAT_COMMAND_ACTION.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(UsageMessage.HELP.toString());
        }

        final String commandWord = matcher.group(REGEX_GROUP_COMMAND_WORD).trim();
        final String actionWord = matcher.group(REGEX_GROUP_ACTION_WORD).trim();

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Action word: " + actionWord);

        switch (commandWord) {
        case LocalCourseCommand.COMMAND_WORD:
            return getLocalCourseCommandWithoutArg(userInput, actionWord);
        case PartnerCourseCommand.COMMAND_WORD:
            return getPartnerCourseCommandWithoutArg(userInput, actionWord);
        case UniversityCommand.COMMAND_WORD:
            return getUniversityCommandWithoutArg(userInput, actionWord);
        case MappingCommand.COMMAND_WORD:
            return getMappingCommandWithoutArg(userInput, actionWord);
        case NoteCommand.COMMAND_WORD:
            return getNoteCommandWithoutArg(userInput, actionWord);
        default:
        }

        logger.finer("This user input caused a ParseException: " + userInput);
        if (commandWord.equals(ExitCommand.COMMAND_WORD)) {
            throw new ParseException(UsageMessage.EXIT.toString());
        }
        throw new ParseException(UsageMessage.HELP.toString());
    }

    /**
     * Parses a command with an action word and contains arguments.
     *
     * @param userInput String of userInput
     * @return the command based on the user input.
     * @throws ParseException If the input is invalid.
     */
    public Command parseCommandActionArg(String userInput) throws ParseException {
        final Matcher matcher = COMMAND_FORMAT_COMMAND_ACTION_ARG.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(UsageMessage.HELP.toString());
        }
        final String commandWord = matcher.group(REGEX_GROUP_COMMAND_WORD).trim();
        final String actionWord = matcher.group(REGEX_GROUP_ACTION_WORD).trim();
        final String arguments = matcher.group(REGEX_GROUP_ARGUMENTS).trim();

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Action word: " + actionWord + "; Arguments: " + arguments);

        switch (commandWord) {
        case LocalCourseCommand.COMMAND_WORD:
            return getLocalCourseCommandWithArg(userInput, actionWord, arguments);
        case PartnerCourseCommand.COMMAND_WORD:
            return getPartnerCourseCommandWithArg(userInput, actionWord, arguments);
        case NoteCommand.COMMAND_WORD:
            return getNoteCommandWithArg(userInput, actionWord, arguments);
        case UniversityCommand.COMMAND_WORD:
            return getUniversityCommandWithArg(userInput, actionWord, arguments);
        case MappingCommand.COMMAND_WORD:
            return getMappingCommandWithArg(userInput, actionWord, arguments);
        default:
        }

        logger.finer("This user input caused a ParseException: " + userInput);
        if (commandWord.equals(ExitCommand.COMMAND_WORD)) {
            throw new ParseException(UsageMessage.EXIT.toString());
        }
        throw new ParseException(UsageMessage.HELP.toString());
    }

    private LocalCourseCommand getLocalCourseCommandWithArg(String userInput, String actionWord, String arguments)
            throws ParseException {
        switch (actionWord) {
        case LocalCourseAddCommand.ACTION_WORD:
            return new LocalCourseAddCommandParser().parse(arguments);
        case LocalCourseDeleteCommand.ACTION_WORD:
            return new LocalCourseDeleteCommandParser().parse(arguments);
        case LocalCourseSortCommand.ACTION_WORD:
            return new LocalCourseSortCommandParser().parse(arguments);
        case LocalCourseSearchCommand.ACTION_WORD:
            return new LocalCourseSearchCommandParser().parse(arguments);
        case LocalCourseUpdateCommand.ACTION_WORD:
            return new LocalCourseUpdateCommandParser().parse(arguments);
        default:
        }

        logger.finer("This user input caused a ParseException: " + userInput);
        if (actionWord.equals(LocalCourseListCommand.ACTION_WORD)) {
            throw new ParseException(UsageMessage.LOCALCOURSE_LIST.toString());
        }
        throw new ParseException(UsageMessage.LOCALCOURSE.toString());
    }

    private LocalCourseCommand getLocalCourseCommandWithoutArg(String userInput, String actionWord)
            throws ParseException {
        if (actionWord.equals(LocalCourseListCommand.ACTION_WORD)) {
            return new LocalCourseListCommand();
        }

        logger.finer("This user input caused a ParseException: " + userInput);
        switch (actionWord) {
        case LocalCourseAddCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.LOCALCOURSE_ADD.toString());
        case LocalCourseDeleteCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.LOCALCOURSE_DELETE.toString());
        case LocalCourseSearchCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.LOCALCOURSE_SEARCH.toString());
        case LocalCourseSortCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.LOCALCOURSE_SORT.toString());
        case LocalCourseUpdateCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.LOCALCOURSE_UPDATE.toString());
        default:
            throw new ParseException(UsageMessage.LOCALCOURSE.toString());
        }
    }

    private PartnerCourseCommand getPartnerCourseCommandWithArg(String userInput, String actionWord, String arguments)
            throws ParseException {
        switch (actionWord) {
        case PartnerCourseAddCommand.ACTION_WORD:
            return new PartnerCourseAddCommandParser().parse(arguments);
        case PartnerCourseDeleteCommand.ACTION_WORD:
            return new PartnerCourseDeleteCommandParser().parse(arguments);
        case PartnerCourseSearchCommand.ACTION_WORD:
            return new PartnerCourseSearchCommandParser().parse(arguments);
        case PartnerCourseSortCommand.ACTION_WORD:
            return new PartnerCourseSortCommandParser().parse(arguments);
        case PartnerCourseUpdateCommand.ACTION_WORD:
            return new PartnerCourseUpdateCommandParser().parse(arguments);
        default:
        }

        logger.finer("This user input caused a ParseException: " + userInput);
        if (actionWord.equals(PartnerCourseListCommand.ACTION_WORD)) {
            throw new ParseException(UsageMessage.PARTNERCOURSE_LIST.toString());
        }
        throw new ParseException(UsageMessage.PARTNERCOURSE.toString());
    }

    private PartnerCourseCommand getPartnerCourseCommandWithoutArg(String userInput, String actionWord)
            throws ParseException {
        if (actionWord.equals(PartnerCourseListCommand.ACTION_WORD)) {
            return new PartnerCourseListCommand();
        }

        logger.finer("This user input caused a ParseException: " + userInput);
        switch (actionWord) {
        case PartnerCourseAddCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.PARTNERCOURSE_ADD.toString());
        case PartnerCourseDeleteCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.PARTNERCOURSE_DELETE.toString());
        case PartnerCourseSearchCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.PARTNERCOURSE_SEARCH.toString());
        case PartnerCourseSortCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.PARTNERCOURSE_SORT.toString());
        case PartnerCourseUpdateCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.PARTNERCOURSE_UPDATE.toString());
        default:
            throw new ParseException(UsageMessage.PARTNERCOURSE.toString());
        }
    }

    private UniversityCommand getUniversityCommandWithoutArg(String userInput, String actionWord)
            throws ParseException {
        switch (actionWord) {
        case UniversityListCommand.ACTION_WORD:
            return new UniversityListCommand();
        case UniversitySortCommand.ACTION_WORD:
            return new UniversitySortCommand(new UniversityComparatorByUniversityName());
        default:
        }

        logger.finer("This user input caused a ParseException: " + userInput);
        if (actionWord.equals(UniversitySearchCommand.ACTION_WORD)) {
            throw new ParseException(UsageMessage.UNIVERSITY_SEARCH.toString());
        }
        throw new ParseException(UsageMessage.UNIVERSITY.toString());
    }

    private UniversityCommand getUniversityCommandWithArg(String userInput, String actionWord, String arguments)
            throws ParseException {
        if (actionWord.equals(UniversitySearchCommand.ACTION_WORD)) {
            return new UniversitySearchCommandParser().parse(arguments);
        }

        logger.finer("This user input caused a ParseException: " + userInput);
        switch (actionWord) {
        case UniversityListCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.UNIVERSITY_LIST.toString());
        case UniversitySortCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.UNIVERSITY_SORT.toString());
        default:
            throw new ParseException(UsageMessage.UNIVERSITY.toString());
        }
    }

    private MappingCommand getMappingCommandWithArg(String userInput, String actionWord, String arguments)
            throws ParseException {
        switch (actionWord) {
        case MappingAddCommand.ACTION_WORD:
            return new MappingAddCommandParser().parse(arguments);
        case MappingDeleteCommand.ACTION_WORD:
            return new MappingDeleteCommandParser().parse(arguments);
        case MappingSearchCommand.ACTION_WORD:
            return new MappingSearchCommandParser().parse(arguments);
        case MappingSortCommand.ACTION_WORD:
            return new MappingSortCommandParser().parse(arguments);
        default:
        }

        logger.finer("This user input caused a ParseException: " + userInput);
        if (actionWord.equals(MappingListCommand.ACTION_WORD)) {
            throw new ParseException(UsageMessage.MAPPING_LIST.toString());
        }
        throw new ParseException(UsageMessage.MAPPING.toString());
    }

    private MappingCommand getMappingCommandWithoutArg(String userInput, String actionWord) throws ParseException {
        if (actionWord.equals(MappingListCommand.ACTION_WORD)) {
            return new MappingListCommand();
        }
        logger.finer("This user input caused a ParseException: " + userInput);
        switch (actionWord) {
        case MappingAddCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.MAPPING_ADD.toString());
        case MappingDeleteCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.MAPPING_DELETE.toString());
        case MappingSearchCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.MAPPING_SEARCH.toString());
        case MappingSortCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.MAPPING_SORT.toString());
        default:
            throw new ParseException(UsageMessage.MAPPING.toString());
        }
    }

    private NoteCommand getNoteCommandWithArg(String userInput, String actionWord, String arguments)
            throws ParseException {
        switch (actionWord) {
        case NoteAddCommand.ACTION_WORD:
            return new NoteAddCommandParser().parse(arguments);
        case NoteSearchCommand.ACTION_WORD:
            return new NoteSearchCommandParser().parse(arguments);
        case NoteDeleteCommand.ACTION_WORD:
            return new NoteDeleteCommandParser().parse(arguments);
        case NoteUpdateCommand.ACTION_WORD:
            return new NoteUpdateCommandParser().parse(arguments);
        case NoteTagCommand.ACTION_WORD:
            return new NoteTagCommandParser().parse(arguments);
        case NoteClearTagCommand.ACTION_WORD:
            return new NoteClearTagCommandParser().parse(arguments);
        default:
        }

        logger.finer("This user input caused a ParseException: " + userInput);
        if (actionWord.equals(NoteListCommand.ACTION_WORD)) {
            throw new ParseException(UsageMessage.NOTE_LIST.toString());
        }
        throw new ParseException(UsageMessage.NOTE.toString());
    }

    private NoteCommand getNoteCommandWithoutArg(String userInput, String actionWord) throws ParseException {
        if (actionWord.equals(NoteListCommand.ACTION_WORD)) {
            return new NoteListCommand();
        }
        logger.finer("This user input caused a ParseException: " + userInput);
        switch (actionWord) {
        case NoteAddCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.NOTE_ADD.toString());
        case NoteClearTagCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.NOTE_CLEAR_TAG.toString());
        case NoteDeleteCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.NOTE_DELETE.toString());
        case NoteSearchCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.NOTE_SEARCH.toString());
        case NoteTagCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.NOTE_TAG.toString());
        case NoteUpdateCommand.ACTION_WORD:
            throw new ParseException(UsageMessage.NOTE_UPDATE.toString());
        default:
            throw new ParseException(UsageMessage.NOTE.toString());
        }
    }
}
