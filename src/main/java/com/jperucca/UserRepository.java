package com.jperucca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Async
    CompletableFuture<List<User>> findAllBy();
}
