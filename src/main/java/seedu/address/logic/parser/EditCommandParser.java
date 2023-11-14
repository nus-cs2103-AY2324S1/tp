package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FINANCIAL_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.financialplan.FinancialPlan;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = processRawCommand(args);
        Index index = getIndex(argMultimap);
        EditPersonDescriptor editPersonDescriptor = makeEditPersonDescriptor(argMultimap);
        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> financialPlans} into a {@code Set<FinancialPlan>}
     * if {@code financialPlans} is non-empty.
     * If {@code financialPlans} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<FinancialPlan>} containing zero financial plans.
     */
    private Optional<Set<FinancialPlan>> parseFinancialPlansForEdit(
            Collection<String> financialPlans) throws ParseException {
        assert financialPlans != null;

        if (financialPlans.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> financialPlanSet = financialPlans.size() == 1 && financialPlans.contains("")
                ? Collections.emptySet() : financialPlans;
        return Optional.of(ParserUtil.parseFinancialPlans(financialPlanSet));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Processes the given {@code String} into an ArgumentMultimap in the context of an EditCommand.
     *
     * @param args Raw command string.
     * @return ArgumentMultimap containing argument values to edit a Person with.
     * @throws ParseException if the string contains extra arguments for Person fields that require at most
     *      one argument.
     */
    private ArgumentMultimap processRawCommand(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_NEXT_OF_KIN_NAME, PREFIX_NEXT_OF_KIN_PHONE, PREFIX_FINANCIAL_PLAN, PREFIX_TAG);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_NEXT_OF_KIN_NAME, PREFIX_NEXT_OF_KIN_PHONE);
        return argMultimap;
    }

    /**
     * Gets the index from an ArgumentMultimap.
     *
     * @param argumentMultimap Multimap to extract index from.
     * @return Index value.
     * @throws ParseException If the map does not contain a valid index in its preamble.
     */
    private Index getIndex(ArgumentMultimap argumentMultimap) throws ParseException {
        try {
            return ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Creates an EditPersonDescriptor using the values from an ArgumentMultimap. Will ignore values not relevant to the
     * EditPersonDescriptor class.
     *
     * @param argumentMultimap Multimap to draw values from.
     * @return EditPersonDescriptor to pass to EditCommand.
     * @throws ParseException if any relevant values are invalid or if the resulting EditPersonDescriptor has no values
     *      to edit.
     */
    private EditPersonDescriptor makeEditPersonDescriptor(ArgumentMultimap argumentMultimap) throws ParseException {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argumentMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argumentMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argumentMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argumentMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argumentMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argumentMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argumentMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argumentMultimap.getValue(PREFIX_NEXT_OF_KIN_NAME).isPresent()) {
            editPersonDescriptor.setNextOfKinName(ParserUtil.parseNextOfKinName(argumentMultimap
                    .getValue(PREFIX_NEXT_OF_KIN_NAME)
                    .get()));
        }
        if (argumentMultimap.getValue(PREFIX_NEXT_OF_KIN_PHONE).isPresent()) {
            editPersonDescriptor.setNextOfKinPhone(ParserUtil.parseNextOfKinPhone(argumentMultimap
                    .getValue(PREFIX_NEXT_OF_KIN_PHONE)
                    .get()));
        }
        parseFinancialPlansForEdit(argumentMultimap.getAllValues(PREFIX_FINANCIAL_PLAN))
                .ifPresent(editPersonDescriptor::setFinancialPlans);
        parseTagsForEdit(argumentMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return editPersonDescriptor;
    }
}
