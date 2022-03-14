package local.uniclog.mainframe.api.dao;

import local.uniclog.mainframe.dao.telegram.dto.TelegramTORGUserEntityDataTransferObject;
import local.uniclog.mainframe.dao.telegram.service.TelegramTORGUserEntityDataAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TelegramTORGUserDataAccessController")
@RequiredArgsConstructor
public class TelegramTORGUserDataAccessController {
    private final TelegramTORGUserEntityDataAccessService service;

    /**
     * Save entity to database
     *
     * @param entity saved entity
     * @return entity
     */
    @PutMapping("/save")
    public ResponseEntity<TelegramTORGUserEntityDataTransferObject> save(@RequestBody TelegramTORGUserEntityDataTransferObject entity) {
        var transferObject = service.save(entity);
        if (transferObject == null)
            return ResponseEntity.internalServerError().build();
        else return ResponseEntity.ok().body(transferObject);
    }

    /**
     * Update entity to database
     *
     * @param entity update entity
     * @return entity
     */
    @PatchMapping("/update")
    public ResponseEntity<TelegramTORGUserEntityDataTransferObject> update(@RequestBody TelegramTORGUserEntityDataTransferObject entity) {
        var transferObject = service.update(entity);
        if (transferObject == null)
            return ResponseEntity.internalServerError().build();
        else return ResponseEntity.ok().body(transferObject);
    }

    /**
     * Find entity by id
     *
     * @param id telegram id
     * @return entity
     */
    @GetMapping("/findByUserTelegramId/{id}")
    public ResponseEntity<TelegramTORGUserEntityDataTransferObject> findByUserTelegramId(@PathVariable Long id) {
        var transferObject = service.findByUserTelegramId(id);
        return (transferObject == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok().body(transferObject);
    }

    /**
     * Find all subscribers
     *
     * @return List of entities
     */
    @GetMapping("/findAllSubscribers")
    public ResponseEntity<List<TelegramTORGUserEntityDataTransferObject>> findAllSubscribers() {
        var transferObjectList = service.findAllSubscribers();
        return (transferObjectList.isEmpty())
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok().body(transferObjectList);
    }

    /**
     * Find all entities
     *
     * @return List of entities
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<TelegramTORGUserEntityDataTransferObject>> findAll() {
        var transferObjectList = service.findAll();
        return (transferObjectList.isEmpty())
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok().body(transferObjectList);
    }

    /**
     * Delete entity from database
     *
     * @param id telegram id
     * @return list of entities
     */
    @DeleteMapping("/deleteAllByUserTelegramId/{id}")
    public ResponseEntity<List<TelegramTORGUserEntityDataTransferObject>> deleteAllByUserTelegramId(@PathVariable Long id) {
        var transferObjectList = service.deleteAllByUserTelegramId(id);
        return (transferObjectList.isEmpty())
                ? ResponseEntity.internalServerError().build()
                : ResponseEntity.ok().body(transferObjectList);
    }
}
