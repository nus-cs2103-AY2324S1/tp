package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_LOCALCODE;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_LOCALNAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TestUtil;

public class SeplendidArgumentTokenizerTest {

    /**
     * Unit testing of SeplendidArgumentTokenizer
     */
    @Test
    public void tokenize_multipleArguments_success() {

        SeplendidArgumentMap actualArgumentMap = SeplendidArgumentTokenizer.tokenize(
                TestUtil.getSquareBracketWrappedArgument(TYPICAL_LOCAL_COURSE_CODE)
                        + TestUtil.getSquareBracketWrappedArgument(TYPICAL_LOCAL_COURSE_NAME),
                PARAMETER_LOCALCODE, PARAMETER_LOCALNAME);

        assertArgumentEquals(actualArgumentMap, PARAMETER_LOCALCODE, TYPICAL_LOCAL_COURSE_CODE);
        assertArgumentEquals(actualArgumentMap, PARAMETER_LOCALNAME, TYPICAL_LOCAL_COURSE_NAME);

    }


    private void assertArgumentEquals(SeplendidArgumentMap argMultimap, SeplendidParameter parameter,
                                      String expectedValue) {
        assertEquals(expectedValue, argMultimap.getValue(parameter).get());
    }
}
