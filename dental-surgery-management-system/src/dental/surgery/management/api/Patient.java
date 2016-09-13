package dental.surgery.management.api;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Patient implements Comparator<Patient> {

    int id;
    String name;
    String address;
    String phoneNumber;
    List<Procedure> procedure;
    List<Payment> payment;

    public Patient(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    Patient() {
        this.name = "";
        this.address = "";
        this.phoneNumber = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Procedure> getProcedure() {
        if (procedure == null) {
            procedure = new ArrayList<Procedure>();
        }
        return procedure;
    }

    public void setProcedure(List<Procedure> procedure) {
        this.procedure = procedure;
    }

    public List<Payment> getPayment() {
        if (payment == null) {
            payment = new ArrayList<Payment>();
        }
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    @Override
    public int compare(Patient arg0, Patient arg1) {
        return arg0.getId() - arg1.getId();
    }

    public static class OrderByName implements Comparator<Patient> {

        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
