package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELLING_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.logic.commands.AddSellerCommand;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.displayable.seller.Seller;

/**
 * A utility class for Person.
 */
public class PersonUtil {
    /**
     * Returns an add command string for adding the {@code buyer}.
     */
    public static String getAddCommand(Buyer buyer) {
        return AddBuyerCommand.COMMAND_WORD + " " + getBuyerDetails(buyer);
    }
    /**
     * Returns an add command string for adding the {@code seller}.
     */
    public static String getAddCommand(Seller seller) {
        return AddSellerCommand.COMMAND_WORD + " " + getSellerDetails(seller);
    }

    /**
     * Returns the part of command string for the given {@code buyer}'s details.
     */
    public static String getBuyerDetails(Buyer buyer) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + buyer.getName().fullName + " ");
        sb.append(PREFIX_PHONE + buyer.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + buyer.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + buyer.getAddress().value + " ");
        sb.append(PREFIX_HOUSE_INFO + buyer.getHouseInfo().toString() + " ");
        // priority field is optional
        // sb.append(PREFIX_PRIORITY + buyer.getPriority().toString() + " ");
        buyer.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
    /**
     * Returns the part of command string for the given {@code seller}'s details.
     */
    public static String getSellerDetails(Seller seller) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + seller.getName().fullName + " ");
        sb.append(PREFIX_PHONE + seller.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + seller.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + seller.getAddress().value + " ");
        sb.append(PREFIX_SELLING_ADDRESS + seller.getSellingAddress().value + " ");
        sb.append(PREFIX_HOUSE_INFO + seller.getHouseInfo().toString() + " ");
        // priority field is optional
        // sb.append(PREFIX_PRIORITY + seller.getPriority().toString() + " ");
        seller.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
}
