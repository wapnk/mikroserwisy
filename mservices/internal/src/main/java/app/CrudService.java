package app;

import app.util.Model;
import app.util.ModelQueryParams;
import app.util.ModelSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CrudService {

    @Value("${app.default.page.size}")
    private int pageSize;
    @Value("${app.default.order.column}")
    private String defaultOrderColumn;

    private final ModelRepository modelRepository;


    @Autowired
    public CrudService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public ResponseEntity<Model> create(Model model) {
        if (model.getDataDodania() == null) {
            model.setDataDodania(LocalDateTime.now().toString());
        }
        return ResponseEntity.of(Optional.of(modelRepository.save(model)));
    }

    public ResponseEntity<Model> update(Model model) {
        if (model.getDataAktualizacji() == null) {
            model.setDataAktualizacji(LocalDateTime.now().toString());
        }
        return ResponseEntity.of(Optional.of(modelRepository.save(model)));
    }

    public ResponseEntity<Model> delete(Model model) {
        modelRepository.delete(model);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<Model>> read(ModelQueryParams queryParams) {

        Specification<Model> specification = new ModelSpecification(queryParams);
        if (queryParams.getOrderBy() == null) {
            queryParams.setOrderBy(new String[]{defaultOrderColumn});
        }

        int pgs = queryParams.getPageSize() == null ? pageSize : queryParams.getPageSize();
        Pageable pageable = PageRequest.of(queryParams.getPageNumber(), pgs, Sort.by(queryParams.getOrderBy()));
        Page<Model> page = modelRepository.findAll(specification, pageable);
        return ResponseEntity.of(Optional.of(page.getContent()));
    }
}
