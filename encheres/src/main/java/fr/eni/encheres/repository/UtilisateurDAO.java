/**
 *
 */
package fr.eni.encheres.repository;

import fr.eni.encheres.bo.Utilisateur;

import java.util.List;

/**
 * Interface en charge de
 *
 * @projet : encheres - V1.0
 * @author : kim, ophélie, alex
 * @since: 9 sept. 2024 - 13:33:59
 */
public interface UtilisateurDAO {

    public void create(Utilisateur utilisateur);

    public Utilisateur readById(Integer noUtilisateur);

    public Utilisateur readByPseudo(String pseudo);

    public List<Utilisateur> readAll();

    boolean doesEmailExist(String email);

    boolean doesPseudoExist(String pseudo);

    Utilisateur readByEmail(String email);

    public void update(Utilisateur utilisateur);

    public void updateCredit(Utilisateur utilisateur);

}
