package seedu.address.logic.parser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public enum CliAnimalSyntax {
    NAME(new Prefix("n/")),
    PET_ID(new Prefix("i/")),
    SEX(new Prefix("g/")),
    SPECIES(new Prefix("s/")),
    BREED(new Prefix("b/")),
    DATE_OF_BIRTH(new Prefix("db/")),
    DATE_OF_ADMISSION(new Prefix("da/"));

    private final Prefix prefix;

    /**
     * Constructs a CliAnimalSyntax Enum with the given Prefix.
     *
     * @param prefix the given Prefix.
     */
    CliAnimalSyntax(Prefix prefix) {
        this.prefix = prefix;
    }

    public Prefix getPrefix() {
        return prefix;
    }

    /**
     * Returns the corresponding CliAnimalSyntax from the given Prefix.
     *
     * @param prefix the given Prefix.
     * @return the corresponding CliAnimalSyntax from the given Prefix.
     *
     * @throws IllegalArgumentException if the given Prefix is not a valid CliAnimalSyntax Enum.
     */
    public static CliAnimalSyntax getCliSyntaxFromPrefix(Prefix prefix) {
        for (CliAnimalSyntax syntax : CliAnimalSyntax.values()) {
            if (syntax.getPrefix().equals(prefix)) {
                return syntax;
            }
        }
        throw new IllegalArgumentException("Invalid Prefix provided.");
    }

    /**
     * Returns the list of mandatory Prefixes. Defined by the developers.
     *
     * @return the list of mandatory Prefix.
     */
    public static List<Prefix> getAnimalAttributePrefixes() {
        return Stream.of(NAME, PET_ID, SEX, SPECIES, BREED, DATE_OF_BIRTH, DATE_OF_ADMISSION)
            .map(CliAnimalSyntax::getPrefix)
            .collect(Collectors.toList());
    }

    public String getUsage() {
        return String.format("%s%s", this.getPrefix(), this);
    }

    /**
     * Returns the string representation of this Enum. E.g. NAME will return [NAME].
     *
     * @return the string representation of this Enum.
     */
    @Override
    public String toString() {
        return String.format("%s", this.name());
    }
}

