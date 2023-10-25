package networkbook.commons.util;

import java.io.IOException;

public interface ThrowingIOExceptionConsumer<T> {
    void accept(T item) throws IOException;
}
