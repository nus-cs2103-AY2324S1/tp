package seedu.address.model;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.lessons.TaskList;
import seedu.address.model.person.Name;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Tags;
import seedu.address.model.tag.Tag;

/**
 * Represents a ListEntry in the application.
 */
public abstract class ListEntry<T> {
    protected Name name = Name.DEFAULT_NAME;
    protected Remark remark = Remark.DEFAULT_REMARK;
    protected Tags tags = new Tags();

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
    public void setNameIfNotDefault(Name name) {
        if (name != null && !name.equals(Name.DEFAULT_NAME)) {
            setName(name);
        }
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        requireAllNonNull(tags);
        this.tags = tags;
    }
    public void setTagsIfNotDefault(Set<Tag> tags) {
        if (tags != null) {
            setTagsIfNotDefault(new Tags(tags));
        }
    }

    public void setTagsIfNotDefault(Tags tags) {
        if (tags != null && !tags.equals(new Tags())) {
            setTags(tags);
        }
    }

    public Remark getRemark() {
        return remark;
    }

    public void setRemark(Remark remark) {
        requireAllNonNull(remark);
        this.remark = remark;
    }

    public void setRemarkIfNotDefault(Remark remark) {
        if (remark != null && !remark.equals(Remark.DEFAULT_REMARK)) {
            setRemark(remark);
        }
    }
    public Set<Tag> getTagsSet() {
        return tags.getTagSetClone();
    }

    //public static ListEntry getDefault${className);
    public abstract T clone();
}
