package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class PendingQuestion {

    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param pendingQuestion A valid remark.
     */
    public PendingQuestion(String pendingQuestion) {
        requireNonNull(pendingQuestion);
        value = pendingQuestion;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Remark)) {
            return false;
        }

        PendingQuestion otherPq = (PendingQuestion) other;
        return value.equals(otherPq.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
