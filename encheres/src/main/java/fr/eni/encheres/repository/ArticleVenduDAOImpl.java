package fr.eni.encheres.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	private final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS ([nom_article], [description], date_debut_encheres, date_fin_encheres, prix_initial,prix_vente, no_utilisateur, no_categorie) VALUES (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :prix_vente :no_utilisateur, :no_categorie);";
	private final String READ_ALL_ARTICLES = "select a.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, a.no_utilisateur, a.no_categorie, u.pseudo, c.libelle, r.rue, r.code_postal, r.ville"
			+ " from ARTICLES_VENDUS A"
			+ " join CATEGORIES C on a.no_categorie=c.no_categorie"
			+ " join UTILISATEURS U on  a.no_utilisateur=u.no_utilisateur"
			+ " join RETRAITS R on a.no_article=r.no_article";		
	private final String UPDATE_PRIX_VENTE = "UPDATE ARTICLES_VENDUS SET prix_vente = :prixVente where no_article = :noArticle";
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
		namedParameters.addValue("prix_vente", articleVendu.getPrixVente());
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
	
	//requette pour recherche tous les articles
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
	
	//requette pour rechercher les articles par num article
	@Override
	public ArticleVendu readById(Integer noArticle) {
		var sql = READ_ALL_ARTICLES + " where A.no_article=?";
		return jdbcTemplate.queryForObject(sql, new ArticleVenduMapper(), noArticle);
	}

	



//	//requette pour rechercher les articles par catégorie
//	@Override
//	public List<ArticleVendu> findByCategorie(Integer noCategorie) {
//		var sql = READ_ALL_ARTICLES + " WHERE A.no_categorie = ?";
//		return jdbcTemplate.query(sql, new ArticleVenduMapper(), noCategorie);
//		 }
//	
//
//	//requette pour rechercher les articles par nom de l'article (barre de recherche)
//	@Override
//	public List<ArticleVendu> findByNom(String searchTerm) {
//		var sql = READ_ALL_ARTICLES + " WHERE A.nom_article LIKE ?";
//		String queryParam = "%" + searchTerm + "%";
//		return jdbcTemplate.query(sql, new ArticleVenduMapper(), queryParam);
//	}
	
	
	//requette dynamique qui gère à la fois le filtre avec catégorie et recherche dans la barre
	@Override
	public List<ArticleVendu> findFilteredArticles(Integer noCategorie, String searchTerm) {
	    // Base de la requête
	    String sql = READ_ALL_ARTICLES + " WHERE 1=1";  // "1=1" permet d'ajouter des conditions facilement
	    
	    List<Object> params = new ArrayList<>();
	    
	    // Filtrer par catégorie si présente
	    if (noCategorie != null) {
	        sql += " AND a.no_categorie = ?";
	        params.add(noCategorie);
	    }
	    
	    // Filtrer par nom si présent
	    if (searchTerm != null && !searchTerm.isEmpty()) {
	        sql += " AND a.nom_article LIKE ?";
	        params.add("%" + searchTerm + "%");  // Utilisation des jokers pour la recherche partielle
	    }

	    // Exécuter la requête avec les paramètres ajoutés
	    return jdbcTemplate.query(sql, new ArticleVenduMapper(), params.toArray());
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public ArticleVendu updatePrixVenteById(Integer noArticle, Integer prixVente) {
		return null;
	}

	@Override
	public ArticleVendu deleteById(Integer noArticle) {
		return null;
	}

	@Override
	public ArticleVendu updateById(Integer noArticle) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
