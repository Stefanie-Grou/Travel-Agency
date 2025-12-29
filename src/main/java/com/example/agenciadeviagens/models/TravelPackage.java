package com.example.agenciadeviagens.models;

import com.example.agenciadeviagens.services.Discounts;
import com.example.agenciadeviagens.utils.ReceiptFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.example.agenciadeviagens.AppRunner.DEFAULT_DOUBLE;

public class TravelPackage {

    private Customer customer;
    private Double total;
    private List<Reservation> reservations;
    private Double discountAppliedByCashback;
    private Double discountAppliedByHotelFlight;
    private Double discountAppliedByFullTravelPackage;
    private Double totalDiscount;

    public TravelPackage() {
    }

    public TravelPackage(Customer customer, Double total,
                         List<Reservation> reservations) {
        this.customer = customer;
        this.total = total;
        this.reservations = reservations;
        this.totalDiscount = DEFAULT_DOUBLE;
        this.discountAppliedByCashback = DEFAULT_DOUBLE;
        this.discountAppliedByHotelFlight = DEFAULT_DOUBLE;
        this.discountAppliedByFullTravelPackage = DEFAULT_DOUBLE;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Double getDiscountAppliedByCashback() {
        return discountAppliedByCashback;
    }

    public void setDiscountAppliedByCashback(Double discountAppliedByCashback) {
        this.discountAppliedByCashback = discountAppliedByCashback;
    }

    public Double getDiscountAppliedByHotelFlight() {
        return discountAppliedByHotelFlight;
    }

    public void setDiscountAppliedByHotelFlight(Double discountAppliedByHotelFlight) {
        this.discountAppliedByHotelFlight = discountAppliedByHotelFlight;
    }

    public Double getDiscountAppliedByFullTravelPackage() {
        return discountAppliedByFullTravelPackage;
    }

    public void setDiscountAppliedByFullTravelPackage(Double discountAppliedByFullTravelPackage) {
        this.discountAppliedByFullTravelPackage = discountAppliedByFullTravelPackage;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();

        // Header
        receipt.append(ReceiptFormatter.createHeader("TRAVEL PACKAGE RECEIPT"));

        // Customer Info
        receipt.append("\n");
        receipt.append(ReceiptFormatter.createLine("Customer", customer.getName()));
        receipt.append(ReceiptFormatter.createLine("Customer ID", customer.getId().toString()));

        // Reservations Section
        receipt.append(ReceiptFormatter.createSection("RESERVATIONS"));

        for (int i = 0; i < reservations.size(); i++) {
            Reservation r = reservations.get(i);
            receipt.append(String.format("\n  Reservation #%d\n", i + 1));
            receipt.append(String.format("    Hotel........... %s\n", r.getHotel() ? "✓" : "✗"));
            receipt.append(String.format("    Meal............ %s\n", r.getMeal() ? "✓" : "✗"));
            receipt.append(String.format("    Flight.......... %s\n", r.getFlight() ? "✓" : "✗"));
            receipt.append(String.format("    Transport....... %s\n", r.getTransport() ? "✓" : "✗"));
            receipt.append(String.format("    Subtotal........ %s\n",
                    ReceiptFormatter.formatCurrency(r.getTotal())));
        }

        // Totals Section
        receipt.append(ReceiptFormatter.createSection("SUMMARY"));
        receipt.append(ReceiptFormatter.createLine("Subtotal",
                ReceiptFormatter.formatCurrency(total + totalDiscount)));

        if (getTotalDiscount() > 0) {
            receipt.append(ReceiptFormatter.createLine("Discount",
                    "-" + ReceiptFormatter.formatCurrency(totalDiscount)));
        }

        if (customer.getCashback() > 0) {
            receipt.append(ReceiptFormatter.createLine("Cashback Available",
                    String.valueOf(customer.getCashback())));
        }

        receipt.append("\n");
        receipt.append(ReceiptFormatter.createLine("TOTAL",
                ReceiptFormatter.formatCurrency(total)));

        // Footer
        receipt.append("\n");
        receipt.append(ReceiptFormatter.createFooter());
        receipt.append(ReceiptFormatter.centerText("Thank you for your business!"));
        receipt.append("\n").append(ReceiptFormatter.createFooter());

        return receipt.toString();
    }
}
