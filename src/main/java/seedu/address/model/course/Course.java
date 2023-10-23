package seedu.address.model.course;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.person.Student;
import seedu.address.model.person.UniqueStudentList;

/**
 * Represents a Student's course in the student list.
 * Guarantees: immutable; is valid as declared in {@link #isValidCourse(String)}
 */
public class Course {


    public static final String MESSAGE_CONSTRAINTS =
            "Course code should contain a two- or three-letter prefix, "
                    + "four digits course code and an optional one-letter suffix";
    public static final String VALIDATION_REGEX = "\\w{2,3}\\d{4}\\w?";
    public final String value;
    private final UniqueStudentList students;

    /**
     * Constructs a {@code Course}.
     *
     * @param course A valid course code.
     */
    public Course(String course) {
        requireNonNull(course);
        checkArgument(isValidCourse(course), MESSAGE_CONSTRAINTS);
        value = course;
        students = new UniqueStudentList();
    }

    /**
     * Returns the course code.
     */
    public String getCourse() {
        return value;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidCourse(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Adds a student to the course being taught by the TA.
     * @param student A student to be added to the course.
     */
    public void addItem(Student student) {
        this.students.add(student);
    }


    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameCourse(Course otherCourse) {
        if (otherCourse == this) {
            return true;
        }

        return otherCourse != null
                && otherCourse.getCourse().equals(getCourse());
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
        if (!(other instanceof Course)) {
            return false;
        }

        Course otherCourse = (Course) other;
        return value.equals(otherCourse.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
