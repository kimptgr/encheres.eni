/**
 * 
 */
package fr.eni.encheres.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : kpatingre2024
 * @since: 13 sept. 2024 - 14:52:04
 */
@Repository
public class EnchereDAOImpl implements EnchereDAO {
//	String SELECT_ALL_ENCHERE_INNER_JOIN = "SELECT a.[no_article], a.[nom_article], a.[description], a.[date_debut_encheres], a.[date_fin_encheres], a.[prix_initial], a.[prix_vente], a.[no_utilisateur], a.[no_categorie], e.[date_enchere], e.[montant_enchere], u.[pseudo], u.[nom], u.[prenom], u.[email], u.[telephone], u.[rue], u.[code_postal], u.[ville], u.[credit] FROM [ENCHERES].[dbo].[ARTICLES_VENDUS] a INNER JOIN [ENCHERES].[dbo].[ENCHERES] e ON a.[no_article] = e.[no_article] INNER JOIN [ENCHERES].[dbo].[UTILISATEURS] u ON a.[no_utilisateur] = u.[no_utilisateur]";
	String SELECT_ALL_ENCHERE = "SELECT e.no_utilisateur, e.no_article, e.date_enchere, montant_enchere FROM ENCHERES e;";
	String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (:noUtilisateur, :noArticle, :dateEnchere, :montantEnchere);";
	String UPDATE_ENCHERE ="UPDATE ENCHERES SET date_enchere=:dateEnchere, montant_enchere = :montantEnchere WHERE no_article=:noArticle AND no_utilisateur = :noUtilisateur" ;
	String SELECT_ENCHERE_WITH_NOARTICLE = "SELECT e.no_utilisateur, e.no_article, e.date_enchere, montant_enchere FROM ENCHERES e WHERE no_article=:noArticle;";
	String SELECT_ALL_ENCHERE_BY_USERNAME = "SELECT e.no_utilisateur, e.no_article, e.date_enchere, montant_enchere FROM ENCHERES e WHERE no_utilisateur=:noUtilisateur;";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public void createEnchere(Enchere enchere) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("noUtilisateur", enchere.getUtilisateur().getNoUtilisateur());
		namedParameters.addValue("noArticle", enchere.getArticleVendu().getNoArticle());
		namedParameters.addValue("dateEnchere", enchere.getDateEnchere());
		namedParameters.addValue("montantEnchere", enchere.getMontantEnchere());

		namedParameterJdbcTemplate.update(INSERT_ENCHERE, namedParameters);
	}

	@Override
	public void updateEnchere(Enchere enchere) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("noUtilisateur", enchere.getUtilisateur().getNoUtilisateur());
		namedParameters.addValue("noArticle", enchere.getArticleVendu().getNoArticle());
		namedParameters.addValue("dateEnchere", enchere.getDateEnchere());
		namedParameters.addValue("montantEnchere", enchere.getMontantEnchere());

		namedParameterJdbcTemplate.update(UPDATE_ENCHERE, namedParameters);

	}

	@Override
	public List<Enchere> readAllEncheres() {
		return namedParameterJdbcTemplate.query(SELECT_ALL_ENCHERE, new EnchereRowMapper());
	}

	@Override
	public List<Enchere> readEncheresByNoArticle(Integer noArticle) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("noArticle", noArticle);
		return namedParameterJdbcTemplate.query(SELECT_ENCHERE_WITH_NOARTICLE, namedParameters, new EnchereRowMapper());
	}

	@Override
	public List<Enchere> readEncheresByNoUser(Integer noUser) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("noUtilisateur", noUser);
		
		return namedParameterJdbcTemplate.query(SELECT_ALL_ENCHERE_BY_USERNAME, namedParameters, new EnchereRowMapper());
	}
	
	class EnchereRowMapper implements RowMapper<Enchere> {
		
		@Override
		public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
			var e = new Enchere();
			e.setDateEnchere(rs.getTimestamp("date_enchere").toLocalDateTime());
			e.setMontantEnchere(rs.getInt("montant_enchere"));
			
			//Association vers l'utilisateur
			Utilisateur u = new Utilisateur();
			u.setNoUtilisateur(rs.getInt("no_utilisateur"));
			e.setUtilisateur(u);
			
			//Association vers l'article
			ArticleVendu av = new ArticleVendu();
			av.setNoArticle(rs.getInt("no_article"));
			e.setArticleVendus(av);
			return e;
		}
	}
}
