package org.example.sastruts.test.action;

import org.example.sastruts.test.dto.BookDto;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.struts.annotation.Execute;

import javax.annotation.Resource;
import java.util.List;

public class IndexAction {

    @Resource
    protected JdbcManager jdbcManager;
    public List<BookDto> bookList;

    @Execute(validator = false)
    public String list() {
        this.bookList = jdbcManager
                .selectBySql(BookDto.class, "SELECT * FROM book")
                .getResultList();
        return "booklist.jsp";
    }
}
