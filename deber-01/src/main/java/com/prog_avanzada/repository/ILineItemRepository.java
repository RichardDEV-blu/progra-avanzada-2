package com.prog_avanzada.repository;

import com.prog_avanzada.model.LineItem;

import java.util.List;
import java.util.Optional;

public interface ILineItemRepository {

  List<LineItem> findAll();

  Optional<LineItem> findById(Long orderId, Integer idx);

  boolean save(LineItem entity);

  boolean update(LineItem entity);

  boolean deleteById(Long orderId, Integer idx);
}