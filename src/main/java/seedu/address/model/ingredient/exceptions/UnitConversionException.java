package seedu.address.model.ingredient.exceptions;

import seedu.address.model.ingredient.Unit;

/**
 * Signals that the operation is unable to convert the current unit to the specified unit.
 */
public class UnitConversionException extends RuntimeException {
    private static String message = "Unit %1$s cannot be converted to %2$s!";

    public UnitConversionException(Unit fromUnit, Unit toUnit) {
        super(String.format(message, fromUnit.toString(), toUnit.toString()));
    }
}
