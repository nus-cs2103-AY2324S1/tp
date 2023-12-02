package seedu.address.storage;

import java.util.Optional;

import seedu.address.model.ReadOnlyBook;

/**
 * Represents a book that references one other book.
 * @param <T> The type of the book.
 * @param <R> The type of the referencing book.
 */
public abstract class BookStorageWithReference<T, R> implements BookStorage<T> {

    private Optional<ReadOnlyBook<R>> referenceBook;

    public BookStorageWithReference() {
        referenceBook = Optional.empty();
    }

    public void setReferenceBook(Optional<ReadOnlyBook<R>> referenceBook) {
        this.referenceBook = referenceBook;
    }

    public Optional<ReadOnlyBook<R>> getReferenceBook() {
        return this.referenceBook;
    }

    /**
     * Sets another book which this book will take reference from
     * @param referenceBook The book to reference
     */
    public abstract void setReferencingBook(ReadOnlyBook<R> referenceBook);
}
