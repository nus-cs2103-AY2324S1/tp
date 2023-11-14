package seedu.address.storage;

import static seedu.address.logic.Messages.MESSAGE_PERSON_DOES_NOT_EXIST;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.PlanDateTime;
import seedu.address.model.plan.PlanName;




/**
 * Jackson-friendly version of {@link Plan}.
 */
class JsonAdaptedPlan {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Plan's %s field is missing!";

    private final String planName;
    private final String dateTime;
    private final String friend;
    private final Boolean completionStatus;

    /**
     * Constructs a {@code JsonAdaptedPlan} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPlan(@JsonProperty("planName") String planName, @JsonProperty("dateTime") String dateTime,
                             @JsonProperty("friend") String friend,
                             @JsonProperty("completionStatus") Boolean completionStatus) {
        this.planName = planName;
        this.dateTime = dateTime;
        this.friend = friend;
        this.completionStatus = completionStatus;
    }

    /**
     * Converts a given {@code Plan} into this class for Jackson use.
     */
    public JsonAdaptedPlan(Plan source) {
        planName = source.getPlanName().toString();
        dateTime = source.getPlanDateTime().toStringRaw();
        friend = source.getPlanFriend().getName().fullName;
        completionStatus = source.getPlanComplete();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Plan} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Plan toModelType(AddressBook addressBook) throws IllegalValueException {
        if (friend == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(friend)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Person modelPlanFriend;
        try {
            modelPlanFriend = addressBook.getPersonByName(new Name(friend));
        } catch (PersonNotFoundException e) {
            throw new IllegalValueException(MESSAGE_PERSON_DOES_NOT_EXIST);
        }

        if (planName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PlanName.class.getSimpleName()));
        }
        if (!PlanName.isValidPlanName(planName)) {
            throw new IllegalValueException(PlanName.MESSAGE_CONSTRAINTS);
        }
        final PlanName modelPlanName = new PlanName(planName);
        PlanDateTime modelPlanDateTime;
        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PlanDateTime.class.getSimpleName()));
        }
        try {
            modelPlanDateTime = new PlanDateTime(dateTime);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(PlanDateTime.MESSAGE_CONSTRAINTS);
        }

        return new Plan(modelPlanName, modelPlanDateTime, modelPlanFriend, this.completionStatus);

    }

}
