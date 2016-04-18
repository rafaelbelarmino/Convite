package dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import antlr.collections.List;
import entidade.Pessoa;
import hibernate.HibernateUtil;

public class PessoaDao extends GenericDao<integer, Pessoa>{
private static PessoaDao instance;
	
	public static PessoaDao getInstance() {	
		
		instance = new PessoaDao();	
		
		return instance;
	}
	public List<Pessoa> getAll() throws HibernateException {
		// TODO Auto-generated method stub
		return getAll("Pessoa.getAll");
	}

	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return Pessoa.class;
	}

	public Pessoa find(Pessoa entity) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Pessoa getByKeyAuth(String keyAuth) throws HibernateException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Pessoa pessoa = null;
		
		try {
			
			String hql = "from Pessoa keyAuth = :keyAuth";
			
			Query query = session.createQuery(hql);
			query.setParameter("keyAuth", keyAuth);			
			
			pessoa = (Pessoa) query.uniqueResult();
	        
		} catch (HibernateException hibernateException) {
			
			session.getTransaction().rollback();
			
			throw new HibernateException(hibernateException);
			
		} finally {
		
			session.close();
		}
		
		return pessoa;
	}
}


  