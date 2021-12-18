package local.uniclog.mainframe.api.dao;

import local.uniclog.mainframe.dao.extensions.service_ekey.entity.EsKeyEntity;
import local.uniclog.mainframe.dao.extensions.service_ekey.service.EsKeyEntityDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EsKeyDataAccessController {
    private final EsKeyEntityDataService service;

    @PutMapping("/save")
    public ResponseEntity<EsKeyEntity> save(@RequestBody EsKeyEntity entity) {
        if ((entity = service.save(entity)) != null)
            return new ResponseEntity<>(entity, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody EsKeyEntity entity) {
        service.delete(entity);
    }

    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll() {
        service.deleteAll();
    }

    @DeleteMapping("/deleteByKey/{entity}")
    public ResponseEntity<EsKeyEntity> deleteByKey(@PathVariable("entity") String key) {
        EsKeyEntity entity = service.deleteByKey(key);
        return (entity != null)
                ? new ResponseEntity<>(entity, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/findByDateAfter")
    public ResponseEntity<List<EsKeyEntity>> findByDateAfter(@RequestBody LocalDateTime date) {
        List<EsKeyEntity> entityList = service.findByDateAfter(date);
        return (entityList != null)
                ? new ResponseEntity<>(entityList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<EsKeyEntity>> findAll() {
        List<EsKeyEntity> entityList = service.findAll();
        return (entityList != null)
                ? new ResponseEntity<>(entityList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByKey/{key}")
    public ResponseEntity<EsKeyEntity> findByKey(@PathVariable("key") String key) {
        EsKeyEntity entity = service.findByKey(key);
        return (entity != null)
                ? new ResponseEntity<>(entity, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
