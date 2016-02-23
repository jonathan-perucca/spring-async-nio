package com.jperucca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public DeferredResult getPublicUsers() {
        final DeferredResult<List> result = new DeferredResult<>();

        CompletableFuture
                .supplyAsync(userRepository::findAllBy)
                .thenApply(this::filterPublicUsers)
                .whenComplete((users, throwable) -> feedPromise(result, users));


        return result;
    }

    private boolean feedPromise(DeferredResult<List> result, CompletableFuture<List<User>> users) {
        return result.setResult(users.getNow(emptyList()));
    }

    private CompletableFuture<List<User>> filterPublicUsers(CompletableFuture<List<User>> promise) {
        return promise.thenApply(this::feedFilteredPublicUsers);
    }

    private List<User> feedFilteredPublicUsers(List<User> users) {
        return users.stream().filter(User::isPublic).collect(toList());
    }
}
