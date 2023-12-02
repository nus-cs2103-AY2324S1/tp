//@@author Gabriel4357
package seedu.address.logic.parser.delivery;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.logic.commands.delivery.DeliveryAddCommand;
import seedu.address.logic.commands.delivery.DeliveryAddCommand.DeliveryAddDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.delivery.DeliveryDate;

/**
 * Parses input arguments and creates a new DeliveryAddCommand object
 */
public class DeliveryAddCommandParser implements Parser<DeliveryAddCommand> {

    private static final Logger logger = Logger.getLogger(DeliveryAddCommandParser.class.getName());

    /**
     * Parses the given {@code String} of arguments in the context of the DeliveryAddCommand
     * and returns a DeliveryAddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeliveryAddCommand parse(String args) throws ParseException {
        requireNonNull(args);
        logger.info("Parsing DeliveryAddCommand: " + args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CUSTOMER_ID, PREFIX_DATE);

        if (!argMultimap.arePrefixesPresent(PREFIX_CUSTOMER_ID, PREFIX_DATE)
                || argMultimap.getPreamble().isEmpty()) {
            logger.warning("No/Wrong prefix provided. Invalid Command Format.");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeliveryAddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CUSTOMER_ID, PREFIX_DATE);

        DeliveryAddDescriptor deliveryAddDescriptor = new DeliveryAddDescriptor();

        Optional<String> customerId = argMultimap.getValue(PREFIX_CUSTOMER_ID);
        Optional<String> deliveryDate = argMultimap.getValue(PREFIX_DATE);

        if (!argMultimap.isEmptyPreamble()) {
            deliveryAddDescriptor.setDeliveryName(ParserUtil
                    .parseDeliveryName(argMultimap.getPreamble()));
        }
        if (customerId.isPresent()) {
            int id = ParserUtil.parseId(customerId.get());
            deliveryAddDescriptor.setCustomerId(id);
        }
        if (deliveryDate.isPresent()) {
            DeliveryDate date = ParserUtil.parseDeliveryDate(deliveryDate.get());
            deliveryAddDescriptor.setDeliveryDate(date);
        }

        logger.info("DeliveryAddDescriptor created: " + deliveryAddDescriptor);
        return new DeliveryAddCommand(deliveryAddDescriptor);
    }
}
//@@author Gabriel4357
