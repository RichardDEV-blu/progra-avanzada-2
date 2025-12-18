package com.programacion.avanzada;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainJpa01 {
  public static void main(String[] args) {
    try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("avanzada")) {


    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
