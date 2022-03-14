package local.uniclog.mainframe.api.dao;

import local.uniclog.mainframe.dao.teamspeak.dto.TeamspeakUserEntityDataTransferObject;
import local.uniclog.mainframe.dao.teamspeak.service.TeamspeakUserEntityDataAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Api Controller for Teamspeak Data Access Service
 *
 * @author uniclog
 * @version 0.2
 */
@RestController
@RequestMapping("/api/TeamspeakUserDataAccessController")
@RequiredArgsConstructor
public class TeamspeakUserDataAccessController {
    private final TeamspeakUserEntityDataAccessService service;

    /**
     * Save entity to database
     *
     * @param entity saved entity
     * @return entity
     */
    @PutMapping("/save")
    public ResponseEntity<TeamspeakUserEntityDataTransferObject> save(@RequestBody TeamspeakUserEntityDataTransferObject entity) {
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
    public ResponseEntity<TeamspeakUserEntityDataTransferObject> update(@RequestBody TeamspeakUserEntityDataTransferObject entity) {
        var transferObject = service.update(entity);
        if (transferObject == null)
            return ResponseEntity.internalServerError().build();
        else return ResponseEntity.ok().body(transferObject);
    }

    /**
     * Find entity by token
     *
     * @param token teamspeak token
     * @return entity
     */
    @GetMapping("/findByTeamspeakToken/{token}")
    public ResponseEntity<TeamspeakUserEntityDataTransferObject> findByTeamspeakToken(@PathVariable String token) {
        var transferObject = service.findByTeamspeakToken(token);
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
    public ResponseEntity<List<TeamspeakUserEntityDataTransferObject>> findAllSubscribers() {
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
    public ResponseEntity<List<TeamspeakUserEntityDataTransferObject>> findAll() {
        var transferObjectList = service.findAll();
        return (transferObjectList.isEmpty())
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok().body(transferObjectList);
    }

    /**
     * Delete entity from database
     *
     * @param token teamspeak token
     * @return list of entities
     */
    @DeleteMapping("/deleteByTeamspeakToken/{token}")
    public ResponseEntity<List<TeamspeakUserEntityDataTransferObject>> deleteByTeamspeakToken(@PathVariable String token) {
        var transferObjectList = service.deleteByTeamspeakToken(token);
        return (transferObjectList.isEmpty())
                ? ResponseEntity.internalServerError().build()
                : ResponseEntity.ok().body(transferObjectList);
    }
}
