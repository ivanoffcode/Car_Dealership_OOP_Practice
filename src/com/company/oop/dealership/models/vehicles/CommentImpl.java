package com.company.oop.dealership.models.vehicles;

import com.company.oop.dealership.models.vehicles.contracts.Comment;
import static com.company.oop.dealership.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public class CommentImpl implements Comment {

    public static final int CONTENT_LEN_MIN = 3;
    public static final int CONTENT_LEN_MAX = 200;
    private static final String CONTENT_LEN_ERR = format(
            "Content must be between %d and %d characters long!",
            CONTENT_LEN_MIN,
            CONTENT_LEN_MAX);

    private String author;
    private String content;

    public CommentImpl(String content, String author) {
        setContent(content);
        setAuthor(author);
    }

    private void setAuthor(String author) {
        this.author = author;
    }

    private void setContent(String content) {
        validateStringLength(content, CONTENT_LEN_MIN, CONTENT_LEN_MAX, CONTENT_LEN_ERR);
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return format("----------\n" +
                "%s\n" +
                "User: %s\n" +
                "----------%n", getContent(), getAuthor());
    }

//TODO
}