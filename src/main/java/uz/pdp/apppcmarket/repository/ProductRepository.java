package uz.pdp.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import uz.pdp.apppcmarket.entity.Product;

import java.util.List;


public interface  ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByCategory_Id(Integer category_id);
}
