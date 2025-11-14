package com.progAvanzada.conf;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
@ApplicationScoped
public class DBConnection {

  @Produces
  @ApplicationScoped
  public DataSource dataSource(){
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl("jdbc:postgresql://localhost:5433/prograavanzada");
    config.setUsername("postgres");
    config.setPassword("labcom,2015");
    return new HikariDataSource(config);
  }

}

