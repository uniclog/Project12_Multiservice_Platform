package local.uniclog.mainframe.api.dao;

import local.uniclog.mainframe.dao.telegram.dto.TelegramTORGUserEntityDto;
import local.uniclog.mainframe.dao.telegram.service.TelegramTORGUserEntityDataAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Api Controller for Telegram TORG Data Access Service
 *
 * @author uniclog
 * @version 0.2
 */
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
    public ResponseEntity<TelegramTORGUserEntityDto> save(@RequestBody TelegramTORGUserEntityDto entity) {
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
    public ResponseEntity<TelegramTORGUserEntityDto> update(@RequestBody TelegramTORGUserEntityDto entity) {
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
    public ResponseEntity<TelegramTORGUserEntityDto> findByUserTelegramId(@PathVariable Long id) {
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
    public ResponseEntity<List<TelegramTORGUserEntityDto>> findAllSubscribers() {
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
    public ResponseEntity<List<TelegramTORGUserEntityDto>> findAll() {
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
    public ResponseEntity<List<TelegramTORGUserEntityDto>> deleteAllByUserTelegramId(@PathVariable Long id) {
        var transferObjectList = service.deleteAllByUserTelegramId(id);
        return (transferObjectList.isEmpty())
                ? ResponseEntity.internalServerError().build()
                : ResponseEntity.ok().body(transferObjectList);
    }
}
