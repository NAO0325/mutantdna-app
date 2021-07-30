package com.mutant.dna.repository;


import com.mutant.dna.model.DnaRead;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DnaReadRepo extends JpaRepository<DnaRead, Long> {

    @Query("SELECT d FROM DnaRead d WHERE d.isMutant = ?1 ")
    List<DnaRead> findAllByType(boolean isMutant);

}
