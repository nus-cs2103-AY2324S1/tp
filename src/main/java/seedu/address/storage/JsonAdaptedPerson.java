package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attachment.Attachment;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gpa;
import seedu.address.model.person.InterviewScore;
import seedu.address.model.person.IsBookmarked;
import seedu.address.model.person.IsHidden;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PreviousGrade;
import seedu.address.model.person.StudentNumber;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String studentNo;
    private final String name;
    private final String phone;
    private final String email;
    private final Double gpa;
    private final String previousGrade;
    private final Double interviewScore; // this is uppercase Double because it can be null
    private final String comment;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedAttachment> attachments = new ArrayList<>();
    private final Boolean isHidden;
    private final Boolean isBookmarked;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator

    public JsonAdaptedPerson(
            @JsonProperty("studentNo") String studentNo,
            @JsonProperty("name") String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("gpa") Double gpa,
            @JsonProperty("previousGrade") String previousGrade,
            @JsonProperty("interviewScore") Double interviewScore,
            @JsonProperty("comment") String comment,
            @JsonProperty("tags") List<JsonAdaptedTag> tags,
            @JsonProperty("attachments") List<JsonAdaptedAttachment> attachments,
            @JsonProperty("isHidden") boolean isHidden,
            @JsonProperty("isBookmarked") boolean isBookmarked) {
        this.studentNo = studentNo;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gpa = gpa;
        this.previousGrade = previousGrade;
        this.interviewScore = interviewScore;
        this.comment = comment;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.isHidden = isHidden;
        if (attachments != null) {
            this.attachments.addAll(attachments);
        }
        this.isBookmarked = isBookmarked;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        studentNo = source.getStudentNumber().value;
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        gpa = source.getGpa().value;
        previousGrade = source.getPreviousGrade().toString();
        interviewScore = source.getInterviewScore().map(score -> score.value).orElse(null);
        comment = source.getComment().map(comment -> comment.comment).orElse(null);
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        isHidden = source.getIsHidden().value;
        attachments.addAll(source.getAttachments().stream()
                .map(JsonAdaptedAttachment::new)
                .collect(Collectors.toList()));
        isBookmarked = source.getIsBookmarked().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's
     * {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        final List<Attachment> personAttachments = new ArrayList<>();
        for (JsonAdaptedAttachment attachment : attachments) {
            personAttachments.add(attachment.toModelType());
        }

        if (studentNo == null) {
            throw new IllegalValueException(String.format(
                            MISSING_FIELD_MESSAGE_FORMAT,
                            StudentNumber.class.getSimpleName()));
        }
        if (!StudentNumber.isValidStudentNumber(studentNo)) {
            throw new IllegalValueException(StudentNumber.MESSAGE_CONSTRAINTS);
        }
        final StudentNumber modelStudentNo = new StudentNumber(studentNo);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (gpa == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gpa.class.getSimpleName()));
        }
        if (!Gpa.isValidGpa(gpa)) {
            throw new IllegalValueException(Gpa.MESSAGE_CONSTRAINTS);
        }
        final Gpa modelGpa = new Gpa(gpa);

        if (previousGrade == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, PreviousGrade.class.getSimpleName()));
        }
        if (!PreviousGrade.isValidGrade(previousGrade)) {
            throw new IllegalValueException(PreviousGrade.MESSAGE_CONSTRAINTS);
        }
        final PreviousGrade modelPreviousGrade = new PreviousGrade(previousGrade);

        if (interviewScore != null && !InterviewScore.isValidInterviewScore(interviewScore)) {
            throw new IllegalValueException(InterviewScore.MESSAGE_CONSTRAINTS);
        }
        final Optional<InterviewScore> modelInterviewScore = interviewScore != null
                ? Optional.of(new InterviewScore(interviewScore))
                : Optional.empty();

        if (comment != null && !Comment.isValidComment(comment)) {
            throw new IllegalValueException(Comment.MESSAGE_CONSTRAINTS);
        }
        final Optional<Comment> modelComment = comment != null
                ? Optional.of(new Comment(comment))
                : Optional.empty();

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (isHidden == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, IsHidden.class.getSimpleName()));
        }
        final IsHidden modelIsHidden = new IsHidden(isHidden);

        if (isBookmarked == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, IsBookmarked.class.getSimpleName()));
        }
        final IsBookmarked modelBookmark = new IsBookmarked(isBookmarked);

        return new Person(
                modelStudentNo,
                modelName,
                modelPhone,
                modelEmail,
                modelGpa,
                modelPreviousGrade,
                modelInterviewScore,
                modelComment,
                modelTags,
                personAttachments,
                modelIsHidden,
                modelBookmark);
    }

}
