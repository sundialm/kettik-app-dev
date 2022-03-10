package kz.iitu.kettik.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter @Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractDTO implements Serializable {
    private int id;
}
