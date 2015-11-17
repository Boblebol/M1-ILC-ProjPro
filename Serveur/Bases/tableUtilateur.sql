CREATE TABLE Utilisateurs
(
	-- L'UTILISATEUR
	idUtilisateur			INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	pseudoUtilisateur		VARCHAR (50) NOT NULL,
	motDePasseUtilisateur		VARCHAR (50) NOT NULL,
	-- SES INFORMATIONS	
	nomUtilisateur			VARCHAR (50),
	prenomUtilisateur		VARCHAR (50),
	mailUtilisateur			VARCHAR (50) NOT NULL,
	-- LOCALISATION SATELLITE
	lastLongitudeUtilisateur	INT NOT NULL, -- derniere longitude lors de la synchronisation
	lastLattitudeUtilisateur  	INT NOT NULL -- derniere lattitude lors de la synchronisation
)
					
