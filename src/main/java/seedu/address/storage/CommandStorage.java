package seedu.address.storage;

import javafx.util.Pair;

import java.util.ArrayList;

public class CommandStorage {

    private final ArrayList<Pair<Boolean, String>> commandHistory;
    private int historyPointer;

    public CommandStorage() {
        commandHistory = new ArrayList<>();
        historyPointer = -1;
    }

    public void addCommand(boolean isValid, String text) {
        commandHistory.add(new Pair<>(isValid, text));
        historyPointer = commandHistory.size();
    }

    public Pair<Boolean, String> getCommand() {
        if (commandHistory.isEmpty() || historyPointer == commandHistory.size()) {
            return new Pair<>(true, "");
        }
        return commandHistory.get(historyPointer);
    }

    public void decrementPointer() {
        historyPointer--;
        if (commandHistory.isEmpty()) {
            historyPointer = -1;
        } else if (historyPointer < 0) {
            historyPointer = 0;
        }
    }

    public void incrementPointer() {
        historyPointer++;
        if (historyPointer > commandHistory.size()) {
            historyPointer = commandHistory.size();
        }
    }
}
