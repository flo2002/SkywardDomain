package fhv.ws22.se.skyward.domain.paymentParser;

import java.time.LocalDate;

public class Payment {
    private Integer res;
    private LocalDate date;
    private Float amount;
    private String iban;

    public Payment() {
    }

    public Integer getRes() {
        return res;
    }
    public void setRes(Integer res) {
        this.res = res;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getIban() {
        return iban;
    }
    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "PaymentDto{" +
                "res=" + res +
                ", date=" + date +
                ", amount=" + amount +
                ", iban='" + iban + '\'' +
                '}';
    }
}
