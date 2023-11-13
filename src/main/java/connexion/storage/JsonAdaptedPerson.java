package connexion.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import connexion.commons.exceptions.IllegalValueException;
import connexion.model.person.Company;
import connexion.model.person.Email;
import connexion.model.person.Job;
import connexion.model.person.LastModifiedDateTime;
import connexion.model.person.Mark;
import connexion.model.person.Name;
import connexion.model.person.Note;
import connexion.model.person.Person;
import connexion.model.person.Phone;
import connexion.model.person.Schedule;
import connexion.model.person.ScheduleName;
import connexion.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String company;
    private final String job;
    private final String mark;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String lastModifiedDateTime;
    private final String schedule;
    private final String scheduleName;
    private final String note;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("company") String company,
            @JsonProperty("job") String job, @JsonProperty("tags") List<JsonAdaptedTag> tags,
            @JsonProperty("mark") String mark, @JsonProperty("schedule") String schedule,
            @JsonProperty("scheduleName") String scheduleName,
            @JsonProperty("last_modified") String lastModifiedDateTime, @JsonProperty("note") String note) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.company = company;
        this.job = job;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.scheduleName = scheduleName;
        this.schedule = schedule;
        this.mark = mark;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.note = note;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().getValue();
        phone = source.getPhone().getValue();
        email = source.getEmail().getValue();
        company = source.getCompany().getValue();
        job = source.getJob().getValue();
        mark = source.getMarkStatus().toString();
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        schedule = source.getSchedule().map(Objects::toString).orElse("");
        scheduleName = source.getScheduleName().map(Objects::toString).orElse("");
        lastModifiedDateTime = source.getLastModifiedDateTime().toString();
        note = source.getNote().getValue();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

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

        if (company == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName()));
        }
        if (!Company.isValidCompany(company)) {
            throw new IllegalValueException(Company.MESSAGE_CONSTRAINTS);
        }
        final Company modelCompany = new Company(company);

        if (job == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Job.class.getSimpleName()));
        }
        if (!Job.isValidJob(job)) {
            throw new IllegalValueException(Job.MESSAGE_CONSTRAINTS);
        }
        final Job modelJob = new Job(job);

        if (mark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Mark.class.getSimpleName()));
        }

        if (!Mark.isValidMark(mark)) {
            throw new IllegalValueException(Mark.MESSAGE_CONSTRAINTS);
        }

        final Mark markStatus = Mark.fromString(mark);

        if (lastModifiedDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LastModifiedDateTime.class.getSimpleName()));
        }
        if (!LastModifiedDateTime.isValidLastModifiedDateTime(lastModifiedDateTime)) {
            throw new IllegalValueException(LastModifiedDateTime.MESSAGE_CONSTRAINTS);
        }

        if (schedule == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Schedule.class.getSimpleName()));
        }

        // Checks the string if it is a valid schedule time. If string is empty, do not throw error
        if (!Schedule.isValidScheduleTime(schedule) && !schedule.isEmpty()) {
            throw new IllegalValueException(Schedule.MESSAGE_CONSTRAINTS);
        }

        final Optional<Schedule> modelSchedule = Optional.ofNullable(schedule)
                .filter(sch -> !sch.isEmpty())
                .map(Schedule::new);

        if (scheduleName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ScheduleName.class.getSimpleName()));
        }

        // Checks the string if it is a valid schedule name. If string is empty, do not throw error
        if (!ScheduleName.isValidScheduleName(scheduleName) && !scheduleName.isEmpty()) {
            throw new IllegalValueException(ScheduleName.MESSAGE_CONSTRAINTS);
        }

        final Optional<ScheduleName> modelScheduleName = Optional.ofNullable(scheduleName)
                .filter(sch -> !sch.isEmpty())
                .map(ScheduleName::new);

        final LastModifiedDateTime lastModified =
                LastModifiedDateTime.fromString(lastModifiedDateTime);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Note.class.getSimpleName()));
        }

        if (!Note.isValidNote(note)) {
            throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
        } else if (!Note.hasValidLength(note)) {
            throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS_CHARACTER_LIMIT);
        }
        final Note modelNote = new Note(note);

        Person newPerson = new Person(
                modelName, modelPhone, modelEmail, modelCompany, modelJob, markStatus,
                modelTags, modelSchedule, modelScheduleName, lastModified, modelNote);
        return newPerson;
    }

}
