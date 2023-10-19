package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * Dummy class with fields used for testing serialization/deserialization.
 *
 * Fields have different visibilities to test Jackson object mapper behavior.
 */
public class SerializableClass {
    private String name = "This is a dummy class.";
    protected List<LocalDateTime> localDateTimeList = new ArrayList<>();
    public HashMap<Integer, String> integerToStringMap = new HashMap<>();

    public SerializableClass() {
        this.localDateTimeList.add(LocalDateTime.MIN);
        this.localDateTimeList.add(LocalDateTime.MAX);
        this.localDateTimeList.add(LocalDateTime.of(1, 1, 1, 1, 1));

        this.integerToStringMap.put(1, "One");
        this.integerToStringMap.put(2, "Two");
        this.integerToStringMap.put(3, "Three");
    }

    public String getName() {
        return this.name;
    }

    public List<LocalDateTime> getList() {
        return this.localDateTimeList;
    }
}
