package fjp.converter.dao;

import java.util.List;

import fjp.converter.entity.Security;

public interface SecurityDAO {
	public Security find(long i);
	public void create(Security e);
	public void delete(Security e);
	public void delete(long i);
	public void deleteAll();
}
