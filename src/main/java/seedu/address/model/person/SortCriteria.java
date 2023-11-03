package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the criteria that can be used to sort a StudentList.
 */
public class SortCriteria {
    public static final String MESSAGE_CONSTRAINTS_ENUMS = "Sort criteria should be name/tag";
    private final Field field;

    /**
     * Enumeration representing the criteria that can be used to sort a StudentList.
     */
    public enum Field {
        NAME,
        TAG
    }

    /**
     * Constructs a SortCriteria object with the specified field.
     *
     * @param field The sort criteria field (NAME or TAG).
     */
    public SortCriteria(Field field) {
        requireNonNull(field);
        checkArgument(isValidSortCriteria(field.toString()), MESSAGE_CONSTRAINTS_ENUMS);
        this.field = field;
    }

    /**
     * Returns true if a given string is a valid sort criteria.
     *
     * @param test The string to test.
     * @return True if the string is a valid sort criteria, false otherwise.
     */
    public static boolean isValidSortCriteria(String test) {
        return test.toUpperCase().equals(Field.TAG.toString()) || test.equals(Field.NAME.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Field)) {
            return false;
        }

        Field otherField = (Field) other;
        return field.toString().equals(otherField.toString());
    }

    @Override
    public int hashCode() {
        return field.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + field.toString() + ']';
    }

    /**
     * Gets the field of this SortCriteria object.
     *
     * @return The sort criteria field (NAME or TAG).
     */
    public Field getField() {
        return this.field;
    }
}
