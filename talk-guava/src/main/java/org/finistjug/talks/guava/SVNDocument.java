package org.finistjug.talks.guava;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;


public class SVNDocument {

    private final String path;
    private final String name;
    private final String author;
    private final String repository;
    private final Long revision;
    private final Date date;
    private final Long size;
    private final String message;

    public SVNDocument(String path, String name, String author, String repository, Long revision, Date date, Long size, String message) {
        // Ensure that required fields are not null
        this.path = checkNotNull(path);
        this.name = checkNotNull(name);
        this.author = author;
        this.repository = checkNotNull(repository);
        this.revision = checkNotNull(revision);
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

    public int compareTo(SVNDocument other) {
        // if repos are not the same,
        // we see the documents as "equals"
        if (repository.compareTo(other.repository) != 0) {
            return 0;
        }
        // now the pretty part
        return ComparisonChain.start()
                .compare(date, other.date)
                .compare(revision, other.revision)
                .result();
    }
}
