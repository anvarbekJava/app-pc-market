package uz.pdp.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.apppcmarket.custom.CustomRole;
import uz.pdp.apppcmarket.entity.Role;
@RepositoryRestResource(path = "role", collectionResourceRel = "list", excerptProjection = CustomRole.class)
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
