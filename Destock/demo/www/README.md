Destock
=====

Le dossier app contient l'ensemble des pages du projet, connexion, inscription etc...
chaque page peut soit être un simple .html, soit un duo de .html et d'un controlleur .ctrl.js
Dans ce cas il faut le mettre dans un dossier spécifique pour eviter que ce soit un bordel.

Voici le fonctionnnement : on arrive sur /demo/www/index.html

dedans il y a une inclusion du controlleur :

  <!-- perso Destock -->
  <script src="app/connexion/connexion.ctrl.js"></script>  
  
  ensuite dans /demo/www/app/app.js il y a un inclusion du module :
  
  // Persos
  'demo.connexion.ctrl',  
  
  ATTENTION aux virgules !! Sinon ça fait une page blanche !
  
  plus bas dans la page on indique quel controlleur utiliser en fonction de
  l'adresse html dans laquelle on se trouve :
  
 
      .state('connexion', {
        url: "/connexion",
        templateUrl: "app/connexion/connexion.html",
        controller: "ConnexionCtrl"
      })
	  
	  