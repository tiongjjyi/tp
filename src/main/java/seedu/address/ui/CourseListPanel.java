package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.parser.StageManager;
import seedu.address.model.course.Course;



/**
 * Panel containing the list of courses.
 */
public class CourseListPanel extends UiPart<Region> {
    private static final String FXML = "CourseListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CourseListPanel.class);

    @FXML
    private ListView<Course> courseListView;

    /**
     * Creates a {@code CourseListPanel} with the given {@code ObservableList}.
     */
    public CourseListPanel(ObservableList<Course> courseList) {
        super(FXML);
        courseListView.setItems(courseList);
        courseListView.setCellFactory(listView -> new CourseListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Course} using a {@code CourseCard}.
     */
    class CourseListViewCell extends ListCell<Course> {
        @Override
        protected void updateItem(Course course, boolean empty) {
            super.updateItem(course, empty);

            if (empty || course == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (StageManager.isSelectedCourseNull() ||
                        StageManager.getSelectedCourse().equals(course)) {
                    setGraphic(new CourseCard(course, getIndex() + 1, false).getRoot());
                } else if (!StageManager.getSelectedCourse().equals(course)) {
                    setGraphic(new CourseCard(course, getIndex() + 1, true).getRoot());
                }
            }
        }
    }
}
