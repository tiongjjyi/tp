package seedu.address.model.info.course;

/**
 * Represents a course in the university.
 */
public class Course {
    private String courseName;
    private String courseCode;
    private String courseDescription;
    private String courseDepartment;
    private String courseFaculty;

    /**
     * Constructs a Course object with the specified details.
     *
     * @param courseName        The name of the course.
     * @param courseCode        The unique code assigned to the course.
     * @param courseDescription A brief description of the course.
     * @param courseDepartment  The department to which the course belongs.
     * @param courseFaculty     The faculty offering the course.
     */
    public Course(String courseName, String courseCode, String courseDescription, String courseDepartment,
            String courseFaculty) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseDescription = courseDescription;
        this.courseDepartment = courseDepartment;
        this.courseFaculty = courseFaculty;
    }

    /**
     * Gets the name of the course.
     *
     * @return The name of the course.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Gets the unique code assigned to the course.
     *
     * @return The course code.
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Returns a string representation of the course in the format: "CourseCode :
     * CourseName".
     *
     * @return A string representation of the course.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCourseCode())
                .append(" : ")
                .append(getCourseName());
        return builder.toString();
    }
}
