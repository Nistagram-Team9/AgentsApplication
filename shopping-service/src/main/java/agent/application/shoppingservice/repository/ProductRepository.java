package agent.application.shoppingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agent.application.shoppingservice.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
