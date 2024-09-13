package fr.eni.encheres.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
	
	private final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS ([nom_article], [description], date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :no_utilisateur, :no_categorie);";
	private final String READ_ALL_ARTICLES = "select a.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, a.no_utilisateur, a.no_categorie, u.pseudo, c.libelle, r.rue, r.code_postal, r.ville"
			+ " from ARTICLES_VENDUS A"
			+ " join CATEGORIES C on a.no_categorie=c.no_categorie"
			+ " join UTILISATEURS U on  a.no_utilisateur=u.no_utilisateur"
			+ " join RETRAITS R on a.no_article=r.no_article";		
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
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
		

		namedParameterJdbcTemplate.update(INSERT_ARTICLE, namedParameters, keyHolder, new String[]{"no_article"});

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
		
		namedParameterJdbcTemplate.update(INSERT_RETRAIT, namedParametersRetrait);

	}
	

	@Override
	public List<ArticleVendu> readAll() {
		return jdbcTemplate.query(READ_ALL_ARTICLES, new ArticleVenduMapper());
	}

	
	
	class ArticleVenduMapper implements RowMapper<ArticleVendu> {
		@Override
		public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
			var article = new ArticleVendu();
			article.setNoArticle(rs.getInt("no_article"));
			article.setNomArticle(rs.getString("nom_article"));
			article.setDescription(rs.getString("description"));
			
			
			article.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres").toLocalDateTime());
			article.setDateFinEncheres(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
		    
		    article.setMiseAPrix(rs.getInt("prix_initial"));
			article.setPrixVente(rs.getInt("prix_vente"));
			
			article.setCategorie(new Categorie(rs.getInt("no_categorie"),(rs.getString("libelle"))));
			article.setVendeur(new Utilisateur(rs.getInt("no_utilisateur"),(rs.getString("pseudo"))));
			article.setRetrait(new Retrait(rs.getString("rue"),rs.getString("code_postal"),(rs.getString("ville"))));


			return article;
		}
	}
	
	
	@Override
	public ArticleVendu readById(Integer noArticle) {
		var sql = READ_ALL_ARTICLES + " where A.no_article=?";
		return jdbcTemplate.queryForObject(sql, new ArticleVenduMapper(), noArticle);
	}

	@Override
	public ArticleVendu updateById(Integer noArticle) {
		return null;
	}

	@Override
	public ArticleVendu deleteById(Integer noArticle) {
		return null;
	}



	@Override
	public List<ArticleVendu> findByCategorie(Integer noCategorie) {
		var sql = READ_ALL_ARTICLES + " WHERE A.no_categorie = ?";
		return jdbcTemplate.query(sql, new ArticleVenduMapper(), noCategorie);
		 }
}
