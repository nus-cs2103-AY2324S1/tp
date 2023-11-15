package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.displayable.UniqueDisplayableList;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.displayable.seller.Seller;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameDisplayable comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {
    private final UniqueDisplayableList<Buyer> buyers;
    private final UniqueDisplayableList<Seller> sellers;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        buyers = new UniqueDisplayableList<>();
        sellers = new UniqueDisplayableList<>();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the lists in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the buyer list with {@code buyers}.
     * {@code buyers} must not contain duplicate buyers.
     */
    public void setBuyers(List<Buyer> buyers) {
        this.buyers.setDisplayables(buyers);
    }

    /**
     * Replaces the contents of the displayable list with {@code sellers}.
     * {@code sellers} must not contain duplicate sellers.
     */
    public void setSellers(List<Seller> sellers) {
        this.sellers.setDisplayables(sellers);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setBuyers(newData.getBuyerList());
        setSellers(newData.getSellerList());
    }

    //// displayable-level operations

    /**
     * Returns true if a buyer with the same identity as {@code buyer} exists in the address book's buyer list.
     */
    public boolean hasBuyer(Buyer buyer) {
        requireNonNull(buyer);
        return buyers.contains(buyer);
    }
    /**
     * Returns true if a buyer with a similar identity as {@code buyer} exists in the address book's buyer list.
     */
    public boolean hasSimilarBuyer(Buyer buyer) {
        requireNonNull(buyer);
        return buyers.containsSimilar(buyer);
    }
    /**
     * Returns true if a seller with the same identity as {@code seller} exists in the address book's seller list.
     */
    public boolean hasSeller(Seller seller) {
        requireNonNull(seller);
        return sellers.contains(seller);
    }
    /**
     * Returns true if a seller with a similar identity as {@code seller} exists in the address book's seller list.
     */
    public boolean hasSimilarSeller(Seller seller) {
        requireNonNull(seller);
        return sellers.containsSimilar(seller);
    }

    /**
     * Returns true if a buyer has the same name as a seller in the address book's seller list.
     */
    public boolean buyerHasSameSellerName(Buyer buyer) {
        requireNonNull(buyer);
        return sellers.contains(buyer);
    }

    /**
     * Returns true if a seller has the same name as buyer in the address book's buyer list.
     */
    public boolean sellerHasSameBuyerName(Seller seller) {
        requireNonNull(seller);
        return buyers.contains(seller);
    }

    /**
     * Adds a buyer to the address book's buyer list.
     * The buyer must not already exist in the buyer list.
     */
    public void addBuyer(Buyer buyer) {
        buyers.add(buyer);
    }

    /**
     * Adds a seller to the address book's seller list.
     * The seller must not already exist in the seller list.
     */
    public void addSeller(Seller seller) {
        sellers.add(seller);
    }


    /**
     * Replaces the given buyer {@code targetBuyer} in the list with {@code editedBuyer}.
     * {@code targetBuyer} must exist in the address book's buyer list.
     * The buyer identity of {@code editedBuyer} must not be the same as another existing buyer in the buyer list.
     */
    public void setBuyer(Buyer targetBuyer, Buyer editedBuyer) {
        requireNonNull(editedBuyer);

        buyers.setDisplayable(targetBuyer, editedBuyer);
    }

    /**
     * Replaces the given seller {@code targetSeller} in the list with {@code editedSeller}.
     * {@code targetSeller} must exist in the address book's seller list.
     * The seller identity of {@code editedSeller} must not be the same as another existing seller in the seller list.
     */
    public void setSeller(Seller targetSeller, Seller editedSeller) {
        requireNonNull(editedSeller);

        sellers.setDisplayable(targetSeller, editedSeller);
    }

    /**
     * Removes {@code buyerKey} from this {@code AddressBook's buyer list}.
     * {@code buyerKey} must exist in the address book's buyer list.
     */
    public void removeBuyer(Buyer buyerKey) {
        buyers.remove(buyerKey);
    }

    /**
     * Removes {@code sellerKey} from this {@code AddressBook's seller list}.
     * {@code sellerKey} must exist in the address book's seller list.
     */
    public void removeSeller(Seller sellerKey) {
        sellers.remove(sellerKey);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("buyers", buyers)
                .add("sellers", sellers)
                .toString();
    }

    @Override
    public ObservableList<Buyer> getBuyerList() {
        return buyers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Seller> getSellerList() {
        return sellers.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return buyers.equals(otherAddressBook.buyers) && sellers.equals(otherAddressBook.sellers);
    }

    @Override
    public int hashCode() {
        return buyers.hashCode() ^ sellers.hashCode();
    }
}
