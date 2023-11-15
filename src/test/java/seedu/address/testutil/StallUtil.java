package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.AddStallCommand;
import seedu.address.logic.commands.EditStallCommand.EditStallDescriptor;
import seedu.address.model.stall.Stall;


/**
 * A utility class for Stall.
 */
public class StallUtil {

    /**
     * Returns an add command string for adding the {@code stall}.
     */
    public static String getAddCommand(Stall stall) {
        return AddStallCommand.COMMAND_WORD + " " + getStallDetails(stall);
    }

    /**
     * Returns the part of command string for the given {@code stall}'s details.
     */
    public static String getStallDetails(Stall stall) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + stall.getName().fullName + " ");
        sb.append(PREFIX_LOCATION + stall.getLocation().locationName + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStallDescriptor}'s details.
     */
    public static String getEditStallDescriptorDetails(EditStallDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getLocation().ifPresent(location -> sb.append(PREFIX_LOCATION).append(location.locationName)
                .append(" "));
        return sb.toString();
    }
}
