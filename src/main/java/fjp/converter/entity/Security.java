package fjp.converter.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import java.io.Serializable;

@NamedQuery(name="Security.deleteAll", query="delete from Security")
@Entity
public class Security implements Serializable{

	@Id
	private long id;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("id=%d", id);
	}
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(!(o instanceof Security)) return false;
		Security e = (Security) o;
		return e.getId() == getId();
	}

	@Override
	public int hashCode() {
		return Long.hashCode(getId());
	}
}
