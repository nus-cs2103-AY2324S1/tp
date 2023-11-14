package connectify.model;

/**
 * Represents an Entity in Connectify. An entity is either a Company or a Person.
 */
public abstract class Entity {
    /**
     * Gets the name of the entity.
     *
     * @return the name of the entity.
     */
    public abstract Name getName();
}
