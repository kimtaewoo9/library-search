package com.mansereok.librarysearch.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "daily_stat")
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyStat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "query")
	private String query;

	@Column(name = "eventDateTime")
	private LocalDateTime eventDateTime;

	private DailyStat(String query, LocalDateTime eventDateTime) {
		this.query = query;
		this.eventDateTime = eventDateTime;
	}

	public static DailyStat create(String query, LocalDateTime eventDateTime){
		return new DailyStat(query, eventDateTime);
	}
}
