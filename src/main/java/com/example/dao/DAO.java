package com.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public interface DAO {
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;
}
