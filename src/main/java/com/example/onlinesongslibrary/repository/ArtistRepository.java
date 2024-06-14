package com.example.onlinesongslibrary.repository;

import com.example.onlinesongslibrary.model.Artist.Artist;
import com.example.onlinesongslibrary.web.dto.SpecificArtistResponseDto;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{

    @Query("SELECT a FROM Artist a WHERE a.nationality = :nationality AND a.dateOfBirth < :date")
    List<Artist> findAllByNationalityAndDateOfBirthBefore(@Param("nationality") String nationality, @Param("date") Date date);

    Artist findByName(String name);
}
