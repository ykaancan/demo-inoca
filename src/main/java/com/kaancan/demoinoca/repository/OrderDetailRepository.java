package com.kaancan.demoinoca.repository;

import com.kaancan.demoinoca.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
}
