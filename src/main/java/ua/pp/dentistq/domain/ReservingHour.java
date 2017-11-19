package ua.pp.dentistq.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import ua.pp.dentistq.exception.SettingHourException;

/**
 * @author Vitalii Hrushyn
 * This class represents hour which could be reserved by customer for visiting a dentist.
 * Please notice that further in docs often mentions such term as "day" or "dentist calendar day" 
 * which however isn't an entity in this app, it's just an abstraction which includes proper
 * quantity of reserving hours (entities of this class) that are related to this day (as a date). 
 */

@Component
@Entity
@Table(name="RESERVING_HOURS")
public class ReservingHour implements Serializable {

	private static final long serialVersionUID = -8708712032227567301L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	/**
	 * Date of reserving hour
	 */
	@Column(name="date")
	private LocalDate date;
	
	/**
	 * Definited hour from 9 to 17 (represents working day)
	 */
	@Column(name="hour")
	private int hour;
	
	/**
	 * Description to reservation of the hour. If description = null - the hour isn't reserved
	 */
	@Column(name="description", nullable=true) 
	private String description;
	
	public ReservingHour() {
		super();
	}
	
	public ReservingHour(LocalDate date, int hour) {
		super();
		this.date = date;
		this.hour = hour;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	/**
	 * Setter of hour field, the value out of 9 to 17 can't be assigned
	 * @param hour
	 * @throws SettingHourException
	 */
	public void setHour(int hour) throws SettingHourException {
		if (hour < 9 || hour > 17) {
			throw new SettingHourException("Exception caused of trying to assign value out of 9-17");
		} else {
			this.hour = hour;
		}
	}

	public void removeDescription() {
		this.description = null;		
	}

}
