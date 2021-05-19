package fjp.converter.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Index;

import java.io.Serializable;

@NamedQuery(name="Quote.deleteAll", query="delete from Quote")
@NamedQuery(name="Quote.findAll", query="select q from Quote q"
//+ " left join fetch q.security"
+ " order by q.security.id, q.date")
@NamedQuery(name="Quote.findLastTwo", query="select q,qq from Quote q"
+ " left join fetch q.security"
+ " left join Quote qq on (qq.security = q.security and qq.date ="
+ " (select max(t.date) from Quote t where t.security = qq.security and t.date < q.date))"
+ " where q.date = (select max(tt.date) from Quote tt where tt.security = q.security)")
@Entity
@Table(indexes=@Index(columnList="security_id, quote_date", unique=true))
public class Quote implements Serializable{

	@Id
	private long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="security_id", nullable=false)
	private Security security;

	@Column(name="quote_date", nullable=false)
	private LocalDate date;

	private int value;


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return this.date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int v) {
		this.value = v;
	}

	public Security getSecurity() {
		return this.security;
	}
	public void setSecurity(Security s) {
		this.security = s;
	}

	@Override
	public String toString() {
		return String.format("id=%d, securityId=%s, date='%s', value=%s", id, security.getId(), date, value);
	}
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(!(o instanceof Quote)) return false;
		Quote e = (Quote) o;
		return e.getId() == getId();
	}

	@Override
	public int hashCode() {
		return Long.hashCode(getId());
	}
}
