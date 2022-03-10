package kz.iitu.kettik.authentication.entities;

import kz.iitu.kettik.interfaces.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@Table(name = "roles")
@NoArgsConstructor(force = true)
public class Role extends AbstractEntity {

    @Column
    private String name;

}
