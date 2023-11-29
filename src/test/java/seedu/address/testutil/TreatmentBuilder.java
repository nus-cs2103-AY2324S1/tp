package seedu.address.testutil;


import seedu.address.model.treatment.Treatment;
import seedu.address.model.treatment.TreatmentCost;
import seedu.address.model.treatment.TreatmentName;
import seedu.address.model.treatment.TreatmentTime;

/**
 * A utility class to help with building Treatment objects.
 */
public class TreatmentBuilder {

    public static final String DEFAULT_NAME = "Wisdom Tooth";
    public static final String DEFAULT_COST = "1893";
    public static final String DEFAULT_TIME = "03:00";


    private TreatmentName treatmentName;
    private TreatmentCost treatmentCost;
    private TreatmentTime treatmentTime;

    /**
     * Creates a {@code TreatmentBuilder} with the default details.
     */
    public TreatmentBuilder() {
        treatmentName = new TreatmentName(DEFAULT_NAME);
        treatmentCost = new TreatmentCost(DEFAULT_COST);
        treatmentTime = new TreatmentTime(DEFAULT_TIME);
    }

    /**
     * Initializes the TreatmentBuilder  with the data of {@code treatmentToCopy}.
     */
    public TreatmentBuilder(Treatment treatmentToCopy) {
        treatmentName = treatmentToCopy.getName();
        treatmentCost = treatmentToCopy.getCost();
        treatmentTime = treatmentToCopy.getTime();
    }

    /**
     * Sets the {@code TreatmentName} of the {@code Treatment} that we are building.
     */
    public TreatmentBuilder withName(String name) {
        this.treatmentName = new TreatmentName(name);
        return this;
    }

    /**
     * Sets the {@code cost} of the {@code Treatment} that we are building.
     */
    public TreatmentBuilder withCost(String cost) {
        this.treatmentCost = new TreatmentCost(cost);
        return this;
    }


    /**
     * Sets the {@code time} of the {@code Treatment} that we are building.
     */
    public TreatmentBuilder withTime(String time) {
        this.treatmentTime = new TreatmentTime(time);
        return this;
    }

    public Treatment build() {
        return new Treatment(treatmentName, treatmentCost, treatmentTime);
    }


}
