package br.api.notebook.model;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class PayWithBankSlipEntity extends PaymentEntity{
    private String BankSlipCode;
    private java.sql.Date date;

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
