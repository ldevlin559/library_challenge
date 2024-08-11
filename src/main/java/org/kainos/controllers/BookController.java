package org.kainos.controllers;


import org.kainos.services.BookService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/api/books")
public class BookController {

    BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() throws SQLException {
        return Response.ok().entity(bookService.getAllBooks()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("id") final int id)
            throws SQLException {
        return Response.ok().entity(bookService.getBookById(id)).build();
    }
}
