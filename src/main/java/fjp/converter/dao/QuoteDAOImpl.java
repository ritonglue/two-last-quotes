package fjp.converter.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.Local;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import fjp.converter.entity.Quote;

@Local(QuoteDAO.class)
@Stateless
public class QuoteDAOImpl implements QuoteDAO {
	@PersistenceContext
	private EntityManager em;

	public Quote find(long i) {
		return em.find(Quote.class, i);
	}
	@Override
	public void create(Quote e) {
		em.persist(e);
	}
	@Override
	public void delete(long i) {
		var e = this.find(i);
		if(e != null) em.remove(e);
	}
	@Override
	public void delete(Quote e) {
		if(e == null) return;
		delete(e.getId());
	}

	@Override
	public List<Object[]> findLastTwo() {
		return em.createNamedQuery("Quote.findLastTwo")
			.getResultList();
	}
	@Override
	public List<Quote> findAll() {
		return em.createNamedQuery("Quote.findAll", Quote.class)
			.getResultList();
	}
	@Override
	public void deleteAll() {
		em.createNamedQuery("Quote.deleteAll").executeUpdate();
	}
}
