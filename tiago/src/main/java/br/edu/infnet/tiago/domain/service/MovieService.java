package br.edu.infnet.tiago.domain.service;


import br.edu.infnet.tiago.domain.model.Movie;
import br.edu.infnet.tiago.domain.repository.MovieRepository;
import br.edu.infnet.tiago.infrastructure.exception.custom.NotFoundException;
import br.edu.infnet.tiago.infrastructure.external.omdb.OmdbApiService;
import br.edu.infnet.tiago.infrastructure.external.omdb.OmdbMovie;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    private final OmdbApiService omdbApiService;

    @Transactional
    public Movie create(Movie movie) {
        OmdbMovie omdbMovie = omdbApiService.findByImdbId(movie.getImdbId());
        movie = movie.withPoster(omdbMovie.getPoster())
                .withImdbRating(omdbMovie.getImdbRating())
                .withReleaseDate(omdbMovie.getReleaseDate())
                .withDurationMinutes(omdbMovie.getDurationMinutes())
                .withBoxOfficeDollars(omdbMovie.getBoxOfficeDollars());
        movie = movieRepository.save(movie);
        return getById(movie.getId());
    }

    @Transactional(readOnly = true)
    public Page<Movie> search(Specification<Movie> specification, Pageable pageable) {
        return movieRepository.findAll(specification, pageable);
    }

    @Transactional(readOnly = true)
    public Movie getById(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new NotFoundException(format("Movie '%s' not found", movieId)));
    }

    @Transactional
    public Movie update(Long movieId, Movie movie) {
        var existingMovie = getById(movieId);

        if (!existingMovie.getImdbId().equalsIgnoreCase(movie.getImdbId())) {
            OmdbMovie omdbMovie = omdbApiService.findByImdbId(movie.getImdbId());
            movie = movie.withPoster(omdbMovie.getPoster())
                    .withImdbRating(omdbMovie.getImdbRating())
                    .withReleaseDate(omdbMovie.getReleaseDate())
                    .withDurationMinutes(omdbMovie.getDurationMinutes())
                    .withBoxOfficeDollars(omdbMovie.getBoxOfficeDollars());
        }
        existingMovie = existingMovie.withImdbId(movie.getImdbId())
                .withTitle(movie.getTitle())
                .withSynopsis(movie.getSynopsis())
                .withCountry(movie.getCountry())
                .withActors(movie.getActors())
                .withStudio(movie.getStudio())
                .withDirector(movie.getDirector())
                .withBoxOfficeDollars(movie.getBoxOfficeDollars())
                .withBudgetDollars(movie.getBudgetDollars())
                .withReleaseDate(movie.getReleaseDate())
                .withDurationMinutes(movie.getDurationMinutes())
                .withGenres(movie.getGenres())
                .withImdbRating(movie.getImdbRating())
                .withPoster(movie.getPoster())
                .withLanguages(movie.getLanguages())
                .withSubtitles(movie.getSubtitles())
                .withVersion(movie.getVersion());
        existingMovie = movieRepository.save(existingMovie);
        return getById(existingMovie.getId());
    }

    @Transactional
    public void delete(Long movieId) {
        if (!exists(movieId)) throw new NotFoundException(format("Movie '%s' not found", movieId));
        movieRepository.deleteById(movieId);
    }

    @Transactional(readOnly = true)
    public boolean exists(Long movieId) {
        return movieRepository.existsById(movieId);
    }
}