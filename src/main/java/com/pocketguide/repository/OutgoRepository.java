package com.pocketguide.repository;

import com.pocketguide.model.Outgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutgoRepository extends JpaRepository<Outgo, Long> {
}
