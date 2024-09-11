package fr.eni.encheres;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;

import fr.eni.encheres.bll.ArticleVenduService;
import fr.eni.encheres.bo.ArticleVendu;

@SpringBootApplication
public class EncheresApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncheresApplication.class, args);
	}
}
