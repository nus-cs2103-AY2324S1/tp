package seedu.address.logic.parser;

/**
 * A parameter acting as a placeholder for an argument following a command.
 * E.g. [localcode] in localcourse add [localcode] [localname]
 * E.g. [localcode] in localcourse delete [localcode]
 */
public class SeplendidParameter {

    private final String parameterName;

    public SeplendidParameter(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }

    @Override
    public String toString() {
        return getParameterName();
    }

    @Override
    public int hashCode() {
        return parameterName == null ? 0 : parameterName.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SeplendidParameter)) {
            return false;
        }

        SeplendidParameter otherSeplendidParameter = (SeplendidParameter) other;
        return parameterName.equals(otherSeplendidParameter.parameterName);
    }
}

