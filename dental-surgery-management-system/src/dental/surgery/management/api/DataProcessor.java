package dental.surgery.management.api;

import java.util.List;

public interface DataProcessor {

	public void saveProcedure(Procedure procedure);

	public void deleteProcedure(int id);

	public List<Procedure> getProcedure();
	
        public Procedure getProcedure(int procedureId);
        
	public void savePatient(Patient patient);

	public void deletePatient(int id);

	public List<Patient> getPatients();
	
        public Patient getPatient(int patientId);
        
        public void savePatientProcedure(int patientId, int procedureId);

	public void deletePatientProcedure(int patientId, int procedureId);
              
	public void savePayment(int patientId, Payment payment);

	public void deletePayment(int patientId, int paymentId);
	
	public List<Payment> getPayment(int patientId);
	
	public void commit();
	
        public void loadData();
}
