package uz.pdp.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.apppcmarket.entity.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
}
