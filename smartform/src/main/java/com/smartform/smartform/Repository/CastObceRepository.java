package com.smartform.smartform.Repository;

import com.smartform.smartform.model.CastObce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastObceRepository extends JpaRepository<CastObce, String> {
}
