/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dental.surgery.management.system.process;

import dental.surgery.management.api.DataProcessor;
import dental.surgery.management.api.FileSystemDataProcessor;
import dental.surgery.management.api.Patient;
import dental.surgery.management.api.Payment;
import dental.surgery.management.api.Procedure;
import dental.surgery.management.system.panel.MainPanel;
import dental.surgery.management.system.panel.ProcedurePanel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 *
 */
public class Processor {

    public static DataProcessor dataProcessor = null;
    public static int currentPatientId = 0;

    public static void initializeApplication() {
        dataProcessor = new FileSystemDataProcessor();

        loadProdedureTable();

        loadPatientTable();


    }

    private static void loadProdedureTable() {
        Object[] data = new Object[3];
        DefaultTableModel tModel = (DefaultTableModel) ProcedurePanel.procedureTable.getModel();
        tModel.setRowCount(0);
        MainPanel.patientProdedureComboBox.removeAllItems();
        List<Procedure> procedureList = dataProcessor.getProcedure();
        System.out.println(procedureList.size());
        for (int i = 0; i < procedureList.size(); i++) {
            data[0] = procedureList.get(i).getId();
            data[1] = procedureList.get(i).getName();
            data[2] = procedureList.get(i).getCost();
            tModel.addRow(data);
            MainPanel.patientProdedureComboBox.addItem(procedureList.get(i).getName());
        }
    }

    public static void saveProcedure(String name, double cost) {
        dataProcessor.saveProcedure(new Procedure(name, cost));
        loadProdedureTable();
    }

    public static void deleteProcedure(int id) {
        dataProcessor.deleteProcedure(id);
        loadProdedureTable();
    }

    private static void loadPatientTable() {
        Object[] data = new Object[2];
        DefaultTableModel tModel = (DefaultTableModel) MainPanel.patientTable.getModel();
        tModel.setRowCount(0);
        List<Patient> patients = dataProcessor.getPatients();
        for (int i = 0; i < patients.size(); i++) {
            data[0] = patients.get(i).getId();
            data[1] = patients.get(i).getName();
            tModel.addRow(data);
        }
    }

    public static void loadPatient(int patientId) {
        currentPatientId = patientId;
        Patient patient = dataProcessor.getPatient(patientId);
        MainPanel.patientNameTextField.setText(patient.getName());
        MainPanel.addressTextArea.setText(patient.getAddress());
        MainPanel.contactTextField.setText(patient.getPhoneNumber());
        loadPatientProdedure(patient);
        loadPatientPayment(patient);
    }

    public static void savePatient(String name, String address, String contact) {
        dataProcessor.savePatient(new Patient(name, address, contact));
        loadPatientTable();
    }

    public static void deletePatient(int id) {
        dataProcessor.deletePatient(id);
        loadPatientTable();
    }

    public static void loadPatientProdedure(int patientId) {
        Patient patient = dataProcessor.getPatient(patientId);
        loadPatientProdedure(patient);
    }

    public static void loadPatientProdedure(Patient patient) {
        Object[] data = new Object[3];
        DefaultTableModel tModel = (DefaultTableModel) MainPanel.patientProcedurejTable.getModel();
        tModel.setRowCount(0);
        for (int i = 0; i < patient.getProcedure().size(); i++) {
            data[0] = patient.getProcedure().get(i).getId();
            data[1] = patient.getProcedure().get(i).getName();
            data[2] = patient.getProcedure().get(i).getCost();
            tModel.addRow(data);
        }
    }

    public static void savePatientProcedure() {
        int index = MainPanel.patientProdedureComboBox.getSelectedIndex();
        Procedure pr = dataProcessor.getProcedure().get(index);
        dataProcessor.savePatientProcedure(currentPatientId, pr.getId());
        loadPatientProdedure(currentPatientId);
    }

    public static void loadPatientPayment(int patientId) {
        Patient patient = dataProcessor.getPatient(patientId);
        loadPatientPayment(patient);
    }

    public static void loadPatientPayment(Patient patient) {
        Object[] data = new Object[3];
        DefaultTableModel tModel = (DefaultTableModel) MainPanel.paymentTable.getModel();
        tModel.setRowCount(0);
        for (int i = 0; i < patient.getPayment().size(); i++) {
            data[0] = patient.getPayment().get(i).getId();
            data[1] = patient.getPayment().get(i).getDate().toString();
            data[2] = patient.getPayment().get(i).getAmount();
            tModel.addRow(data);
        }
    }

    public static void deletePatientProcedure(int procedureId) {
        dataProcessor.deletePatientProcedure(currentPatientId, procedureId);
        loadPatientProdedure(currentPatientId);
    }

    public static void commit() {
        dataProcessor.commit();
    }

    public static void addPayment(double amount) {
        Payment payment = new Payment(amount, new Date(System.currentTimeMillis()));
        dataProcessor.savePayment(currentPatientId, payment);
        loadPatientPayment(currentPatientId);
    }

