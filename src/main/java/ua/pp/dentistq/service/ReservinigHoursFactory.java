package ua.pp.dentistq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.pp.dentistq.domain.ReservingHour;

/**
 * @author Vitalii Hrushyn
 *
 */

@Service
public interface ReservinigHoursFactory {
	
	/**
	 * This method creates defined in param "count" quantity of the dentist calendar days (proper quantity of reserving hours)
	 * and besides these days will have dates in proper order after the latest existing day.
	 * @see ReservingHour 
	 * @param count
	 * @return List<ReservingHour>
	 */
	List<ReservingHour> createNewDentistCalendarDays(int count);
	
}
