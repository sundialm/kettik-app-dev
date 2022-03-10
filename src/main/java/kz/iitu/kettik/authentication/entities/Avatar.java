package kz.iitu.kettik.authentication.entities;

import kz.iitu.kettik.interfaces.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Table(name = "images")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Avatar extends AbstractEntity {

    @Column
    @NotNull
    private String filePath;

    @NotNull
    @OneToOne
    private User user;
}
