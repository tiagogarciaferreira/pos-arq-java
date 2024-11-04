package br.edu.infnet.tiago.infrastructure.repository;

import br.edu.infnet.tiago.domain.model.Studio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class StudioRepositoryImpl implements StudioRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Page<Studio> find(Specification<Studio> specification, Pageable pageable) {
        return null;
    }
}