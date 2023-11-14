package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Stores mapping of parameters to their respective arguments.
 * Each key may be associated with multiple argument values.
 * Values for a given key are stored in a list, and the insertion ordering is maintained.
 * Keys are unique, but the list of argument values may contain duplicate argument values,
 * i.e. the same argument value can be inserted multiple times for the same prefix.
 */
public class SeplendidArgumentMap {

    /**
     * Parameters mapped to their respective arguments
     */
    private final Map<SeplendidParameter, String> parameterToArgMap = new HashMap<>();

    /**
     * Associates the specified argument value with {@code parameter} key in this map.
     * If the map previously contained a mapping for the key, the new value overrides the existing one.
     *
     * @param parameter SeplendidParameter key with which the specified argument value is to be associated.
     * @param argValue  Argument value to be associated with the specified SeplendidParameter key
     */
    public void put(SeplendidParameter parameter, String argValue) {
        parameterToArgMap.put(parameter, argValue);
    }

    /**
     * Returns the only value of {@code parameter} if any.
     */
    public Optional<String> getValue(SeplendidParameter parameter) {
        String argument = parameterToArgMap.get(parameter);
        return (argument == null || argument.isEmpty())
                ? Optional.empty()
                : Optional.of(argument);
    }

    public boolean contains(SeplendidParameter parameterLocalcode) {
        return parameterToArgMap.containsKey(parameterLocalcode);
    }

    public int getIndexOf(SeplendidParameter parameter) {
        List<SeplendidParameter> parameters = new ArrayList<>(parameterToArgMap.keySet());
        if (parameters.get(1) == CliSyntax.PARAMETER_LOCALCODE) {
            return 0;
        }
        return 1;
    }
}
