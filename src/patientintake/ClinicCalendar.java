package patientintake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ClinicCalendar {

   private List<PatientAppointment> appointments;
   private LocalDate today;

   public ClinicCalendar(LocalDate today) {
      this.appointments = new ArrayList<>();
      this.today = today;

   }

   public void addAppointment(String patientFirstName, String patientLastName, String doctorKey,
                              String dateTime) {
      Doctor doc = Doctor.valueOf(doctorKey.toLowerCase());
      LocalDateTime localDateTime = null;
      try{
         if(dateTime.toLowerCase().startsWith("today")){
            String[] parts = dateTime.split(" ", 2);
            LocalTime time = LocalTime.parse(parts[1].toUpperCase(),
                    DateTimeFormatter.ofPattern("h:mm a", Locale.US));
            localDateTime = LocalDateTime.of(today, time);
         }else{
            localDateTime = LocalDateTime.parse(dateTime.toUpperCase(),
            DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));
         }
      }catch(DateTimeParseException e) {
         System.out.println("Error: Invalid date/time format. Please use M/d/yyyy h:mm a.");
      }
      PatientAppointment appointment = new PatientAppointment(patientFirstName, patientLastName,
         localDateTime, doc);
      appointments.add(appointment);
   }

   public boolean hasAppointments(LocalDate date){
      return appointments.stream().anyMatch(appt -> appt.getAppointmentDateTime().toLocalDate().equals(date));
   }

   public List<PatientAppointment> getAppointments() {
      return this.appointments;
   }

}
