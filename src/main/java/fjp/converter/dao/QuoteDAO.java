package fjp.converter.dao;

import java.util.List;

import fjp.converter.entity.Quote;

public interface QuoteDAO {
	public Quote find(long i);
	public void create(Quote e);
	public void delete(Quote e);
	public void delete(long i);
	public List<Object[]> findLastTwo();
	public List<Quote> findAll();
	public void deleteAll();
}
