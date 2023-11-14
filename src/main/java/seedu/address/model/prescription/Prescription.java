package seedu.address.model.prescription;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Prescription in the prescription list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Prescription {

    // Predicates
    public static final IsCompletedPredicate COMPLETED_PREDICATE = new IsCompletedPredicate();
    public static final IsTodayPredicate TODAY_PREDICATE = new IsTodayPredicate();
    public static final IsValidDatesPredicate DATES_PREDICATE = new IsValidDatesPredicate();
    public static final IsLowInStockPredicate STOCK_PREDICATE = new IsLowInStockPredicate();
    public static final IsAboutToExpirePredicate EXPIRE_PREDICATE = new IsAboutToExpirePredicate();
    private static final String DEFAULT_CONSUMPTION_COUNT = "0";


    // Data fields
    private final Name name;
    private final Optional<Dosage> dosage;
    private final Optional<Frequency> frequency;
    private final Date startDate;
    private final Optional<Date> endDate;
    private final Optional<Date> expiryDate;
    private final Optional<Stock> totalStock;
    private final ConsumptionCount consumptionCount;
    private Boolean isCompleted;
    private final Optional<Note> note;
    private final Set<Name> conflictingDrugs = new HashSet<>();

    /**
     * Constructor for prescription without consumption count and isCompleted.
     */
    public Prescription(Name name, Dosage dosage, Frequency frequency, Date startDate,
                        Date endDate, Date expiryDate, Stock totalStock, Note note, Set<Name> conflictingDrugs) {
        this(name, dosage, frequency, startDate, endDate, expiryDate,
            totalStock, new ConsumptionCount(DEFAULT_CONSUMPTION_COUNT), false, note, conflictingDrugs);
    }

    /**
     * Constructor for prescription.
     */
    public Prescription(Name name, Dosage dosage, Frequency frequency, Date startDate,
                        Date endDate, Date expiryDate, Stock totalStock, ConsumptionCount consumptionCount,
                        Boolean isCompleted, Note note, Set<Name> conflictingDrugs) {
        requireAllNonNull(name);
        this.name = name;
        this.dosage = Optional.ofNullable(dosage);
        this.frequency = Optional.ofNullable(frequency);
        this.startDate = startDate;
        this.endDate = Optional.ofNullable(endDate);
        this.expiryDate = Optional.ofNullable(expiryDate);
        this.totalStock = Optional.ofNullable(totalStock);
        this.consumptionCount = consumptionCount;
        this.isCompleted = isCompleted;
        this.note = Optional.ofNullable(note);
        this.conflictingDrugs.addAll(conflictingDrugs);
    }

    public Name getName() {
        return name;
    }

    public Optional<Dosage> getDosage() {
        return dosage;
    }

    public Optional<Frequency> getFrequency() {
        return frequency;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Optional<Date> getEndDate() {
        return endDate;
    }

    public Optional<Date> getExpiryDate() {
        return expiryDate;
    }

    public Optional<Stock> getTotalStock() {
        return totalStock;
    }
    public ConsumptionCount getConsumptionCount() {
        return consumptionCount;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public Optional<Note> getNote() {
        return note;
    }

    public void addConflictingDrug(Name drug) {
        conflictingDrugs.add(drug);
    }

    public Set<Name> getConflictingDrugs() {
        return conflictingDrugs;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Returns true if both prescriptions have the same name.
     * This defines a weaker notion of equality between two prescriptions.
     */
    public boolean isSamePrescription(Prescription otherPrescription) {
        if (otherPrescription == this) {
            return true;
        }

        return otherPrescription != null
                && otherPrescription.getName().equals(getName())
                && otherPrescription.getStartDate().equals(getStartDate());
    }

    /**
     * Returns true if the prescription is past the end date.
     */
    public boolean isEnded() {
        if (endDate.isPresent()) {
            LocalDate currentDate = LocalDate.now();
            LocalDate prescriptionEndDate = endDate.get().getDate();
            return currentDate.isAfter(prescriptionEndDate);
        }
        return false;
    }

    public void resetConsumptionCount() {
        consumptionCount.resetConsumptionCount();
    }

    /**
     * Returns true if both prescriptions have the same identity and data fields.
     * This defines a stronger notion of equality between two prescriptions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Prescription)) {
            return false;
        }

        Prescription otherPrescription = (Prescription) other;
        return name.equals(otherPrescription.name)
                && dosage.equals(otherPrescription.dosage)
                && frequency.equals(otherPrescription.frequency)
                && startDate.equals(otherPrescription.startDate)
                && endDate.equals(otherPrescription.endDate)
                && expiryDate.equals(otherPrescription.expiryDate)
                && totalStock.equals(otherPrescription.totalStock)
                && consumptionCount.equals(otherPrescription.consumptionCount)
                && isCompleted.equals(otherPrescription.isCompleted)
                && note.equals(otherPrescription.note);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, dosage, frequency, startDate,
                endDate, expiryDate, totalStock, consumptionCount, isCompleted, note);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("dosage", dosage)
                .add("frequency", frequency)
                .add("startDate", startDate)
                .add("endDate", endDate)
                .add("expiryDate", expiryDate)
                .add("totalStock", totalStock)
                .add("consumptionCount", consumptionCount)
                .add("isCompleted", isCompleted)
                .add("note", note)
                .toString();
    }

    /**
     * Returns true if this Prescription has a conflict with the drug to be added.
     */
    public boolean hasDrugClash(Prescription toAdd) {
        if (conflictingDrugs.contains(toAdd.getDrug())) {
            return true;
        }
        return false;
    }

    public Name getDrug() {
        return new Name(name.toString());
    }
}
