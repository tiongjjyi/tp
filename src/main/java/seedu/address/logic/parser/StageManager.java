package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.model.course.Course;

/**
 * This Singleton class manages the stages of the application.
 */
public class StageManager {
    private static StageManager singleInstance = null;
    private Stages currentStage = Stages.HOME;
    private Course selectedCourse = null;

    private StageManager() {
    }

    public static StageManager getInstance() {
        if (singleInstance == null) {
            singleInstance = new StageManager();
            return singleInstance;
        }
        return singleInstance;
    }

    public Stages getStage() {
        return currentStage;
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setCourseStage(Course selectedCourse) {
        requireNonNull(selectedCourse);
        currentStage = Stages.COURSE;
        this.selectedCourse = selectedCourse;
    }

    public void setHomeStage() {
        currentStage = Stages.HOME;
        selectedCourse = null;
    }

    public Course getCurrentCourse() {
        return selectedCourse;
    }

    public boolean isSelectedCourseNull() {
        return selectedCourse == null;
    }

}
