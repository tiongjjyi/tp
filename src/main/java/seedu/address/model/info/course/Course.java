package seedu.address.model.info.course;

public class Course {
    private String courseName;
    private String courseCode;
    private String courseDescription;
    private String courseDepartment;
    private String courseFaculty;

    public Course(String courseName, String courseCode, String courseDescription, String courseDepartment,
            String courseFaculty) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseDescription = courseDescription;
        this.courseDepartment = courseDepartment;
        this.courseFaculty = courseFaculty;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCourseCode())
                .append(" : ")
                .append(getCourseName());
        return builder.toString();
    }

}
