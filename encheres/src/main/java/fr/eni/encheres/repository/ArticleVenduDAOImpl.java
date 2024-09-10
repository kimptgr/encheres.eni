package fr.eni.encheres.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

@Repository
public class ArticleVenduDAOImpl implements ArticleVenduDAO {

	private final String READ_ALL_FILM = "select no_article, nom_article, description, date_debut_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie  from ARTICLES_VENDUS";
		
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public void create(ArticleVendu articleVendu) {
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public List<ArticleVendu> readAll() {
		return jdbcTemplate.query(READ_ALL_FILM, new ArticleVenduMapper());
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
			
			
			// Association avec catégorie
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(rs.getInt("no_categorie"));
			article.setCategorie(categorie);
			
			// Association avec utilisateur
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
			article.setVendeur(utilisateur);

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
