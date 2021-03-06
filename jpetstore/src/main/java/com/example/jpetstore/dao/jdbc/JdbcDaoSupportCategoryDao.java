package com.example.jpetstore.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import com.example.jpetstore.dao.CategoryDao;
import com.example.jpetstore.domain.Category;
// import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

//@Repository	// 설정 파일에서 명시적  DI 설정
public class JdbcDaoSupportCategoryDao extends JdbcDaoSupport implements CategoryDao {
  
	private static final String SELECT_CATEGORY_LIST_SQL = "select CATID, NAME, DESCN from CATEGORY ";														 
	private static final String SELECT_CATEGORY_SQL = "select CATID, NAME || ' (by JdbcDaoSupport)' as NAME, DESCN "
													+ "from CATEGORY where CATID = ?";

	public List<Category> getCategoryList() throws DataAccessException {
		List<Category> list = getJdbcTemplate().query(SELECT_CATEGORY_LIST_SQL,
			new RowMapper<Category>() {
				public Category mapRow(ResultSet rs, int rowNum)throws SQLException {
					Category category = new Category();
					category.setCategoryId(rs.getString("CATID"));
					category.setName(rs.getString("NAME"));
					category.setDescription(rs.getString("DESCN"));
					return category;
				}
			});
		return list;	  
	}

	public Category getCategory(String categoryId) throws DataAccessException {
		Category category = (Category) getJdbcTemplate().queryForObject(SELECT_CATEGORY_SQL,
				new Object[] { categoryId }, new RowMapper<Category>() {
					public Category mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Category category = new Category();
						category.setCategoryId(rs.getString("CATID"));
						category.setName(rs.getString("NAME"));
						category.setDescription(rs.getString("DESCN"));
						return category;
					}
				});
		return category;	 
	}

	private static final String SELECT_COUNT_SQL = "select count(*) from CATEGORY";
	public int selectCount() {
		return getJdbcTemplate().queryForObject(SELECT_COUNT_SQL, Integer.class);
	}

	static final String INSERT_SQL = "insert into GUESTBOOK_MESSAGE (GUEST_NAME, CONTENT) values (?, ?)";
	public void insertNewCategory(Category category) {
		getJdbcTemplate().update(INSERT_SQL,
			new Object[] { category.getCategoryId(), category.getName(), category.getDescription() });
	}
}
