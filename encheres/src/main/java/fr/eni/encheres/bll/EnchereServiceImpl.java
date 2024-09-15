/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.repository.EnchereDAO;
import fr.eni.encheres.repository.UtilisateurDAO;

@Service
public class EnchereServiceImpl implements EnchereService {
	private EnchereDAO enchereDAO;
	private UtilisateurDAO utilisateurDAO;
	
	public EnchereServiceImpl(EnchereDAO enchereDAO, UtilisateurDAO utilisateurDAO) {
		this.enchereDAO = enchereDAO;
		this.utilisateurDAO = utilisateurDAO;
	}
	
	@Transactional
	@Override
	public void addEnchere(Enchere e) {
		boolean isValid = true;

		isValid &= isAuctionDateValid(e);
		isValid &= isAuctionHigher(e);
		isValid &= isUserRichEnough(e);
		
		// lower the balance of the new bidder
		if (isValid) {
			System.out.println("EnchereServiceImpl.addEnchere()");
			var credit = e.getUtilisateur().getCredit();
			var proposition = e.getMontantEnchere();
			e.getUtilisateur().setCredit(credit-proposition);
			utilisateurDAO.updateCredit(e.getUtilisateur());
		}
		
		List<Enchere> encheres = findEnchereByNoArticle(e.getArticleVendu().getNoArticle());
		if (isValid && encheres != null && !encheres.isEmpty() ) {
		Enchere highestBid = encheres.stream()
                .max(Comparator.comparing(Enchere::getMontantEnchere)).orElse(null);
		Utilisateur previousBidder = highestBid.getUtilisateur();
		
		//return the money to the previous highest bidder
		if (isValid && encheres!= null && !encheres.isEmpty()) {
			System.err.println("EnchereServiceImpl.addEnchere()" + previousBidder == null);
			previousBidder.setCredit(previousBidder.getCredit()+highestBid.getMontantEnchere());
			utilisateurDAO.updateCredit(previousBidder);
		};
		}
		//verify if bidder must be update or create
		if (isValid && !encheres.isEmpty()
				&& encheres.stream().anyMatch(a -> a.getUtilisateur().getNoUtilisateur() == e.getUtilisateur().getNoUtilisateur()))
			enchereDAO.updateEnchere(e);
			else {
				enchereDAO.createEnchere(e);
			};
	}

	@Override
	public List<Enchere> findAllEncheres() {
		return enchereDAO.readAllEncheres();
	}

	@Override
	public List<Enchere> findEnchereByNoArticle(Integer noArticle) {
		return enchereDAO.readEncheresByNoArticle(noArticle);
	}

	@Override
	public List<Enchere> findEncheresByNoUser(Integer noUser) {
		return enchereDAO.readEncheresByNoUser(noUser);
	}

	@Override
	public void modifyEnchere(Enchere enchere) {	
	}
	
	private boolean isAuctionDateValid(Enchere e) {
		System.out.println("EnchereServiceImpl.isAuctionDateValid()");
		boolean isValid = true ;
		var dateEnchere = e.getDateEnchere();
		var dateFinEnchere = e.getArticleVendu().getDateFinEncheres();
		var dateDebutEnchere = e.getArticleVendu().getDateDebutEncheres();
		if (dateEnchere.isBefore(dateDebutEnchere) || dateEnchere.isAfter(dateFinEnchere)) 
			isValid = false;
		return isValid;
	}
	
	private boolean isAuctionHigher(Enchere e) {
		System.out.println("EnchereServiceImpl.isAuctionHigher()");
		var proposition = e.getMontantEnchere();
		var otherAuctions = enchereDAO.readEncheresByNoArticle(e.getArticleVendu().getNoArticle());
		
		return !otherAuctions.stream().anyMatch(a -> a.getMontantEnchere() > proposition);
	}
	
	private boolean isUserRichEnough(Enchere e) {
		System.out.println("EnchereServiceImpl.isUserRichEnough()");
		var proposition = e.getMontantEnchere();
		var userMoney = e.getUtilisateur().getCredit();
		return (userMoney-proposition >= 0);
	}

}
