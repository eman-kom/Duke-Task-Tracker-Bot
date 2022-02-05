package seedu.task;

import java.time.LocalDateTime;

public class Event extends Task {

    private final LocalDateTime at;

    /**
     * Constructor for Event class
     * @param description Description of Task
     * @param at Datetime of task to be held
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for Event class
     * @param description Description of Task
     * @param isCompleted Marks whether task is completed or not
     * @param at Datetime of task to be held
     */
    public Event(String description, boolean isCompleted, LocalDateTime at) {
        super(description, isCompleted);
        this.at = at;
    }

    @Override
    public String toFile() {
        return "E\t" + super.toFile() + "\t" + at.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + at.format(FORMATTER) + ")";
    }
}