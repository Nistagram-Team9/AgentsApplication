package agent.application.agentreports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agent.application.agentreports.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
