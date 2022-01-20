package local.uniclog.mainframe.api.dao;

import local.uniclog.mainframe.dao.teamspeak.dto.TeamspeakUserEntityDataTransferObject;
import local.uniclog.mainframe.dao.teamspeak.service.TeamspeakUserEntityDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/TeamspeakUserDataAccessController")
@RequiredArgsConstructor
public class TeamspeakUserDataAccessController {
    private final TeamspeakUserEntityDataService service;

    /**
     * Save entity to database
     *
     * @param entity saved entity
     * @return entity
     */
    @PutMapping("/save")
    public ResponseEntity<TeamspeakUserEntityDataTransferObject> save(@RequestBody TeamspeakUserEntityDataTransferObject entity) {
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
    public ResponseEntity<TeamspeakUserEntityDataTransferObject> update(@RequestBody TeamspeakUserEntityDataTransferObject entity) {
        var teamspeakUserEntity = service.convertFromDataTransferObject(entity);
        if ((teamspeakUserEntity = service.update(teamspeakUserEntity)) != null)
            return ResponseEntity.ok().body(service.convertToDataTransferObject(teamspeakUserEntity));
        else return ResponseEntity.internalServerError().build();
    }

    /**
     * Find entity by token
     *
     * @param token teamspeak token
     * @return entity
     */
    @GetMapping("/findByTeamspeakToken")
    public ResponseEntity<TeamspeakUserEntityDataTransferObject> findByTeamspeakToken(@RequestParam String token) {
        var entity = service.findByTeamspeakToken(token);
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
    public ResponseEntity<List<TeamspeakUserEntityDataTransferObject>> findAllSubscribers() {
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
    public ResponseEntity<List<TeamspeakUserEntityDataTransferObject>> findAll() {
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
     * @param token teamspeak token
     * @return list of entities
     */
    @DeleteMapping("/deleteByTeamspeakToken")
    public ResponseEntity<List<TeamspeakUserEntityDataTransferObject>> deleteByTeamspeakToken(@RequestBody String token) {
        var dtoList = service.deleteByTeamspeakToken(token).stream()
                .map(service::convertToDataTransferObject)
                .collect(Collectors.toList());
        return (dtoList.isEmpty())
                ? ResponseEntity.internalServerError().build()
                : ResponseEntity.ok().body(dtoList);
    }
}
