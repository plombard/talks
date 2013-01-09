package org.finistjug.talks.guava;

import com.google.common.base.Objects;

import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


public class SVNDocument {

    public String path;
    public String name;
    public String author;
    public String repository;
    public Long revision;
    public Date date;
    public Long size;
    public String message;

    public SVNDocument(String path, String name, String author, String repository, Long revision, Date date, Long size, String message) {
        // Ensure that required fields are not null
        this.path = checkNotNull(path);
        this.name = checkNotNull(name);
        this.author = author;
        this.repository = checkNotNull(repository);
        // throws a IllegalStateException if the revision is negative
        checkArgument(revision >= 0, "Revision number %s cannot be negative", revision);
        this.revision = revision;
        this.date = date;
        this.size = size;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        // No hassle with null-checking,
        // but Objects.equal is JDK7-redundant.
        if (!(o instanceof SVNDocument)) {
            return false;
        }

        SVNDocument that = (SVNDocument) o;
        return Objects.equal(name, that.name)
                && Objects.equal(path, that.path)
                && Objects.equal(repository, that.repository)
                && Objects.equal(revision, that.revision);
    }

    @Override
    public int hashCode() {
        // Easier to read,
        // but again, JDK7 redundant.
        return Objects.hashCode(name, path, repository, revision);
    }

    @Override
    public String toString() {
        // A-HA, now we're talkin'
        return Objects.toStringHelper(this)
                .omitNullValues()
                .add("path", path)
                .add("name", name)
                .add("author", author)
                .add("repository", repository)
                .add("revision", revision)
                .add("date", date)
                .add("size", size)
                .add("message", message)
                .toString();
    }

}
