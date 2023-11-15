package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRESCRIPTION;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.prescription.ConsumptionCount;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Stock;

public class ParserUtilTest {
    private static final String INVALID_NAME = "@sp!r!n";
    private static final String INVALID_DOSAGE = "abc";
    private static final String INVALID_LARGE_VALUE = "9999999999";
    private static final String INVALID_FREQUENCY = "Yearly";
    private static final String INVALID_DATE = "1/1/2023";
    private static final String INVALID_TOTAL_STOCK = "a";
    private static final String INVALID_CONSUMPTION = "a";
    private static final String INVALID_NOTE = "***";

    private static final String VALID_NAME = "Aspirin";
    private static final String VALID_DOSAGE = "1";
    private static final String VALID_FREQUENCY = "Daily";
    private static final String VALID_START_DATE = "01/10/2023";
    private static final String VALID_END_DATE = "23/02/2024";
    private static final String VALID_EXPIRY_DATE = "12/12/2024";
    private static final String VALID_TOTAL_STOCK = "100";
    private static final String VALID_CONSUMPTION = "1";
    private static final String VALID_NOTE = "Take after food";

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
        assertEquals(INDEX_FIRST_PRESCRIPTION, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PRESCRIPTION, ParserUtil.parseIndex("  1  "));
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
    public void parseDosage_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDosage((String) null));
    }

    @Test
    public void parseDosage_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDosage(INVALID_DOSAGE));
    }

    @Test
    public void parseDosage_validValueWithoutWhitespace_returnsDosage() throws Exception {
        Dosage expectedDosage = new Dosage(VALID_DOSAGE);
        assertEquals(expectedDosage, ParserUtil.parseDosage(VALID_DOSAGE));
    }

    @Test
    public void parseDosage_validValueWithWhitespace_returnsTrimmedDosage() throws Exception {
        String dosageWithWhitespace = WHITESPACE + VALID_DOSAGE + WHITESPACE;
        Dosage expectedDosage = new Dosage(VALID_DOSAGE);
        assertEquals(expectedDosage, ParserUtil.parseDosage(dosageWithWhitespace));
    }

    @Test
    public void parseDosage_invalidLargeValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDosage(INVALID_LARGE_VALUE));
    }

    @Test
    public void parseDosage_invalidDosage_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDosage("0"));
    }

    @Test
    public void parseFrequency_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseFrequency((String) null));
    }

    @Test
    public void parseFrequency_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFrequency(INVALID_FREQUENCY));
    }

    @Test
    public void parseFrequency_validValueWithoutWhitespace_returnsFrequency() throws Exception {
        Frequency expectedFrequency = new Frequency(VALID_FREQUENCY);
        assertEquals(expectedFrequency, ParserUtil.parseFrequency(VALID_FREQUENCY));
    }

    @Test
    public void parseFrequency_validValueWithWhitespace_returnsTrimmedFrequency() throws Exception {
        String frequencyWithWhitespace = WHITESPACE + VALID_FREQUENCY + WHITESPACE;
        Frequency expectedFrequency = new Frequency(VALID_FREQUENCY);
        assertEquals(expectedFrequency, ParserUtil.parseFrequency(frequencyWithWhitespace));
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsStartDate() throws Exception {
        Date expectedStartDate = new Date(VALID_START_DATE);
        assertEquals(expectedStartDate, ParserUtil.parseDate(VALID_START_DATE));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedStartDate() throws Exception {
        String startDateWithWhitespace = WHITESPACE + VALID_START_DATE + WHITESPACE;
        Date expectedStartDate = new Date(VALID_START_DATE);
        assertEquals(expectedStartDate, ParserUtil.parseDate(startDateWithWhitespace));
    }

    @Test
    public void parseDate_invalidDay_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate("00/01/2023"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDate("32/01/2023"));
    }

    @Test
    public void parseDate_invalidMonth_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate("01/00/2023"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDate("01/13/2023"));
    }

    @Test
    public void parseDate_invalidYear_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate("01/01/0000"));
    }

    @Test
    public void parseDate_invalidFebruaryNotLeapYear_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate("29/02/2023"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDate("30/02/2023"));
    }

    @Test
    public void parseDate_invalidFebruaryLeapYear_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate("30/02/2024"));
    }

    @Test
    public void parseDate_validFebruaryLeapYear_returnsDate() throws Exception {
        Date expectedStartDate = new Date("29/02/2024");
        assertEquals(expectedStartDate, ParserUtil.parseDate("29/02/2024"));
    }

    @Test
    public void parseDate_invalidMonthsWith30Days_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate("31/04/2023"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDate("31/06/2023"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDate("31/09/2023"));
        assertThrows(ParseException.class, () -> ParserUtil.parseDate("31/11/2023"));
    }

    @Test
    public void parseTotalStock_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTotalStock((String) null));
    }

    @Test
    public void parseTotalStock_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTotalStock(INVALID_TOTAL_STOCK));
    }

    @Test
    public void parseTotalStock_validValueWithoutWhitespace_returnsTotalStock() throws Exception {
        Stock expectedTotalStock = new Stock(VALID_TOTAL_STOCK);
        assertEquals(expectedTotalStock, ParserUtil.parseTotalStock(VALID_TOTAL_STOCK));
    }

    @Test
    public void parseTotalStock_validValueWithWhitespace_returnsTrimmedTotalStock() throws Exception {
        String totalStockWithWhitespace = WHITESPACE + VALID_TOTAL_STOCK + WHITESPACE;
        Stock expectedTotalStock = new Stock(VALID_TOTAL_STOCK);
        assertEquals(expectedTotalStock, ParserUtil.parseTotalStock(totalStockWithWhitespace));
    }

    @Test
    public void parseTotalStock_invalidLargeValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTotalStock(INVALID_LARGE_VALUE));
    }

    @Test
    public void parseConsumptionCount_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseConsumptionCount((String) null));
    }

    @Test
    public void parseConsumptionCount_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseConsumptionCount(INVALID_CONSUMPTION));
    }

    @Test
    public void parseConsumptionCount_validValueWithoutWhitespace_returnsConsumptionCount() throws Exception {
        ConsumptionCount expectedConsumptionCount = new ConsumptionCount(VALID_CONSUMPTION);
        assertEquals(expectedConsumptionCount, ParserUtil.parseConsumptionCount(VALID_CONSUMPTION));
    }

    @Test
    public void parseConsumptionCount_validValueWithWhitespace_returnsTrimmedConsumptionCount() throws Exception {
        String consumptionCountWithWhitespace = WHITESPACE + VALID_CONSUMPTION + WHITESPACE;
        ConsumptionCount expectedConsumptionCount = new ConsumptionCount(VALID_CONSUMPTION);
        assertEquals(expectedConsumptionCount, ParserUtil.parseConsumptionCount(consumptionCountWithWhitespace));
    }

    @Test
    public void parseNote_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNote((String) null));
    }

    @Test
    public void parseNote_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNote(INVALID_NOTE));
    }

    @Test
    public void parseNote_validValueWithoutWhitespace_returnsNote() throws Exception {
        Note expectedNote = new Note(VALID_NOTE);
        assertEquals(expectedNote, ParserUtil.parseNote(VALID_NOTE));
    }

    @Test
    public void parseNote_validValueWithWhitespace_returnsTrimmedNote() throws Exception {
        String noteWithWhitespace = WHITESPACE + VALID_NOTE + WHITESPACE;
        Note expectedNote = new Note(VALID_NOTE);
        assertEquals(expectedNote, ParserUtil.parseNote(noteWithWhitespace));
    }
}
