package kz.iitu.kettik.authentication.repositories;

import kz.iitu.kettik.authentication.entities.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Integer>, JpaSpecificationExecutor<Avatar> {
}
