package com.mansereok.librarysearch.repository;

import com.mansereok.librarysearch.controller.response.StatResponse;
import com.mansereok.librarysearch.entity.DailyStat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyStatRepository extends JpaRepository<DailyStat, Long> {

	long countByQueryAndEventDateTimeBetween(
		String query,
		LocalDateTime start,
		LocalDateTime end
	);

	@Query("SELECT new com.mansereok.librarysearch.controller.response.StatResponse(ds.query, count(ds.query)) " +
		"FROM DailyStat ds " +
		"GROUP BY ds.query ORDER BY count(ds.query) DESC")
	List<StatResponse> findTopQuery(Pageable pageable);
}
