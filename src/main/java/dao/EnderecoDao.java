package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entity.Denuncia;
import entity.Endereco;
import entity.HibernateUtil;

public class EnderecoDao {

	
public void salvaEndereco (Endereco endereco) {
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(endereco);
		s.getTransaction().commit();
		s.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Denuncia> listDenuncia(String strPesquisa) {
		List<Denuncia> list = new ArrayList<Denuncia>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		
		Criteria crit = s.createCriteria(Endereco.class);
		crit.add(Restrictions.like("Endereco", '%' + strPesquisa + '%'));
		list = crit.list();
		// SQL list = s.createSQLQuery("SELECT * FROM Denuncia WHERE Documento_Denuncia LIKE '%strPesquisa%'").list();
		//list = s.createQuery("from Denuncia d where d.Documento_Denuncia= : strPesquisa").setString("strPesquisa",strPesquisa).list();
		
		s.getTransaction().commit();
		s.close();
		return list;
	}
	
	public void removeEndereco(Integer id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Endereco e = (Endereco) s.load(Endereco.class, id);
		s.delete(e);
		s.getTransaction().commit();
		s.close();
	}

	public void editarEndereco(Endereco endereco) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(endereco);
		s.getTransaction().commit();
		s.close();
	}
}
