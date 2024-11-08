package br.edu.infnet.tiago.v2.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PropertyReader {

    private final Environment environment;

    public String getOmdbApiKey() {
        return environment.getProperty("omdb.api.key", "9da844b2");
    }
}