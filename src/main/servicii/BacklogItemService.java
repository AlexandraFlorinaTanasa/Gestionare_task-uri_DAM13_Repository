package org.gestionare_taskuri.servicii;

import org.gestionare_taskuri.repository.BacklogItemRepository;
import org.gestionare_taskuri.task.BacklogItem;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class BacklogItemService {

    private final BacklogItemRepository backlogItemRepository;

    @Autowired
    public BacklogItemService(BacklogItemRepository backlogItemRepository) {
        this.backlogItemRepository = backlogItemRepository;
    }

    // Obține toate elementele backlog
    public List<BacklogItem> getAllBacklogItems() {
        return backlogItemRepository.findAll();
    }

    // Obține un element backlog după ID
    public BacklogItem getBacklogItemById(Integer id) {
        Optional<BacklogItem> backlogItem = backlogItemRepository.findById(id.longValue());
        return backlogItem.orElseThrow(() -> new RuntimeException("BacklogItem not found with id: " + id));
    }

    // Adaugă un nou element backlog
    public BacklogItem addBacklogItem(BacklogItem backlogItem) {
        return backlogItemRepository.save(backlogItem);
    }

    // Actualizează un element backlog existent
    public BacklogItem updateBacklogItem(Integer id, BacklogItem backlogItem) {
        if (!backlogItemRepository.existsById(id.longValue())) {
            throw new RuntimeException("BacklogItem not found with id: " + id);
        }
        backlogItem.setId((int) id.longValue()); // Setăm ID-ul pentru a actualiza elementul existent
        return backlogItemRepository.save(backlogItem);
    }

    // Șterge un element backlog după ID
    public void deleteBacklogItem(Integer id) {
        if (!backlogItemRepository.existsById(id.longValue())) {
            throw new RuntimeException("BacklogItem not found with id: " + id);
        }
        backlogItemRepository.deleteById(id.longValue());
    }
}
