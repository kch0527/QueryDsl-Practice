package querydsl.example.querydsl.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "school")
    private List<Student> student = new ArrayList<>();

    @Builder
    public School(String name) {
        this.name = name;
    }
}
