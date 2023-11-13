package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.risklevel.RiskLevel;
import seedu.address.model.student.Address;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_RISK_LEVEL = "#friend";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_NAME_2 = "Ben";
    private static final String VALID_PHONE = "12345678";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_RISK_LEVEL_1 = "high";
    private static final String VALID_RISK_LEVEL_2 = "low";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseName_validValueWithExtraWhitespace_returnsTrimmedName() throws Exception {
        String nameWithExtraWhitespace = VALID_NAME + WHITESPACE + WHITESPACE + VALID_NAME_2;
        String nameWithoutExtraWhitespace = VALID_NAME + " " + VALID_NAME_2;
        Name expectedName = new Name(nameWithoutExtraWhitespace);
        assertEquals(expectedName, ParserUtil.parseName(nameWithExtraWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    // Ambiguous method call because of overloaded method
    //@Test
    //public void parseRiskLevel_null_throwsNullPointerException() {
    //    assertThrows(NullPointerException.class, () -> ParserUtil.parseRiskLevel(null));
    //}

    @Test
    public void parseRiskLevel_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRiskLevel(INVALID_RISK_LEVEL));
    }

    @Test
    public void parseRiskLevel_validValueWithoutWhitespace_returnsTag() throws Exception {
        RiskLevel expectedTag = new RiskLevel(VALID_RISK_LEVEL_1);
        assertEquals(expectedTag, ParserUtil.parseRiskLevel(VALID_RISK_LEVEL_1));
    }

    @Test
    public void parseRiskLevel_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_RISK_LEVEL_1 + WHITESPACE;
        RiskLevel expectedTag = new RiskLevel(VALID_RISK_LEVEL_1);
        assertEquals(expectedTag, ParserUtil.parseRiskLevel(tagWithWhitespace));
    }

    // Ambiguous method due overload
    //@Test
    //public void parseRiskLevel_null_throwsNullPointerException() {
    //    assertThrows(NullPointerException.class, () -> ParserUtil.parseRiskLevel(null));
    //}

    @Test
    public void parseRiskLevel_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRiskLevel(Arrays.asList(
                                                                            VALID_RISK_LEVEL_1, INVALID_RISK_LEVEL)));
    }

    @Test
    public void parseRiskLevel_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseRiskLevel(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseRiskLevel_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<RiskLevel> actualTagSet = ParserUtil.parseRiskLevel(Arrays.asList(VALID_RISK_LEVEL_1));
        Set<RiskLevel> expectedTagSet = new HashSet<RiskLevel>(Arrays.asList(
                                                               new RiskLevel(VALID_RISK_LEVEL_1)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
