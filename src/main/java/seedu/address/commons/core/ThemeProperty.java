package seedu.address.commons.core;


import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import seedu.address.model.Theme;

/**
 * Represents a custom property for managing the theme of an application.
 */
public class ThemeProperty implements Property<Theme> {
    private final SimpleObjectProperty<Theme> delegate = new SimpleObjectProperty<>(Theme.DARK);

    @Override
    public void bind(ObservableValue<? extends Theme> observable) {
        delegate.bind(observable);
    }

    @Override
    public void unbind() {
        delegate.unbind();
    }

    @Override
    public boolean isBound() {
        return delegate.isBound();
    }

    @Override
    public void bindBidirectional(Property<Theme> other) {
        delegate.bindBidirectional(other);
    }

    @Override
    public void unbindBidirectional(Property<Theme> other) {
        delegate.unbindBidirectional(other);
    }

    @Override
    public Object getBean() {
        return delegate.getBean();
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public void addListener(ChangeListener<? super Theme> listener) {
        delegate.addListener(listener);
    }

    @Override
    public void addListener(InvalidationListener listener) {
        delegate.addListener(listener);
    }
    @Override
    public void removeListener(ChangeListener<? super Theme> listener) {
        delegate.removeListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        delegate.removeListener(listener);
    }

    @Override
    public Theme getValue() {
        return delegate.getValue();
    }

    @Override
    public void setValue(Theme value) {
        delegate.setValue(value);
    }
    public ThemeProperty getCopy() {
        ThemeProperty newTheme = new ThemeProperty();
        newTheme.setValue(this.getValue());
        return newTheme;
    }
}
