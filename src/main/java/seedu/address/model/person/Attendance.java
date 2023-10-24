package seedu.address.model.person;

/**
 * Represents a Student's attendance in the address book.
 * Guarantees: immutable; is always valid
 */
public class Attendance {
    private final boolean isPresent;

    /**
     * Constructs an {@code Attendance} object.
     *
     * @param isPresent Represents whether the student is present (true) or absent (false).
     */
    public Attendance(boolean isPresent) {
        this.isPresent = isPresent;
    }

    public boolean isPresent() {
        return isPresent;
    }

    @Override
    public String toString() {
        return isPresent ? "Present" : "Absent";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Attendance)) {
            return false;
        }

        Attendance otherAttendance = (Attendance) other;
        return isPresent == otherAttendance.isPresent;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(isPresent);
    }
}
