package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Student;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class RemarkContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public RemarkContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsIgnoreCase(student.getRemark().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkContainsKeywordsPredicate)) {
            return false;
        }

        RemarkContainsKeywordsPredicate otherRemarkContainsKeywordsPredicate = (RemarkContainsKeywordsPredicate) other;
        return keywords.equals(otherRemarkContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
