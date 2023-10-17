package networkbook.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import networkbook.commons.core.index.Index;
import networkbook.logic.Messages;
import networkbook.logic.commands.EditCommand;
import networkbook.logic.commands.EditCommand.EditPersonDescriptor;
import networkbook.logic.parser.exceptions.ParseException;
import networkbook.model.person.Email;
import networkbook.model.person.Link;
import networkbook.model.tag.Tag;
import networkbook.model.util.UniqueList;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args,
                        CliSyntax.PREFIX_NAME,
                        CliSyntax.PREFIX_PHONE,
                        CliSyntax.PREFIX_EMAIL,
                        CliSyntax.PREFIX_LINK,
                        CliSyntax.PREFIX_GRADUATING_YEAR,
                        CliSyntax.PREFIX_COURSE,
                        CliSyntax.PREFIX_SPECIALISATION,
                        CliSyntax.PREFIX_TAG
                );

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(
                            Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                            EditCommand.MESSAGE_USAGE
                    ),
                    pe
            );
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
                CliSyntax.PREFIX_NAME,
                CliSyntax.PREFIX_PHONE,
                CliSyntax.PREFIX_EMAIL,
                CliSyntax.PREFIX_LINK,
                CliSyntax.PREFIX_GRADUATING_YEAR,
                CliSyntax.PREFIX_COURSE,
                CliSyntax.PREFIX_SPECIALISATION
        );

        EditPersonDescriptor editPersonDescriptor = generateEditPersonDescriptor(argMultimap);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Creates an {@code EditPersonDescriptor} based on the arguments provided in an edit or add command.
     * @throws ParseException if the user input does not conform the expected format
     */
    public static EditPersonDescriptor generateEditPersonDescriptor(ArgumentMultimap argMultimap)
            throws ParseException {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(
                    ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(
                    ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get()));
        }
        parseEmailsForEdit(argMultimap.getAllValues(CliSyntax.PREFIX_EMAIL))
                .ifPresent(editPersonDescriptor::setEmails);
        parseLinksForEdit(argMultimap.getAllValues(CliSyntax.PREFIX_LINK))
                .ifPresent(editPersonDescriptor::setLinks);
        if (argMultimap.getValue(CliSyntax.PREFIX_GRADUATING_YEAR).isPresent()) {
            editPersonDescriptor.setGraduatingYear(
                    ParserUtil.parseGraduatingYear(argMultimap.getValue(CliSyntax.PREFIX_GRADUATING_YEAR).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_COURSE).isPresent()) {
            editPersonDescriptor.setCourse(
                    ParserUtil.parseCourse(argMultimap.getValue(CliSyntax.PREFIX_COURSE).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_SPECIALISATION).isPresent()) {
            editPersonDescriptor.setSpecialisation(
                    ParserUtil.parseSpecialisation(argMultimap.getValue(CliSyntax.PREFIX_SPECIALISATION).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(CliSyntax.PREFIX_TAG))
                .ifPresent(editPersonDescriptor::setTags);
        if (argMultimap.getValue(CliSyntax.PREFIX_PRIORITY).isPresent()) {
            editPersonDescriptor.setPriority(
                    ParserUtil.parsePriority(argMultimap.getValue(CliSyntax.PREFIX_PRIORITY).get()));
        }

        return editPersonDescriptor;
    }

    /**
     * Parses {@code Collection<String> emails} into a {@code UniqueList<Email>} wrapped in an {@code Optional}.
     */
    private static Optional<UniqueList<Email>> parseEmailsForEdit(Collection<String> emails) throws ParseException {
        requireNonNull(emails);

        if (emails.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseEmails(emails));
    }

    /**
     * Parses {@code Collection<String> links} into a {@code UniqueList<Link>} wrapped in an {@code Optional}.
     */
    private static Optional<UniqueList<Link>> parseLinksForEdit(Collection<String> links) throws ParseException {
        requireNonNull(links);

        if (links.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseLinks(links));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code UniqueList<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code UniqueList<Tag>} containing zero tags.
     */
    private static Optional<UniqueList<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        requireNonNull(tags);

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
