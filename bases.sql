CREATE TABLE Prommotion
(
    -- LA PROMOTION
    id              INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- id de la promotion
    nomPromo        VARCHAR(50), -- nom de la promo
    -- LE PRODUIT
    nomProduit      VARCHAR(50), -- nom/ref du produit
    marque          VARCHAR(50), -- marque du produit
    catégorie       VARCHAR(50), -- catégorie du produit -> les imposer dans le formulaire d'ajout de promo
    description     BLOB, -- description du produit
    ancienPrix      INT, -- ancien prix du produit
    NouveauPrix     INT, -- nouveau prix du produit
    estimationSt    VARCHAR(50), --estimation du stock
    -- LE MAGASIN
    magasin         VARCHAR(50), -- nom du magasin
    numRue          INT NOT NULL, -- addresse
    rue             VARCHAR(50) NOT NULL, -- addresse
    codePostal      INT(5) NOT NULL, -- addresse
    ville           VARCHAR(50) NOT NULL, -- addresse
    -- LE CREATEUR
    idUser          INT NOT NULL FOREIGN KEY REFERENCES Users(id)
)
