/**
 * 
 */
package fr.eni.encheres.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Classe en charge de
 * 
 * @projet : encheres - V1.0
 * @author : kim, ophélie, alex
 * @since: 9 sept. 2024 - 13:34:57
 */
@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {
	// Jointure Utilisateurs+Encheres+Articles_Vendus
	private final String INSERT = "INSERT INTO Utilisateurs(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) "
			+ " VALUES (:pseudo,:nom,:prenom,:email,:telephone,:rue,:code_postal,:ville,:mot_de_passe,:credit,:administrateur)";
	private final String FIND_ALL = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur "
			+ "FROM UTILISATEURS;";
	private final String FIND_BY_PSEUDO = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur "
			+ "FROM UTILISATEURS " + "WHERE PSEUDO=:pseudo;";
	private final String FIND_BY_ID = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur FROM UTILISATEURS WHERE no_utilisateur=:noUtilisateur;";
	private final String VERIF_BY_EMAIL = "select COUNT(*) from UTILISATEURS where email= ?;";

	private final String FIND_BY_EMAIL = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur"
			+ " FROM UTILISATEURS WHERE EMAIL= :email ;";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void create(Utilisateur utilisateur) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("pseudo", utilisateur.getPseudo());
		namedParameters.addValue("nom", utilisateur.getNom());
		namedParameters.addValue("prenom", utilisateur.getPrenom());
		namedParameters.addValue("email", utilisateur.getEmail());
		namedParameters.addValue("telephone", utilisateur.getTelephone());
		namedParameters.addValue("rue", utilisateur.getRue());
		namedParameters.addValue("code_postal", utilisateur.getCodePostal());
		namedParameters.addValue("ville", utilisateur.getVille());
		namedParameters.addValue("mot_de_passe", utilisateur.getMotDePasse());
		namedParameters.addValue("credit", utilisateur.getCredit());
		namedParameters.addValue("administrateur", utilisateur.getAdministrateur());

		jdbcTemplate.update(INSERT, namedParameters, keyHolder);

		if (keyHolder != null && keyHolder.getKey() != null) {
			utilisateur.setNoUtilisateur(keyHolder.getKey().intValue());
		}

	}

	@Override
	public Utilisateur readById(Integer noUtilisateur) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("noUtilisateur", noUtilisateur);
		return jdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, new UtilisateurRowMapper());
	}

	@Override
	public Utilisateur readByPseudo(String pseudo) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("pseudo", pseudo);
		return jdbcTemplate.queryForObject(FIND_BY_PSEUDO, namedParameters, new UtilisateurRowMapper());
	}
	
	@Override
	public Utilisateur readByEmail(String email) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("email", email);
		return jdbcTemplate.queryForObject(FIND_BY_EMAIL, namedParameters, new UtilisateurRowMapper());
	}
	@Override
	public boolean verifByEmail(String email) {
		var sql=VERIF_BY_EMAIL;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("email", email);
		Integer count = jdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
		return count != null && count > 0;
	}
	
	

	@Override
	public List<Utilisateur> readAll() {
		return jdbcTemplate.query(FIND_ALL, new UtilisateurRowMapper());
	}

	/**
	 * Classe de mapping pour gérer les liens d'association vers Genre - et
	 * Réalisateurs
	 */
	class UtilisateurRowMapper implements RowMapper<Utilisateur> {
		@Override
		public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
			Utilisateur u = new Utilisateur();
			u.setNoUtilisateur(rs.getInt("no_utilisateur"));
			u.setPseudo(rs.getString("pseudo"));
			u.setPrenom(rs.getString("nom"));
			u.setPrenom(rs.getString("prenom"));
			u.setEmail(rs.getString("email"));
			u.setTelephone(rs.getString("telephone"));
			u.setRue(rs.getString("rue"));
			u.setCodePostal(rs.getString("code_postal"));
			u.setVille(rs.getString("ville"));
			u.setMotDePasse(rs.getString("mot_de_passe"));
			u.setCredit(rs.getInt("credit"));
			u.setAdministrateur(rs.getBoolean("administrateur"));

			return u;
		}
	}

	@Override
	public void update(Utilisateur utilisateur) {
		String UPDATE_USER = "UPDATE Utilisateurs SET pseudo = :pseudo,prenom = :prenom,nom= :nom,email=:email,telephone=:telephone,rue=:rue,code_postal=:code_postal,ville=:ville,mot_de_passe=:mot_de_passe,credit=:credit,administrateur=:administrateur WHERE no_utilisateur = :noUtilisateur";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("pseudo", utilisateur.getPseudo());
		namedParameters.addValue("nom", utilisateur.getNom());
		namedParameters.addValue("prenom", utilisateur.getPrenom());
		namedParameters.addValue("email", utilisateur.getEmail());
		namedParameters.addValue("telephone", utilisateur.getTelephone());
		namedParameters.addValue("rue", utilisateur.getRue());
		namedParameters.addValue("code_postal", utilisateur.getCodePostal());
		namedParameters.addValue("ville", utilisateur.getVille());
		namedParameters.addValue("mot_de_passe", utilisateur.getMotDePasse());
		namedParameters.addValue("credit", utilisateur.getCredit());
		namedParameters.addValue("administrateur", utilisateur.getAdministrateur());
		namedParameters.addValue("noUtilisateur", utilisateur.getNoUtilisateur());
		jdbcTemplate.update(UPDATE_USER, namedParameters);
		
	}

	@Override
	public void updateCredit(Utilisateur utilisateur) {
		String UPDATE_CREDIT = "UPDATE Utilisateurs SET credit=:credit WHERE no_utilisateur = :noUtilisateur";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("credit", utilisateur.getCredit());
		namedParameters.addValue("noUtilisateur", utilisateur.getNoUtilisateur());
		jdbcTemplate.update(UPDATE_CREDIT, namedParameters);
		
	}

}
