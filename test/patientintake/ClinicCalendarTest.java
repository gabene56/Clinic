package patientintake;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


class ClinicCalendarTest {

    private ClinicCalendar calendar;

    @BeforeAll
    static void testSetup() {
    //    System.out.println("Before all tests...");
    }
    @BeforeEach
    void initEach() {
    //    System.out.println("Before each test...");
        calendar = new ClinicCalendar(LocalDate.now());
    }
    @Test
    public void allowEntryOfAppointment() {

    //    System.out.println("Before entry of appointment...");
        calendar.addAppointment("Shmuel", "Jacobs", "avery", "02/23/2025 9:30 AM");
        List<PatientAppointment>appointments = calendar.getAppointments();
        assertNotNull(appointments);
        assertEquals(1, appointments.size());

        PatientAppointment enteredAppt = appointments.get(0);
        assertEquals("Shmuel",enteredAppt.getPatientFirstName());
        assertEquals("Jacobs",enteredAppt.getPatientLastName());
        assertSame(Doctor.avery,enteredAppt.getDoctor());
        assertNotSame("2/23/2025 9:30 AM",
            enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")));
    }

    @Test
    void returnCurrentDaysAppointments() {

    //    System.out.println("Before returning today's appointments...");
        calendar.addAppointment("Shmuel", "Jacobs", "avery",
                "today 2:00 pm");
        calendar.addAppointment("Shira", "Jacobs", "avery",
                "3/02/2025 3:00 pm");

        assertEquals(1, calendar.getTodaysAppointments().size());
    }

    @Test
    void returnTrueForHasAppointmentsIfThereAreAppointments() {
    //    System.out.println("True if appointments exist ...");
        calendar.addAppointment("Shmuel", "Jacobs", "avery",
                "2/23/2025 9:30 AM");
        assertTrue(calendar.hasAppointments(LocalDate.of(2025, 2, 23)));
    }

    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
    //    System.out.println("True if no appointments...");
        assertFalse(calendar.hasAppointments(LocalDate.of(2025, 2, 28)));
    }


    @AfterEach
    void teardown() {
    //    System.out.println("After each test...");
    }

    @AfterAll
    static void tearDownAll() {
    //    System.out.println("After all tests...");
    }
}