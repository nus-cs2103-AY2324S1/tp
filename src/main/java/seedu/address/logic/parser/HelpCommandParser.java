package seedu.address.logic.parser;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The parser for all secondary {@code delete} commands
 */
public class HelpCommandParser implements Parser<HelpCommand> {
    @Override
    public HelpCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            return new HelpCommand(true);
        }

        switch (trimmedArgs) {

        case AddCommand.COMMAND_WORD:
            return new HelpCommand(true, trimmedArgs);

        case EditCommand.COMMAND_WORD:
            return new HelpCommand(true, trimmedArgs);

        case DeleteCommand.COMMAND_WORD:
            return new HelpCommand(true, trimmedArgs);

        case ClearCommand.COMMAND_WORD:
            return new HelpCommand(true, trimmedArgs);

        case FindCommand.COMMAND_WORD:
            return new HelpCommand(true, trimmedArgs);

        case ListCommand.COMMAND_WORD:
            return new HelpCommand(true, trimmedArgs);

        default:
            List<Double> similarityScores = CliSyntax.COMMAND_LIST.stream()
                .mapToDouble((command) -> ParserUtil.score(command, trimmedArgs))
                .boxed()
                .collect(Collectors.toList());

            double maxScore = Collections.max(similarityScores);

            if (maxScore < 0.5) {
                return new HelpCommand(false);
            }

            return new HelpCommand(false, CliSyntax.COMMAND_LIST.get(similarityScores.indexOf(maxScore)));
        }
    }
}
