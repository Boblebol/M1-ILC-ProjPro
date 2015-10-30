CREATE TABLE Utilisateurs
(
   	-- L'UTILISATEUR
	idUtilisateur			INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	pseudoUtilisateur		VARCHAR (50) NOT NULL,
	-- SES INFORMATIONS
	nomUtilisateur			VARCHAR (50),
	prenomUtilisateur		VARCHAR (50),
	mailUtilisateur			VARCHAR (50) NOT NULL,
	-- LOCALISATION SATELLITE
	lastLongitudeUtilisateur	INT NOT NULL, -- derniere longitude lors de la synchronisation
	lastLattitudeUtilisateur	INT NOT NULL -- derniere lattitude lors de la synchronisation
)


CREATE TABLE Prommotions
(
	 -- LA PROMOTION
    idPromo         	INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- id de la promotion
    nomPromo        	VARCHAR(50), -- nom de la promo
    -- LE PRODUIT
    nomProduit      	VARCHAR(50), -- nom/ref du produit
    marque          	VARCHAR(50), -- marque du produit
    catégorie       	VARCHAR(50), -- catégorie du produit -> les imposer dans le formulaire d'ajout de promo
	description     	BLOB, -- description du produit
	ancienPrix      	INT, -- ancien prix du produit
    nouveauPrix     	INT, -- nouveau prix du produit
    estimationStock    	VARCHAR(50), -- estimation du stock
    dateCreation    	TIMESTAMP, -- date ou la promo a ete postee
    dureeValidite   	INT,
	 -- LE MAGASIN
	 magasin         	VARCHAR(50), -- nom du magasin
	 -- LOCALISATION POSTALE
    numRue          	INT NOT NULL, -- addresse
    rue             	VARCHAR(50) NOT NULL, -- addresse
    codePostal      	INT(5) NOT NULL, -- addresse
    ville           	VARCHAR(50) NOT NULL, -- addresse
    -- LOCALISTAION SATELLITE
    longitude	    	INT NOT NULL, -- longitude
    lattitude       	INT NOT NULL, -- lattitude
    -- STATUT
    active	    		BOOLEAN, -- Tant que la promo as moins de 24h par defaut ou que 3 personne l'aient signalee
    -- LE CREATEUR
    idUtilisateur       INT NOT NULL ,
    FOREIGN KEY (idUtilisateur) REFERENCES Utilisateurs(idUtilisateur)
)

-- association entre un utilisateur et une marque | catégorie de produit | un autre utilisateur
CREATE TABLE AssociationUtilisateurPromotion 
(
	-- L'ASSOCIATION
	idAssociation 		INT NOT NULL AUTO_INCREMENT PRIMARY KEY,	-- id de l'association
	-- L'UTILISATEUR
	idUtilisateur		INT NOT NULL,
	-- LE SUIVI
	marque          	VARCHAR(50), -- marque du produit
	catégorie       	VARCHAR(50), -- catégorie du produit 
    poseurDAnnonce		INT NOT NULL,
    
     FOREIGN KEY (idUtilisateur)REFERENCES Utilisateurs(idUtilisateur), -- id de l'utilisateur
     FOREIGN KEY (poseurDAnnonce) REFERENCES Utilisateurs(idUtilisateur) -- un autre utilisateur
)
