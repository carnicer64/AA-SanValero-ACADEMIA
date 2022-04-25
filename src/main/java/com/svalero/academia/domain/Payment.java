package com.svalero.academia.domain;

import java.util.Objects;

public class Payment {
    private String idPay;
    private int payedQuotes;
    private int payIdEnroll;

    public Payment(String idPay, int payedQuotes) {
        this.idPay = idPay;
        this.payedQuotes = payedQuotes;
    }

    public String getIdPay() {
        return idPay;
    }

    public void setIdPay(String idPay) {
        this.idPay = idPay;
    }

    public int getPayedQuotes() {
        return payedQuotes;
    }

    public void setPayedQuotes(int payedQuotes) {
        this.payedQuotes = payedQuotes;
    }

    public int getPayIdEnroll() {
        return payIdEnroll;
    }

    public void setPayIdEnroll(int payIdEnroll) {
        this.payIdEnroll = payIdEnroll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return payedQuotes == payment.payedQuotes && payIdEnroll == payment.payIdEnroll && Objects.equals(idPay, payment.idPay);
    }
}
