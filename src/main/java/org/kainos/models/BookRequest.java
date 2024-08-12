package org.kainos.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookRequest {
    private int bookId;
    private String title;
    private String author;
    private String publisher;
    private double price;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(final int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    @JsonCreator
    public BookRequest(
            @JsonProperty("bookId") final int bookId,
            @JsonProperty("title") final String title,
            @JsonProperty("publisher") final String publisher,
            @JsonProperty("price") final double price) {
        this.bookId = bookId;
        this.title = title;
        this.publisher = publisher;
        this.price = price;

    }



}
