package seedu.address.model.ingredient;

import java.util.HashMap;
import java.util.Map;

import seedu.address.model.ingredient.exceptions.UnitConversionException;
import seedu.address.model.ingredient.exceptions.UnitFormatException;

// TODO Add JavaDocs
/**
 * Stub
 */
public enum Unit {
    GRAM,
    KILOGRAM,
    PIECE;

    public static final String MESSAGE_CONSTRAINTS = "Unit should be GRAM, KILOGRAM or PIECE";
    //May need to update this to 3-dimensional matrix for different ingredient conversions.
    private static final Map<Unit, Map<Unit, Double>> conversionMatrix = new HashMap<>();
    private static final Map<String, Unit> unitAliases = new HashMap<>();

    //Set the values of the conversion matrix.
    static {
        for (int i = 0; i < Unit.values().length; i++) {
            //Set the diagonal of the conversion matrix to 1.
            addConversionRatio(Unit.values()[i], Unit.values()[i], 1);
        }
        addConversionRatio(KILOGRAM, GRAM, 1000);
    }

    static {
        addUnitAliases(new String[]{"g", "gram", "GRAM"}, GRAM);
        addUnitAliases(new String[]{"kg", "kilogram", "KILOGRAM"}, KILOGRAM);
        addUnitAliases(new String[]{"pc", "pcs", "piece", "pieces", "PIECE", "PIECES"}, PIECE);
    }

    /**
     * Adds conversion ratio for a given pair of units, also adds the reciprocal for the reverse pair.
     * @param from The unit to convert from.
     * @param to The unit to convert to.
     * @param ratio The ratio to multiply the value by.
     */
    private static void addConversionRatio(Unit from, Unit to, double ratio) {
        conversionMatrix.computeIfAbsent(from, x -> new HashMap<>());
        conversionMatrix.get(from).put(to, ratio);

        //Assume commutative conversion for all units
        conversionMatrix.computeIfAbsent(to, x -> new HashMap<>());
        conversionMatrix.get(to).put(from, 1 / ratio);
    }

    private static void addUnitAliases(String[] aliases, Unit unit) {
        for (String alias : aliases) {
            unitAliases.put(alias, unit);
        }
    }

    /**
     * Get the conversion ratio between two units.
     * @param from The unit to convert from.
     * @param to The unit to convert to.
     * @return The conversion ratio.
     */
    protected static double getConversionRatio(Unit from, Unit to) {
        if (!conversionMatrix.get(from).containsKey(to)) {
            throw new UnitConversionException(from, to);
        }
        return conversionMatrix.get(from).get(to);
    }


    @Override
    public String toString() {
        return name();
    }

    /**
     * Parses the string argument as a Unit.
     * @param str The string argument to be parsed.
     * @return The parsed Unit.
     */
    public static Unit parseUnit(String str) {
        if (unitAliases.containsKey(str)) {
            return unitAliases.get(str);
        } else {
            throw new UnitFormatException(str);
        }
    }
}
