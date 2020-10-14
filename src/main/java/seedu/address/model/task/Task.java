package seedu.address.model.task;

import java.time.LocalDateTime;

public abstract class Task {
    /** A brief description of the task. */
    protected String description;

    /** Tracks the completion of the task. */
    protected boolean isDone;

    /** The URL associated with the task. */
    protected Link link;

    /**
     * Constructs a task that has not been completed with a description.
     *
     * @param description a brief description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task that has not been completed with a description and a link.
     *
     * @param description a brief description of the task
     * @param link the URl for the task
     */
    public Task(String description, Link link) {
        this.description = description;
        this.isDone = false;
        this.link = link;
    }

    /**
     * Constructs a task, which may or may not have been completed, with a description.
     *
     * @param isDone indicates if the task has been completed.
     * @param description a brief description of the task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     * Returns tick symbol when task is indicated as done.
     * Returns X symbol when task is not indicated as done.
     *
     * @return the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns the description of the task as a string.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the URL of the task as a String.
     */
    public String getLink() {
        if (this.link != null) {
            return this.link.getValue();
        } else {
            return "";
        }
    }
    /**
     * Indicates that the task has been completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns a boolean value indicating if the task is equal to
     * another object by determining if descriptions and isDone parameters
     * are equal.
     *
     * @param o an object that is compared to the task to determine if both are equal
     * @return true or false if the object is equal or not equal to the task respectively.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Task) {
            Task task = (Task) o;
            boolean isEqualTask = this.description.equals(task.description) && this.isDone == task.isDone;
            return isEqualTask;
        } else {
            return false;
        }
    }

    public boolean isSameTask(Task task) {
        return this.equals(task);
    }

    /**
     * Returns the string representation of task, which includes the status icon
     * and description.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns the string representation of the task in a format to be inputted into a text file for data storage.
     *
     * @return the string representation of the task to be saved in a text file.
     */
    public String saveFormat() {
        if (isDone) {
            return "T | 1 | " + this.getDescription();
        } else {
            return "T | 0 | " + this.getDescription();
        }
    }

    public abstract LocalDateTime getDeadline();
    public abstract LocalDateTime getStart();
    public abstract LocalDateTime getEnd();
    public abstract String getDateTime();
    public abstract LocalDateTime getLocalDateTime();
    public abstract boolean isTodo();
    public abstract boolean isEvent();
    public abstract String getType();
}
