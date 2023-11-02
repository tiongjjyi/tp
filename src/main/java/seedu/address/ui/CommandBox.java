package seedu.address.ui;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    private ArrayList<String> stringArray;
    private int arrayPointer = 0;

    @FXML
    private TextField commandTextField;


    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        stringArray = new ArrayList<>();
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        setHistory();
    }

    /**
     * Sets the command text field to be able to use the 'up' and 'down' keys to view previously entered commands
     */
    @FXML
    private void setHistory() {
        commandTextField.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.UP) {
                if (!stringArray.isEmpty()) {
                    if (arrayPointer < 0) {
                        arrayPointer = 0;
                    }
                    String history = stringArray.get(arrayPointer);
                    setTextBox(history);
                    arrayPointer--;
                }
            } else if (key.getCode() == KeyCode.DOWN) {
                if (!stringArray.isEmpty()) {
                    arrayPointer++;
                    if (arrayPointer >= stringArray.size()) {
                        clearTextBox();
                        arrayPointer = stringArray.size() - 1;
                    } else {
                        String history = stringArray.get(arrayPointer);
                        commandTextField.setText(history);
                    }
                }
            }
        });
    }

    /**
     * Clears the UI text field displayed the user
     */
    @FXML
    private void clearTextBox() {
        commandTextField.setText("");
    }

    /**
     * Sets the UI text field to the argument string
     */
    @FXML
    private void setTextBox(String s) {
        commandTextField.setText(s);
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        stringArray.add(commandText);
        arrayPointer = stringArray.size() - 1;
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
        clearTextBox();
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
