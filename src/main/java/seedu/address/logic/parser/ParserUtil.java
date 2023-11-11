package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.util.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.CreateTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Score;
import seedu.address.model.person.ScoreList;
import seedu.address.model.person.Status;
import seedu.address.model.person.StatusTypes;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";


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
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String statusType} into a {@code StatusType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code statusType} is invalid.
     */
    public static StatusTypes parseStatusType(String statusType) throws ParseException {
        requireNonNull(statusType);
        String trimmedStatus = statusType.trim().toLowerCase();
        if (!StatusTypes.isValidStatusType(trimmedStatus)) {
            throw new ParseException("PLACEHOLDER: PARSE_EXCEPTION STATUS TYPE");
        }
        switch (trimmedStatus) {
        case "interviewed":
            return StatusTypes.INTERVIEWED;
        case "offered":
            return StatusTypes.OFFERED;
        case "rejected":
            return StatusTypes.REJECTED;
        case "preliminary":
            return StatusTypes.PRELIMINARY;
        default:
            throw new ParseException("e");
        }
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tagName, String tagCategory) throws ParseException {
        requireNonNull(tagName);
        requireNonNull(tagCategory);
        UniqueTagList uniqueTagList = new UniqueTagList();
        String trimmedTag = tagName.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return uniqueTagList.getTag(trimmedTag, tagCategory);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        if (tags.size() == 0) {
            return tagSet;
        }
        String listTags = tags.toString();
        String cleanedList = listTags.replaceAll("[\\[\\]]", "");
        String[] tagNameCategoryPairs = cleanedList.split(",");

        for (String tagNameCategory : tagNameCategoryPairs) {
            tagNameCategory = tagNameCategory.trim();
            if (tagNameCategory.split("\\s+").length > 1) {
                String[] nameCategory = tagNameCategory.split("\\s+");
                // category specified
                String tagName = nameCategory[1];
                String tagCategory = nameCategory[0];
                tagSet.add(parseTag(tagName, tagCategory));
            } else {
                // category not specified
                tagSet.add(parseTag(tagNameCategory, ""));
            }
        }
        return tagSet;
    }

    /**
     * Parses a collection of tag strings into an array of tag categories.
     *
     * @param tags A collection of tag strings to be parsed.
     * @return An array of tag categories extracted from the provided collection of tag strings.
     */
    public static String[] parseTagCategories(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        String listTags = tags.toString();
        String cleanedList = listTags.replaceAll("[\\[\\]]", "");
        String[] tagNameCategoryPairs = cleanedList.split(",");
        for (String tagNameCategory : tagNameCategoryPairs) {
            tagNameCategory = tagNameCategory.trim();
            if (tagNameCategory.split("\\s+").length > 1) {
                if (!Tag.isValidTagName(tagNameCategory.split("\\s+")[1])) {
                    throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
                }
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateTagCommand.MESSAGE_USAGE));
            }
        }
        return tagNameCategoryPairs;
    }

    /**
     * Parses a list of keywords into an array of strings.
     *
     * @param keywordsList A list of keywords, where each element may contain multiple words.
     * @return An array of strings where each element represents an individual keyword.
     * @throws ParseException if any of the search parameters contain non-alphanumeric characters e.g. commas
     *
     *     The method first converts the list of keywords into a string representation,
     *     e.g., [Alex, Yeoh] (including square brackets). It then removes the square brackets
     *     from the string representation, resulting in a cleaned string, e.g., Alex, Yeoh (no square brackets).
     *     Finally, the cleaned string is split into an array of strings, where each word separated
     *     by a whitespace or comma is considered a single element.
     *
     *     Example:
     *     If keywordsList is ["John Doe"], the returned array will be ["John", "Doe"].
     */
    private static String[] parseSinglePrefixParams(Collection<String> keywordsList) {
        String list = keywordsList.toString();
        String cleanedList = list.replaceAll("[\\[\\]]", "");
        String[] singlePrefixParams = cleanedList.split("\\s+");
        return singlePrefixParams;
    }

    /**
     * Parses {@code Collection<String> status parameters} into a {@code List<String> of status}.
     */
    public static List<String> parseSinglePrefixStatus(Collection<String> statuses)
            throws ParseException {
        requireNonNull(statuses);
        String[] statusArr = parseSinglePrefixParams(statuses);
        final List<String> statusList = new ArrayList<>();
        for (String status : statusArr) {
            status = status.trim();
            if (!StatusTypes.isValidStatusType(status.toLowerCase())) {
                throw new ParseException(Status.MESSAGE_CONSTRAINTS);
            }
            checkArgument(StatusTypes.isValidStatusType(status.toLowerCase()), Status.MESSAGE_CONSTRAINTS);
            statusList.add(status);
        }
        return statusList;
    }

    /**
     * Parses {@code Collection<String> name parameters} into a {@code List<String> of names}.
     */
    public static List<String> parseSinglePrefixName(Collection<String> names)
            throws ParseException {
        requireNonNull(names);
        String[] nameArr = parseSinglePrefixParams(names);
        final List<String> nameList = new ArrayList<>();
        for (String name : nameArr) {
            name = name.trim();
            if (!Name.isValidName(name)) {
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            }
            checkArgument(Name.isValidName(name), Name.MESSAGE_CONSTRAINTS);
            nameList.add(name);
        }
        return nameList;
    }

    /**
     * Parses {@code Collection<String> tag parameters} into a {@code List<String> of tags}.
     */
    public static List<String> parseSinglePrefixTags(Collection<String> tags)
            throws ParseException {
        requireNonNull(tags);
        UniqueTagList uniqueTagList = new UniqueTagList();
        String[] tagArr = parseSinglePrefixParams(tags);
        final List<String> tagList = new ArrayList<>();
        List<String> nonExistingTags = new ArrayList<>();

        for (String tag : tagArr) {
            tag = tag.trim();

            // Check if the tag is valid
            if (!Tag.isValidTagName(tag)) {
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
            }

            // Check if the tag already exists in the uniqueTagList
            if (!uniqueTagList.containsTagName(tag)) {
                nonExistingTags.add(tag);
            } else {
                tagList.add(tag);
            }
        }

        if (!nonExistingTags.isEmpty()) {
            // Throw an exception with a message specifying all non-existing tags
            throw new ParseException(Tag.MESSAGE_TAG_DOES_NOT_EXIST + String.join(", ", nonExistingTags));
        }

        return tagList;
    }


    /**
     * Parses a {@code String score} into a {@code Score}.
     *
     * @param score String to be parsed
     * @return Score object
     * @throws ParseException if the given {@code score} is invalid.
     */
    public static Score parseScore(String score) throws ParseException {
        requireNonNull(score);
        String trimmedScore = score.trim();
        if (!StringUtil.isNonNegativeInteger(trimmedScore)) {
            throw new ParseException(Score.MESSAGE_CONSTRAINTS);
        }
        return new Score(Integer.parseInt(trimmedScore));
    }

    /**
     * Parses a Tag Score string input and turns it into a Pair with head as Tag and tail as Score.
     * @param tagScore String to be parsed
     * @return Pair with head as Tag and tail as Score
     * @throws ParseException if the given {@code tagScorePair} is invalid.
     */
    public static Pair<Tag, Score> parseTagScore(String tagScore) throws ParseException {
        requireNonNull(tagScore);
        String trimmedTagScorePair = tagScore.trim();
        String[] tagScorePairArr = trimmedTagScorePair.split(" ");
        if (tagScorePairArr.length != 2) {
            throw new ParseException("Invalid score, score must be non-negative integer.");
        }
        Tag tag = parseTag(tagScorePairArr[0], "");
        if (!ScoreList.isValidScoreTag(tag)) {
            throw new ParseException("Invalid score tag, tag must a tag of the category " +
                    "assessment and must exist on the applicant" + " (made using "
                    + "the create command.");
        }
        Score score = parseScore(tagScorePairArr[1]);
        return new Pair<>(tag, score);
    }
}
