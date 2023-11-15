package seedu.address.model.person.timetable;

/**
 * Represents the half-hour blocks within a day.
 * Each block is represented by a start time and end time.
 */
public class HalfHourBlocks implements Comparable<HalfHourBlocks> {
    private final int startHalfHour;
    private final int endHalfHour;

    /**
     * Constructs a new HalfHourBlocks with specified start and end half-hour blocks.
     *
     * @param startHalfHour The starting half-hour block (inclusive).
     * @param endHalfHour The ending half-hour block (exclusive).
     */
    public HalfHourBlocks(int startHalfHour, int endHalfHour) {
        this.startHalfHour = startHalfHour;
        this.endHalfHour = endHalfHour;
    }

    /**
     * Checks if the current HalfHourBlocks overlaps with another HalfHourBlocks.
     *
     * @param other The other HalfHourBlocks to check against.
     * @return true if there's an overlap, false otherwise.
     */
    public boolean overlaps(HalfHourBlocks other) {
        return this.startHalfHour < other.endHalfHour && other.startHalfHour < this.endHalfHour;
    }

    /**
     * Returns a new HalfHourBlocks representing the overlap between the current and another HalfHourBlocks.
     *
     * @param other The other HalfHourBlocks to check against.
     * @return A new HalfHourBlocks representing the overlap.
     */
    public HalfHourBlocks getOverlap(HalfHourBlocks other) {
        if (!this.overlaps(other)) {
            return null;
        }
        int overlapStart = Math.max(this.startHalfHour, other.startHalfHour);
        int overlapEnd = Math.min(this.endHalfHour, other.endHalfHour);
        return new HalfHourBlocks(overlapStart, overlapEnd);
    }

    @Override
    public int compareTo(HalfHourBlocks other) {
        if (this.startHalfHour != other.startHalfHour) {
            return Integer.compare(this.startHalfHour, other.startHalfHour);
        } else {
            return Integer.compare(this.endHalfHour, other.endHalfHour);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        HalfHourBlocks that = (HalfHourBlocks) other;
        return this.startHalfHour == that.startHalfHour && this.endHalfHour == that.endHalfHour;
    }

    @Override
    public int hashCode() {
        return 31 * startHalfHour + endHalfHour;
    }

    /**
     * Returns a string representation of the HalfHourBlocks object.
     *
     * @return A string representation of the HalfHourBlocks object.
     */
    @Override
    public String toString() {
        String startTimeString = String.format("%04d", startHalfHour / 2 * 100 + (startHalfHour % 2) * 30);
        String endTimeString = String.format("%04d", endHalfHour / 2 * 100 + (endHalfHour % 2) * 30);
        return startTimeString + " " + endTimeString;
    }
}
