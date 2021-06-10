package agent.application.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import agent.application.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
