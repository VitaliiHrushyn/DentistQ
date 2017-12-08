package ua.pp.dentistq.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.pp.dentistq.domain.ReservingHour;

/**
 * @author Vitalii Hrushyn
 *
 */

@Service
public class SimpleReservinigHoursFactory implements ReservinigHoursFactory{

	/**
	 * This method creates defined in param "count" quantity of the dentist calendar days (proper quantity of reserving hours)
	 * and besides these days will have dates in proper order after the latest existing day. 
	 * @see ReservingHour
	 * @param count
	 * @return List<ReservingHour>
	 */
	@Override
	public List<ReservingHour> createNewDentistCalendarDays(int count) {		
		
		List<ReservingHour> reservingHoursForNewDays = new ArrayList<>();
		for (int i = 31 - count; i < 31; i++) {
			LocalDate date = LocalDate.now().plusDays(i);
			for (int j = 9; j < 18; j++) {
				reservingHoursForNewDays.add(new ReservingHour(date, j));
			}		
		}		
		return reservingHoursForNewDays;
	}
}
