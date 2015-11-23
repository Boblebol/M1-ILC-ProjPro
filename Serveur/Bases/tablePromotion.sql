CREATE TABLE Promotions
(
	 -- LA PROMOTION
	 idPromo		INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- id de la promotion
	 nomPromo           VARCHAR(50), -- nom de la promo
	 -- LE PRODUIT
	 nomProduit		VARCHAR(50), -- nom/ref du produit
	 marque          	VARCHAR(50), -- marque du produit
	 categorie       	     VARCHAR(50), -- catégorie du produit -> les imposer dans le formulaire d'ajout de promo
	 description     	       BLOB, -- description du produit
 	 ancienPrix      	    FLOAT, -- ancien prix du produit
	 nouveauPrix      FLOAT, -- nouveau prix du produit
	 estimationStock      VARCHAR(50), -- estimation du stock
	 dateCreation     TIMESTAMP, -- date ou la promo a ete postee
	 dureeValidite   	       INT,
	 -- LE MAGASIN
	 magasin		VARCHAR(50), -- nom du magasin
	 -- LOCALISTAION SATELLITE
	 longitude		FLOAT NOT NULL, -- longitude
	 lattitude       	FLOAT NOT NULL, -- lattitude
	 -- STATUT
	 active			BOOLEAN, -- Tant que la promo as moins de 24h par defaut ou que 3 personne l'aient signalee
	 -- LE CREATEUR
	 idUtilisateur       INT NOT NULL ,
	 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateurs(idUtilisateur)
)
																									 
