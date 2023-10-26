package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code PendingQuestion} exists.
 */
public class AllPendingQuestionPredicate implements Predicate<Student> {
    //private final List<String> keywords;

    public AllPendingQuestionPredicate() {
        //this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return student.getPendingQuestion().value.length() > 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        //AllPendingQuestionPredicate otherAllPendingQuestionPredicate = (AllPendingQuestionPredicate) other;
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
