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

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestParam EsKeyEntity key) {
        service.save(key);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam EsKeyEntity entity) {
        service.delete(entity);
    }

    @GetMapping("/deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll() {
        service.deleteAll();
    }

    @GetMapping("/deleteByKey/{key}")
    public ResponseEntity<EsKeyEntity> deleteByKey(@PathVariable String key) {
        EsKeyEntity entity = service.deleteByKey(key);
        return (entity != null)
                ? new ResponseEntity<>(entity, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByDateAfter/{date}")
    public ResponseEntity<List<EsKeyEntity>> findByDateAfter(@PathVariable LocalDateTime date) {
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
    public ResponseEntity<EsKeyEntity> findByKey(@PathVariable String key) {
        EsKeyEntity entity = service.deleteByKey(key);
        return (entity != null)
                ? new ResponseEntity<>(entity, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
