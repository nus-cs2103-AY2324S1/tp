package seedu.lovebook.model.person;

import java.util.Objects;

import seedu.lovebook.commons.util.ToStringBuilder;

public class Preferences {
    private Age age;
    private Gender gender;
    private Height height;
    private Income income;

    Preferences() {
        this.age = new Age("21");
        this.gender = new Gender("F");
        this.height = new Height("170");
        this.income = new Income("10000");
    }

    public Preferences(Age age, Gender gender, Height height, Income income) {
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.income = income;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.age, this.gender, this.height, this.income);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("age", age)
                .add("gender", gender)
                .add("height", height)
                .add("income", income)
                .toString();
    }
}
