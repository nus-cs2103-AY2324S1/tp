//@@author Gabriel4357
package seedu.address.logic.parser.delivery;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.delivery.DeliveryEditCommand;
import seedu.address.logic.commands.delivery.DeliveryEditCommand.DeliveryEditDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.delivery.DeliveryDate;
import seedu.address.model.delivery.DeliveryName;
import seedu.address.model.delivery.DeliveryStatus;
import seedu.address.model.delivery.Note;

/**
 * Parses input arguments and creates a new DeliveryEditCommand object
 */
public class DeliveryEditCommandParser implements Parser<DeliveryEditCommand> {

    private static final Logger logger = Logger.getLogger(DeliveryEditCommandParser.class.getName());

    /**
     * Parses the given {@code String} of arguments in the context of the DeliveryEditCommand
     * and returns an DeliveryEditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeliveryEditCommand parse(String args) throws ParseException {
        requireNonNull(args);

        logger.info("Parsing DeliveryEditCommand: " + args);

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CUSTOMER_ID,
                PREFIX_DATE, PREFIX_STATUS, PREFIX_NOTE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            logger.info("Index parsed: " + index);
        } catch (ParseException pe) {
            logger.warning("Index parse failed. Invalid Command Format.");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryEditCommand.MESSAGE_USAGE), pe);
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_CUSTOMER_ID,
            PREFIX_DATE, PREFIX_STATUS, PREFIX_NOTE);

        DeliveryEditDescriptor deliveryEditDescriptor = createDeliveryEditDescriptor(argMultimap);

        logger.info("DeliveryEditDescriptor created: " + deliveryEditDescriptor);
        return new DeliveryEditCommand(index, deliveryEditDescriptor);
    }

    /**
     * Creates and returns a {@code DeliveryEditDescriptor} based on the given {@code ArgumentMultimap}.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeliveryEditDescriptor createDeliveryEditDescriptor(ArgumentMultimap argMultimap) throws ParseException {

        DeliveryEditDescriptor deliveryEditDescriptor = new DeliveryEditDescriptor();
        Optional<String> name = argMultimap.getValue(PREFIX_NAME);
        Optional<String> customerId = argMultimap.getValue(PREFIX_CUSTOMER_ID);
        Optional<String> date = argMultimap.getValue(PREFIX_DATE);
        Optional<String> status = argMultimap.getValue(PREFIX_STATUS);
        Optional<String> note = argMultimap.getValue(PREFIX_NOTE);


        if (name.isPresent()) {
            DeliveryName deliveryName = ParserUtil.parseDeliveryName(name.get());
            deliveryEditDescriptor.setDeliveryName(deliveryName);
        }
        if (customerId.isPresent()) {
            int id = ParserUtil.parseId(customerId.get());
            deliveryEditDescriptor.setCustomerId(id);
        }
        if (date.isPresent()) {
            DeliveryDate deliveryDate = ParserUtil.parseDeliveryDate(date.get());
            deliveryEditDescriptor.setDeliveryDate(deliveryDate);
        }
        if (status.isPresent()) {
            DeliveryStatus deliveryStatus = ParserUtil.parseDeliveryStatus(status.get());
            deliveryEditDescriptor.setStatus(deliveryStatus);
        }
        if (note.isPresent()) {
            Note deliveryNote = ParserUtil.parseNote(note.get());
            deliveryEditDescriptor.setNote(deliveryNote);
        }

        if (!deliveryEditDescriptor.isAnyFieldEdited()) {
            logger.warning("No fields provided.");
            throw new ParseException(DeliveryEditCommand.MESSAGE_NOT_EDITED);
        }
        return deliveryEditDescriptor;
    }
}
//@@author Gabriel4357
