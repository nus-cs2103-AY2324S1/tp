package seedu.address.model.path;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.pathelement.PathElement;
import seedu.address.model.pathelement.PathElementType;
import seedu.address.model.pathelement.exceptions.InvalidPathElementException;

public class Path {
    protected List<PathElement> pathElements;
    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    public Path() throws InvalidPathException {
        this.pathElements = new ArrayList<>();
    }

    // Common constructor logic
    protected void commonConstructor(String path) throws InvalidPathException {
        String[] elementStrs = path.split("/");

        for (String elementStr : elementStrs) {
            logger.info(elementStr);
            try {
                PathElement element = PathElement.parsePathElement(elementStr);
                logger.info("Current element " + element.toString());

                if (element.getType() == PathElementType.CURRENT) {
                    continue;
                }

                if (element.getType() == PathElementType.PARENT) {
                    PathElement prevElement = this.pathElements.get(this.pathElements.size() - 1);

                    if (prevElement.getType() == PathElementType.ROOT) {
                        throw new InvalidPathException("Unable to navigate above home directory");
                    }
                    if (prevElement.getType() != PathElementType.PARENT) {
                        this.pathElements.remove(this.pathElements.size() - 1);
                        continue;
                    }

                } else if (!this.pathElements.isEmpty()) {
                    PathElement prevElement = this.pathElements.get(this.pathElements.size() - 1);

                    int priorityDiff = element.getPriorityDiff(prevElement);

                    // Make sure curr element is has 1 level lower
                    if (priorityDiff != -1 && prevElement.getType() != PathElementType.PARENT) {
                        throw new InvalidPathException("Invalid path structure.");
                    }
                }

                this.pathElements.add(element);
            } catch (InvalidPathElementException e) {
                throw new InvalidPathException("Invalid path element.");
            }
            logger.info(this.toString());
        }
    }

    @Override
    public String toString() {
        List<String> pathElementStrings = pathElements.stream()
                .map(PathElement::toString)
                .collect(Collectors.toList());

        return String.join("/", pathElementStrings);
    }
}
