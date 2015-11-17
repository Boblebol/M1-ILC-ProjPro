CREATE TABLE Prommotions
(
	 -- LA PROMOTION
	 idPromo		INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- id de la promotion
	 nomPromo           VARCHAR(50), -- nom de la promo
	 -- LE PRODUIT
	 nomProduit		VARCHAR(50), -- nom/ref du produit
	 marque          	VARCHAR(50), -- marque du produit
	 catégorie       	     VARCHAR(50), -- catégorie du produit -> les imposer dans le formulaire d'ajout de promo
	 description     	       BLOB, -- description du produit
 	 ancienPrix      	    INT, -- ancien prix du produit
	 nouveauPrix      INT, -- nouveau prix du produit
	 estimationStock      VARCHAR(50), -- estimation du stock
	 dateCreation     TIMESTAMP, -- date ou la promo a ete postee
	 dureeValidite   	       INT,
	 -- LE MAGASIN
	 magasin		VARCHAR(50), -- nom du magasin
	 -- LOCALISATION POSTALE
	 numRue		INT NOT NULL, -- addresse
	 rue                VARCHAR(50) NOT NULL, -- addresse
	 codePostal      		INT(5) NOT NULL, -- addresse
	 ville           	VARCHAR(50) NOT NULL, -- addresse
	 -- LOCALISTAION SATELLITE
	 longitude		INT NOT NULL, -- longitude
	 lattitude       	INT NOT NULL, -- lattitude
	 -- STATUT
	 active			BOOLEAN, -- Tant que la promo as moins de 24h par defaut ou que 3 personne l'aient signalee
	 -- LE CREATEUR
	 idUtilisateur       INT NOT NULL ,
	 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateurs(idUtilisateur)
)
																									 
