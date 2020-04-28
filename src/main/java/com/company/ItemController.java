package com.company;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@RestController
class ItemController {

    private final ItemRepository repository;

    ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/items")
    List<Item> all() {
        return repository.findAll();
    }

    @PostMapping("/items")
    Item newItem(@RequestBody Item newItem) {
        return repository.save(newItem);
    }

    // Single item

    @GetMapping("/items/{id}")
    Item one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException());
    }

    @PutMapping("/items/{id}")
    Item replaceItem(@RequestBody Item newItem, @PathVariable Long id) {

        return repository.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    item.setStatus(newItem.getStatus());
                    return repository.save(item);
                })
                .orElseGet(() -> {
                    newItem.setId(id);
                    return repository.save(newItem);
                });

    }

    @DeleteMapping("/items/{id}")
    void deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
    }
}