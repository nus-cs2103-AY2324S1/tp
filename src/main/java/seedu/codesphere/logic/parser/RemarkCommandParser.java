package seedu.codesphere.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.commons.exceptions.IllegalValueException;
import seedu.codesphere.logic.commands.RemarkCommand;
import seedu.codesphere.logic.parser.exceptions.ParseException;
import seedu.codesphere.model.student.Remark;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class RemarkCommandParser implements Parser<RemarkCommand> {
    public static final String MESSAGE_CONSTRAINTS =
            "Remark should not be blank." + "\nExample: remark 2 r/needs more help";
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE), ive);
        }

        String remark = argMultimap.getAllValuesAsString(PREFIX_REMARK).orElse("");

        if (remark.isEmpty()) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
        return new RemarkCommand(index, new Remark(remark));
    }
}
