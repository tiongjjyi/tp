package seedu.address.model.course;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Student;
import seedu.address.model.person.UniqueStudentList;

/**
 * Represents a Course in the course list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Course {

    // Course identity field
    public final CourseName courseName;

    // Course data field
    private final UniqueStudentList students;
    private FilteredList<Student> filteredStudents;
    private final SortedList<Student> sortedStudents;

    /**
     * Every field must be present and not null.
     */
    public Course(CourseName courseName) {
        requireNonNull(courseName);
        this.courseName = courseName;
        this.students = new UniqueStudentList();
        this.filteredStudents = new FilteredList<>(this.students.asUnmodifiableObservableList());
        this.sortedStudents = new SortedList<>(this.students.asUnmodifiableObservableList());
    }

    /**
     * Returns the course name.
     */
    public CourseName getCourseName() {
        return courseName;
    }

    /**
     * Returns the list of students in the course.
     */
    public UniqueStudentList getStudentList() {
        return students;
    }

    public void clearStudentList() {
        this.students.clearAll();
    }

    /**
     * Returns the list of students in the course.
     */
    public int getCourseSize() {
        return this.students.size();
    }

    /**
     * Returns the number of students tagged with GOOD StudentRank.
     */
    public int getGoodTagCount() {
        return this.students.getGoodTagCount();
    }

    /**
     * Returns the number of students tagged with AVERAGE StudentRank.
     */
    public int getAverageTagCount() {
        return this.students.getAverageTagCount();
    }

    /**
     * Returns the number of students tagged with POOR StudentRank.
     */
    public int getPoorTagCount() {
        return this.students.getPoorTagCount();
    }

    /**
     * Returns the number of students with a non-empty pending question field.
     */
    public int getPendingQuestionCount() {
        return this.students.getPendingQuestionCount();
    }

    /**
     * Adds a student to the course being taught by the TA.
     *
     * @param student A student to be added to the course.
     */
    public boolean hasStudent(Student student) {
        return this.students.contains(student);
    }

    /**
     * Adds a student to the course being taught by the TA.
     *
     * @param student A student to be added to the course.
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     * Sets a student to the course being taught by the TA.
     *
     * @param target        The target student to be replaced.
     * @param editedStudent The student to be set.
     */
    public void setStudent(Student target, Student editedStudent) {
        this.students.setStudent(target, editedStudent);
    }

    /**
     * Removes a student from the course being taught by the TA.
     *
     * @param student A student to be removed from the course.
     */
    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedStudentList}
     */
    public ObservableList<Student> getFilteredStudentList() {
        return this.filteredStudents;
    }

    /**
     * Returns an unmodifiable view of the filtered list of {@code Student}
     * @param predicate The predicate filter to run on the student list
     */
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the sorted list of {@code Student}
     * @param comparator The comparator filter to run on the student list
     */
    public void updateSortedStudentList(Comparator<Student> comparator) {
        requireNonNull(comparator);
        sortedStudents.setComparator(comparator);
        this.filteredStudents = new FilteredList<>(sortedStudents);
    }

    /**
     * Sorts the students in the course by the specified sort criteria.
     *
     * @param sortCriteria The sort criteria for sorting the students.
     */
    public void sortStudentsBy(SortCriteria sortCriteria) {
        requireNonNull(sortCriteria);

        Comparator<Student> tagComparator = Comparator.comparing(student -> student.getTag().ranking);
        Comparator<Student> nameComparator = Comparator.comparing(student -> student.getName().toString(),
                String.CASE_INSENSITIVE_ORDER);

        if (sortCriteria.getField().toString().equals(SortCriteria.Field.TAG.toString())) {
            updateSortedStudentList(tagComparator);
        }
        if (sortCriteria.getField().toString().equals(SortCriteria.Field.NAME.toString())) {
            updateSortedStudentList(nameComparator);
        }
    }

    /**
     * Returns an unmodifiable view of the filtered list of {@code Student} in the original order
     */
    public void resetFilteredStudentList() {
        this.filteredStudents = new FilteredList<>(this.students.asUnmodifiableObservableList());
    }

    /**
     * Returns true if both courses are of the same name.
     * This defines a weaker notion of equality between two courses.
     */
    public boolean isSameCourse(Course otherCourse) {
        if (otherCourse == this) {
            return true;
        }

        return otherCourse != null
                && otherCourse.getCourseName().equals(getCourseName());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("course name", courseName)
                .toString();
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
        return courseName.equals(otherCourse.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName);
    }

}
