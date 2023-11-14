package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL_MEDIA_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.socialmedialink.SocialMediaLink;
import seedu.address.model.tutorial.Tutorial;

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
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MAJOR, PREFIX_YEAR, PREFIX_EMAIL,
                PREFIX_DESCRIPTION, PREFIX_TUTORIAL, PREFIX_SOCIAL_MEDIA_LINK, PREFIX_NATIONALITY, PREFIX_GENDER);

        Email email;

        try {
            email = ParserUtil.parseEmail(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_MAJOR, PREFIX_YEAR, PREFIX_EMAIL,
                PREFIX_DESCRIPTION, PREFIX_NATIONALITY, PREFIX_GENDER);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_MAJOR).isPresent()) {
            editPersonDescriptor.setMajor(ParserUtil.parseMajor(argMultimap.getValue(PREFIX_MAJOR).get()));
        }
        if (argMultimap.getValue(PREFIX_YEAR).isPresent()) {
            editPersonDescriptor.setYear(ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editPersonDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        /*
        List<String> tutorialsStrings = argMultimap.getAllValues(PREFIX_TUTORIAL);
        if (!tutorialsStrings.isEmpty()) {
            Set<Tutorial> tutorialList = ParserUtil.parseTutorials(tutorialsStrings);
            editPersonDescriptor.setTutorials(tutorialList);
        }
        */
        parseTutorialsForEdit(argMultimap.getAllValues(PREFIX_TUTORIAL))
                .ifPresent(editPersonDescriptor::setTutorials);

        parseSocialMediaLinksForEdit(argMultimap.getAllValues(PREFIX_SOCIAL_MEDIA_LINK))
                .ifPresent(editPersonDescriptor::setSocialMediaLinks);

        if (argMultimap.getValue(PREFIX_NATIONALITY).isPresent()) {
            editPersonDescriptor.setNationality(
                ParserUtil.parseNationality(argMultimap.getValue(PREFIX_NATIONALITY).get()));
        }

        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editPersonDescriptor.setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(email, editPersonDescriptor);
    }

    private Optional<Set<Tutorial>> parseTutorialsForEdit(Collection<String> tutorials) throws ParseException {
        assert tutorials != null;

        if (tutorials.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tutorialSet =
                tutorials.size() == 1 && tutorials.contains("") ? Collections.emptySet() : tutorials;
        return Optional.of(ParserUtil.parseTutorials(tutorialSet));
    }

    /**
     * Parses {@code Collection<String> socialMediaLinks} into a {@code Set<SocialMediaLink>}
     * if {@code socialMediaLinks} is non-empty.
     * If {@code socialMediaLinks} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<SocialMediaLink>} containing zero tags.
     */
    private Optional<Set<SocialMediaLink>> parseSocialMediaLinksForEdit(Collection<String> socialMediaLinks)
            throws ParseException {
        assert socialMediaLinks != null;

        if (socialMediaLinks.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> socialMediaLinkSet = socialMediaLinks.size() == 1 && socialMediaLinks.contains("")
                ? Collections.emptySet() : socialMediaLinks;
        return Optional.of(ParserUtil.parseSocialMediaLinks(socialMediaLinkSet));
    }

}
