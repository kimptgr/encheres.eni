package fr.eni.encheres.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fr.eni.encheres.bo.ArticleVendu;

@Repository
public class ArticleVenduDAOImpl implements ArticleVenduDAO {

	private final String READ_ALL_FILM_WITH_JOIN = "select a.*, c.libelle, u.pseudo, u.email  from ARTICLES_VENDUS A  \r\n"
			+ "join CATEGORIES C on C.no_categorie=a.no_categorie\r\n"
			+ "join UTILISATEURS U on u.no_utilisateur=a.no_utilisateur";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void create(ArticleVendu articleVendu) {
		
	}

	@Override
	public List<ArticleVendu> readAll() {
		return jdbcTemplate.query(READ_ALL_FILM_WITH_JOIN, new ArticleVenduMapper());
	}

	class ArticleVenduMapper implements RowMapper<ArticleVendu> {
		@Override
		public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
			var article = new ArticleVendu();
			article.setNomArticle(rs.getString("nom_article"));
			article.setDescription(rs.getString("description"));
		    
			java.sql.Date dateDebutEncheresSql = rs.getDate("date_debut_encheres"); 
		     { article.setDateDebutEncheres(dateDebutEncheresSql.toLocalDate()); }
		    
		     java.sql.Date dateFinEncheresSql = rs.getDate("date_fin_encheres");
		      {article.setDateFinEncheres(dateFinEncheresSql.toLocalDate());}
			
		    article.setMiseAPrix(rs.getInt("prix_initial"));
			article.setPrixVente(rs.getInt("prix_vente"));
			article.getCategorie().setLibelle(rs.getString("libelle"));
			article.getAcheteur().setPseudo(rs.getString("pseudo"));
			article.getAcheteur().setEmail(rs.getString("email"));

			return article;
		}
	}

	
	
	@Override
	public ArticleVendu readById(Integer noArticle) {
		return null;
	}

	@Override
	public ArticleVendu updateById(Integer noArticle) {
		return null;
	}

	@Override
	public ArticleVendu deleteById(Integer noArticle) {
		return null;
	}



}
