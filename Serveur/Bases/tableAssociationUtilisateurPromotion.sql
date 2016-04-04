-- association entre un utilisateur et une marque | catégorie de produit | un autre utilisateur
CREATE TABLE AssociationUtilisateurPromotion
(
	-- L'ASSOCIATION
	idAssociation		INT NOT NULL AUTO_INCREMENT PRIMARY KEY,	-- id de l'association
   	-- L'UTILISATEUR
	idClient		INT NOT NULL,
	-- LE SUIVI
	marque		VARCHAR(50), -- marque du produit
	categorie              VARCHAR(50), -- catégorie du produit
	magasin 	INT, -- un magasin
	FOREIGN KEY (idClient)REFERENCES Client(idClient), -- id de l'utilisateur
	FOREIGN KEY (magasin) REFERENCES Magasin(idMagasin) -- un magasin
)
	  
