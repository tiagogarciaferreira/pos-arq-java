package br.edu.infnet.tiago.v2.domain.service;


import br.edu.infnet.tiago.v2.domain.model.Actor;
import br.edu.infnet.tiago.v2.domain.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    @Transactional
    public Actor create(Actor actor) {
        return actorRepository.save(actor);
    }

    @Transactional(readOnly = true)
    public Page<Actor> search(Specification<Actor> specification, Pageable pageable) {
        return actorRepository.findAll(specification, pageable);
    }

    @Transactional(readOnly = true)
    public Actor getById(Long actorId) {
        return actorRepository.findById(actorId).get();
    }

    @Transactional
    public Actor update(Actor actor) {
        return actorRepository.save(actor);
    }

    @Transactional
    public void delete(Long actorId) {
        actorRepository.deleteById(actorId);
    }
}