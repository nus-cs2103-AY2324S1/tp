package seedu.address.model.treatment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;


/**
 * Class which represents a Treatment offered by the dental clinic
 */
public class Treatment {

    public final TreatmentCost cost;
    public final TreatmentTime time;
    public final TreatmentName name;

    /**
     * Instantiates a treatment
     * @param name name of the treatment
     * @param cost cost of the treatment
     * @param time estimated duration of the treatment
     */
    public Treatment(TreatmentName name, TreatmentCost cost, TreatmentTime time) {
        requireAllNonNull(name, cost, time);
        this.cost = cost;
        this.time = time;
        this.name = name;
    }

    public TreatmentCost getCost() {
        return cost;
    }

    public TreatmentTime getTime() {
        return time;
    }

    public TreatmentName getName() {
        return name;
    }

    /**
     * Returns true if both treatments have the same name. This defines a weaker notion of equality
     * between two treatments.
     */
    public boolean isSameTreatment(Treatment otherTreatment) {
        if (otherTreatment == this) {
            return true;
        }

        return otherTreatment != null
            && otherTreatment.getName().equals(getName());
    }


    /**
     * Returns true if both treatments have the same identity and data fields. This defines a stronger
     * notion of equality between two treatments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Treatment)) {
            return false;
        }

        Treatment otherTreatment = (Treatment) other;
        return getTime().equals(otherTreatment.getTime())
            && getName().equals(otherTreatment.getName())
            && getCost().equals(otherTreatment.getCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCost(), getTime());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("name", getName())
            .add("cost", getCost())
            .add("time", getTime())
            .toString();
    }


}
