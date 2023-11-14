package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.ParserUtil.areValuesEnclosedAndNonEmpty;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.INVALID_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.INVALID_PARTNER_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.INVALID_UNIVERSITY_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_UNIVERSITY_NAME;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourseAttribute;
import seedu.address.model.localcourse.LocalName;
import seedu.address.model.mapping.MappingAttribute;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerCourseAttribute;
import seedu.address.model.partnercourse.PartnerName;
import seedu.address.model.tag.Tag;
import seedu.address.model.university.UniversityAttribute;
import seedu.address.model.university.UniversityName;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

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
        assertEquals(INDEX_FIRST_OBJECT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_OBJECT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    // localcourse
    @Test
    public void parseLocalCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLocalCode(null));
    }

    @Test
    public void parseLocalCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCode(INVALID_LOCAL_COURSE_CODE));
    }

    @Test
    public void parseLocalCode_validValueWithoutWhitespace_returnsLocalCode() throws Exception {
        LocalCode expectedLocalCode = new LocalCode(TYPICAL_LOCAL_COURSE_CODE);
        assertEquals(expectedLocalCode, ParserUtil.parseLocalCode(TYPICAL_LOCAL_COURSE_CODE));
    }

    @Test
    public void parseLocalCode_validValueWithWhitespace_returnsTrimmedLocalCode() throws Exception {
        String localCodeWithWhitespace = WHITESPACE + TYPICAL_LOCAL_COURSE_CODE + WHITESPACE;
        LocalCode expectedLocalCode = new LocalCode(TYPICAL_LOCAL_COURSE_CODE);
        assertEquals(expectedLocalCode, ParserUtil.parseLocalCode(localCodeWithWhitespace));
    }

    @Test
    public void parseLocalName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLocalName(null));
    }

    @Test
    public void parseLocalName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalName(INVALID_LOCAL_COURSE_NAME));
    }

    @Test
    public void parseLocalName_validValueWithoutWhitespace_returnsLocalName() throws Exception {
        LocalName expectedLocalName = new LocalName(TYPICAL_LOCAL_COURSE_NAME);
        assertEquals(expectedLocalName, ParserUtil.parseLocalName(TYPICAL_LOCAL_COURSE_NAME));
    }

    @Test
    public void parseLocalName_validValueWithWhitespace_returnsTrimmedLocalName() throws Exception {
        String localNameWithWhitespace = WHITESPACE + TYPICAL_LOCAL_COURSE_NAME + WHITESPACE;
        LocalName expectedLocalName = new LocalName(TYPICAL_LOCAL_COURSE_NAME);
        assertEquals(expectedLocalName, ParserUtil.parseLocalName(localNameWithWhitespace));
    }

    @Test
    public void parseLocalCourseAttributeForSearch_validValues() throws ParseException {
        assertEquals(LocalCourseAttribute.LOCALCODE, ParserUtil.parseLocalCourseAttributeForSearch("localcode"));
        assertEquals(LocalCourseAttribute.LOCALNAME, ParserUtil.parseLocalCourseAttributeForSearch("localname"));
        assertEquals(LocalCourseAttribute.LOCALDESCRIPTION,
                     ParserUtil.parseLocalCourseAttributeForSearch("localdescription"));
    }

    @Test
    public void parseLocalCourseAttributeForSearch_invalidValues_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSearch(""));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSearch("a"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSearch("1"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSearch("@"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSearch("abc123"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSearch("local code"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSearch("localcode "));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSearch(" localcode"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSearch(null));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSearch("localunit"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSearch("LOCALCODE"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSearch("LOCALNAME"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSearch("UNIT"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSearch("DESCRIPTION"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSearch("invalid_attribute"));
    }

    @Test
    public void parseLocalCourseAttributeForSort_validValues() throws ParseException {
        assertEquals(LocalCourseAttribute.LOCALCODE, ParserUtil.parseLocalCourseAttributeForSort("localcode"));
        assertEquals(LocalCourseAttribute.LOCALNAME, ParserUtil.parseLocalCourseAttributeForSort("localname"));
    }

    @Test
    public void parseLocalCourseAttributeForSort_invalidValues_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort(""));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort("a"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort("1"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort("@"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort("abc123"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort("local code"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort("localcode "));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort(" localcode"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort(null));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort("localunit"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort("localdescription"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort("LOCALCODE"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort("LOCALNAME"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort("UNIT"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort("DESCRIPTION"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForSort("invalid_attribute"));
    }

    @Test
    public void parseLocalCourseAttributeForUpdate_validValues() throws ParseException {
        assertEquals(LocalCourseAttribute.LOCALCODE, ParserUtil.parseLocalCourseAttributeForUpdate("localcode"));
        assertEquals(LocalCourseAttribute.LOCALNAME, ParserUtil.parseLocalCourseAttributeForUpdate("localname"));
        assertEquals(LocalCourseAttribute.LOCALUNIT, ParserUtil.parseLocalCourseAttributeForUpdate("unit"));
        assertEquals(LocalCourseAttribute.LOCALDESCRIPTION,
                     ParserUtil.parseLocalCourseAttributeForUpdate("localdescription"));
    }

    @Test
    public void parseLocalCourseAttributeForUpdate_invalidValues_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForUpdate(""));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForUpdate("a"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForUpdate("1"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForUpdate("@"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForUpdate("abc123"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForUpdate("local code"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForUpdate("localcode "));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForUpdate(" localcode"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForUpdate(null));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForUpdate("LOCALCODE"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForUpdate("LOCALNAME"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForUpdate("UNIT"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForUpdate("DESCRIPTION"));
        assertThrows(ParseException.class, () -> ParserUtil.parseLocalCourseAttributeForUpdate("invalid_attribute"));
    }

    // partner course

    @Test
    public void parseUniversityName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseUniversityName(null));
    }

    @Test
    public void parseUniversityName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityName(INVALID_UNIVERSITY_NAME));
    }

    @Test
    public void parseUniversityName_validValueWithoutWhitespace_returnsUniversityName() throws Exception {
        UniversityName expectedUniversityName = new UniversityName(TYPICAL_UNIVERSITY_NAME);
        assertEquals(expectedUniversityName, ParserUtil.parseUniversityName(TYPICAL_UNIVERSITY_NAME));
    }

    @Test
    public void parseUniversityName_validValueWithWhitespace_returnsTrimmedUniversityName() throws Exception {
        String universityNameWithWhitespace = WHITESPACE + TYPICAL_UNIVERSITY_NAME + WHITESPACE;
        UniversityName expectedUniversityName = new UniversityName(TYPICAL_UNIVERSITY_NAME);
        assertEquals(expectedUniversityName, ParserUtil.parseUniversityName(universityNameWithWhitespace));
    }

    @Test
    public void parsePartnerCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePartnerCode(null));
    }

    @Test
    public void parsePartnerCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCode(INVALID_PARTNER_COURSE_CODE));
    }

    @Test
    public void parsePartnerCode_validValueWithoutWhitespace_returnsPartnerCode() throws Exception {
        PartnerCode expectedPartnerCode = new PartnerCode(TYPICAL_PARTNER_COURSE_CODE);
        assertEquals(expectedPartnerCode, ParserUtil.parsePartnerCode(TYPICAL_PARTNER_COURSE_CODE));
    }

    @Test
    public void parsePartnerCode_validValueWithWhitespace_returnsTrimmedPartnerCode() throws Exception {
        String partnerCodeWithWhitespace = WHITESPACE + TYPICAL_PARTNER_COURSE_CODE + WHITESPACE;
        PartnerCode expectedPartnerCode = new PartnerCode(TYPICAL_PARTNER_COURSE_CODE);
        assertEquals(expectedPartnerCode, ParserUtil.parsePartnerCode(partnerCodeWithWhitespace));
    }

    @Test
    public void parsePartnerName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePartnerName(null));
    }

    @Test
    public void parsePartnerName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerName(INVALID_PARTNER_COURSE_NAME));
    }

    @Test
    public void parsePartnerName_validValueWithoutWhitespace_returnsPartnerName() throws Exception {
        PartnerName expectedPartnerName = new PartnerName(TYPICAL_PARTNER_COURSE_NAME);
        assertEquals(expectedPartnerName, ParserUtil.parsePartnerName(TYPICAL_PARTNER_COURSE_NAME));
    }

    @Test
    public void parsePartnerName_validValueWithWhitespace_returnsTrimmedPartnerName() throws Exception {
        String partnerNameWithWhitespace = WHITESPACE + TYPICAL_PARTNER_COURSE_NAME + WHITESPACE;
        PartnerName expectedPartnerName = new PartnerName(TYPICAL_PARTNER_COURSE_NAME);
        assertEquals(expectedPartnerName, ParserUtil.parsePartnerName(partnerNameWithWhitespace));
    }

    @Test
    public void parsePartnerCourseAttributeForSearch_validValues() throws ParseException {
        assertEquals(PartnerCourseAttribute.PARTNERCODE,
                     ParserUtil.parsePartnerCourseAttributeForSearch("partnercode"));
        assertEquals(PartnerCourseAttribute.PARTNERNAME,
                     ParserUtil.parsePartnerCourseAttributeForSearch("partnername"));
        assertEquals(PartnerCourseAttribute.PARTNERDESCRIPTION,
                     ParserUtil.parsePartnerCourseAttributeForSearch("description"));
        assertEquals(PartnerCourseAttribute.UNIVERSITY, ParserUtil.parsePartnerCourseAttributeForSearch("university"));
    }

    @Test
    public void parsePartnerCourseAttributeForSearch_invalidValues_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch(""));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch("a"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch("1"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch("@"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch("abc123"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch("partner code"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch("partnercode "));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch(" partnercode"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch(null));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch("partnerunit"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch("PARTNERCODE"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch("PARTNERNAME"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch("PARTNERUNIT"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch("PARTNERDESCRIPTION"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch("UNIVERSITY"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSearch("invalid_attribute"));
    }

    @Test
    public void parsePartnerCourseAttributeForSort_validValues() throws ParseException {
        assertEquals(PartnerCourseAttribute.PARTNERCODE, ParserUtil.parsePartnerCourseAttributeForSort("partnercode"));
        assertEquals(PartnerCourseAttribute.PARTNERNAME, ParserUtil.parsePartnerCourseAttributeForSort("partnername"));
        assertEquals(PartnerCourseAttribute.UNIVERSITY, ParserUtil.parsePartnerCourseAttributeForSort("university"));
    }

    @Test
    public void parsePartnerCourseAttributeForSort_invalidValues_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort(""));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort("a"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort("1"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort("@"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort("abc123"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort(null));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort("partner code"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort("partnercode "));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort(" partnercode"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort("PARTNERCODE"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort("PARTNERNAME"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort("PARTNERUNIT"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort("PARTNERDESCRIPTION"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort("UNIVERSITY"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort("invalid_attribute"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort("partnerunit"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForSort("partnerdescription"));
    }

    @Test
    public void parsePartnerCourseAttributeForUpdate_validValues() throws ParseException {
        assertEquals(PartnerCourseAttribute.PARTNERCODE,
                     ParserUtil.parsePartnerCourseAttributeForUpdate("partnercode"));
        assertEquals(PartnerCourseAttribute.PARTNERNAME,
                     ParserUtil.parsePartnerCourseAttributeForUpdate("partnername"));
        assertEquals(PartnerCourseAttribute.PARTNERUNIT,
                     ParserUtil.parsePartnerCourseAttributeForUpdate("unit"));
        assertEquals(PartnerCourseAttribute.PARTNERDESCRIPTION,
                     ParserUtil.parsePartnerCourseAttributeForUpdate("description"));
    }

    @Test
    public void parsePartnerCourseAttributeForUpdate_invalidValues_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate(""));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate("a"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate("1"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate("@"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate("abc123"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate("partner code"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate("partnercode "));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate(" partnercode"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate(null));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate("university"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate("PARTNERCODE"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate("PARTNERNAME"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate("PARTNERUNIT"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate("PARTNERDESCRIPTION"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate("UNIVERSITY"));
        assertThrows(ParseException.class, () -> ParserUtil.parsePartnerCourseAttributeForUpdate("invalid_attribute"));
    }

    // mapping
    @Test
    public void parseMappingAttributeForSearch_validValues() throws ParseException {
        assertEquals(MappingAttribute.LOCALCODE, ParserUtil.parseMappingAttributeForSearch("localcode"));
        assertEquals(MappingAttribute.LOCALNAME, ParserUtil.parseMappingAttributeForSearch("localname"));
        assertEquals(MappingAttribute.PARTNERCODE, ParserUtil.parseMappingAttributeForSearch("partnercode"));
        assertEquals(MappingAttribute.PARTNERNAME, ParserUtil.parseMappingAttributeForSearch("partnername"));
        assertEquals(MappingAttribute.UNIVERSITY, ParserUtil.parseMappingAttributeForSearch("university"));
        assertEquals(MappingAttribute.INFORMATION, ParserUtil.parseMappingAttributeForSearch("information"));
    }

    @Test
    public void parseMappingAttributeForSearch_invalidValues_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch(""));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch("a"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch("1"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch("@"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch("abc123"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch("local code"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch("localcode "));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch(" localcode"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch(null));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch("LOCALCODE"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch("LOCALNAME"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch("PARTNERCODE"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch("PARTNERNAME"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch("PARTNERDESCRIPTION"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch("UNIVERSITY"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSearch("invalid_attribute"));
    }

    @Test
    public void parseMappingAttributeForSort_validValues() throws ParseException {
        assertEquals(MappingAttribute.LOCALCODE, ParserUtil.parseMappingAttributeForSort("localcode"));
        assertEquals(MappingAttribute.LOCALNAME, ParserUtil.parseMappingAttributeForSort("localname"));
        assertEquals(MappingAttribute.PARTNERCODE, ParserUtil.parseMappingAttributeForSort("partnercode"));
        assertEquals(MappingAttribute.PARTNERNAME, ParserUtil.parseMappingAttributeForSort("partnername"));
        assertEquals(MappingAttribute.UNIVERSITY, ParserUtil.parseMappingAttributeForSort("university"));
        assertEquals(MappingAttribute.INFORMATION, ParserUtil.parseMappingAttributeForSort("information"));
    }

    @Test
    public void parseMappingAttributeForSort_invalidValues_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort(""));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort("a"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort("1"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort("@"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort("abc123"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort("local code"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort("localcode "));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort(" localcode"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort(null));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort("LOCALCODE"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort("LOCALNAME"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort("PARTNERCODE"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort("PARTNERNAME"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort("UNIVERSITY"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort("DESCRIPTION"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMappingAttributeForSort("invalid_attribute"));
    }

    // university
    @Test
    public void parseUniversityAttributeForSearch_validValues() throws ParseException {
        assertEquals(UniversityAttribute.UNIVERSITY, ParserUtil.parseUniversityAttributeForSearch("university"));
    }

    @Test
    public void parseUniversityAttributeForSearch_invalidValues_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("units"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort(""));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort(" "));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("a"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("1"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("@"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("abc123"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("univer sity"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("university "));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort(" university"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort(null));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("UNIVERSITY"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("invalid_attribute"));
    }

    @Test
    public void parseUniversityAttributeForSort_validValues() throws ParseException {
        assertEquals(UniversityAttribute.UNIVERSITY, ParserUtil.parseUniversityAttributeForSearch("university"));
    }

    @Test
    public void parseUniversityAttributeForSort_invalidValues_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("units"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort(""));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort(" "));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("a"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("1"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("@"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("abc123"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("univer sity"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("university "));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort(" university"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort(null));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("UNIVERSITY"));
        assertThrows(ParseException.class, () -> ParserUtil.parseUniversityAttributeForSort("invalid_attribute"));
    }


    @Test
    public void areValuesEnclosedAndNonEmpty_emptyArgs_returnsFailure() {
        assertEquals(ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE, areValuesEnclosedAndNonEmpty(""));
    }

    @Test
    public void areValuesEnclosedAndNonEmpty_invalidValueWithMismatchBrackets1_returnsFailure() {
        assertEquals(ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE,
                areValuesEnclosedAndNonEmpty("[CS2103T [Software Engineering]"));
    }

    @Test
    public void areValuesEnclosedAndNonEmpty_validValue1_returnsSuccess() {
        assertEquals(ParserUtil.AreValuesEnclosedAndNonEmptyResult.SUCCESS,
                areValuesEnclosedAndNonEmpty("[CS2103T] [Software Engineering]"));
    }

    @Test
    public void areValuesEnclosedAndNonEmpty_invalidValueWithMismatchBrackets2_returnsFalse() {
        assertEquals(ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE,
                areValuesEnclosedAndNonEmpty("[CS2103T] [Software Engineering [potato tomato]"));
    }

    @Test
    public void areValuesEnclosedAndNonEmpty_validValue2_returnsTrue() {
        assertEquals(ParserUtil.AreValuesEnclosedAndNonEmptyResult.SUCCESS,
                areValuesEnclosedAndNonEmpty("[CS2103T] [Software Engineering] [potato tomato]"));
    }

    // This shouldn't happen though, due to initial trim in SeplendidParser.
    @Test
    public void areValuesEnclosedAndNonEmpty_precedingWhiteSpaceValue_returnsTrue() {
        assertEquals(ParserUtil.AreValuesEnclosedAndNonEmptyResult.SUCCESS,
                areValuesEnclosedAndNonEmpty("      [CS2103T] [Software Engineering]"));
    }

    // This shouldn't happen though, due to initial trim in SeplendidParser.
    @Test
    public void areValuesEnclosedAndNonEmpty_trailingWhiteSpaceValue_returnsTrue() {
        assertEquals(ParserUtil.AreValuesEnclosedAndNonEmptyResult.SUCCESS,
                areValuesEnclosedAndNonEmpty("[CS2103T] [Software Engineering]       "));
    }
}
