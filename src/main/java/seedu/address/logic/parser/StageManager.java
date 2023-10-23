package seedu.address.logic.parser;

import seedu.address.model.course.Course;

/**
 * This class manages the stages of the application.
 */
public class StageManager {
    private static Stages currentStage = Stages.HOME;
    private static Course selectedCourse = null;

    public static Stages getStage() {
        return currentStage;
    }

    public static Course getSelectedCourse() {
        return selectedCourse;
    }

    public static void setCourseStage(Course selectedCourse) {
        StageManager.currentStage = Stages.COURSE;
        StageManager.selectedCourse = selectedCourse;
    }

    public static void setHomeStage() {
        StageManager.currentStage = Stages.HOME;
        StageManager.selectedCourse = null;
    }

    public Course getCurrentCourse() {
        return selectedCourse;
    }
}
