package com.prog_avanzada.services.impl;

import com.prog_avanzada.model.Book;
import com.prog_avanzada.services.interfaces.IBookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookRepository implements IBookRepository {

  @Inject
  DataSource dataSource;

  @Override
  public Optional<Book> findByIsbn(String isbn) {
    String sql = "SELECT isbn, title, price, version FROM books WHERE isbn = ?";

    try (Connection connection = dataSource.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {

      ps.setString(1, isbn);

      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          Book book = new Book();

          book.setIsbn(rs.getString("isbn"));
          book.setTitle(rs.getString("title"));
          book.setPrice(rs.getDouble("price"));
          book.setVersion(rs.getInt("version"));

          return Optional.of(book);
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return Optional.empty();
  }

  @Override
  public List<Book> findAll() {
    List<Book> books = new ArrayList<>();
    String sql = "SELECT isbn, title, price, version FROM books";

    try (Connection connection = dataSource.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

      while (rs.next()) {
        Book book = new Book();
        book.setIsbn(rs.getString("isbn"));
        book.setTitle(rs.getString("title"));
        book.setPrice(rs.getDouble("price"));
        book.setVersion(rs.getInt("version"));

        books.add(book);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return books;
  }

  @Override
  public void insert(Book book) {
    String sql = "INSERT INTO books (isbn, title, price, version) VALUES (?, ?, ?, ?)";

    try (Connection connection = dataSource.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {

      ps.setString(1, book.getIsbn());
      ps.setString(2, book.getTitle());
      ps.setDouble(3, book.getPrice());
      ps.setInt(4, book.getVersion());

      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateBook(Book book) {
    String sql = "UPDATE books SET title = ?, price = ?, version = ? WHERE isbn = ?";

    try (Connection connection = dataSource.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {

      ps.setString(1, book.getTitle());
      ps.setDouble(2, book.getPrice());
      ps.setInt(3, book.getVersion());

      ps.setString(4, book.getIsbn());

      int filasAfectadas = ps.executeUpdate();
      if (filasAfectadas == 0) {
        System.out.println("No se encontro el libro para actualizar");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(String isbn) {
    String sql = "DELETE FROM books WHERE isbn = ?";

    try (Connection connection = dataSource.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {

      ps.setString(1, isbn);
      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}