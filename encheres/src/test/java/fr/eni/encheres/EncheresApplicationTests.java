package fr.eni.encheres;



import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.repository.ArticleVenduDAO;
import fr.eni.encheres.repository.CategorieDAO;

@SpringBootTest
class EncheresApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
	@Autowired
	private ArticleVenduDAO articleVenduDAO ;
	
	@Autowired
	private CategorieDAO categorieDAO;
	
	@Test
	public void c1_readAllCategorie() {
        List<Categorie> cats = categorieDAO.readAll();
        cats.forEach(System.out::println);
	}
	
	@Test
	public void c2_readOneCategorie() {
        Categorie cats = categorieDAO.readById(1);
        System.out.println(cats);
	}

	

//	@Test
//	    public void testFindAllArticles() {
//	        // Appel de la méthode findAllArticles
//	        List<ArticleVendu> articles = articleVenduService.findAllArticles();
//	        
//	        // Vérification du résultat
//	        assertNotNull(articles);
//	        assertEquals(3, articles.size());
//	        articles.forEach(article -> System.out.println(article));
//	    }
	   
	   @Test
	    void testReadAll() {
	        List<ArticleVendu> article = articleVenduDAO.readAll();
	        article.forEach(System.out::println);
	        }
	   
	   @Test
	    void testReadUnArticle() {
	        var article = articleVenduDAO.readById(18);
	        System.out.println(article);
	        }
	   
//	   @Test
//	    void TestFindByCategorie() {
//	        var articleCat = articleVenduDAO.findByCategorie(2);
//	        articleCat.forEach(System.out::println);
//	        }
	   
//	   @Test
//	    void TestFindByNom() {
//	        var articleNOM = articleVenduDAO.findByNom("Ordinateur");
//	        articleNOM.forEach(System.out::println);
//	        }
	   
	   @Test
	    void TestFindFilter() {
	        var articlesFiltres = articleVenduDAO.findFilteredArticles(3, null,"nonDebutees");
	        articlesFiltres.forEach(System.out::println);
	        }
	   

}
