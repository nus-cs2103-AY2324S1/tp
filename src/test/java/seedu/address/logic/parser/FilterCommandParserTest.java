package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_METRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VALUE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.statistics.StatisticMetric;
import seedu.address.model.tag.Tag;


public class FilterCommandParserTest {
    private static final Tag VALID_TAG = new Tag("CS2103T", "assessment");
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_missingTagField_failure() {
        String userInput = " " + PREFIX_METRIC + "MEAN";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_missingMetricField_failure() {
        String userInput = " " + PREFIX_TAG + "CS2103T";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidMetric_failure() {
        String userInput = " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "MEANNN";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_metricCaseInsensitive_success() {
        String userInput = " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "mEaN";
        FilterCommand expectedCommand = new FilterCommand(VALID_TAG, StatisticMetric.MEAN, 0);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validMean_success() {
        String userInput = " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "MEAN";
        FilterCommand expectedCommand = new FilterCommand(VALID_TAG, StatisticMetric.MEAN, 0);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validMeanWithValue_success() {
        // Value will be default zero when being inputted creating the command for mean and median
        String userInput = " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "MEAN" + " " + "val/50";
        FilterCommand expectedCommand = new FilterCommand(VALID_TAG, StatisticMetric.MEAN, 0);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validMedian_success() {
        String userInput = " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "MEDIAN";
        FilterCommand expectedCommand = new FilterCommand(VALID_TAG, StatisticMetric.MEDIAN, 0);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validMedianWithValue_success() {
        // Value will be default zero when being inputted creating the command for mean and median
        String userInput = " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "MEDIAN" + " " + "val/50";
        FilterCommand expectedCommand = new FilterCommand(VALID_TAG, StatisticMetric.MEDIAN, 0);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validPercentile_success() {
        String userInput = " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "PERCENTILE" + " " + PREFIX_VALUE
                + "50";
        FilterCommand expectedCommand = new FilterCommand(VALID_TAG, StatisticMetric.PERCENTILE, 50);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidPercentileWithoutValue_throwParseException() {
        String userInput = " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "PERCENTILE";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_validScore_success() {
        String userInput = " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "SCORE" + " " + PREFIX_VALUE + "50";
        FilterCommand expectedCommand = new FilterCommand(VALID_TAG, StatisticMetric.SCORE, 50);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidScoreWithoutValue_throwParseException() {
        String userInput = " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "SCORE";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidScoreValue_throwParseException() {
        String userInput = " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "SCORE" + " " + PREFIX_VALUE + "-50";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidExtraField_throwParseException() {
        String userInput = " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "SCORE" + " " + PREFIX_VALUE + "50"
                + " " + PREFIX_VALUE + "60";
        assertThrows(ParseException.class, () -> parser.parse(userInput));

        String userInput2 = " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "SCORE" + " " + PREFIX_VALUE + "50"
                + " " + PREFIX_TAG + "CS2103T";
        assertThrows(ParseException.class, () -> parser.parse(userInput2));

        String userInput3 = " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "SCORE" + " " + PREFIX_VALUE + "50"
                + " " + PREFIX_METRIC + "MEAN";
        assertThrows(ParseException.class, () -> parser.parse(userInput3));

        String userInput4 = " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "SCORE" + " " + PREFIX_VALUE + "50"
                + " " + PREFIX_TAG + "CS2103T" + " " + PREFIX_METRIC + "MEAN";
        assertThrows(ParseException.class, () -> parser.parse(userInput4));
    }

}
