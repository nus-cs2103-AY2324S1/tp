package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.CompareCalendarByTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parser for creating a CompareCalendarByTagCommand.
 */
public class CompareCalendarByTagCommandParser implements Parser<CompareCalendarByTagCommand> {
    private static final String splitRegex = "\\s+";
    @Override
    public CompareCalendarByTagCommand parse(String userInput) throws ParseException {
        try {
            String trimmedInput = userInput.trim();
            List<Tag> tagList = new ArrayList<Tag>();
            if (!trimmedInput.isEmpty()) {
                String[] tagStringArray = trimmedInput.split(splitRegex);
                Set<Tag> tagSet = ParserUtil.parseTags(Arrays.asList(tagStringArray));
                tagList.addAll(tagSet);
            }
            return new CompareCalendarByTagCommand(tagList);
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, CompareCalendarByTagCommand.MESSAGE_USAGE));
        }
    }
}
