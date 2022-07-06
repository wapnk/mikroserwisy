package app;

import app.util.Model;
import app.util.ModelQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("in")
public class CrudController {

    @Autowired
    private CrudService crudService;

    @PostMapping("create")
    public ResponseEntity<Model> create(@RequestBody Model model) {
        return crudService.create(model);
    }

    @PostMapping("read")
    public ResponseEntity<List<Model>> read(@RequestBody ModelQueryParams queryParams) {
        return crudService.read(queryParams);
    }

    @PutMapping("update")
    public ResponseEntity<Model> update(@RequestBody Model model) {
        return crudService.update(model);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Model> delete(@RequestBody Model model) {
        return crudService.delete(model);
    }
}
