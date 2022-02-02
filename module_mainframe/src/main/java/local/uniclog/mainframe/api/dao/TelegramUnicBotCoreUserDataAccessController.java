package local.uniclog.mainframe.api.dao;

import local.uniclog.mainframe.dao.telegram.dto.TelegramUnicBotCoreUserEntityDataTransferObject;
import local.uniclog.mainframe.dao.telegram.service.TelegramUnicBotCoreUserEntityDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/TelegramUnicBotCoreUserDataAccessController")
@RequiredArgsConstructor
public class TelegramUnicBotCoreUserDataAccessController {
    private final TelegramUnicBotCoreUserEntityDataService service;

    /**
     * Save entity to database
     *
     * @param entity saved entity
     * @return entity
     */
    @PutMapping("/save")
    public ResponseEntity<TelegramUnicBotCoreUserEntityDataTransferObject> save(@RequestBody TelegramUnicBotCoreUserEntityDataTransferObject entity) {
        var teamspeakUserEntity = service.convertFromDataTransferObject(entity);
        if ((teamspeakUserEntity = service.save(teamspeakUserEntity)) != null)
            return ResponseEntity.ok().body(service.convertToDataTransferObject(teamspeakUserEntity));
        else return ResponseEntity.internalServerError().build();
    }

    /**
     * Update entity to database
     *
     * @param entity update entity
     * @return entity
     */
    @PatchMapping("/update")
    public ResponseEntity<TelegramUnicBotCoreUserEntityDataTransferObject> update(@RequestBody TelegramUnicBotCoreUserEntityDataTransferObject entity) {
        var teamspeakUserEntity = service.convertFromDataTransferObject(entity);
        if ((teamspeakUserEntity = service.update(teamspeakUserEntity)) != null)
            return ResponseEntity.ok().body(service.convertToDataTransferObject(teamspeakUserEntity));
        else return ResponseEntity.internalServerError().build();
    }

    /**
     * Find entity by id
     *
     * @param id telegram id
     * @return entity
     */
    @GetMapping("/findByUserTelegramId")
    public ResponseEntity<TelegramUnicBotCoreUserEntityDataTransferObject> findByUserTelegramId(@RequestParam Long id) {
        var entity = service.findByUserTelegramId(id);
        var dto = service.convertToDataTransferObject(entity);
        return (dto == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok().body(dto);
    }

    /**
     * Find all subscribers
     *
     * @return List of entities
     */
    @GetMapping("/findAllSubscribers")
    public ResponseEntity<List<TelegramUnicBotCoreUserEntityDataTransferObject>> findAllSubscribers() {
        var dtoList = service.findAllSubscribers().stream()
                .map(service::convertToDataTransferObject)
                .collect(Collectors.toList());
        return (dtoList.isEmpty())
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok().body(dtoList);
    }

    /**
     * Find all entities
     *
     * @return List of entities
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<TelegramUnicBotCoreUserEntityDataTransferObject>> findAll() {
        var dtoList = service.findAll().stream()
                .map(service::convertToDataTransferObject)
                .collect(Collectors.toList());
        return (dtoList.isEmpty())
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok().body(dtoList);
    }

    /**
     * Delete entity from database
     *
     * @param id telegram id
     * @return list of entities
     */
    @DeleteMapping("/deleteAllByUserTelegramId")
    public ResponseEntity<List<TelegramUnicBotCoreUserEntityDataTransferObject>> deleteAllByUserTelegramId(@RequestBody Long id) {
        var dtoList = service.deleteAllByUserTelegramId(id).stream()
                .map(service::convertToDataTransferObject)
                .collect(Collectors.toList());
        return (dtoList.isEmpty())
                ? ResponseEntity.internalServerError().build()
                : ResponseEntity.ok().body(dtoList);
    }
}
