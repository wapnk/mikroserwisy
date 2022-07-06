package app;

import app.util.Model;
import app.util.ModelQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ex")
public class Controller {

    @Autowired
    private RestTemplate restTemplate;
    private static final String internalServerUrl = "http://gateway-service/in/";

    @PostMapping("create")
    public ResponseEntity<Model> create(@RequestBody Model model) {

        HttpEntity<Model> entity = new HttpEntity<>(model);
        return restTemplate.exchange(internalServerUrl.concat("create"), HttpMethod.POST, entity, Model.class);
    }

    @PostMapping("read")
    public ResponseEntity<List<Model>> read(@RequestBody ModelQueryParams params) {
        HttpEntity<ModelQueryParams> entity = new HttpEntity<>(params);
        List response = restTemplate.exchange(internalServerUrl.concat("read"), HttpMethod.POST, entity, List.class).getBody();
        return ResponseEntity.of(Optional.of(new ArrayList<>(response)));
    }

    @PutMapping("update")
    public ResponseEntity<Model> update(@RequestBody Model model) {

        HttpEntity<Model> entity = new HttpEntity<>(model);
        return restTemplate.exchange(internalServerUrl.concat("update"), HttpMethod.POST, entity, Model.class);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Model> delete(@RequestBody Model model) {
        HttpEntity<Model> entity = new HttpEntity<>(model);
        return restTemplate.exchange(internalServerUrl.concat("delete"), HttpMethod.POST, entity, Model.class);
    }


}
