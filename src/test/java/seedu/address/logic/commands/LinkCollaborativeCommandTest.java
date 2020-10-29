package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.getTypicalEventsTaskList;
import static seedu.address.testutil.TypicalTodos.CHORES;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.link.LinkCollaborativeCommand;
import seedu.address.logic.commands.link.LinkMeetingCommand;
import seedu.address.model.*;
import seedu.address.model.person.Person;
import seedu.address.model.task.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class LinkCollaborativeCommandTest {
    private Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalEventsTaskList());

    @Test
    public void constructor_nullMeetingLink_throwsNullPointerException() {
        Index index = Index.fromOneBased(1);
        assertThrows(NullPointerException.class, () -> new LinkCollaborativeCommand(index, null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        ModelStubAcceptingTodoAdded modelStub = new ModelStubAcceptingTodoAdded();
        Index index = Index.fromOneBased(100);
        CollaborativeLink link = new CollaborativeLink("Google Meet",
                "https://www.google.com");
        try {
            CommandResult commandResult = new LinkCollaborativeCommand(index, link).execute(modelStub);
        } catch (CommandException e) {
            assertEquals(new CommandException(Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX).toString(),
                    e.toString());
        }
    }

    @Test
    public void equals() {
        Index index = Index.fromOneBased(1);
        CollaborativeLink googleLink = new CollaborativeLink("Google Meet",
                "https://www.google.com");
        CollaborativeLink zoomLink = new CollaborativeLink("Zoom Meeting",
                "https://www.zoom.com");
        LinkCollaborativeCommand linkGoogle = new LinkCollaborativeCommand(index, googleLink);
        LinkCollaborativeCommand linkZoom = new LinkCollaborativeCommand(index, zoomLink);

        // same object -> returns true
        assertTrue(linkGoogle.equals(linkGoogle));

        // same values -> returns true
        LinkCollaborativeCommand linkZoomCopy = new LinkCollaborativeCommand(index, zoomLink);
        assertTrue(linkZoom.equals(linkZoomCopy));

        // different types -> returns false
        assertFalse(linkGoogle.equals(1));

        // null -> returns false
        assertFalse(linkGoogle.equals(null));

        // different person -> returns false
        assertFalse(linkGoogle.equals(linkZoom));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        // Needs to be null to update "Due Soon" section in GUI after adding a task.
        @Override
        public ObservableList<Task> getDueSoonTaskList() {
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<? super Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTodo(Todo todo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTodo(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskList getTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public AddCommand markAsDone(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedTaskList(Comparator<Task> taskComparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedPersonList(Comparator<Person> personComparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the to-do being added.
     */
    private class ModelStubAcceptingTodoAdded extends ModelStub {
        final ArrayList<Todo> todosAdded = new ArrayList<>();

        @Override
        public boolean hasTask(Task todo) {
            requireNonNull(todo);
            return todosAdded.stream().anyMatch(todo::isSameTask);
        }

        @Override
        public void addTodo(Todo todo) {
            requireNonNull(todo);
            todosAdded.add(todo);
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            return expectedModel.getFilteredTaskList();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

    }
}
