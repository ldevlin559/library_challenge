package org.kainos.services;

import org.kainos.daos.BookDao;
import org.kainos.exceptions.DoesNotExistException;
import org.kainos.exceptions.Entity;
import org.kainos.exceptions.FailedToCreateException;
import org.kainos.models.Book;
import org.kainos.models.BookRequest;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    BookDao bookDao;

    public BookService(final BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> getAllBooks() throws SQLException {
        return bookDao.getAllBooks();
    }

    public Book getBookById(final int id) throws SQLException {
        return bookDao.getBookById(id);
    }

    public void updateBook(final int id, final BookRequest book)
            throws SQLException, DoesNotExistException {
        Book bookToUpdate = bookDao.getBookById(id);

        if (bookToUpdate != null) {
            throw new DoesNotExistException(Entity.BOOK);
        }

        bookDao.updateBook(id, book);
    }

    public int createBook(final BookRequest book)
            throws SQLException,
            FailedToCreateException {
        int id = bookDao.createBook(book);

        if (id == -1) {
            throw new FailedToCreateException(Entity.BOOK);
        }
        return id;
    }

    public void deleteBook(final int id)
            throws SQLException, DoesNotExistException {
        Book bookToDelete = bookDao.getBookById(id);

        if (bookToDelete == null) {
            throw new DoesNotExistException(Entity.BOOK);
        }

        bookDao.deleteBook(id);


    }
}
