package com.example.autogaragebackend.repository;

import com.example.autogaragebackend.model.Afspraak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AfspraakRepository extends JpaRepository<Afspraak, Long> {
    public List<Afspraak> findAllByStatus(String status);

    @Query(value = "SELECT * FROM Afspraak a WHERE a.klant_id =:id",
            nativeQuery = true)
    public List<Afspraak> fetchByKlant(@Param("id") long id);

    @Query(value = "SELECT * FROM Afspraak a WHERE a.klant_id =:id and a.status=:status",
            nativeQuery = true)
    public List<Afspraak> fetchByKlantEnStatus(@Param("id") long id, @Param("status") String status);

}

