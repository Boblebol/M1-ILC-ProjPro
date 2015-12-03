-- association entre un utilisateur et une marque | catégorie de produit | un autre utilisateur
CREATE TABLE AssociationUtilisateurPromotion
(
	-- L'ASSOCIATION
	idAssociation		INT NOT NULL AUTO_INCREMENT PRIMARY KEY,	-- id de l'association
   	-- L'UTILISATEUR
	idUtilisateur		INT NOT NULL,
	-- LE SUIVI
	marque		VARCHAR(50), -- marque du produit
	categorie              VARCHAR(50), -- catégorie du produit
	idUtilSuivi	INT NOT NULL,
	FOREIGN KEY (idUtilisateur)REFERENCES Utilisateurs(idUtilisateur), -- id de l'utilisateur
	FOREIGN KEY (idUtilSuivi) REFERENCES Utilisateurs(idUtilisateur) -- un autre utilisateur
)
	  
