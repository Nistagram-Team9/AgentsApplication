package agent.application.agentreports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agent.application.agentreports.model.ShoppingOrder;

@Repository
public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Integer> {

}