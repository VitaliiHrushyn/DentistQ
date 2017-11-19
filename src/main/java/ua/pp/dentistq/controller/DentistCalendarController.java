package ua.pp.dentistq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.pp.dentistq.domain.ReservingHour;
import ua.pp.dentistq.service.DentistCalendar;

/**
 * @author Vitalii Hrushyn
 *
 */

@RestController
@RequestMapping("/calendar")
public class DentistCalendarController {
	
	
	@Autowired
	DentistCalendar dentistCalendar;
    
    public DentistCalendarController() {
		super();
	}
    
    /**
     * Return all reserving hours from db.
     * @return JSON with Iterable<ReservingHour>
     */
    @RequestMapping("/getall")
    public Iterable<ReservingHour> getAll() {
        return dentistCalendar.getAll();
    }
    
    /**
     * This method adds needed quantity of days (proper quantity of reserving hours) to dentist calendar 
     * to get next 30 days from current date.
     * @return JSON with String which represents quantity of the days added to the dentist calendar
     */
    @RequestMapping("/update")
    public String updateCalendar() {    	
    	return ""+dentistCalendar.update()+" day(s) were added to calendar";    	
    }
    
    /**
     * Return list of reserving hours which correspond to definite day
     * @param day
     * @return JSON with Iterable<ReservingHour>
     */
    @RequestMapping("/getbyday")
    public List<ReservingHour> findByDay(@RequestParam(value="day", defaultValue="today") String day) {
    	return dentistCalendar.getByDay(day);
    }
    
    @RequestMapping("/setreserving")
    public String setReserving(@RequestParam(value="hour") Long hourId, @RequestParam(value="description") String description) {
    	return dentistCalendar.setReserving(hourId, description);
    }
    
    @RequestMapping("/changereserving")
    public String changeReserving(@RequestParam(value="hour") Long hourId, @RequestParam(value="description") String description) {
    	return dentistCalendar.changeReserving(hourId, description);
    }
    
    @RequestMapping("/getcurrentcalendar")
    public List<ReservingHour> getCurrentCalendar() {
    	return dentistCalendar.getCurrentCalendar();
    }
}
 