package org.kainos.daos;

import org.kainos.models.Book;
import org.kainos.models.BookRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private final int five = 5;
    private final int four = 4;
    private final int three = 3;

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT id as `BookID`, title, author,"
                            + "publisher, price FROM Books");

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("BookID"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        resultSet.getDouble("price"));

                books.add(book);
            }
        }
        return books;
    }

    public Book getBookById(final int bookID) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query =
                    "SELECT id as `BookID`, title, author,"
                            + "publisher, price FROM Books WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, bookID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new Book(
                        resultSet.getInt("BookID"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        resultSet.getDouble("price"));
            }
        }
        return null;
    }

    public void updateBook(final int id, final BookRequest book)
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String updateStatement = "UPDATE Books SET title = ?, author = ?,"
                    + "publisher = ?, price = ? WHERE id = ?";
            PreparedStatement statement =
                    connection.prepareStatement(updateStatement);
            statement.setString(1, "title");
            statement.setString(2, "author");
            statement.setString(three, "publisher");
            statement.setDouble(four, Double.parseDouble("price"));
            statement.setInt(five, id);
            statement.executeUpdate();
        }
    }

    public int createBook(final BookRequest book) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String insertStatement = "INSERT INTO Books (title, author,"
                    + " publisher, price) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection
                    .prepareStatement(insertStatement);

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(three, book.getPublisher());
            statement.setDouble(four, book.getPrice());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return -1;
    }
    public void deleteBook(final int bookID) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String deleteStatement = "DELETE FROM Books WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(
                    deleteStatement);
            statement.setInt(1, bookID);
            statement.executeUpdate();
        }
    }
}
