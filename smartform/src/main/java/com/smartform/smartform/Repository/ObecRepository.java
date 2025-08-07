package com.smartform.smartform.Repository;

import com.smartform.smartform.model.Obec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObecRepository extends JpaRepository<Obec, String> {
}
