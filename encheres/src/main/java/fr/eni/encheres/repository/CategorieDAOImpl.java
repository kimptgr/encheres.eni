/**
 * 
 */
package fr.eni.encheres.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.encheres.bo.Categorie;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : kpatingre2024
 * @since: 11 sept. 2024 - 10:18:40
 */
@Repository
public class CategorieDAOImpl implements CategorieDAO{
	private final String READ_ALL_CAT="select no_categorie, libelle from categories;";
	private final String READ_CAT_BY_ID="select no_categorie, libelle from categories WHERE no_categorie=?;";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Categorie readById(Integer id) {
		var categorie = jdbcTemplate.queryForObject(READ_CAT_BY_ID, new BeanPropertyRowMapper<>(Categorie.class), id);
		return categorie;
	}

	@Override
	public List<Categorie> readAll() {
		var categories = jdbcTemplate.query(READ_ALL_CAT, new BeanPropertyRowMapper<>(Categorie.class));
		return categories;
	}

}
