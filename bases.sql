CREATE TABLE Prommotions
(
    -- LA PROMOTION
    idPromo         INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- id de la promotion
    nomPromo        VARCHAR(50), -- nom de la promo
    -- LE PRODUIT
    nomProduit      VARCHAR(50), -- nom/ref du produit
    marque          VARCHAR(50), -- marque du produit
    catégorie       VARCHAR(50), -- catégorie du produit -> les imposer dans le formulaire d'ajout de promo
    description     BLOB, -- description du produit
    ancienPrix      INT, -- ancien prix du produit
    NouveauPrix     INT, -- nouveau prix du produit
    estimationSt    VARCHAR(50), --estimation du stock
    dureeValidite   INT, --duree de validite de la promo -> trouver meilleur type, peut etre date 
    -- LE MAGASIN
    magasin         VARCHAR(50), -- nom du magasin
    numRue          INT NOT NULL, -- addresse
    rue             VARCHAR(50) NOT NULL, -- addresse
    codePostal      INT(5) NOT NULL, -- addresse
    ville           VARCHAR(50) NOT NULL, -- addresse
    -- LE CREATEUR
    idUser          INT NOT NULL FOREIGN KEY REFERENCES Utilisateurs(id)
)

CREATE TABLE Utilisateurs
(
	--L'utilisateur
	idUtilisateur	INT NOT NULL AUTO_INCREMENT PRIMARY KEY
)
