package com.votreentreprise;

import java.util.ArrayList;
import java.util.List;

public class ProduitService {
	
	private List<Produit> produits = new ArrayList<>();

    public void createProduit(Produit produit) {
        // Verification de l'unicité du product par ID
        if (produits.stream().anyMatch(p -> p.getId().equals(produit.getId()))) {
            throw new IllegalArgumentException("Un produit avec le même ID existe déjà.");
        }

        // Verification de l'unicité du product par Nom
        if (produits.stream().anyMatch(p -> p.getNom().equals(produit.getNom()))) {
            throw new IllegalArgumentException("Un produit avec le même nom existe déjà.");
        }

        // Validation des données (prix et quantité positifs)
        if (produit.getPrix() < 0 || produit.getQuantite() < 0) {
            throw new IllegalArgumentException("Le prix et la quantité doivent être positifs.");
        }

        produits.add(produit);
    }
    public Produit rechercheProduit(Long id) {
        // Logique de lecture d'un produit
        return produits.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
    }
    public void updateProduit(Produit produit) {
        // Vérification de l'existence du produit
        if (!produits.contains(produit)) {
            throw new IllegalArgumentException("Le produit n'existe pas pour la mise à jour.");
        }

        // Validation des données (prix et quantité positifs)
        if (produit.getPrix() < 0 || produit.getQuantite() < 0) {
            throw new IllegalArgumentException("Le prix et la quantité doivent être positifs.");
        }

        // Mise à jour du produit
        produits.removeIf(p -> p.getId().equals(produit.getId()));
        produits.add(produit);
    }
    
    public void deleteProduit(Long id) {
        // Vérification de l'existence du produit
        Produit produit = rechercheProduit(id);
        if (produit == null) {
            throw new IllegalArgumentException("Le produit n'existe pas pour la suppression.");
        }

        produits.remove(produit);
    }
    
    
    
    
    
}
