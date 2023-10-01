package seedu.address.model.info.course;

import java.util.ArrayList;

public class CourseList {
    private ArrayList<Course> courseList;

    public CourseList() {
        courseList = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void removeCourse(Course course) {
        courseList.remove(course);
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

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
