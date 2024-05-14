package com.eam.demo.controller;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eam.demo.models.Booking;
import com.eam.demo.models.Hotel;
import com.eam.demo.models.ReserveStatus;
import com.eam.demo.models.Room;
import com.eam.demo.models.User;
import com.eam.demo.services.BookingService;
import com.eam.demo.services.HotelService;
import com.eam.demo.services.ReserveStatusService;
import com.eam.demo.services.RoomService;
import com.eam.demo.services.UserService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/booking")
public class Bookingcontroller {

    @Autowired
    RoomService roomService;

    @Autowired
    UserService userService;

    @Autowired
    HotelService hotelService;

    @Autowired
    ReserveStatusService reserveStatusService;

    @Autowired
    BookingService bookingService;

    @GetMapping("/bookingForm")
    public String mostrarFormularioUsuario() {
        return "booking/bookingdates";
    }

    @PostMapping("/searchReservation")
    public String searchReservation(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            Model model) { // Include Model to add attributes

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date endDateParsed = dateFormat.parse(endDate);
            List<Room> availableRooms = roomService.findAllRoomsForBooking(endDateParsed);

            List<User> users = userService.findAllUsers();

            // Logging room numbers, consider removing after debugging
            availableRooms.forEach(room -> System.out.println(room.getRoomNumber()));
            availableRooms.forEach(room -> System.out.println(room.getHotel().getHotelName()));
            users.forEach(user -> System.out.println(user.getUserName()));

            // Add availableRooms to the model to be accessed by Thymeleaf
            model.addAttribute("rooms", availableRooms);
            model.addAttribute("endDate", endDate);
            model.addAttribute("startDate", startDate);
            model.addAttribute("users", users);

            // Return the Thymeleaf template that will show the rooms
            return "booking/availableRooms"; // assume you have a template named availableRooms.html

        } catch (ParseException e) {
            e.printStackTrace();
            model.addAttribute("error", "Invalid date format");
            return "booking/bookingdates"; // Redirect back to the booking date form with an error message
        }
    }

    @PostMapping("/createReservation")
    public String createReservation(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("selectedRooms") List<Long> selectedRoomsIds,
            @RequestParam("hotelId") Long hotelId,
            @RequestParam("userId") Long userId) {

        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Selected Rooms: " + selectedRoomsIds);
        System.out.println("Hotel ID: " + hotelId);
        System.out.println("User ID: " + userId);
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDateParsed = dateFormat.parse(endDate);

            Date endDateParsed = dateFormat.parse(endDate);
            Double totalPrice = this.roomService.totalPaymentForRooms(selectedRoomsIds);
            User user = this.userService.findAUser(userId);
            Hotel hotel = this.hotelService.findHotelByID(hotelId).get();
            ReserveStatus reserveStatus = this.reserveStatusService.findReserveStatusById(1L).get();

            Booking myBooking = new Booking(0L, startDateParsed, endDateParsed,
                    totalPrice, user, hotel, reserveStatus);
            Booking bookingCreated = this.bookingService.saveBooking(myBooking, selectedRoomsIds);

            System.out.println("bookingCreated" + bookingCreated.getIdBooking());
            // Redirige a la página de confirmación de reserva o a donde sea apropiado
            return "booking/bookingdates"; // Suponiendo que tengas una vista llamada confirmation.html
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "booking/bookingdates"; // Suponiendo que tengas una vista llamada confirmation.html

        }
    }

    @GetMapping("/listBookings")
    public String listFilteredBookings(@RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) Long hotelId,
            Model model) {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if(startDate == null)
                startDate = "2024-01-01";
            if(endDate == null)
                endDate = "2024-12-31";
                    
            Date start = dateFormat.parse(startDate);

            Date end = dateFormat.parse(endDate);

            List<Booking> filteredBookings = bookingService.findBookingsByfilters(start, end, hotelId);
            System.out.println("filteredBookings" + filteredBookings.size());
            List<Hotel> hotels = hotelService.listHotels();
            model.addAttribute("bookings", filteredBookings);
            model.addAttribute("hotels", hotels);
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
            model.addAttribute("hotelId", hotelId);
            return "booking/bookingReport"; // el nombre de tu archivo HTML
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "booking/bookingReport"; // el nombre de tu archivo HTML

        }
    }

    @GetMapping("/bookingList")
    public String listBookings(Model model) {
        List<Booking> bookings = bookingService.listBookings();
        List<Hotel> hotels = hotelService.listHotels();
        model.addAttribute("hotels", hotels);

        model.addAttribute("bookings", bookings);
        return "booking/bookingReport"; // Nombre del archivo HTML en templates
    }

    @GetMapping("/download")
    public void downloadPDF(HttpServletResponse response,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) Long hotelId) throws IOException, DocumentException, ParseException {

        System.out.println("PDF GENERATOR");
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=booking_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        Date start = dateFormatter.parse(startDate);
        Date end = dateFormatter.parse(endDate);

        export(response, start, end, hotelId);
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Booking ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Entry Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Departure Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Total Price", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table, Date start, Date end, Long hotelId) {
        List<Booking> filteredBookings = bookingService.findBookingsByfilters(start, end, hotelId);

        for (Booking booking : filteredBookings) {
            table.addCell(String.valueOf(booking.getIdBooking()));
            table.addCell(String.valueOf(booking.getReservationEntryDate()));
            table.addCell(String.valueOf(booking.getReservationDepartureDate()));
            table.addCell(String.valueOf(booking.getTotalReservationPrice()));
            table.addCell(String.valueOf(booking.getReserveStatus().getNameReserveStatus()));

        }
    }

    public void export(HttpServletResponse response, Date start, Date end, Long hotelId)
            throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Booking", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f });
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table, start, end, hotelId);

        document.add(table);

        document.close();

    }

}
