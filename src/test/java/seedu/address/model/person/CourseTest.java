package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CourseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Course(null));
    }

    @Test
    public void constructor_invalidCourse_throwsIllegalArgumentException() {
        String invalidCourse = "";
        assertThrows(IllegalArgumentException.class, () -> new Course(invalidCourse));
    }

    @Test
    public void isValidCourse() {
        // null Course number
        assertThrows(NullPointerException.class, () -> Course.isValidCourse(null));

        // invalid Course numbers
        assertFalse(Course.isValidCourse("")); // empty string
        assertFalse(Course.isValidCourse(" ")); // spaces only
        assertFalse(Course.isValidCourse("91")); // less than 3 numbers
        assertFalse(Course.isValidCourse("Course")); // non-numeric
        assertFalse(Course.isValidCourse("9011p041")); // alphabets within digits
        assertFalse(Course.isValidCourse("9312 1534")); // spaces within digits

        // valid Course numbers
        assertTrue(Course.isValidCourse("CS2103T"));
        assertTrue(Course.isValidCourse("CS2101"));
        assertTrue(Course.isValidCourse("MA2001"));
    }

    @Test
    public void equals() {
        Course course = new Course("CS2101");

        // same values -> returns true
        assertTrue(course.equals(new Course("CS2101")));

        // same object -> returns true
        assertTrue(course.equals(course));

        // null -> returns false
        assertFalse(course.equals(null));

        // different types -> returns false
        assertFalse(course.equals(5.0f));

        // different values -> returns false
        assertFalse(course.equals(new Course("CS2100")));
    }
}
