package seedu.application.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.model.ApplicationBook;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.job.Job;

/**
 * An Immutable ApplicationBook that is serializable to JSON format.
 */
@JsonRootName(value = "applicationbook")
class JsonSerializableApplicationBook {

    public static final String MESSAGE_DUPLICATE_JOB = "Jobs list contains duplicate job(s).";

    private final List<JsonAdaptedJob> jobs = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableApplicationBook} with the given jobs.
     */
    @JsonCreator
    public JsonSerializableApplicationBook(@JsonProperty("jobs") List<JsonAdaptedJob> jobs) {
        this.jobs.addAll(jobs);
    }

    /**
     * Converts a given {@code ReadOnlyApplicationBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableApplicationBook}.
     */
    public JsonSerializableApplicationBook(ReadOnlyApplicationBook source) {
        jobs.addAll(source.getJobList().stream().map(JsonAdaptedJob::new).collect(Collectors.toList()));
    }

    /**
     * Converts this application book into the model's {@code ApplicationBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ApplicationBook toModelType() throws IllegalValueException {
        ApplicationBook applicationBook = new ApplicationBook();
        for (JsonAdaptedJob jsonAdaptedJob : jobs) {
            Job job = jsonAdaptedJob.toModelType();
            if (applicationBook.hasJob(job)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_JOB);
            }
            applicationBook.addJob(job);
        }
        return applicationBook;
    }

}
