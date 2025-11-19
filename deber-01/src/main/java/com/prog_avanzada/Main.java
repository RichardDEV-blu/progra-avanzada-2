package com.prog_avanzada;

import com.prog_avanzada.model.*;
import com.prog_avanzada.repository.*;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Main {
  public static void main(String[] args) {

    try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {

      ICustomerRepository customerRepository = container.select(ICustomerRepository.class).get();
      IPurchaseOrderRepository orderRepository = container.select(IPurchaseOrderRepository.class).get();
      IBookRepository bookRepository = container.select(IBookRepository.class).get();
      ILineItemRepository lineItemRepo = container.select(ILineItemRepository.class).get();
      IInventoryRepository inventoryRepo = container.select(IInventoryRepository.class).get();
      IAuthorRepository authorRepo = container.select(IAuthorRepository.class).get();
      IBookAuthorRepository bookAuthorRepo = container.select(IBookAuthorRepository.class).get();

      // ! Guardar customer
//      Customer newCustomer = Customer.builder()
//          .name("Juan Perez")
//          .email("juan@google.com")
//          .version(1)
//          .build();
//      boolean customerSaved = customerRepository.save(newCustomer);
//      System.out.println("Customer guardado: " + customerSaved);

      // ! Buscar customer por id
      Optional<Customer> customer = customerRepository.findById(10L);

      System.out.println("Customer encontrado: " + customer);

      // ! 3. Actualizar customer
//      boolean updateResult = customerRepository.update(
//          Customer.builder()
//              .id(10L)
//              .name("Juan Perez Actualizado")
//              .email("jaunactualiado@google.com")
//              .version(2)
//              .build()
//      );
//      System.out.println("Customer actualizado: " + updateResult);

      // ! 4. Obtener todos los customers
      List<Customer> customers = customerRepository.findAll();

      System.out.println(customer);

      // ! 1. Guardar Orden
//      PurchaseOrder newOrder = PurchaseOrder.builder()
//              .placedOn(LocalDateTime.now())
//              .deliveredOn(null)
//              .status(1)
//              .total(250)
//              .customerId(10L)
//              .build();

//       boolean orderSaved = orderRepository.save(newOrder);
//       System.out.println("Orden guardada: " + orderSaved);


      // ! 2. Buscar Orden por id

      Optional<PurchaseOrder> order = orderRepository.findById(1L);

      System.out.println("Orden encontrada: " + order);

      // ! 3. Actualizar Orden
//      boolean orderUpdated = orderRepository.update(
//          PurchaseOrder.builder()
//              .id(1L)
//              .placedOn(LocalDateTime.now())
//              .deliveredOn(LocalDateTime.now())
//              .status(2)
//              .total(250)
//              .customerId(10L)
//              .build()
//      );

//      System.out.println("Orden actualizada: " + orderUpdated);


      // ! 4. Eliminar Orden
//      boolean orderDeleted = orderRepository.deleteById(1L);
//      System.out.println("Orden eliminada: " + orderDeleted);


      // ! 5. Obtener todas las órdenes
      List<PurchaseOrder> allOrders = orderRepository.findAll();

      allOrders.forEach(System.out::println);

      // ! libro
//      Book newBook = Book.builder()
//              .isbn("ISBN-999")
//              .title("El Arte de la Programacion")
//              .price(59.99)
//              .version(1)
//              .build();

//      boolean wasSaved = bookRepository.save(newBook);
//      System.out.println("Libro guardado: " + wasSaved);

      Optional<Book> foundBook = bookRepository.findById("ISBN-999");

      System.out.println("Libro encontrado: " + foundBook);

      // ! line item
//      LineItem item = LineItem.builder()
//              .orderId(1L)
//              .idx(1)
//              .bookIsbn("ISBN-999")
//              .quantity(3)
//              .build();

      // ! Guardar
//      boolean guardado = lineItemRepo.save(item);
//      System.out.println("LineItem guardado: " + guardado);

      Optional<LineItem> itemFound = lineItemRepo.findById(1L, 1);
      System.out.println(itemFound);


      // ! Inventory

//      Inventory inv = Inventory.builder()
//              .bookIsbn("ISBN-999")
//              .supplied(100)
//              .sold(5)
//              .version(1)
//              .build();

//      boolean saved = inventoryRepo.save(inv);
//      System.out.println("Inventory guardado: " + saved);

      Optional<Inventory> foundInv = inventoryRepo.findById("ISBN-999");
      System.out.println(foundInv);


      // ! Author
//      Author newAuthor = Author.builder()
//              .name("Stephen King")
//              .version(1)
//              .build();

      // ! Guardar
//      boolean saved = authorRepo.save(newAuthor);
//      System.out.println("Autor guardado: " + saved);

      Optional<Author> authorId1 = authorRepo.findById(1L);
      System.out.println("Autor encontrado: " + authorId1);

      // ! BookAuthor
//      BookAuthor bookAuthor = BookAuthor.builder()
//              .bookIsbn("ISBN-999")
//              .authorId(1L)
//              .build();
//
//      boolean saved = bookAuthorRepo.save(bookAuthor);
//      System.out.println("fue creado: " + saved);

      Optional<BookAuthor> found = bookAuthorRepo.findById("ISBN-999", 1L);
      System.out.println("found " + found);
    }
  }
}