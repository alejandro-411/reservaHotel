
package com.eam.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Payments")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(name = "totalPrice")
    private double totalPrice;

    @Column(name = "paymentStatus")
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bookingId", referencedColumnName = "idBooking")
    private Booking booking;

    // Constructores, getters y setters

    public Payments() {
        super();
    }

    public Payments(Long paymentId, double totalPrice, String paymentStatus, User user, Booking booking) {
        this.paymentId = paymentId;
        this.totalPrice = totalPrice;
        this.paymentStatus = paymentStatus;
        this.user = user;
        this.booking = booking;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
