package dao;

import org.hibernate.Session;
import entity.Fiscal;
import entity.HibernateUtil;

public class FiscalDao {

	// receber um objeto da classe Fiscal pelo método addFiscal
	public void addFiscal (Fiscal fiscal) {
		try {
		// cria uma nova sessão e captura pelo método estático do hibernate util
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(fiscal);
		s.getTransaction().commit(); //não estava querendo salvar no banco pois esqueci do gettransaction e do commit
		s.close();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
			
		}
	
	}
}