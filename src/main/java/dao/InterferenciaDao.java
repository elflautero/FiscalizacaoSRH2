package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entity.HibernateUtil;
import entity.Interferencia;

public class InterferenciaDao {

	
public void salvaInterferencia (Interferencia interferencia) {
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(interferencia);
		s.getTransaction().commit();
		s.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Interferencia> listInterferencia(String strPesquisaInterferencia) {
		
		List<Interferencia> list = new ArrayList<Interferencia>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		
		Criteria crit = s.createCriteria(Interferencia.class);
		crit.add(Restrictions.like("inter_Desc_Endereco", '%' + strPesquisaInterferencia + '%'));
		list = crit.list();
			
		s.getTransaction().commit();
		s.close();
		return list;
	}
	
	public void removeInterferencia(Integer id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Interferencia i = (Interferencia) s.load(Interferencia.class, id);
		s.delete(i);
		s.getTransaction().commit();
		s.close();
	}

	public void editarInterferencia(Interferencia interferencia) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(interferencia);
		s.getTransaction().commit();
		s.close();
	}
	
	public void mergeInterferencia(Interferencia interferencia) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.merge(interferencia);
		s.getTransaction().commit();
		s.close();
	}
	
	public void persistInterferencia(Interferencia interferencia) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.persist(interferencia);
		s.getTransaction().commit();
		s.close();
	}
	
}
