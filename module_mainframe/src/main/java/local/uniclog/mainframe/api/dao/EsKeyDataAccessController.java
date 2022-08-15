package local.uniclog.mainframe.api.dao;

import local.uniclog.mainframe.dao.extensions.service_ekey.dto.EsKeyEntityDto;
import local.uniclog.mainframe.dao.extensions.service_ekey.service.EsKeyEntityDataAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Rest Api Controller for EsKey Data Access Service
 *
 * @author uniclog
 * @version 0.2
 */
@RestController
@RequestMapping("/api/EsKeyDataAccessController")
@RequiredArgsConstructor
public class EsKeyDataAccessController {
    private final EsKeyEntityDataAccessService service;

    /**
     * Save entity to database
     *
     * @param entity saved entity
     * @return entity
     */
    @PutMapping("/save")
    public ResponseEntity<EsKeyEntityDto> save(@RequestBody EsKeyEntityDto entity) {
        var transferObject = service.save(entity);
        if (transferObject == null)
            return ResponseEntity.internalServerError().build();
        else return ResponseEntity.ok().body(transferObject);
    }

    /**
     * Delete entity from database
     *
     * @param entity entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody EsKeyEntityDto entity) {
        return ResponseEntity.ok().body(service.delete(entity));
    }

    /**
     * Delete all entity
     */
    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll() {
        service.deleteAll();
    }

    /**
     * Delete by key value
     *
     * @param key key value
     * @return entity or null
     */
    @DeleteMapping("/deleteByKey/{key}")
    public ResponseEntity<EsKeyEntityDto> deleteByKey(@PathVariable String key) {
        var entity = service.deleteByKey(key);
        return (entity != null)
                ? ResponseEntity.ok().body(entity)
                : ResponseEntity.notFound().build();
    }

    /**
     * Find by key after date
     *
     * @param date date
     * @return list of entities
     */
    @PostMapping("/findByDateAfter")
    public ResponseEntity<List<EsKeyEntityDto>> findByDateAfter(@RequestBody LocalDateTime date) {
        var entityList = service.findByDateAfter(date);
        return (entityList != null)
                ? ResponseEntity.ok().body(entityList)
                : ResponseEntity.notFound().build();
    }

    /**
     * Find all entities
     *
     * @return List of entities
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<EsKeyEntityDto>> findAll() {
        var entityList = service.findAll();
        return (entityList != null)
                ? ResponseEntity.ok().body(entityList)
                : ResponseEntity.notFound().build();
    }

    /**
     * Get by key value
     *
     * @param key key value
     * @return EsKey entity
     */
    @GetMapping("/findByKey/{key}")
    public ResponseEntity<EsKeyEntityDto> findByKey(@PathVariable String key) {
        var entity = service.findByKey(key);
        return (entity != null)
                ? ResponseEntity.ok().body(entity)
                : ResponseEntity.notFound().build();
    }
}
