package com.prog_avanzada.services;

import com.prog_avanzada.model.Book;
import com.prog_avanzada.model.Customer;
import com.prog_avanzada.repository.ICustomerRepository;
import io.helidon.dbclient.DbClient;
import io.helidon.dbclient.DbRow;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CustomerRepository implements ICustomerRepository {

    private final DbClient dbClient;

    @Inject
    public CustomerRepository(DbClient dbClient) {
        this.dbClient = dbClient;
    }

    @Override
    public List<Customer> findAll() {
        Stream<DbRow> rows = this.dbClient.execute()
                .createQuery("SELECT * FROM customer")
                .execute();



        return List.of();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean save(Customer entity) {
        return false;
    }

    @Override
    public boolean update(Customer entity) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    private Book mapToBook(DbRow row) {
        Book.builder()
                .price(row.column("price").as(Double.class).get())
                .build();


        return new Book();
    }
}
