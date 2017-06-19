package ui;

/**
 * A string enum to describe in one place the path to use for each view
 */
public enum View {

    MAIN("mainView"),
    REQUISITIONS("requisition"),
    FEEDBACKS("feedback");

    private final String text;

    View(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}