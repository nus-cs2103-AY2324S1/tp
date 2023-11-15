package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.prescription.ConsumptionCount;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.Stock;



/**
 * Jackson-friendly version of {@link Prescription}.
 */
class JsonAdaptedPrescription {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Prescription's %s field is missing!";

    private final String name;
    private String dosage;
    private String frequency;
    private final String startDate;
    private String endDate;
    private String expiryDate;
    private String totalStock;
    private final String consumptionCount;
    private Boolean isCompleted;
    private String note;
    private final List<JsonAdaptedDrug> drugs = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPrescription} with the given prescription details.
     */
    @JsonCreator
    public JsonAdaptedPrescription(@JsonProperty("name") String name,
                                   @JsonProperty("dosage") String dosage,
                                   @JsonProperty("frequency") String frequency,
                                   @JsonProperty("startDate") String startDate,
                                   @JsonProperty("endDate") String endDate,
                                   @JsonProperty("expiryDate") String expiryDate,
                                   @JsonProperty("totalStock") String totalStock,
                                   @JsonProperty("consumptionCount") String consumptionCount,
                                   @JsonProperty("isCompleted") Boolean isCompleted,
                                   @JsonProperty("note") String note) {
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expiryDate = expiryDate;
        this.totalStock = totalStock;
        this.consumptionCount = consumptionCount;
        this.isCompleted = isCompleted;
        this.note = note;
        if (drugs != null) {
            this.drugs.addAll(drugs);
        }
    }

    /**
     * Converts a given {@code Prescription} into this class for Jackson use.
     */
    public JsonAdaptedPrescription(Prescription source) {
        name = source.getName().toString();
        if (source.getDosage().isPresent()) {
            dosage = source.getDosage().get().toString();
        }
        if (source.getFrequency().isPresent()) {
            frequency = source.getFrequency().get().toString();
        }
        startDate = source.getStartDate().toString();
        if (source.getEndDate().isPresent()) {
            endDate = source.getEndDate().get().toString();
        }
        if (source.getExpiryDate().isPresent()) {
            expiryDate = source.getExpiryDate().get().toString();
        }
        if (source.getTotalStock().isPresent()) {
            totalStock = source.getTotalStock().get().toString();
        }
        consumptionCount = source.getConsumptionCount().toString();
        isCompleted = source.getIsCompleted();
        if (source.getNote().isPresent()) {
            note = source.getNote().get().toString();
        }
        drugs.addAll(source.getConflictingDrugs()
                .stream()
                .map(JsonAdaptedDrug::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted prescription object into the model's {@code Prescription} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted prescription.
     */
    public Prescription toModelType() throws IllegalValueException {
        final List<Name> conflictingDrugs = new ArrayList<>();
        for (JsonAdaptedDrug drug : drugs) {
            conflictingDrugs.add(drug.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        Dosage modelDosage;
        if (dosage == null) {
            modelDosage = null;
        } else if (!Dosage.isValidDosage(dosage)) {
            throw new IllegalValueException(Dosage.MESSAGE_CONSTRAINTS);
        } else {
            modelDosage = new Dosage(dosage);
        }

        Frequency modelFrequency;
        if (frequency == null) {
            modelFrequency = null;
        } else if (!Frequency.isValidFrequency(frequency)) {
            throw new IllegalValueException(Frequency.MESSAGE_CONSTRAINTS);
        } else {
            modelFrequency = new Frequency(frequency);
        }

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDateFormat(startDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelStartDate = new Date(startDate);

        Date modelEndDate;
        if (endDate == null) {
            modelEndDate = null;
        } else if (!Date.isValidDateFormat(endDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        } else {
            modelEndDate = new Date(endDate);
        }

        Date modelExpiryDate;
        if (expiryDate == null) {
            modelExpiryDate = null;
        } else if (!Date.isValidDateFormat(expiryDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        } else {
            modelExpiryDate = new Date(expiryDate);
        }

        Stock modelTotalStock;
        if (totalStock == null) {
            modelTotalStock = null;
        } else if (!Stock.isValidStockFormat(totalStock)) {
            throw new IllegalValueException(Stock.MESSAGE_CONSTRAINTS);
        } else {
            modelTotalStock = new Stock(totalStock);
        }

        if (consumptionCount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Stock.class.getSimpleName()));
        }
        final ConsumptionCount modelConsumptionCount = new ConsumptionCount(consumptionCount);

        if (isCompleted == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Stock.class.getSimpleName()));
        }
        final Boolean modelIsCompleted = isCompleted;

        Note modelNote;
        if (note == null) {
            modelNote = null;
        } else if (!Note.isValidNote(note)) {
            throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
        } else {
            modelNote = new Note(note);
        }

        final Set<Name> modelDrugs = new HashSet<>(conflictingDrugs);
        return new Prescription(modelName, modelDosage, modelFrequency, modelStartDate,
                modelEndDate, modelExpiryDate, modelTotalStock, modelConsumptionCount, modelIsCompleted,
                modelNote, modelDrugs);
    }

}
