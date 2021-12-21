package local.uniclog.mainframe.api.dao;

import local.uniclog.mainframe.dao.extensions.service_ekey.entity.EsKeyEntity;
import local.uniclog.mainframe.dao.extensions.service_ekey.service.EsKeyEntityDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Rest Api Controller for EsKey Service
 */

@RestController
@RequestMapping("/api/EsKeyDataAccessController")
@RequiredArgsConstructor
public class EsKeyDataAccessController {
    private final EsKeyEntityDataService service;

    /**
     * Save entity to database
     * @param entity saved entity
     * @return entity
     */
    @PutMapping("/save")
    public ResponseEntity<EsKeyEntity> save(@RequestBody EsKeyEntity entity) {
        if ((entity = service.save(entity)) != null)
            return new ResponseEntity<>(entity, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Delete entity from database
     * @param entity entity
     */
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody EsKeyEntity entity) {
        service.delete(entity);
    }

    /**
     * Delete entity
     */
    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll() {
        service.deleteAll();
    }

    /**
     * Delete by key value
     * @param key key value
     * @return entity or null
     */
    @DeleteMapping("/deleteByKey/{entity}")
    public ResponseEntity<EsKeyEntity> deleteByKey(@PathVariable("entity") String key) {
        var entity = service.deleteByKey(key);
        return (entity != null)
                ? new ResponseEntity<>(entity, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Find by key after date
     * @param date date
     * @return list of entities
     */
    @PostMapping("/findByDateAfter")
    public ResponseEntity<List<EsKeyEntity>> findByDateAfter(@RequestBody LocalDateTime date) {
        var entityList = service.findByDateAfter(date);
        return (entityList != null)
                ? new ResponseEntity<>(entityList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Find all entities
     * @return List of entities
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<EsKeyEntity>> findAll() {
        var entityList = service.findAll();
        return (entityList != null)
                ? new ResponseEntity<>(entityList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Get by key value
     * @param key key value
     * @return EsKey entity
     */
    @GetMapping("/findByKey/{key}")
    public ResponseEntity<EsKeyEntity> findByKey(@PathVariable("key") String key) {
        var entity = service.findByKey(key);
        return (entity != null)
                ? new ResponseEntity<>(entity, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
