CREATE TABLE Magasin
(
	-- LE CLIENT
	idMagasin			INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	mailMagasin			VARCHAR (50) NOT NULL,
	motDePasseMagasin	VARCHAR (50) NOT NULL,
	-- SES INFORMATIONS	
	nomMagasin			VARCHAR (50),
	addresseMagasin		VARCHAR (200),
	-- LOCALISATION SATELLITE
	LongitudeMagasin	INT NOT NULL, -- derniere longitude lors de la synchronisation
	LattitudeMagasin  	INT NOT NULL -- derniere lattitude lors de la synchronisation
)