//package seedu.address.storage;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonValue;
//import seedu.address.commons.exceptions.IllegalValueException;
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.model.tag.UniqueTagList;
//import seedu.address.model.tag.Tag;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class JsonAdaptedUniqueTagList {
//
//    private final HashMap<String, List<JsonAdaptedTag>> categories;
//
//    @JsonCreator
//    public JsonAdaptedUniqueTagList(HashMap<String, List<JsonAdaptedTag>> categories) {
//        this.categories = categories;
//    }
//
//    public JsonAdaptedUniqueTagList(UniqueTagList source) {
//        this.categories = new HashMap<>();
//        // Convert the internal structure to JSON-friendly format
//        source.getCategories().forEach((category, tags) -> {
//            List<JsonAdaptedTag> jsonTags = tags.stream()
//                    .map(JsonAdaptedTag::new)
//                    .collect(Collectors.toList());
//            this.categories.put(category, jsonTags);
//        });
//    }
//
//    public UniqueTagList toModelType() {
//        UniqueTagList uniqueTagList = new UniqueTagList();
//        // Convert back from JSON format to internal structure
//        categories.forEach((category, jsonTags) -> {
//            List<Tag> tags = null;
//            for (JsonAdaptedTag jsonAdaptedTag : jsonTags) {
//                try {
//                    tags.add(jsonAdaptedTag.toModelType());
//                } catch (IllegalValueException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            tags.forEach(tag -> {
//                try {
//                    uniqueTagList.createTags(category, tag.tagName);
//                } catch (CommandException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        });
//        return uniqueTagList;
//    }
//}
