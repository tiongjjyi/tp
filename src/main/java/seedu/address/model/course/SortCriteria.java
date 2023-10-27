package seedu.address.model.course;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Enumeration representing the criteria that can be used to sort a StudentList.
 */
public class SortCriteria {
    public static final String MESSAGE_CONSTRAINTS_ENUMS = "Sort criteria should be name/tag";

    private final Field field;
    public enum Field {
        NAME,
        TAG
    }

    public SortCriteria(Field field) {
        requireNonNull(field);
        checkArgument(isValidSortCriteria(field.toString()), MESSAGE_CONSTRAINTS_ENUMS);
        this.field = field;
    }

    /**
     * Returns true if a given string is a valid sort criteria.
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

    public Field getField() {
        return this.field;
    }
}
