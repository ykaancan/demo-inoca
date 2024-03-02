package com.kaancan.demoinoca.repository;

import com.kaancan.demoinoca.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findAllByCustomerId(UUID customerId);
}
