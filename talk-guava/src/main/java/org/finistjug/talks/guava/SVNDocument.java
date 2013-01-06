package org.finistjug.talks.guava;

import java.util.Date;

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
        this.path = path;
        this.name = name;
        this.author = author;
        this.repository = repository;
        this.revision = revision;
        this.date = date;
        this.size = size;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SVNDocument that = (SVNDocument) o;

        if (!name.equals(that.name)) return false;
        if (!path.equals(that.path)) return false;
        if (!repository.equals(that.repository)) return false;
        if (!revision.equals(that.revision)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = path.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + repository.hashCode();
        result = 31 * result + revision.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("SVNDocument{");
        if (path != null) {
            result.append("path='").append(path).append("',");
        }
        if (name != null) {
            result.append("name='").append(name).append("',");
        }
        if (author != null) {
            result.append("author='").append(author).append("',");
        }
        if (repository != null) {
            result.append("repository='").append(repository).append("',");
        }
        if (revision != null) {
            result.append("revision='").append(revision).append("',");
        }
        if (date != null) {
            result.append("date='").append(date).append("',");
        }
        if (size != null) {
            result.append("size='").append(size).append("',");
        }
        if (message != null) {
            result.append("message='").append(message).append("',");
        }
        result.append("}");
        return result.toString();
    }

    public int compareTo(SVNDocument other) {
        // if repos are not the same,
        // we see the documents as "equals"
        if (repository.compareTo(other.repository) != 0) {
            return 0;
        }
        // else date
        int cmp = date.compareTo(other.date);
        if (cmp != 0) {
            return cmp;
        }
        // else revision
        return Long.compare(revision, other.revision);
    }
}
