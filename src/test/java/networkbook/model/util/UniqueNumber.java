package networkbook.model.util;

class UniqueNumber implements Identifiable<UniqueNumber> {
    private int number;
    // to distinguish equals from identity
    private int dummy;

    public UniqueNumber(int number, int dummy) {
        this.number = number;
        this.dummy = dummy;
    }

    @Override
    public boolean isSame(UniqueNumber another) {
        return this.number == another.number;
    }

    @Override
    public String getValue() {
        return String.valueOf(this.number);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof UniqueNumber)) {
            return false;
        }

        UniqueNumber anotherNumber = (UniqueNumber) object;
        return this.number == anotherNumber.number && this.dummy == anotherNumber.dummy;
    }
}
