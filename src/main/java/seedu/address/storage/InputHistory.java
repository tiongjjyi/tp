package seedu.address.storage;

import javafx.util.Pair;

import java.util.ArrayList;

public class InputHistory implements InputStorage {

    private static final Pair<Boolean, String> EMPTY_PAIR = new Pair<>(true, "");
    private final ArrayList<Pair<Boolean, String>> inputs;
    private int inputPointer;

    public InputHistory() {
        inputs = new ArrayList<>();
        inputPointer = -1;
    }

    @Override
    public Pair<Boolean, String> getInput() {
        if (inputs.isEmpty() || inputPointer == inputs.size()) {
            return EMPTY_PAIR;
        }
        return inputs.get(inputPointer);
    }

    @Override
    public void addInput(boolean isValid, String text) {
        inputs.add(new Pair<>(isValid, text));
        inputPointer = inputs.size();
    }


    @Override
    public void decrementPointer() {
        inputPointer--;
        if (inputs.isEmpty()) {
            inputPointer = -1;
        } else if (inputPointer < 0) {
            inputPointer = 0;
        }
    }

    @Override
    public void incrementPointer() {
        inputPointer++;
        if (inputPointer > inputs.size()) {
            inputPointer = inputs.size();
        }
    }


    public int getInputPointer() {
        return this.inputPointer;
    }

    public int getInputHistorySize() {
        return this.inputs.size();
    }

}
