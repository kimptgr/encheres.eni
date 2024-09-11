package fr.eni.encheres.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

@Repository
public class ArticleVenduDAOImpl implements ArticleVenduDAO {


	private final String READ_ALL_ARTICLES = "select no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie  from ARTICLES_VENDUS";
	private final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS ([nom_article], [description], date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :no_utilisateur, :no_categorie);";

		
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public void create(ArticleVendu articleVendu) {
	
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("nom_article", articleVendu.getNomArticle());
		namedParameters.addValue("description", articleVendu.getDescription());
		namedParameters.addValue("date_debut_encheres", articleVendu.getDateDebutEncheres());
		namedParameters.addValue("date_fin_encheres", articleVendu.getDateFinEncheres());
		namedParameters.addValue("prix_initial", articleVendu.getMiseAPrix());
		namedParameters.addValue("no_utilisateur", articleVendu.getVendeur().getNoUtilisateur());
		namedParameters.addValue("no_categorie", articleVendu.getCategorie().getNoCategorie());
		

		jdbcTemplate.update(INSERT_ARTICLE, namedParameters, keyHolder, new String[]{"no_article"});

		if (keyHolder != null && keyHolder.getKey() != null) {
			// Mise à jour de l'identifiant du film auto-généré par la base
			articleVendu.setNoArticle((int) keyHolder.getKey().longValue());
		}
		
		Retrait retrait = new Retrait(articleVendu.getVendeur().getRue(), articleVendu.getVendeur().getCodePostal(),articleVendu.getVendeur().getVille(), articleVendu );
		String INSERT_RETRAIT = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES (:no_article, :rue, :code_postal, :ville)";
		MapSqlParameterSource namedParametersRetrait = new MapSqlParameterSource();
		namedParametersRetrait.addValue("no_article", articleVendu.getNoArticle());
		namedParametersRetrait.addValue("rue", articleVendu.getVendeur().getRue());
		namedParametersRetrait.addValue("code_postal", articleVendu.getVendeur().getCodePostal());
		namedParametersRetrait.addValue("ville", articleVendu.getVendeur().getVille());
		
		jdbcTemplate.update(INSERT_RETRAIT, namedParametersRetrait);
		System.out.println(retrait);

	}

	

	
	
	@Override
	public List<ArticleVendu> readAll() {
		return jdbcTemplate.query(READ_ALL_ARTICLES, new ArticleVenduMapper());
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
