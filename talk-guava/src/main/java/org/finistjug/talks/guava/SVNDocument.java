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


}
