package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 */
public class Remark {


	public final String value;

	/**
	 * Constructs a remark
	 *
	 * @param remark A valid remark.
	 */
	public Remark(String remark) {
//		requireNonNull(remark);
		value = remark;
	}

	/**
	 * Returns if a given string is a valid email.
	 */

	@Override
	public String toString() {
		return value;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		// instanceof handles nulls
		if (!(other instanceof Remark)) {
			return false;
		}

		Remark otherRemark = (Remark) other;
		return value.equals(otherRemark.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

}

