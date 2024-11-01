package br.edu.infnet.tiago.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tb_directors")
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Director extends Person {

    @ElementCollection
    @CollectionTable(name = "tb_director_awards", joinColumns = @JoinColumn(name = "director_id"))
    @Column(name = "award_name")
    private List<String> awards;

    @OneToMany(mappedBy = "director")
    private List<Movie> movies;
}