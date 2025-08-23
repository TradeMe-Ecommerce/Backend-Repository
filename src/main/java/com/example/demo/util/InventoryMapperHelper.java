package com.example.demo.util;

import com.example.demo.entity.Inventory;
import com.example.demo.repository.InventoryRepository;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InventoryMapperHelper {

    @Autowired
    private InventoryRepository inventoryRepository;


    public List<Long> map(Set<Inventory> inventories) {
        if (inventories == null) return null;
        return inventories.stream()
                .map(Inventory::getId)
                .collect(Collectors.toList());
    }

    public Set<Inventory> map(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(id ->
                        inventoryRepository.findById(id).orElseThrow( () ->
                                new RuntimeException("Inventory not found " + id))
                ).collect(Collectors.toSet());
    }
}
