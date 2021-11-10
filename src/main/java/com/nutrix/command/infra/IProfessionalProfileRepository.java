package com.nutrix.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfessionalProfileRepository extends JpaRepository<ProfessionalProfile, Integer> {

}
