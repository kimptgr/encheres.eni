/**
 * 
 */
package fr.eni.encheres.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Classe en charge de
 * 
 * @projet : encheres - V1.0
 * @author : aferry2024
 * @since: 9 sept. 2024 - 13:34:57
 */
@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {
	//Jointure Utilisateurs+Encheres+Articles_Vendus
	private  final String FIND_ALL ="SELECT no_utilisateur,pseudonom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateurFROM UTILISATEURS;";
			
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void create(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utilisateur readById(Integer noUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur readByPseudo(String pseudo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur readByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
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
			u.setAdministrateur(rs.getByte("administrateur"));
			
			//Association Encheres
			Enchere e = new Enchere();
			//Pour mapper le champ dateEnchere
			Timestamp timestamp =rs.getTimestamp("date_enchere");
			if (timestamp != null) {
				e.setDateEnchere(timestamp.toLocalDateTime());
			}
			e.setMontant_enchere(rs.getInt("montant_enchere"));
			//u.setEnchere(e);


			return u;
		}
	}

}
