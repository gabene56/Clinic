import org.junit.Test;
import patientintake.ClinicCalendar;
import patientintake.PatientAppointment;

import static  org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class test1 {
    @Test
    public void allowEntryOfAppointment(){
        ClinicCalendar calendar = new ClinicCalendar();
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
        ClinicCalendar calendar = new ClinicCalendar();
        calendar.addAppointment("Shmuel", "Jacobs", "avery", "02/23/2025 10:00 AM" );
        assertTrue(calendar.hasAppointments((LocalDate.of(2025,2,23))));

    }

    @Test
    public void returnFalseForHasAppointmentsIfThereAreNoAPpontments(){
        ClinicCalendar calendar = new ClinicCalendar();
        assertFalse(calendar.hasAppointments(LocalDate.of(2025,2,28)));
    }
}
