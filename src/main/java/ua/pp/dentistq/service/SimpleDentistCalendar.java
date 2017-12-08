package ua.pp.dentistq.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pp.dentistq.domain.ReservingHour;
import ua.pp.dentistq.repository.ReservingHourRepository;

/**
 * @author Vitalii Hrushyn
 *
 */

@Service
public class SimpleDentistCalendar implements DentistCalendar {
	
	@Autowired
    private ReservingHourRepository reservingHourRepository;
	
	@Autowired
    private ReservinigHoursFactory reservinigHoursFactory;
	

	public SimpleDentistCalendar() {
		super();
	}
	
	/**
     * This method adds needed quantity of days (proper quantity of reserving hours) to dentist calendar 
     * to get next 30 days from current date.
     * If db is empty - this method creates 30 dentist calendar days (proper quantity of reserving hours) 
     * from current date. Further when this method will be invoked - it checks how many days exist after current day 
     * and if they less then 30 - creates needed quantity of the days to make them 30 of quantity from current date.
     * @see ReservingHour
     * @return int that represents quantity of the days added to the dentist calendar
     */
	public int update() {
		
		List<ReservingHour> allHours = (List<ReservingHour>) reservingHourRepository.findAll();		
		LocalDate currentDate = LocalDate.now();		
		int count;
		
		if (allHours.size() > 0) {		
			ListIterator<ReservingHour> listIterator = allHours.listIterator(allHours.size()-1);		
			int subcount = 0;	
			
			while (listIterator.hasPrevious()) {
				subcount++;
				if (listIterator.previous().getDate().equals(currentDate)) {
					break;
				}
			}
			
			count = (30 * 9 - subcount);
		} else {
			count = 30 * 9;
		}
		
		int days = count / 9; // split total quantity of hours to quantity of hours per one day to get quantity of days
	
		reservingHourRepository.save(reservinigHoursFactory.createNewDentistCalendarDays(days));
		return days;
	}

	/**
     * Return list of reserving hours which correspond to definite day
     * @param localDate
     * @return Iterable<ReservingHour>
     */
	@Override
	public List<ReservingHour> getByDay(String day) {
    	LocalDate date = null;
    	if (day.equals("today")) {
    		date = LocalDate.now();
    	} else {
    		date = LocalDate.parse(day); // e.g. 2017-11-15
    	}    	
    	return reservingHourRepository.findByDate(date);
    
	}
	
	@Override
	public String setReserving(Long hourId, String description) {
    	ReservingHour hour = reservingHourRepository.findById(hourId);
    	if (hour.getDate().getDayOfWeek() == DayOfWeek.SATURDAY || hour.getDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
    		return "sorry, we don't work on weekends";
    	}
	    if (hour.getDescription() == null) {
	    	hour.setDescription(description);
	    	reservingHourRepository.save(hour);
	    	return "For hour Id = " + hourId + " added reservation: '" + description + "'";
	    }
    	return "sorry, this hour is already reserved";
    }
	
	@Override
	public String changeReserving(Long hourId, String description) {
    	ReservingHour hour = reservingHourRepository.findById(hourId);
    	String message = "sorry, this hour isn't reserved";
    	if (hour.getDescription() != null) {
    		if (description.equals("cansel")) {
    			hour.removeDescription();
    			message = "For hour Id = " + hourId + " reservation has been canseled";
    		} else {
    		hour.setDescription(description);
    		message = "For hour Id = " + hourId + " reservation has been changed to: '" + description + "'";
    		}
    	reservingHourRepository.save(hour);
    	}    	
    	return message;
    }
	
	public Iterable<ReservingHour> getAll() {
        return reservingHourRepository.findAll();
    }
	
	public List<ReservingHour> getCurrentCalendar() {
		List<ReservingHour> hoursFromToday = new ArrayList<>();	
		for (int i = 0; i < 30; i++) {
			hoursFromToday.addAll(reservingHourRepository.findByDate(LocalDate.now().plusDays(i)));			
		}		
		return hoursFromToday;		
	}
	
}