    public static void generateReportWithAllPatients() {
        BufferedWriter bufWriter = null;
        try {

            bufWriter = new BufferedWriter(new FileWriter("ExportedReport1.csv"));
            StringBuffer buffer = new StringBuffer();
            bufWriter.write("PatientId,PatientName,PatientAddress,PatientPhoneNumber,Procedures(separated by - char),TotalAmount,PaidAmount,PaidDate,AmountOwned");
            bufWriter.newLine();
            List<Patient> patientList = dataProcessor.getPatients();
            //Patient
            Collections.sort(patientList, new Patient.OrderByName());

            for (Patient patient : patientList) {
                buffer.append(patient.getId() + ",");
                buffer.append(patient.getName() + ",");
                buffer.append(patient.getAddress() + ",");
                buffer.append(patient.getPhoneNumber() + ",");
                double cost = 0;
                StringBuffer procedureNames = new StringBuffer();
                if (patient.getProcedure() != null && patient.getProcedure().size() > 0) {
                    for (Procedure procedure : patient.getProcedure()) {
                        procedureNames.append(procedure.getName());
                        if (patient.getProcedure().get(patient.getProcedure().size() - 1).getId() != procedure.getId()) {
                            procedureNames.append(" - ");
                        }
                        cost = cost + procedure.getCost();
                    }
                }

                buffer.append(procedureNames + ",");
                buffer.append(cost + ",");
                double paidAmount = 0;
                Date date = null;
                if (patient.getPayment() != null && patient.getPayment().size() > 0) {
                    for (Payment payment : patient.getPayment()) {
                        paidAmount = paidAmount + payment.getAmount();
                        date = payment.getDate();
                    }
                }
                buffer.append(paidAmount + ",");
                buffer.append(date != null ? date : "");
                buffer.append((cost - paidAmount) + ",");
                bufWriter.write(buffer.toString());
                bufWriter.newLine();
                buffer = new StringBuffer();
            }
            bufWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateReportWithCondition() {
        BufferedWriter bufWriter = null;
        try {

            bufWriter = new BufferedWriter(new FileWriter("ExportedReport2.csv"));
            StringBuffer buffer = new StringBuffer();
            bufWriter.write("PatientId,PatientName,PatientAddress,PatientPhoneNumber,Procedures(separated by - char),AmountOwned");
            bufWriter.newLine();
            List<Patient> patientList = dataProcessor.getPatients();
            //Patient
            Collections.sort(patientList, new Patient.OrderByName());
            List<Patient> subList = new ArrayList<Patient>();

            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.set(Calendar.MONTH, currentCalendar.get(Calendar.MONTH) - 6);
            for (Patient patient : patientList) {
                double cost = 0;
                if (patient.getProcedure() != null && patient.getProcedure().size() > 0) {
                    for (Procedure procedure : patient.getProcedure()) {
                        cost = cost + procedure.getCost();
                    }
                }
                double paidAmount = 0;
                Date date = null;
                if (patient.getPayment() != null && patient.getPayment().size() > 0) {
                    for (Payment payment : patient.getPayment()) {
                        paidAmount = paidAmount + payment.getAmount();
                        date = payment.getDate();
                    }
                }

                if ((cost - paidAmount) > 0) {
                    if (date == null) {
                        subList.add(patient);
                    } else if (date.getTime() > (currentCalendar.getTimeInMillis())) {
                        subList.add(patient);
                    }
                }
            }

            for (Patient patient : subList) {
                buffer.append(patient.getId() + ",");
                buffer.append(patient.getName() + ",");
                buffer.append(patient.getAddress() + ",");
                buffer.append(patient.getPhoneNumber() + ",");
                double cost = 0;
                StringBuffer procedureNames = new StringBuffer();
                if (patient.getProcedure() != null && patient.getProcedure().size() > 0) {
                    for (Procedure procedure : patient.getProcedure()) {
                        procedureNames.append(procedure.getName());
                        if (patient.getProcedure().get(patient.getProcedure().size() - 1).getId() != procedure.getId()) {
                            procedureNames.append(" - ");
                        }
                        cost = cost + procedure.getCost();
                    }
                }

                buffer.append(procedureNames + ",");
                buffer.append(cost + ",");
                double paidAmount = 0;
                Date date = null;
                if (patient.getPayment() != null && patient.getPayment().size() > 0) {
                    for (Payment payment : patient.getPayment()) {
                        paidAmount = paidAmount + payment.getAmount();
                        date = payment.getDate();
                    }
                }
                buffer.append(paidAmount + ",");
                buffer.append(date != null ? date : "");
                buffer.append((cost - paidAmount) + ",");
                bufWriter.write(buffer.toString());
                bufWriter.newLine();
                buffer = new StringBuffer();
            }
            bufWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
