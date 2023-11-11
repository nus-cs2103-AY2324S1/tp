package seedu.address.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Represents a map where each key is mapped to a set of values, and each value is mapped back to its keys.
 */
public class BiDirectionalMap<T extends ListEntry<T>, P extends ListEntry<P>> {
    private final Map<Name, HashSet<Name>> forwardMap = new HashMap<>();
    private final Map<Name, HashSet<Name>> reverseMap = new HashMap<>();

    /**
     * Adds a key-value pair and its reverse to the map
     */
    public void addMapping(T t, P p) {
        forwardMap.putIfAbsent(t.getName(), new HashSet<>());
        forwardMap.get(t.getName()).add(p.getName());
        reverseMap.putIfAbsent(p.getName(), new HashSet<>());
        reverseMap.get(p.getName()).add(t.getName());
    }

    /**
     * Removes a key-value pair and its reverse from the map
     */
    public void removeMapping(T t, P p) {
        forwardMap.get(t.getName()).remove(p.getName());
        reverseMap.get(p.getName()).remove(t.getName());
    }

    /**
     * Returns the value associated with the key
     */
    public Name[] get(T t) {
        if (!forwardMap.containsKey(t.getName())) {
            return new Name[0];
        }
        return forwardMap.get(t.getName()).toArray(new Name[0]);
    }

    /**
     * Returns the key associated with the value
     */
    public Name[] getReversed(P p) {
        if (!reverseMap.containsKey(p.getName())) {
            return new Name[0];
        }
        return reverseMap.get(p.getName()).toArray(new Name[0]);
    }

    /**
     * Delete a key and update its associated values from the map
     */
    public void remove(T t) {
        Name[] names = get(t);
        for (Name name : names) {
            reverseMap.get(name).remove(t.getName());
        }
        if (forwardMap.containsKey(t.getName())) {
            forwardMap.get(t.getName()).clear();
        }
    }

    /**
     * Delete a value and update its associated keys from the map
     */
    public void removeReverse(P p) {
        Name[] names = getReversed(p);
        for (Name name : names) {
            forwardMap.get(name).remove(p.getName());
        }
        if (reverseMap.containsKey(p.getName())) {
            reverseMap.get(p.getName()).clear();
        }
    }
    /**
     * When there is a name change to the key, this method should be called to update the map
     */
    public void update(T tOld, T tNew) {
        Name[] names = get(tOld);
        remove(tOld);
        for (Name name : names) {
            reverseMap.get(name).add(tNew.getName());
        }

        // update the forward map
        forwardMap.put(tNew.getName(), convertArrayToHashSet(names));
    }

    public <R> HashSet<R> convertArrayToHashSet(R[] list) {
        return new HashSet<>(Arrays.asList(list));
    }

    /**
     * When there is a name change to the value, this method should be called to update the map
     */
    public void updateReverse(P pOld, P pNew) {
        Name[] names = getReversed(pOld);
        removeReverse(pOld);
        for (Name name : names) {
            forwardMap.get(name).add(pNew.getName());
        }
    }

    /**
     * Saves a BiDirectionalMap to a json file
     */
    public void saveTo(Path p) {
        HashMap<String, HashSet<String>> names = new HashMap<>();
        for (Name name : forwardMap.keySet()) {
            HashSet<String> strings = new HashSet<>();
            for (Name name1 : forwardMap.get(name)) {
                strings.add(name1.toString());
            }
            names.put(name.toString(), strings);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(p.toFile(), names);
        } catch (IOException e) {
            Logger.getGlobal().warning("Failed to save BiDirectionalMap " + e.getMessage());
        }
    }

    /**
     * Reads a BiDirectionalMap from a file
     */
    public static <T extends ListEntry<T>,
            P extends ListEntry<P>> BiDirectionalMap<T, P> readFrom(Path p) throws ParseException {
        BiDirectionalMap<T, P> m = new BiDirectionalMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<HashMap<String, HashSet<String>>> typeRef;
        typeRef = new TypeReference<HashMap<String, HashSet<String>>>() {};
        HashMap<String, HashSet<String>> data;
        try {
            data = objectMapper.readValue(p.toFile(), typeRef);
        } catch (IOException e) {
            Logger.getGlobal().warning("Failed to read BiDirectionalMap " + e.getMessage());
            return new BiDirectionalMap<>();
        }
        for (String name : data.keySet()) {
            HashSet<Name> names = new HashSet<>();
            for (String name1 : data.get(name)) {
                names.add(new Name(name1));
            }
            m.forwardMap.put(new Name(name), names);
        }
        for (Name name : m.forwardMap.keySet()) {
            for (Name name1 : m.forwardMap.get(name)) {
                m.reverseMap.putIfAbsent(name1, new HashSet<>());
                m.reverseMap.get(name1).add(name);
            }
        }
        return m;
    }
}
