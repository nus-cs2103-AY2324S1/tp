package seedu.address.storage;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.fields.ApplicationOutcome;
import seedu.address.model.fields.Cycle;
import seedu.address.model.fields.Description;
import seedu.address.model.fields.Location;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.Pay;
import seedu.address.model.fields.Role;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.tag.Tag;

/**
 * Json version of InternshipRole
 */
public class JsonAdaptedInternshipRole {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "InternshipRole's %s field is missing!";

    private final String name;
    private final String role;
    private final String cycle;
    private final String description;
    private final BigDecimal pay;
    private final Outcome outcome;
    private final String location;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedInternshipRole} with the given internship role details.
     */
    @JsonCreator
    public JsonAdaptedInternshipRole(@JsonProperty("name") String name, @JsonProperty("role") String role,
                                 @JsonProperty("cycle") String cycle, @JsonProperty("description") String desc,
                                 @JsonProperty("pay") BigDecimal pay, @JsonProperty("outcome") Outcome outcome,
                                     @JsonProperty("location") String location,
                                 @JsonProperty("tags") List<JsonAdaptedTag> tags) {

        this.name = name;
        this.role = role;
        this.cycle = cycle;
        this.description = desc;
        this.pay = pay;
        this.outcome = outcome;
        this.location = location;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code InternshipRole} into this class for Jackson use.
     */
    public JsonAdaptedInternshipRole(InternshipRole source) {
        this.name = source.getName().toString();
        this.role = source.getRole().toString();
        this.cycle = source.getCycle().toString();
        this.description = source.getDescription().toString();
        this.pay = source.getPay().getPay().orElse(null);
        this.outcome = source.getApplicationOutcome().getApplicationOutcome();
        this.location = source.getLocation().toString();
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted internship role object into the model's {@code InternshipRole} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted internship role.
     */
    public InternshipRole toModelType() throws IllegalValueException {
        final List<Tag> internshipRoleTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            internshipRoleTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidText(name)) {
            throw new IllegalValueException("Name Not Valid " + Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);


        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()));
        }
        if (!Role.isValidText(role)) {
            throw new IllegalValueException("Role Not Valid " + Role.MESSAGE_CONSTRAINTS);
        }
        final Role modelRole = new Role(role);


        if (cycle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Cycle.class.getSimpleName()));
        }
        if (!Cycle.isValidText(cycle)) {
            throw new IllegalValueException("Role Not Valid " + Cycle.MESSAGE_CONSTRAINTS);
        }
        final Cycle modelCycle = new Cycle(cycle);


        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        final Description modelDescription = new Description(description);


        if (outcome == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Outcome.class.getSimpleName()));
        }
        final ApplicationOutcome modelOutcome = new ApplicationOutcome(outcome);


        if (pay != null && !Pay.isValidPay(pay)) {
            throw new IllegalValueException("Pay Not Valid " + Pay.MESSAGE_CONSTRAINTS);
        }
        final Pay modelPay = new Pay(pay);


        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        final Location modelLocation = new Location(location);


        final Set<Tag> modelTags = new HashSet<>(internshipRoleTags);

        return new InternshipRole(modelName, modelRole, modelCycle,
                modelDescription, modelPay, modelOutcome, modelLocation, modelTags);
    }
}
