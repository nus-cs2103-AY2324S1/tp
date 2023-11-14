package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.messages.ConstraintMessage;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourseAttribute;
import seedu.address.model.localcourse.LocalDescription;
import seedu.address.model.localcourse.LocalName;
import seedu.address.model.localcourse.LocalUnit;
import seedu.address.model.mapping.MappingAttribute;
import seedu.address.model.mapping.MappingMiscInformation;
import seedu.address.model.note.Content;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerCourseAttribute;
import seedu.address.model.partnercourse.PartnerDescription;
import seedu.address.model.partnercourse.PartnerName;
import seedu.address.model.partnercourse.PartnerUnit;
import seedu.address.model.tag.Tag;
import seedu.address.model.university.UniversityAttribute;
import seedu.address.model.university.UniversityName;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Represents the result for areValuesEnclosedAndNonEmpty method.
     */
    public enum AreValuesEnclosedAndNonEmptyResult {
        SUCCESS, FAILURE, EMPTY
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }


    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(ConstraintMessage.TAG_NAME.toString());
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String localCode} into an {@code LocalCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code localCode} is invalid.
     */
    public static LocalCode parseLocalCode(String localCode) throws ParseException {
        requireNonNull(localCode);
        String trimmedLocalCode = localCode.trim();
        if (!LocalCode.isValidLocalCode(trimmedLocalCode)) {
            throw new ParseException(ConstraintMessage.LOCALCOURSE_CODE.toString());
        }
        return new LocalCode(localCode);
    }

    /**
     * Parses a {@code String localName} into an {@code LocalName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code localName} is invalid.
     */
    public static LocalName parseLocalName(String localName) throws ParseException {
        requireNonNull(localName);
        String trimmedLocalName = localName.trim();
        if (!LocalName.isValidLocalName(trimmedLocalName)) {
            throw new ParseException(ConstraintMessage.LOCALCOURSE_NAME.toString());
        }
        return new LocalName(localName);
    }

    /**
     * Parses a {@code String localUnit} into an {@code LocalUnit}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code localName} is invalid.
     */
    public static LocalUnit parseLocalUnit(String localUnit) throws ParseException {
        requireNonNull(localUnit);
        String trimmedLocalUnit = localUnit.trim();
        if (!LocalUnit.isValidLocalUnit(trimmedLocalUnit)) {
            throw new ParseException(ConstraintMessage.LOCALCOURSE_UNIT.toString());
        }
        return new LocalUnit(localUnit);
    }

    /**
     * Parses a {@code String localDescription} into an {@code LocalDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code localDescription} is invalid.
     */
    public static LocalDescription parseLocalDescription(String localDescription) throws ParseException {
        requireNonNull(localDescription);
        String trimmedLocalDescription = localDescription.trim();
        if (!LocalDescription.isValidLocalDescription(trimmedLocalDescription)) {
            throw new ParseException(ConstraintMessage.LOCALCOURSE_DESCRIPTION.toString());
        }
        return new LocalDescription(localDescription);
    }

    /**
     * Parses a {@code String attribute} for localCourse search.
     *
     * @throws ParseException if the given {@code attribute} is invalid.
     */
    public static LocalCourseAttribute parseLocalCourseAttributeForSearch(String attribute) throws ParseException {
        if (!LocalCourseAttribute.isValidAttributeForSearch(attribute)) {
            throw new ParseException(ConstraintMessage.LOCALCOURSE_ATTRIBUTE_SEARCH.toString());
        }
        return LocalCourseAttribute.fromString(attribute);
    }

    /**
     * Parses a {@code String attribute} for localCourse sort.
     *
     * @throws ParseException if the given {@code attribute} is invalid.
     */
    public static LocalCourseAttribute parseLocalCourseAttributeForSort(String attribute) throws ParseException {
        if (!LocalCourseAttribute.isValidAttributeForSort(attribute)) {
            throw new ParseException(ConstraintMessage.LOCALCOURSE_ATTRIBUTE_SORT.toString());
        }
        return LocalCourseAttribute.fromString(attribute);
    }

    /**
     * Parses a {@code String attribute} for localCourse update.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code attribute} is invalid.
     */
    public static LocalCourseAttribute parseLocalCourseAttributeForUpdate(String attribute) throws ParseException {
        if (!LocalCourseAttribute.isValidAttributeForUpdate(attribute)) {
            throw new ParseException(ConstraintMessage.LOCALCOURSE_ATTRIBUTE_UPDATE.toString());
        }
        return LocalCourseAttribute.fromString(attribute);
    }

    /**
     * Parses a {@code String partnerCode} into an {@code PartnerCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code partnerCode} is invalid.
     */
    public static PartnerCode parsePartnerCode(String partnerCode) throws ParseException {
        requireNonNull(partnerCode);
        String trimmedPartnerCode = partnerCode.trim();
        if (!PartnerCode.isValidPartnerCode(trimmedPartnerCode)) {
            throw new ParseException(ConstraintMessage.PARTNERCOURSE_CODE.toString());
        }
        return new PartnerCode(partnerCode);
    }

    /**
     * Parses a {@code String partnerName} into an {@code PartnerName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code partnerName} is invalid.
     */
    public static PartnerName parsePartnerName(String partnerName) throws ParseException {
        requireNonNull(partnerName);
        String trimmedPartnerName = partnerName.trim();
        if (!PartnerName.isValidPartnerName(trimmedPartnerName)) {
            throw new ParseException(ConstraintMessage.PARTNERCOURSE_NAME.toString());
        }
        return new PartnerName(partnerName);
    }

    /**
     * Parses a {@code String partnerUnit} into an {@code PartnerUnit}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code partnerUnit} is invalid.
     */
    public static PartnerUnit parsePartnerUnit(String partnerUnit) throws ParseException {
        requireNonNull(partnerUnit);
        String trimmedPartnerUnit = partnerUnit.trim();
        if (!PartnerUnit.isValidPartnerUnit(trimmedPartnerUnit)) {
            throw new ParseException(ConstraintMessage.PARTNERCOURSE_UNIT.toString());
        }
        return new PartnerUnit(partnerUnit);
    }

    /**
     * Parses a {@code String partnerDescription} into an {@code PartnerDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code partnerDescription} is invalid.
     */
    public static PartnerDescription parsePartnerDescription(String partnerDescription) throws ParseException {
        requireNonNull(partnerDescription);
        String trimmedPartnerDescription = partnerDescription.trim();
        if (!PartnerDescription.isValidPartnerDescription(trimmedPartnerDescription)) {
            throw new ParseException(ConstraintMessage.PARTNERCOURSE_DESCRIPTION.toString());
        }
        return new PartnerDescription(partnerDescription);
    }

    /**
     * Parses a {@code String attribute} for partnerCourse search.
     *
     * @throws ParseException if the given {@code attribute} is invalid.
     */
    public static PartnerCourseAttribute parsePartnerCourseAttributeForSearch(String attribute) throws ParseException {
        if (!PartnerCourseAttribute.isValidAttributeForSearch(attribute)) {
            throw new ParseException(ConstraintMessage.PARTNERCOURSE_ATTRIBUTE_SEARCH.toString());
        }
        return PartnerCourseAttribute.fromString(attribute);
    }

    /**
     * Parses a {@code String attribute} for partnerCourse sort.
     *
     * @throws ParseException if the given {@code attribute} is invalid.
     */
    public static PartnerCourseAttribute parsePartnerCourseAttributeForSort(String attribute) throws ParseException {
        if (!PartnerCourseAttribute.isValidAttributeForSort(attribute)) {
            throw new ParseException(ConstraintMessage.PARTNERCOURSE_ATTRIBUTE_SORT.toString());
        }
        return PartnerCourseAttribute.fromString(attribute);
    }

    /**
     * Parses a {@code String attribute} for partnerCourse update.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code attribute} is invalid.
     */
    public static PartnerCourseAttribute parsePartnerCourseAttributeForUpdate(String attribute) throws ParseException {
        if (!PartnerCourseAttribute.isValidAttributeForUpdate(attribute)) {
            throw new ParseException(ConstraintMessage.PARTNERCOURSE_ATTRIBUTE_UPDATE.toString());
        }
        return PartnerCourseAttribute.fromString(attribute);
    }

    // university
    /**
     * Parses a {@code String universityName}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static UniversityName parseUniversityName(String universityName) throws ParseException {
        requireNonNull(universityName);
        String trimmedUniversityName = universityName.trim();
        if (!UniversityName.isValidUniversityName(trimmedUniversityName)) {
            throw new ParseException(ConstraintMessage.UNIVERSITY_NAME.toString());
        }
        return new UniversityName(universityName);
    }

    /**
     * Parses a {@code String attribute} for university search.
     *
     * @throws ParseException if the given {@code attribute} is invalid.
     */
    public static UniversityAttribute parseUniversityAttributeForSearch(String attribute) throws ParseException {
        if (!UniversityAttribute.isValidAttributeForSearch(attribute)) {
            throw new ParseException(ConstraintMessage.UNIVERSITY_ATTRIBUTE_SEARCH.toString());
        }
        return UniversityAttribute.fromString(attribute);
    }

    /**
     * Parses a {@code String attribute} for university sort.
     *
     * @throws ParseException if the given {@code attribute} is invalid.
     */
    public static UniversityAttribute parseUniversityAttributeForSort(String attribute) throws ParseException {
        if (!UniversityAttribute.isValidAttributeForSort(attribute)) {
            throw new ParseException(ConstraintMessage.UNIVERSITY_ATTRIBUTE_SORT.toString());
        }
        return UniversityAttribute.fromString(attribute);
    }

    /**
     * Parses a {@code String content}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Content parseContent(String content) throws ParseException {
        requireNonNull(content);
        String trimmedContent = content.trim();
        if (!Content.isValidContent(trimmedContent)) {
            throw new ParseException(ConstraintMessage.NOTE_CONTENT.toString());
        }
        return new Content(trimmedContent);
    }

    /**
     * Parses a {@code String tags}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String parseNoteTags(String tags) throws ParseException {
        requireNonNull(tags);
        String trimmedTags = tags.trim();
        return trimmedTags;
    }

    /**
     * Parse a {@code String mappingMiscInformation}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static MappingMiscInformation parseMappingMiscInformation(String mappingMiscInformation)
            throws ParseException {
        requireNonNull(mappingMiscInformation);
        String trimmedMappingMiscInformation = mappingMiscInformation.trim();
        if (!MappingMiscInformation.isValidMappingMiscInformation(trimmedMappingMiscInformation)) {
            throw new ParseException(MappingMiscInformation.MESSAGE_CONSTRAINTS);
        }
        return new MappingMiscInformation(trimmedMappingMiscInformation);
    }

    /**
     * Parses a {@code String attribute} for mapping search.
     *
     * @throws ParseException if the given {@code attribute} is invalid.
     */
    public static MappingAttribute parseMappingAttributeForSearch(String attribute) throws ParseException {
        if (!MappingAttribute.isValidAttributeForSearch(attribute)) {
            throw new ParseException(ConstraintMessage.MAPPING_ATTRIBUTE_SEARCH.toString());
        }
        return MappingAttribute.fromString(attribute);
    }

    /**
     * Parses a {@code String attribute} for mapping sort.
     *
     * @throws ParseException if the given {@code attribute} is invalid.
     */
    public static MappingAttribute parseMappingAttributeForSort(String attribute) throws ParseException {
        if (!MappingAttribute.isValidAttributeForSearch(attribute)) {
            throw new ParseException(ConstraintMessage.MAPPING_ATTRIBUTE_SORT.toString());
        }
        return MappingAttribute.fromString(attribute);
    }


    // SEPlendid ParserUtil starts here

    /**
     * Returns SUCCESS if all arguments are enclosed in square brackets, and are non-empty.
     * The input string must be non-empty. Returns FAILURE if not valid, and EMPTY if argument
     * within square bracket is not present.
     *
     * @param args Arguments in the format of {@code [args1] [args2] ...}.
     * @return AreValuesEnclosedAndNonEmptyResult based on the validity of the input.
     */
    public static AreValuesEnclosedAndNonEmptyResult areValuesEnclosedAndNonEmpty(String args) {
        if (args.isEmpty()) {
            return AreValuesEnclosedAndNonEmptyResult.FAILURE;
        }
        // The number of unclosed open square brackets, used to validate input.
        int bracketCount = 0;
        StringBuilder currValue = new StringBuilder();

        for (int i = 0; i < args.length(); i++) {
            Character currChar = args.charAt(i);
            if (currChar.equals('[')) {
                currValue.setLength(0);
                bracketCount++;
            } else if (currChar.equals(']') && currValue.toString().trim().isEmpty()) {
                return AreValuesEnclosedAndNonEmptyResult.EMPTY;
            } else if (currChar.equals(']')) {
                bracketCount--;
            } else {
                currValue.append(currChar);
            }

            if (bracketCount < 0 || bracketCount > 1) {
                return AreValuesEnclosedAndNonEmptyResult.FAILURE;
            }
        }

        return bracketCount == 0
                ? AreValuesEnclosedAndNonEmptyResult.SUCCESS
                : AreValuesEnclosedAndNonEmptyResult.FAILURE;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code SeplendidArgumentMap}.
     */
    public static boolean areArgumentsPresent(SeplendidArgumentMap argumentMap, SeplendidParameter... parameters) {
        return Stream.of(parameters).allMatch(parameter -> argumentMap.getValue(parameter).isPresent());
    }
}
