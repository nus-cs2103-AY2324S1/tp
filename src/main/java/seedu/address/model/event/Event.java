package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.skill.Skill;
import seedu.address.model.volunteer.Name;
import seedu.address.model.volunteer.Volunteer;

/**
 * Represents an Event in the Event list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event implements Comparable<Event> {
    // Identity fields
    private final EventName eventName;

    // Data fields
    private final Set<Role> roles;
    private final DateTime startDate;
    private final DateTime endDate;
    private final Location location;
    private final Description description;
    private final Set<Material> materials;
    private final Budget budget;
    private final Set<Name> assignedVolunteers;
    private final MaxVolunteerSize maxVolunteerSize;

    /**
     * Every field must be present and not null.
     */
    public Event(EventName eventName, Set<Role> roles, DateTime startDate, DateTime endDate, Location location,
                 Description description, Set<Material> materials, Budget budget, Set<Name> assignedVolunteers,
                 MaxVolunteerSize maxVolunteerSize) {
        requireAllNonNull(eventName, roles, startDate, endDate, location, description, materials, budget,
                assignedVolunteers, maxVolunteerSize);
        this.eventName = eventName;
        this.roles = roles;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.description = description;
        this.materials = materials;
        this.budget = budget;
        this.assignedVolunteers = assignedVolunteers;
        this.maxVolunteerSize = maxVolunteerSize;
    }

    public EventName getEventName() {
        return eventName;
    }
    /**
     * Returns an immutable Role set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }
    public DateTime getStartDate() {
        return startDate;
    }
    public DateTime getEndDate() {
        return endDate;
    }

    public Location getLocation() {
        return location;
    }
    public Description getDescription() {
        return description;
    }
    public MaxVolunteerSize getMaxVolunteerSize() {
        return maxVolunteerSize;
    }
    /**
     * Returns an immutable Material set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Material> getMaterials() {
        return Collections.unmodifiableSet(materials);
    }

    /**
     * Gets a {@code Material} in the current {@code Event} instance that has a specified material name. If there are
     * no such materials, null is returned.
     * @param name The material name to search for.
     * @return The {@code Material} object if present; null otherwise.
     */
    public Material getMaterialByName(String name) {
        List<Material> materialList = materials.stream()
                .filter(material -> material.material.equals(name))
                .collect(Collectors.toList());

        if (materialList.size() >= 1) {
            return materialList.get(0);
        } else {
            return null;
        }
    }

    public Budget getBudget() {
        return budget;
    }
    /**
     * Returns an immutable Volunteer set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Name> getAssignedVolunteers() {
        return Collections.unmodifiableSet(assignedVolunteers);
    }
    /**
     * Adds a volunteer to the {@code assignedVolunteers}, and count them into the quantity of roles needed for the
     * event if their skills match the event role.
     * @param volunteer The volunteer to be added.
     * @return The event after the addition of the new volunteer.
     */
    public Event addVolunteer(Volunteer volunteer) {
        Set<Name> newVolunteers = new HashSet<>(assignedVolunteers);
        newVolunteers.add(volunteer.getName());
        Set<Role> newRoles = new HashSet<>(roles);

        // check if volunteer's skills match the roles
        for (Skill skill : volunteer.getSkills()) {
            for (Role role : roles) {
                if (role.roleName.equals(skill.skillName)) {
                    Role newRole = role.addRoleManpower();
                    newRoles.remove(role);
                    newRoles.add(newRole);
                }
            }
        }

        return new Event(eventName, newRoles, startDate, endDate,
                location, description, materials, budget, newVolunteers, maxVolunteerSize);
    }
    /**
     * Checks if a volunteer is already in {@code assignedVolunteers}.
     * @param volunteer The volunteer to check.
     */
    public boolean hasVolunteer(Volunteer volunteer) {
        return assignedVolunteers.contains(volunteer.getName());
    }
    /**
     * Removes a volunteer from the {@code assignedVolunteers}.
     * @param volunteer The volunteer to be removed.
     * @return The event after the removal of the volunteer.
     */
    public Event removeVolunteer(Volunteer volunteer) {
        Set<Name> newVolunteers = new HashSet<>(assignedVolunteers);
        newVolunteers.remove(volunteer.getName());
        Set<Role> newRoles = new HashSet<>(roles);

        // check if volunteer's skills match the roles
        for (Skill skill : volunteer.getSkills()) {
            for (Role role : roles) {
                if (role.roleName.equals(skill.skillName)) {
                    Role newRole = role.decreaseRoleManpower();
                    newRoles.remove(role);
                    newRoles.add(newRole);
                }
            }
        }

        return new Event(eventName, newRoles, startDate, endDate,
                location, description, materials, budget, newVolunteers, maxVolunteerSize);
    }
    /**
     * Returns a set of volunteers from the {@code assignedVolunteers}.
     */
    public Set<Name> getVolunteerNames() {
        return assignedVolunteers;
    }
    /**
     * Returns true if both events have the same name.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getEventName().equals(getEventName());
    }

    /**
     * Compares two Event objects. This Event is lesser than the specified Event if start date is before the other.
     * If start dates are the same, this event is lesser than the specified Event if end date is before the other.
     *
     * @param otherEvent the object to be compared.
     * @return Integer where -1 represents lesser than, 0 represents equal, 1 represents greater than.
     */
    @Override
    public int compareTo(Event otherEvent) {
        if (startDate.dateAndTime.isBefore(otherEvent.startDate.dateAndTime)) {
            return -1;
        } else if (startDate.dateAndTime.equals(otherEvent.startDate.dateAndTime)) {
            if (endDate.dateAndTime.isBefore(otherEvent.endDate.dateAndTime)) {
                return -1;
            } else if (endDate.dateAndTime.isAfter(otherEvent.endDate.dateAndTime)) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return eventName.equals(otherEvent.eventName)
                && roles.equals(otherEvent.roles)
                && startDate.equals(otherEvent.startDate)
                && endDate.equals(otherEvent.endDate)
                && location.equals(otherEvent.location)
                && description.equals(otherEvent.description)
                && materials.equals(otherEvent.materials)
                && budget.equals(otherEvent.budget)
                && assignedVolunteers.equals(otherEvent.assignedVolunteers)
                && maxVolunteerSize.equals(otherEvent.maxVolunteerSize);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(eventName, roles, startDate, endDate, location, description, materials, budget,
                assignedVolunteers, maxVolunteerSize);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", eventName)
                .add("roles", roles)
                .add("startDate", startDate)
                .add("endDate", endDate)
                .add("location", location)
                .add("description", description)
                .add("materials", materials)
                .add("budget", budget)
                .add("assigned volunteers", assignedVolunteers)
                .toString();
    }

}
