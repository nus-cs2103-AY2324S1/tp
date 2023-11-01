package transact.logic.parser;

import static transact.logic.parser.CliSyntax.PREFIX_DIRECTORY;

import java.nio.file.Path;
import java.nio.file.Paths;

import transact.logic.commands.ImportTransactionCommand;
import transact.logic.parser.exceptions.ParseException;

/**
 * Parses arguments and returns Import Transaction Command
 */
public class ImportTransactionParser implements Parser<ImportTransactionCommand> {
    @Override
    public ImportTransactionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DIRECTORY);
        Path importFilePath = Paths.get(argMultimap.getValue(PREFIX_DIRECTORY).get());
        return new ImportTransactionCommand(importFilePath);
    }
}

