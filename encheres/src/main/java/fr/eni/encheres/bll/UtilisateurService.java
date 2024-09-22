/**
 *
 */
package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurService {
    public void addUser(Utilisateur utilisateur);

    boolean verifByPseudo(String pseudo);

    boolean verifByEmail(String email);

    Utilisateur findById(Integer noUtilisateur);

    Utilisateur findByPseudo(String pseudo);
    
    void updateUser(Utilisateur utilisateur, String oldpassword, String newpasswordconfirm);
}
