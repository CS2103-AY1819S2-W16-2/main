package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the pin-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class PinBook implements ReadOnlyPinBook {

    private final UniquePersonList pinnedPersons;
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        pinnedPersons = new UniquePersonList();
    }

    public PinBook() {}

    /**
     * Creates an PinBook using the Persons in the {@code toBeCopied}
     */
    public PinBook(ReadOnlyPinBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.pinnedPersons.setPersons(persons);
        indicateModified();
    }

    /**
     * Resets the existing data of this {@code PinBook} with {@code newData}.
     */
    public void resetData(ReadOnlyPinBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the pin book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return pinnedPersons.contains(person);
    }

    /**
     * Adds a person to the pin book.
     * The person must not already exist in the pin book.
     */
    public void addPerson(Person p) {
        pinnedPersons.add(p);
        indicateModified();
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the pin book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the pin book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        pinnedPersons.setPerson(target, editedPerson);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code PinBook}.
     * {@code key} must exist in the pin book.
     */
    public void removePerson(Person key) {
        pinnedPersons.remove(key);
        indicateModified();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the pin book has been modified.
     */
    protected void indicateModified() {
        invalidationListenerManager.callListeners(this);
    }

    //// util methods

    @Override
    public String toString() {
        return pinnedPersons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return pinnedPersons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PinBook // instanceof handles nulls
                && pinnedPersons.equals(((PinBook) other).pinnedPersons));
    }

    @Override
    public int hashCode() {
        return pinnedPersons.hashCode();
    }
}
