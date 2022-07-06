package app;


import app.util.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends CrudRepository<Model,Integer>, JpaSpecificationExecutor<Model> {

    @Override
    List<Model> findAll(Specification<Model> spec);

    @Override
    Page<Model> findAll(Specification<Model> spec, Pageable pageable);
}
