package org.kainos.controllers;


import io.swagger.annotations.Api;
import org.kainos.exceptions.DoesNotExistException;
import org.kainos.exceptions.FailedToCreateException;
import org.kainos.models.BookRequest;
import org.kainos.services.BookService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Library Books")
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

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(
            @PathParam("id") final int id, final BookRequest book) {
        try {
            bookService.updateBook(id, book);
            return Response.noContent().build();
        } catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e) {
            return Response.status(Response.Status
                    .INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(final BookRequest book) {
        try {
            return Response
                    .status(Response.Status.CREATED)
                    .entity(bookService.createBook(book))
                    .build();
        } catch (SQLException | FailedToCreateException e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("id") final int id) {
        try {
            bookService.deleteBook(id);
            return Response.noContent().build();
        } catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }
}
