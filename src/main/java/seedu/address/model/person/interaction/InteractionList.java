package seedu.address.model.person.interaction;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Represents interactions as a list of {@code Interaction}.
 */
public class InteractionList {
    private final ArrayList<Interaction> interactions = new ArrayList<>();

    /**
     * Construct an empty {@code InteractionList}.
     */
    public InteractionList() {}

    /**
     * Construct an {@code InteractionList} with the given {@code Interaction}.
     *
     * @param interactions The list of interactions to feed into the {@code InteractionList}.
     */
    public InteractionList(ArrayList<Interaction> interactions) {
        this.interactions.addAll(interactions);
    }

    /**
     * Adds the given {@code Interaction} to the list.
     *
     * @param interaction The {@code Interaction} to add.
     */
    public void addInteraction(Interaction interaction) {
        this.interactions.add(interaction);
    }

    public void addInteractions(InteractionList interactions) {
        this.interactions.addAll(interactions.getInteractions());
    }

    /**
     * Removes the {@code Interaction} at the given index.
     *
     * @param index The index of the {@code Interaction} to remove.
     */
    public void removeInteraction(int index) {
        this.interactions.remove(index);
    }

    /**
     * Returns the {@code Interaction} at the given index.
     *
     * @param index The index of the {@code Interaction} to return.
     */
    public Interaction getInteraction(int index) {
        return this.interactions.get(index);
    }

    /**
     * Returns the ArrayList of {@code Interaction}.
     */
    public ArrayList<Interaction> getInteractions() {
        return this.interactions;
    }

    /**
     * Returns a filtered list of {@code Interaction} that matches the given predicate.
     */
    public InteractionList getFilteredInteraction(Predicate<Interaction> predicate) {
        return new InteractionList(this.interactions.stream()
            .filter(predicate)
            .collect(Collectors.toCollection(ArrayList::new)));
    }
}
