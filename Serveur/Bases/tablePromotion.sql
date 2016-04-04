
CREATE TABLE Promotions
(
	 -- LA PROMOTION
	 idPromo			INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- id de la promotion
	 referencePromo     VARCHAR(50), -- nom de la promo
	 -- LE PRODUIT
	 marquePromo        VARCHAR(50), -- marque du produit
	 nomProduit			VARCHAR(50), -- marque du produit
	 categorie       	     VARCHAR(50), -- catégorie du produit -> les imposer dans le formulaire d'ajout de promo
	 description     	       BLOB, -- description du produit
 	 ancienPrix      	    FLOAT, -- ancien prix du produit
	 nouveauPrix      FLOAT, -- nouveau prix du produit
	 --dateCreation     TIMESTAMP, -- date ou la promo a ete postee
	 dureeValidite   	       INT,
	 -- STATUT
	 active			BOOLEAN, -- Tant que la promo as moins de 24h par defaut ou que 3 personne l'aient signalee
	 -- LE MAGASIN qui a créée l'annonce
	 idMagasin int (50), -- id du magasin qui poste l'annonce
	 FOREIGN KEY (idMagasin) REFERENCES Magasin(idMagasin)
)
																									 
