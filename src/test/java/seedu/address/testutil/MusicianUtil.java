package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.musician.AddCommand;
import seedu.address.logic.commands.musician.EditCommand;
import seedu.address.model.musician.Musician;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Musician.
 */
public class MusicianUtil {

    /**
     * Returns an add command string for adding the {@code musician}.
     */
    public static String getAddCommand(Musician musician) {
        return AddCommand.COMMAND_WORD + " " + getMusicianDetails(musician);
    }

    /**
     * Returns the part of command string for the given {@code musician}'s details.
     */
    public static String getMusicianDetails(Musician musician) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + musician.getName().fullName + " ");
        sb.append(PREFIX_PHONE + musician.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + musician.getEmail().value + " ");
        musician.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        musician.getInstruments().stream().forEach(
            s -> sb.append(PREFIX_INSTRUMENT + s.tagName + " ")
        );
        musician.getGenres().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditMusicianDescriptor}'s details.
     */
    public static String getEditMusicianDescriptorDetails(EditCommand.EditMusicianDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getInstruments().isPresent()) {
            Set<Tag> instruments = descriptor.getInstruments().get();
            if (instruments.isEmpty()) {
                sb.append(PREFIX_INSTRUMENT).append(" ");
            } else {
                instruments.forEach(s -> sb.append(PREFIX_INSTRUMENT).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getGenres().isPresent()) {
            Set<Tag> genres = descriptor.getGenres().get();
            if (genres.isEmpty()) {
                sb.append(PREFIX_GENRE).append(" ");
            } else {
                genres.forEach(s -> sb.append(PREFIX_GENRE).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
