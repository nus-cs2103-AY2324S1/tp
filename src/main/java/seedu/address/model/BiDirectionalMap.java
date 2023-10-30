package seedu.address.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.util.Of;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class BiDirectionalMap<T extends ListEntry<T>,P extends ListEntry<P>> {
    private final Map<Name, HashSet<Name>> forwardMap = new HashMap<>();
    private final Map<Name, HashSet<Name>> reverseMap = new HashMap<>();
    public void addMapping(T t, P p) {
        forwardMap.putIfAbsent(t.getName(), new HashSet<>());
        forwardMap.get(t.getName()).add(p.getName());
        reverseMap.putIfAbsent(p.getName(), new HashSet<>());
        reverseMap.get(p.getName()).add(t.getName());
    }
    public Name[] get(T t) {
        return forwardMap.get(t.getName()).toArray(new Name[0]);
    }
    public Name[] getReversed(P p) {
        return reverseMap.get(p.getName()).toArray(new Name[0]);
    }
    public void remove(T t) {
        Name[] names = get(t);
        for (Name name : names) {
            reverseMap.get(name).remove(t.getName());
        }
        forwardMap.remove(t.getName());
    }
    public void removeReverse(P p) {
        Name[] names = getReversed(p);
        for (Name name : names) {
            forwardMap.get(name).remove(p.getName());
        }
        reverseMap.remove(p.getName());
    }
    public void update(T tOld, T tNew) {
        Name[] names = get(tOld);
        remove(tOld);
        for (Name name : names) {
            reverseMap.get(name).add(tNew.getName());
        }
    }
    public void updateReverse(P pOld, P pNew) {
        Name[] names = getReversed(pOld);
        removeReverse(pOld);
        for (Name name : names) {
            forwardMap.get(name).add(pNew.getName());
        }
    }
    public void saveTo(Path p) throws IOException {
        HashMap<String, HashSet<String>> names = new HashMap<>();
        for (Name name : forwardMap.keySet()) {
            HashSet<String> strings = new HashSet<>();
            for (Name name1 : forwardMap.get(name)) {
                strings.add(name1.toString());
            }
            names.put(name.toString(), strings);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(p.toFile(), objectMapper.writeValueAsString(names));
    }
    public static <T extends ListEntry<T>,P extends ListEntry<P>> BiDirectionalMap<T,P> readFrom(Path p) throws IOException, ParseException {
        BiDirectionalMap<T,P> m = new BiDirectionalMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<HashMap<String, HashSet<String>>> typeRef = new TypeReference<HashMap<String, HashSet<String>>>() {};
        HashMap<String, HashSet<String>> data = objectMapper.readValue(p.toFile(), typeRef);
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
