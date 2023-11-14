package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.MappingCatalogue;
import seedu.address.model.ReadOnlyMappingCatalogue;
import seedu.address.model.mapping.Mapping;

/**
 * An Immutable MappingCatalogue that is serializable to JSON format.
 */
@JsonRootName(value = "mappingcatalogue")
class JsonSerializableMappingCatalogue {

    public static final String MESSAGE_DUPLICATE_MAPPING = "Mapping catalogue contains duplicate mapping(s).";

    private final List<JsonAdaptedMapping> mappings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMappingCatalogue} with the given mappings.
     */
    @JsonCreator
    public JsonSerializableMappingCatalogue(@JsonProperty("mappings") List<JsonAdaptedMapping>
                                                    mappingList) {
        this.mappings.addAll(mappingList);
    }

    /**
     * Converts a given {@code ReadOnlyMappingCatalogue} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMappingCatalogue}.
     */
    public JsonSerializableMappingCatalogue(ReadOnlyMappingCatalogue source) {
        mappings.addAll(source.getMappingList().stream().map(
                JsonAdaptedMapping::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code MappingCatalogue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MappingCatalogue toModelType() throws IllegalValueException {
        MappingCatalogue mappingCatalogue = new MappingCatalogue();
        for (JsonAdaptedMapping jsonAdaptedMapping : mappings) {
            Mapping mapping = jsonAdaptedMapping.toModelType();
            if (mappingCatalogue.hasMapping(mapping)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MAPPING);
            }
            mappingCatalogue.addMapping(mapping);
        }
        return mappingCatalogue;
    }

}
