package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.model.Studio;
import br.edu.infnet.tiago.domain.repository.CountryRepository;
import br.edu.infnet.tiago.domain.repository.StudioRepository;
import br.edu.infnet.tiago.infrastructure.exception.custom.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class StudioService {

    private final StudioRepository studioRepository;

    private final CountryRepository countryRepository;

    @Transactional
    public Studio create(Studio studio) {
        studio = studioRepository.save(studio);
        return getById(studio.getId());
    }

    @Transactional(readOnly = true)
    public Page<Studio> search(Specification<Studio> specification, Pageable pageable) {
        return studioRepository.findAll(specification, pageable);
    }

    @Transactional(readOnly = true)
    public Studio getById(Long studioId) {
        return studioRepository.findById(studioId)
                .orElseThrow(() -> new NotFoundException(format("Studio '%s' not found", studioId)));
    }

    @Transactional
    public Studio update(Long studioId, Studio studio) {
        var existingStudio = getById(studioId).withCountry(studio.getCountry()).withName(studio.getName()).withVersion(studio.getVersion());
        existingStudio = studioRepository.save(existingStudio);
        return getById(existingStudio.getId());
    }

    @Transactional
    public void delete(Long studioId) {
        if (!exists(studioId)) throw new NotFoundException(format("Studio '%s' not found", studioId));
        studioRepository.deleteById(studioId);
    }

    @Transactional(readOnly = true)
    public boolean exists(Long studioId) {
        return studioRepository.existsById(studioId);
    }
}