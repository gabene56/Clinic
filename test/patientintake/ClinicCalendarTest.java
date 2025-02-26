package patientintake;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

class ClinicCalendarTest {

    @Test
    public void allowEntryOfAppointment() {
        ClinicCalendar calendar = new ClinicCalendar();
        calendar.addAppointment("Shmuel", "Jacobs", "avery", "02/23/2025 9:30 AM");
        List<PatientAppointment>appointments = calendar.getAppointments();
        assertNotNull(appointments);
        assertEquals(1, appointments.size());

        PatientAppointment enteredAppt = appointments.get(0);
        assertEquals("Shmuel",enteredAppt.getPatientFirstName());
        assertEquals("Jacobs",enteredAppt.getPatientLastName());
        assertEquals(Doctor.avery,enteredAppt.getDoctor());
        assertEquals("2/23/2025 9:30 AM",
            enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")));
    }
}