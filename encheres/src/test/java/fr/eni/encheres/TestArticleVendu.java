/**
 * 
 */
package fr.eni.encheres;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.repository.ArticleVenduDAOImpl;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : kpatingre2024
 * @since: 10 sept. 2024 - 14:23:36
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestArticleVendu {
    @Autowired
    private ArticleVenduDAOImpl articleVenduDAO;

    @Test
    public void testCreateArticleVendu() {
        Utilisateur user = new Utilisateur();
        user.setNoUtilisateur(1);
        user.setRue("chemin");
        user.setCodePostal("35000");
        user.setVille("Nantes");
        Categorie cat = new Categorie();
        cat.setNoCategorie(3);
        ArticleVendu velo = new ArticleVendu("Trotinette", "Elle roule", LocalDateTime.of(2024, 9, 8, 0, 0), LocalDateTime.of(2024, 11, 8, 0, 0), 5, user, cat);

        articleVenduDAO.create(velo);

    }
}
