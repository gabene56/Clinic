import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import patientintake.ClinicCalendar;
import patientintake.PatientAppointment;

import static  org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class test1 {
    private ClinicCalendar calendar;

    @BeforeEach
    public void init() {
        calendar = new ClinicCalendar(LocalDate.now());
    }

    @Test
    public void allowEntryOfAppointment(){
        calendar.addAppointment("Ahuva", "Benedid", "avery", "02/23/2025 10:00 AM");
        calendar.addAppointment("Gladys", "Benedid", "avery", "02/24/2025 10:00 AM");
        List<PatientAppointment> appointments = calendar.getAppointments();
        assertNotNull(appointments);
        assertEquals(2, appointments.size());
        PatientAppointment secondAppointment = appointments.get(1);
        assertEquals("Gladys", secondAppointment.getPatientFirstName());
        assertEquals("Benedid", secondAppointment.getPatientLastName());
        assertEquals("2025-02-24T10:00", secondAppointment.getAppointmentDateTime().toString());
        assertSame("avery", secondAppointment.getDoctor().toString());
    }

    @Test
    public void returnTrueForHasAppointmentsIfThereAreAppointments(){
        calendar.addAppointment("Shmuel", "Jacobs", "avery", "02/23/2025 10:00 AM" );
        assertTrue(calendar.hasAppointments((LocalDate.of(2025,2,23))));

    }

    @Test
    public void returnFalseForHasAppointmentsIfThereAreNoAPpontments(){
        assertFalse(calendar.hasAppointments(LocalDate.of(2025,2,28)));
    }

    @Test
    public void returnCurrentDaysAppointments() {
        calendar.addAppointment("Shmuel", "Jacobs", "avery",
                "today 2:00 pm");

        assertIterableEquals(calendar.getAppointments(), calendar.getTodaysAppointments());
    }
}
