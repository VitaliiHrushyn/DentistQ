package ua.pp.dentistq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.pp.dentistq.domain.ReservingHour;

/**
 * @author Vitalii Hrushyn
 *
 */

@Service
public interface DentistCalendar {
	
	/**
	 * return reserving hours list of the defined day
	 * @see ReservingHour
	 * @param day
	 * @return List<ReservingHour>
	 */
	List<ReservingHour> getByDay(String day);
	
	/**
	 * This method adds needed quantity of days (proper quantity of reserving hours) to dentist calendar 
     * to get next 30 days from current date.
     * If db is empty - this method creates 30 dentist calendar days (proper quantity of reserving hours) 
     * from current date. Further when this method will be invoked - it checks how many days exist after current day 
     * and if they less then 30 - creates needed quantity of the days to make them 30 of quantity from current date.
	 * @return int that represents quantity of the days added to the dentist calendar
	 */
	int update();	
	
	String setReserving(Long hourId, String description);
		
	String changeReserving(Long hourId, String description);
	
	Iterable<ReservingHour> getAll();
	
	List<ReservingHour> getCurrentCalendar();

}
