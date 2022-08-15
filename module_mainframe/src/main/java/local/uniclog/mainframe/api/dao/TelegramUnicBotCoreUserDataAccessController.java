package local.uniclog.mainframe.api.dao;

import local.uniclog.mainframe.dao.telegram.dto.TelegramUnicBotCoreUserEntityDto;
import local.uniclog.mainframe.dao.telegram.service.TelegramUnicBotCoreUserEntityDataAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Api Controller for Telegram UnicBotCore Data Access Service
 *
 * @author uniclog
 * @version 0.2
 */
@RestController
@RequestMapping("/api/TelegramUnicBotCoreUserDataAccessController")
@RequiredArgsConstructor
public class TelegramUnicBotCoreUserDataAccessController {
    private final TelegramUnicBotCoreUserEntityDataAccessService service;

    /**
     * Save entity to database
     *
     * @param entity saved entity
     * @return entity
     */
    @PutMapping("/save")
    public ResponseEntity<TelegramUnicBotCoreUserEntityDto> save(@RequestBody TelegramUnicBotCoreUserEntityDto entity) {
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
    public ResponseEntity<TelegramUnicBotCoreUserEntityDto> update(@RequestBody TelegramUnicBotCoreUserEntityDto entity) {
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
    public ResponseEntity<TelegramUnicBotCoreUserEntityDto> findByUserTelegramId(@PathVariable Long id) {
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
    public ResponseEntity<List<TelegramUnicBotCoreUserEntityDto>> findAllSubscribers() {
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
    public ResponseEntity<List<TelegramUnicBotCoreUserEntityDto>> findAll() {
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
    public ResponseEntity<List<TelegramUnicBotCoreUserEntityDto>> deleteAllByUserTelegramId(@PathVariable Long id) {
        var transferObjectList = service.deleteAllByUserTelegramId(id);
        return (transferObjectList.isEmpty())
                ? ResponseEntity.internalServerError().build()
                : ResponseEntity.ok().body(transferObjectList);
    }
}
