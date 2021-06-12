package agent.application.shoppingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agent.application.shoppingservice.model.ShoppingOrder;


@Repository
public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Integer> {

}
