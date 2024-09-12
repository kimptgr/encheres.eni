/**
 * 
 */
package fr.eni.encheres.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : kpatingre2024
 * @since: 12 sept. 2024 - 14:11:40
 */
@Repository
public class RetraitDAOImpl implements RetraitDAO {
	private final String SELECT_RETRAIT_BY_NO_ARTICLE ="SELECT no_Article, rue, code_postal, ville from RETRAITS where no_article = ?;";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Retrait readByNoArticle(Integer noArticle) {
		return jdbcTemplate.queryForObject(SELECT_RETRAIT_BY_NO_ARTICLE,new RetraitRowMapper(), noArticle);
	}

	class RetraitRowMapper implements RowMapper<Retrait> {
		@Override
		public Retrait mapRow(ResultSet rs, int rowNum) throws SQLException {
			Retrait r = new Retrait();
			r.setRue(rs.getString("rue"));
			r.setCodePostal(rs.getString("code_postal"));
			r.setVille(rs.getString("ville"));

			// Association vers le membre
			ArticleVendu articleVendu = new ArticleVendu();
			articleVendu.setNoArticle(rs.getInt("no_Article"));
			r.setArticleVendu(articleVendu);
			return r;
		}
	}
	
}
