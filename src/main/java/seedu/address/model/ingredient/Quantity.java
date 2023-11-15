package seedu.address.model.ingredient;

// TODO Add JavaDocs
/**
 * Stub
 */
public class Quantity {
    private double value;
    private Unit unit;

    /**
     * Constructs a Quantity instance.
     * @param value The numerical value of the quantity.
     * @param unit The unit of the quantity.
     */
    public Quantity(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }
    /**
     * Adds a quantity to this quantity.
     * @param otherQuantity
     */
    protected Quantity add(Quantity otherQuantity) {
        return new Quantity(value + otherQuantity.convertToUnit(unit).value, unit);
    }

    /**
     * Subtracts a quantity from this quantity.
     * @param quantity
     */
    protected Quantity subtract(Quantity quantity) {
        return new Quantity(value - quantity.convertToUnit(unit).value, unit);
    }

    /**
     * Converts this quantity to a specified unit.
     * @param toUnit
     * @return The converted quantity.
     */
    protected Quantity convertToUnit(Unit toUnit) {
        return new Quantity(value * Unit.getConversionRatio(this.unit, toUnit), toUnit);
    }

    /**
     * Scales this quantity by a factor.
     * @param factor
     * @return The scaled quantity.
     */
    protected Quantity scale(double factor) {
        return new Quantity(value * factor, unit);
    }

    @Override
    public String toString() {
        return value + " " + unit.toString();
    }

    /**
     * Parses the string argument as a Quantity.
     * @param quantityString The string to be parsed.
     * @return The parsed Quantity object.
     */
    public static Quantity parseQuantity(String quantityString) {
        String[] splitStr = quantityString.split(" ");

        double val = Double.parseDouble(splitStr[0]);
        Unit unit = Unit.parseUnit(splitStr[1]);

        return new Quantity(val, unit);
    }

    // TODO Add JavaDocs
    /**
     * Stub
     * @param quantity Stub
     * @return Stub
     */
    public boolean isLessThan(Quantity quantity) {
        return value < quantity.convertToUnit(unit).value;
    }

    /**
     * Returns whether the ingredient's quantity is less than or equal to the specified quantity
     * @param quantity
     * @return
     */
    public boolean isLessThanOrEqualsTo(Quantity quantity) {
        return value <= quantity.convertToUnit(unit).value;
    }

    @Override
    public boolean equals(Object otherQuantity) {
        if (otherQuantity == this) {
            return true;
        }
        if (otherQuantity instanceof Quantity) {
            Quantity other = (Quantity) otherQuantity;
            if (this.value == other.value && this.unit == other.unit) {
                return true;
            }
        }
        return false;
    }
}
