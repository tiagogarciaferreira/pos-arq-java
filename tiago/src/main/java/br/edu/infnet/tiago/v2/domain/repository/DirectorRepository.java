package br.edu.infnet.tiago.v2.domain.repository;

import br.edu.infnet.tiago.v2.domain.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long>, JpaSpecificationExecutor<Director> {
}