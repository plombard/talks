package org.finistjug.talks.guava;

import java.util.Date;

public class SVNDocument {

    private final String path;
    private final String name;
    private final String author;
    private final String repository;
    private final long revision;
    private final Date date;
    private final long size;
    private final String message;

    public SVNDocument(String path, String name, String author, String repository, long revision, Date date, long size, String message) {
        this.path = path;
        this.name = name;
        this.author = author;
        this.repository = repository;
        this.revision = revision;
        this.date = date;
        this.size = size;
        this.message = message;
    }
}
