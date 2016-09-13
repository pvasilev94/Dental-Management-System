package dental.surgery.management.api;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FileSystemDataProcessor implements DataProcessor {

    private List<Procedure> procedureList;
    private int paymentCount;
    private List<Patient> patientList;
    private String patientFileName = "patient.txt";
    private String procedureFileName = "procedure.txt";

    public FileSystemDataProcessor() {
        this.procedureList = new ArrayList<Procedure>();
        this.paymentCount = 0;
        this.patientList = new ArrayList<Patient>();
        loadData();
    }

    @Override
    public void saveProcedure(Procedure procedure) {
        procedure.setId(procedureList.size() + 1);
        procedureList.add(procedure);
    }

    @Override
    public void deleteProcedure(int id) {
        for (int i = 0; i < procedureList.size(); i++) {
            if (id == procedureList.get(i).getId()) {
                procedureList.remove(i);
            }
        }
    }

    @Override
    public List<Procedure> getProcedure() {
        return procedureList;
    }

    @Override
    public void savePatient(Patient patient) {
        patient.setId(patientList.size() + 1);
        patientList.add(patient);
    }

    @Override
    public void deletePatient(int id) {
        for (int i = 0; i < patientList.size(); i++) {
            if (id == patientList.get(i).getId()) {
                patientList.remove(i);
            }
        }
    }

    @Override
    public List<Patient> getPatients() {
        return patientList;
    }

    @Override
    public void savePayment(int patientId, Payment payment) {
        payment.setId(++paymentCount);
        for (Patient patient : patientList) {
            if (patient.getId() == patientId) {
                patient.getPayment().add(payment);
            }
        }
    }

    @Override
    public void deletePayment(int patientId, int paymentId) {
        for (Patient patient : patientList) {
            if (patient.getId() == patientId) {
                for (int i = 0; i < patient.getPayment().size(); i++) {
                    if (patient.getPayment().get(i).getId() == paymentId) {
                        patient.getPayment().remove(i);
                    }
                }
            }
        }
    }

    @Override
    public List<Payment> getPayment(int patientId) {
        for (Patient patient : patientList) {
            if (patient.getId() == patientId) {
                return patient.getPayment();
            }
        }
        return null;
    }

    @Override
    public void commit() {
        try {

            FileWriter jsonFileWriter = new FileWriter(this.patientFileName);

            JSONArray patients = new JSONArray();
            for (Patient patient : patientList) {
                JSONObject pat = new JSONObject();
                pat.put("id", patient.getId());
                pat.put("name", patient.getName());
                pat.put("phoneNumber", patient.getPhoneNumber());
                pat.put("address", patient.getAddress());

                if (patient.getProcedure() != null && patient.getProcedure().size() > 0) {
                    JSONArray procedures = new JSONArray();
                    for (Procedure procedure : patient.getProcedure()) {
                        JSONObject proc = new JSONObject();
                        proc.put("id", procedure.getId());
                        proc.put("name", procedure.getName());
                        proc.put("cost", procedure.getCost());
                        procedures.add(proc);
                    }
                    pat.put("procedure", procedures);
                }

                if (patient.getPayment() != null && patient.getPayment().size() > 0) {
                    JSONArray payments = new JSONArray();
                    for (Payment payment : patient.getPayment()) {
                        JSONObject pay = new JSONObject();
                        pay.put("id", payment.getId());
                        pay.put("date", payment.getDate().getTime());
                        pay.put("amount", payment.getAmount());
                        payments.add(pay);
                    }
                    pat.put("payment", payments);
                }

                patients.add(pat);
            }
            jsonFileWriter.write(patients.toJSONString());
            jsonFileWriter.flush();
            jsonFileWriter.close();

            jsonFileWriter = new FileWriter(this.procedureFileName);

            JSONArray procedures = new JSONArray();
            for (Procedure procedure : procedureList) {
                JSONObject pat = new JSONObject();
                pat.put("id", procedure.getId());
                pat.put("name", procedure.getName());
                pat.put("cost", procedure.getCost());
                procedures.add(pat);
            }
            jsonFileWriter.write(procedures.toJSONString());
            jsonFileWriter.flush();
            jsonFileWriter.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void loadData() {
        JSONParser parser = new JSONParser();
        FileReader fileReader = null;

        //Load Procedure from file
        try {
            File procedureFile = new File(this.procedureFileName);
            if (procedureFile.exists()) {
                fileReader = new FileReader(this.procedureFileName);
                JSONArray procedures = (JSONArray) parser.parse(fileReader);

                for (int i = 0; i < procedures.size(); i++) {
                    JSONObject proc = (JSONObject) procedures.get(i);
                    Procedure procedure = new Procedure();
                    procedure.setId(((Long) proc.get("id")).intValue());
                    procedure.setName((String) proc.get("name"));
                    procedure.setCost((Double) proc.get("cost"));
                    procedureList.add(procedure);
                }
                fileReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File patientFile = new File(this.patientFileName);
            if (patientFile.exists()) {
                fileReader = new FileReader(this.patientFileName);
                JSONArray patients = (JSONArray) parser.parse(fileReader);
                for (int i = 0; i < patients.size(); i++) {
                    JSONObject pat = (JSONObject) patients.get(i);
                    Patient patient = new Patient();
                    patient.setId(((Long) pat.get("id")).intValue());
                    patient.setName((String) pat.get("name"));
                    patient.setPhoneNumber((String) pat.get("phoneNumber"));
                    patient.setAddress((String) pat.get("address"));

                    JSONArray proc = (JSONArray) pat.get("procedure");
                    if (proc != null) {
                        List<Procedure> locProcedureList = new ArrayList<Procedure>();

                        for (int j = 0; j < proc.size(); j++) {
                            JSONObject procObject = (JSONObject) proc.get(i);
                            Procedure procedure = new Procedure();
                            procedure.setId(((Long) procObject.get("id")).intValue());
                            procedure.setName((String) procObject.get("name"));
                            procedure.setCost((Double) procObject.get("cost"));
                            locProcedureList.add(procedure);
                        }
                        patient.setProcedure(locProcedureList);
                    }

                    JSONArray pay = (JSONArray) pat.get("payment");
                    if (pay != null) {
                        List<Payment> locPaymentList = new ArrayList<Payment>();
                        for (int j = 0; j < pay.size(); j++) {
                            JSONObject payObject = (JSONObject) pay.get(i);
                            Payment payment = new Payment();
                            payment.setId(((Long) payObject.get("id")).intValue());
                            payment.setAmount((Double) payObject.get("amount"));
                            payment.setDate(new Date((Long)payObject.get("date")));
                            locPaymentList.add(payment);
                            paymentCount++;
                        }
                        patient.setPayment(locPaymentList);
                    }
                    patientList.add(patient);
                }
                fileReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Patient and Procedure information loaded from file system");
    }

    public void generateReport() {
    }

    
    @Override
    public Patient getPatient(int patientId) {
        for (Patient patient : patientList) {
            if (patient.getId() == patientId) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public void savePatientProcedure(int patientId, int procedureId) {
        Patient patient = getPatient(patientId);
        Procedure procedure = getProcedure(procedureId);
        patient.getProcedure().add(procedure);
    }

    @Override
    public void deletePatientProcedure(int patientId, int procedureId) {
        Patient patient = getPatient(patientId);
        for (int i = 0; i < patient.getProcedure().size(); i++) {
            if (procedureId == patient.getProcedure().get(i).getId()) {
                patient.getProcedure().remove(i);
            }
        }
    }

    @Override
    public Procedure getProcedure(int procedureId) {
        for (Procedure patient : procedureList) {
            if (patient.getId() == procedureId) {
                return patient;
            }
        }
        return null;
    }
}
