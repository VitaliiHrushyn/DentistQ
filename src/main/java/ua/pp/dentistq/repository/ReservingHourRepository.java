package ua.pp.dentistq.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ua.pp.dentistq.domain.ReservingHour;

/**
 * @author Vitalii Hrushyn
 *
 */

public interface ReservingHourRepository extends CrudRepository<ReservingHour, Long>{
	
	List<ReservingHour> findByDate(LocalDate localDate);
	
	ReservingHour findById(Long Id);


}
