# WikiWhat

## Présentation
WikiWhat? est un jeu permettant de tester ses connaissances et de les comparer avec celles de ses amis. Le but du jeu est de deviner de quelle page Wiképédia sont extraites certaines phrases.  
Au démarrage, le jeu affiche aléatoirement 3 catégories Wiképédia. La liste de toutes les catégories se trouve à l'adresse http://en.wikipedia.org/wiki/Special:Categories.  

Pour chaque catégorie, le nombre de pages associées est également affiché. Le joueur peut alors:
* Demander à afficher 3 nouvelles catégories,  
* Demander à changer une des catégories,  
* Démarrer une partie avec les 3 catégories affichées.
  
Considérons, par exemple, que le joueur joue avec les 3 catégories suivantes : 
* "Films directed by Steven Spielberg" (39 pages), 
* "The Beatles albums" (24 pages)
* "Disney Princess characters" (11 pages).
  
Le jeu sélectionne au hasard une page Wikipedia dans l'une de ces catégories et commence par afficher des phrases prises dans la page. 
> e.g., "Having worked with Cary Guffey on Close Encounters of the Third Kind, Spielberg felt confident in working with a cast composed mostly of child actors."
  
Si la phrase contient le titre de la page, le titre est substitué avec XXX.
> e.g. , "XXX is a 1982 American science fiction fantasy film co-produced and directed by Steven Spielberg, and written by Melissa Mathison."
  
Après chaque indice (i.e., phrase), le joueur a le droit à une seule tentative pour deviner le titre de la page (ici, E.T. the Extra-Terrestrial ). Moins il a besoin d'indices, plus il gagne de points. Le nombre de points pour une page trouvée est égal au nombre total de pages dans les catégories jouées moins le nombre d'indices donnés. Dans notre exemple, si le joueur devine E.T. the Extra-Terrestrial au bout du deuxième indice, il gagne 74 - 2 = 72 points. 
  
Pour chaque page à deviner, le jeu propose au maximum 20 indices. Si le joueur trouve la page, le jeu passe à une nouvelle page, sinon le jeu se termine. À la fin du jeu, le score du joueur est affiché. Diverses informations peuvent être enregistrées (e.g. , meilleurs scores, pages les plus difficiles à trouver...).

## Travail à rendre
Vous devez développer (en binôme) ce petit jeu en utilisant la programmation réactive avec RxJava/RxAndroid.
