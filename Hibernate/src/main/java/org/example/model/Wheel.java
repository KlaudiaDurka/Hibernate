package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity // ta klasa bedzie modelem na podstawie ktorego hibernate stworzy nam tabele
@Getter // generuje gettery
@Setter // generuje settery
@NoArgsConstructor // dodaje konstruktor bezargumentowy
// @Table(name = "Koło") tak można np zmienic nazwe tabeli
// (hibernate nie zmieni doslownie nazwy, a doda taka sama tabele pod inna nazwa)
public class Wheel {

    @Id // oznacza co jest kluczem głównym
    @GeneratedValue(strategy = GenerationType.IDENTITY) // wartosc bedzie generowana przez baze danych (id = 1, 2...)
    private Long id;
    private String name;
    @Column(name = "ROZMIAR")
    private Integer size;
    private String type;


    public Wheel(String name, Integer size, String type) {
        this.name = name;
        this.size = size;
        this.type = type;
    }
}
