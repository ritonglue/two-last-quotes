package fjp.converter.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

import fjp.converter.dao.QuoteDAO;
import fjp.converter.dao.SecurityDAO;
import fjp.converter.entity.Quote;
import fjp.converter.entity.Security;
import javax.inject.Inject;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@WebServlet("/test")
public class Test extends HttpServlet {
	@Inject
	private QuoteDAO dao;
	@Inject
	private SecurityDAO daos;

	private Security createSecurity(long id) {
		Security s = new Security();
		s.setId(id);
		daos.create(s);
		return s;
	}
	private void createQuote(long id, Security s, LocalDate d, int value) {
		Quote q = new Quote();
		q.setId(id);
		q.setSecurity(s);
		q.setDate(d);
		q.setValue(value);
		dao.create(q);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		dao.deleteAll();
		daos.deleteAll();
		//create securities
		long id = 1;
		List<Security> list = new ArrayList<>();
		list.add(createSecurity(id++));
		list.add(createSecurity(id++));
		list.add(createSecurity(id++));
		list.add(createSecurity(id++));

		id = 1;
		int value = 10;
		LocalDate d = LocalDate.now();
		Security s = list.get(0);
		createQuote(id++, s, d = d.minusDays(1), value++);
		createQuote(id++, s, d = d.minusDays(1), value++);
		createQuote(id++, s, d = d.minusDays(1), value++);
		createQuote(id++, s, d = d.minusDays(1), value++);
		createQuote(id++, s, d = d.minusDays(1), value++);

		s = list.get(1);
		createQuote(id++, s, d = d.minusDays(1), value++);
		createQuote(id++, s, d = d.minusDays(1), value++);
		createQuote(id++, s, d = d.minusDays(1), value++);

		s = list.get(2);
		createQuote(id++, s, d = d.minusDays(1), value++);

		var quotes = dao.findAll();
		for(Quote q : quotes) {
			System.out.println(q);
		}

		var list2 = dao.findLastTwo();
		for(Object[] o : list2) {
			System.out.println(String.format("(%s) (%s)", o[0], o[1]));
		}
	}
}
