package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connectify.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static connectify.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static connectify.logic.parser.CliSyntax.PREFIX_EMAIL;
import static connectify.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static connectify.logic.parser.CliSyntax.PREFIX_LOCATION;
import static connectify.logic.parser.CliSyntax.PREFIX_NAME;
import static connectify.logic.parser.CliSyntax.PREFIX_PHONE;
import static connectify.logic.parser.CliSyntax.PREFIX_WEBSITE;
import static java.util.Objects.requireNonNull;

import connectify.commons.core.index.Index;
import connectify.logic.commands.EditCompanyCommand;
import connectify.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCompanyCommand object
 */
public class EditCompanyCommandParser implements Parser<EditCompanyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCompanyCommand
     * and returns an EditCompanyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCompanyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_INDUSTRY, PREFIX_LOCATION,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_WEBSITE, PREFIX_DESCRIPTION);

        Index companyIndex;

        try {
            companyIndex = ParserCompanyUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCompanyCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_INDUSTRY, PREFIX_LOCATION, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_WEBSITE, PREFIX_DESCRIPTION);

        EditCompanyCommand.EditCompanyDescriptor editCompanyDescriptor = new EditCompanyCommand
                .EditCompanyDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editCompanyDescriptor.setName(ParserCompanyUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_INDUSTRY).isPresent()) {
            editCompanyDescriptor.setIndustry(ParserCompanyUtil.parseIndustry(argMultimap
                    .getValue(PREFIX_INDUSTRY).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editCompanyDescriptor.setLocation(ParserCompanyUtil.parseLocation(argMultimap
                    .getValue(PREFIX_LOCATION).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editCompanyDescriptor.setPhone(ParserCompanyUtil.parsePhone(argMultimap
                    .getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editCompanyDescriptor.setEmail(ParserCompanyUtil.parseEmail(argMultimap
                    .getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editCompanyDescriptor.setAddress(ParserCompanyUtil.parseAddress(argMultimap
                    .getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_WEBSITE).isPresent()) {
            editCompanyDescriptor.setWebsite(ParserCompanyUtil.parseWebsite(argMultimap
                    .getValue(PREFIX_WEBSITE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editCompanyDescriptor.setDescription(ParserCompanyUtil.parseDescription(argMultimap
                    .getValue(PREFIX_DESCRIPTION).get()));
        }


        if (!editCompanyDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCompanyCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCompanyCommand(companyIndex, editCompanyDescriptor);
    }
}
