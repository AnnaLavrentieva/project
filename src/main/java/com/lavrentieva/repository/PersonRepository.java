package com.lavrentieva.repository;

import com.lavrentieva.dto.AnalysisDtoItemsByPerson;
import com.lavrentieva.dto.AnalysisDtoItemsByPersonItemize;
import com.lavrentieva.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

    @Query(value = "SELECT person_id FROM persons", nativeQuery = true)
    List<String> getAllId();


    public Optional<Person> getByNameIgnoreCase(final String name);

    @Modifying
    public void deleteByNameIgnoreCase(final String name);

    @Query("SELECT new com.lavrentieva.dto.AnalysisDtoItemsByPerson(p.name, wh.name, " +
            "SUM(w.amount)) " +
            "FROM Person p " +
            "JOIN p.wares w " +
            "JOIN w.warehouse wh " +
            "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(p.name) LIKE LOWER(" +
            "CONCAT('%', :keyword, '%'))) " +
            "GROUP BY p.name, wh.name")
    Page<AnalysisDtoItemsByPerson> getAnalysisDtoItemsByPerson(@Param("keyword") String keyword,
                                                               Pageable pageable);

    @Query("SELECT new com.lavrentieva.dto.AnalysisDtoItemsByPersonItemize(p.name, " +
            "wh.name, wg.name, w.deploymentDate, w.name, SUM(w.amount), w.id) " +
            "FROM Person p " +
            "JOIN p.wares w " +
            "JOIN w.warehouse wh " +
            "JOIN w.wareGroup wg " +
            "WHERE p.name = :id " +
            "GROUP BY p.name, wh.name, wg.name, w.deploymentDate, w.name, w.id")
    Page<AnalysisDtoItemsByPersonItemize> findPersonItemsByPersonId(@Param("id") String id,
                                                                      Pageable pageable);


}
