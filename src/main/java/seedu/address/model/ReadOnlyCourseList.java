package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.course.Course;

/**
 * Unmodifiable view of a student list.
 */
public interface ReadOnlyCourseList {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Course> getCourseList();

}
