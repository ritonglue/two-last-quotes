package fjp.converter.dao;


import javax.ejb.Stateless;
import javax.ejb.Local;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import fjp.converter.entity.Security;

@Local(SecurityDAO.class)
@Stateless
public class SecurityDAOImpl implements SecurityDAO {
	@PersistenceContext
	private EntityManager em;

	public Security find(long i) {
		return em.find(Security.class, i);
	}
	@Override
	public void create(Security e) {
		em.persist(e);
	}
	@Override
	public void delete(long i) {
		var e = this.find(i);
		if(e != null) em.remove(e);
	}
	@Override
	public void delete(Security e) {
		if(e == null) return;
		delete(e.getId());
	}
	@Override
	public void deleteAll() {
		em.createNamedQuery("Security.deleteAll").executeUpdate();
	}
}
