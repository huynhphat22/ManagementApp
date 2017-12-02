package Model.DAO;

import Model.DTO.Payment;

public interface PaymentDAO {

	public Payment save(Payment payment);

	public Payment update(Payment payment);

	public void delete(int id);

	public Payment findById(int id);

	public Iterable<Payment> findAll();
}
