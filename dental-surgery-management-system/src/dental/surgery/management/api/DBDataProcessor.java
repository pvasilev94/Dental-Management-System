package dental.surgery.management.api;

import java.util.List;

public class DBDataProcessor implements DataProcessor {

	@Override
	public void saveProcedure(Procedure procedure) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProcedure(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Procedure> getProcedure() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePatient(Patient patient) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePatient(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Patient> getPatients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePayment(int patientId, Payment payment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePayment(int patientId, int paymentId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Payment> getPayment(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		
	}

    @Override
    public Patient getPatient(int patientId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Procedure getProcedure(int procedureId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void savePatientProcedure(int patientId, int procedureId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePatientProcedure(int patientId, int procedureId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
