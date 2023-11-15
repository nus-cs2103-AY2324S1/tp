package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.SortSellerCommand;
import seedu.address.model.displayable.SortOrder;
import seedu.address.model.displayable.seller.SellerComparator;

public class SortSellerCommandParserTest {
    static final String INVALID_INPUT = " ALASDKJDL";
    static final String INVALID_PREFIX = " z/a";
    static final String INVALID_INPUT_BEFORE_VALID_PREFIX = " ALASDKJDL prio/a";
    static final String INVALID_PREFIX_BEFORE_VALID_PREFIX = " z/a i/a";
    static final String INVALID_INPUT_AND_PREFIX_BEFORE_VALID_PREFIX = " ALASDKJDL z/a prio/a";
    static final String INVALID_INPUT_AFTER_VALID_PREFIX = " prio/a ALASDKJDL";
    static final String INVALID_PREFIX_AFTER_VALID_PREFIX = " i/a z/a";
    static final String INVALID_INPUT_AND_PREFIX_AFTER_VALID_PREFIX = " prio/a ALASDKJDL z/a";
    static final String BAD_FIELDS_INPUT = " ah/qwerty";
    static final String DUPLICATE_PREFIXES = " n/d n/a";
    static final String MULTIPLE_PREFIXES = " prio/d n/d";
    static final String VALID_INPUT = " prio/d";
    private final SortSellerCommandParser parser = new SortSellerCommandParser();

    @Test
    public void assertPassesParse_invalidInput() {
        assertParseSuccess(parser, INVALID_INPUT, new SortSellerCommand(SellerComparator.of()));
    }

    @Test
    public void assertPassesParse_invalidPrefix() {
        assertParseSuccess(parser, INVALID_PREFIX, new SortSellerCommand(SellerComparator.of()));
    }

    @Test
    public void assertPassesParse_invalidInputBeforeValidPrefix() {
        assertParseSuccess(parser, INVALID_INPUT_BEFORE_VALID_PREFIX,
                new SortSellerCommand(SellerComparator.of(PREFIX_PRIORITY, new SortOrder("a"))));
    }

    @Test
    public void assertPassesParse_invalidPrefixBeforeValidPrefix() {
        assertParseSuccess(parser, INVALID_PREFIX_BEFORE_VALID_PREFIX,
                new SortSellerCommand(SellerComparator.of(PREFIX_HOUSE_INFO, new SortOrder("a"))));
    }

    @Test
    public void assertPassesParse_invalidInputAndPrefixBeforeValidPrefix() {
        assertParseSuccess(parser, INVALID_INPUT_AND_PREFIX_BEFORE_VALID_PREFIX,
                new SortSellerCommand(SellerComparator.of(PREFIX_PRIORITY, new SortOrder("a"))));
    }

    @Test
    public void assertFailsParse_invalidInputAfterValidPrefix() {
        assertParseFailure(parser, INVALID_INPUT_AFTER_VALID_PREFIX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortSellerCommand.MESSAGE_USAGE));
    }

    @Test
    public void assertFailsParse_invalidPrefixAfterValidPrefix() {
        assertParseFailure(parser, INVALID_PREFIX_AFTER_VALID_PREFIX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortSellerCommand.MESSAGE_USAGE));
    }

    @Test
    public void assertFailsParse_invalidInputAndPrefixAfterValidPrefix() {
        assertParseFailure(parser, INVALID_INPUT_AND_PREFIX_AFTER_VALID_PREFIX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortSellerCommand.MESSAGE_USAGE));
    }

    @Test
    public void assertFailsParse_badFieldsInput() {
        assertParseFailure(parser, BAD_FIELDS_INPUT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortSellerCommand.MESSAGE_USAGE));
    }
    @Test
    public void assertFailsParse_duplicatePrefixes() {
        Prefix[] duplicatedPrefixes = new Prefix[] {PREFIX_NAME};
        assertParseFailure(parser, DUPLICATE_PREFIXES,
                Messages.getErrorMessageForDuplicatePrefixes(duplicatedPrefixes));
    }

    @Test
    public void assertPassesParse_multiplePrefixes() {
        assertParseSuccess(parser, MULTIPLE_PREFIXES,
                new SortSellerCommand(SellerComparator.of(PREFIX_NAME, new SortOrder("d"))));
    }
    @Test
    public void assertPassesParse_validInput() {
        assertParseSuccess(parser, VALID_INPUT,
                new SortSellerCommand(SellerComparator.of(PREFIX_PRIORITY, new SortOrder("d"))));
    }
}
