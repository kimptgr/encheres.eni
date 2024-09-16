/**
 * 
 */
package fr.eni.encheres;


import java.time.LocalDateTime;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.repository.ArticleVenduDAOImpl;
import fr.eni.encheres.repository.EnchereDAO;

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
    @Autowired
    private EnchereDAO enchereDao;

    @Test
    public void a1_testCreateArticleVendu() {
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
    @Test
    public void b1_testInsertionEnchre() {
    	var av = new ArticleVendu();
    	av.setNoArticle(3);
    	Enchere e = new Enchere();
    	e.setArticleVendus(av);
    	e.setDateEnchere(LocalDateTime.now());
    	e.setMontantEnchere(5);
    	var u = new Utilisateur();
    	u.setNoUtilisateur(1);
    	e.setUtilisateur(u);
    	enchereDao.createEnchere(e);
    }
    @Test
    public void b2_testUpdateEnchre() {
    	var av = new ArticleVendu();
    	av.setNoArticle(2);
    	Enchere e = new Enchere();
    	e.setArticleVendus(av);
    	e.setDateEnchere(LocalDateTime.now());
    	e.setMontantEnchere(4);
    	var u = new Utilisateur();
    	u.setNoUtilisateur(1);
    	e.setUtilisateur(u);
    	enchereDao.updateEnchere(e);
    }
    
    @Test
    void b3_readAllEncheres(){
    	var e = enchereDao.readAllEncheres();
    	System.err.println("Test b3");
    	e.forEach(System.out::println);
    	
    }
    
    @Test
    void b4_readEnchereByNoArticle(){
    	var e = enchereDao.readEncheresByNoArticle(1);
    	System.err.println("Test b4");
    	System.out.println(e);
    }
   
    @Test
    void b5_readEncheresByNoUser(){
    	var e = enchereDao.readEncheresByNoUser(1);
    	System.err.println("Test b5");
    	e.forEach(System.out::println);
    }

}
