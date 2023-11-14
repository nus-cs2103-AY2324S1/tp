package seedu.address.testutil;

import seedu.address.model.MappingCatalogue;
import seedu.address.model.mapping.Mapping;

/**
 * A utility class to help with building MappingCatalogue objects.
 * Example usage: <br>
 * {@code MappingCatalogue lcc = new MappingCatalogueBuilder().withMapping(Mapping).build();}
 */
public class MappingCatalogueBuilder {

    private MappingCatalogue mappingCatalogue;

    public MappingCatalogueBuilder() {
        mappingCatalogue = new MappingCatalogue();
    }

    public MappingCatalogueBuilder(MappingCatalogue lcc) {
        mappingCatalogue = lcc;
    }

    /**
     * Adds a new {@code Mapping} to the {@code MappingCatalogue} that we are building.
     */
    public MappingCatalogueBuilder withMapping(Mapping mapping) {
        mappingCatalogue.addMapping(mapping);
        return this;
    }

    public MappingCatalogue build() {
        return mappingCatalogue;
    }
}
