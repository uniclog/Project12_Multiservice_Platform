package local.uniclog.mainframe.api.dao;

import local.uniclog.mainframe.dao.telegram.dto.TelegramMyFitnessUserEntityDataTransferObject;
import local.uniclog.mainframe.dao.telegram.service.TelegramMyFitnessUserEntityDataAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TelegramMyFitnessUserDataAccessController")
@RequiredArgsConstructor
public class TelegramMyFitnessUserDataAccessController {
    private final TelegramMyFitnessUserEntityDataAccessService service;

    /**
     * Save entity to database
     *
     * @param entity saved entity
     * @return entity
     */
    @PutMapping("/save")
    public ResponseEntity<TelegramMyFitnessUserEntityDataTransferObject> save(@RequestBody TelegramMyFitnessUserEntityDataTransferObject entity) {
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
    public ResponseEntity<TelegramMyFitnessUserEntityDataTransferObject> update(@RequestBody TelegramMyFitnessUserEntityDataTransferObject entity) {
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
    public ResponseEntity<TelegramMyFitnessUserEntityDataTransferObject> findByUserTelegramId(@PathVariable Long id) {
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
    public ResponseEntity<List<TelegramMyFitnessUserEntityDataTransferObject>> findAllSubscribers() {
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
    public ResponseEntity<List<TelegramMyFitnessUserEntityDataTransferObject>> findAll() {
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
    public ResponseEntity<List<TelegramMyFitnessUserEntityDataTransferObject>> deleteAllByUserTelegramId(@PathVariable Long id) {
        var transferObjectList = service.deleteAllByUserTelegramId(id);
        return (transferObjectList.isEmpty())
                ? ResponseEntity.internalServerError().build()
                : ResponseEntity.ok().body(transferObjectList);
    }
}
