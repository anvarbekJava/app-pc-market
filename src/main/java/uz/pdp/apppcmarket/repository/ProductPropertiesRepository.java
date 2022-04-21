package uz.pdp.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.apppcmarket.entity.ProductProperties;
@Repository
public interface ProductPropertiesRepository extends JpaRepository<ProductProperties, Integer> {
        boolean existsByKeyAndValueAndProduct_Id(String key, String value, Integer product_id);
}
