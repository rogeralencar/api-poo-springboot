package br.api.notebook.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

@Entity
public class PayWithBankSlipEntity extends PaymentEntity{
    private String BankSlipCode;
    private Date date;

    @Override
    public void addKey() {
        setBankSlipCode("03399.69925 58700.001801 85108.0010188 74650000010000");
        setName("Boleto Bancário");
        setDate(new Date());
    }

    public PayWithBankSlipEntity(){
        super();
    }

    public String getBankSlipCode() {
        return BankSlipCode;
    }

    public void setBankSlipCode(String bankSlipCode) {
        BankSlipCode = bankSlipCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
