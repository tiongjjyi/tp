package seedu.address.model.info.course;

import java.util.ArrayList;

/**
 * Represents a list of courses in the university.
 */
public class CourseList {
    private ArrayList<Course> courseList;

    /**
     * Constructs an empty CourseList.
     */
    public CourseList() {
        courseList = new ArrayList<>();
    }

    /**
     * Adds a course to the list.
     *
     * @param course The course to be added.
     */
    public void addCourse(Course course) {
        courseList.add(course);
    }

    /**
     * Removes a course from the list.
     *
     * @param course The course to be removed.
     */
    public void removeCourse(Course course) {
        courseList.remove(course);
    }

    /**
     * Retrieves the list of courses.
     *
     * @return An ArrayList containing the courses.
     */
    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    /**
     * Returns a string representation of the course list, with each course on a new
     * line.
     *
     * @return A string representation of the course list.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Course course : courseList) {
            builder.append(course.toString())
                    .append("\n");
        }
        return builder.toString();
    }
}
