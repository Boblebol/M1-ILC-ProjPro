CREATE TABLE Client
(
	-- LE CLIENT
	idClient				INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	mailClient				VARCHAR (50) NOT NULL,
	motDePasseClient		VARCHAR (50) NOT NULL,
	-- SES INFORMATIONS	
	nomClient				VARCHAR (50),
	prenomClient			VARCHAR (50),
	-- LOCALISATION SATELLITE
	lastLongitudeClient		INT NOT NULL, -- derniere longitude lors de la synchronisation
	lastLattitudeClient  	INT NOT NULL -- derniere lattitude lors de la synchronisation
)