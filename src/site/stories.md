## Iteration 0 : Prendre une photo portrait couleur 
	1. Le photomaton doit pouvoir prendre une photo portrait couleur d'un utilisateur.
		* Un portrait représente une photo finale.
		
## Iteration 1 : Prendre une photo portrait N&B 		
	2. Le photomaton doit pouvoir prendre une photo portrait couleur d'un utilisateur.

## Iteration 2 : le photomaton devient payant
	1. Le photomaton ne doit pas permettre à un utilisateur de prendre une photo avant d'avoir payé son prix : 
		* Le prix d'une photo portrait couleur : 3€
		* Le prix d'une photo portrait N&B : 2,75€
[ deux types de tests : - un test pour vérifier le fait de bien appeler le module de validation
			- un test unitaire pour tester la logique métier : monnaie suffisante, insuffisante, etc...  ]
	2. Un utilisateur a quatre essais pour réussir sa photo. Au bout du quatrième essai, le photomaton doit montrer les quatre photos
	à l'utilisateur qui choisit alors celle à imprimer.

## C'est officiel : photos d'identité
	1. Le photomaton propose maintenant l'impression de photos d'identité. 
		* 4 photos d'identités sont imprimées sur une même planche
		* Le tarif est de 3,5€
		* Les photos d'idendités ne peuvent pas être imprimées en N&B
	2. Le photomaton indique à l'utilisateur si la photo qu'il vient de prendre respecte les normes définies
	par le ministère de l'intérieur (interface fournit).

## More fun 
	1. Le photomaton propose maintenant la prise de photos dites "funs", c'est à dire permettant d'intégrer l'utilisateur
	 à des fonds variés.
		* Le tarif d'une photo fun est de 4€

## Partage
	1. Le photomaton  propose l'envoie de photos par mail ou sur les réseaux sociaux.
	2. Les photos d'identités ne peuvent pas être envoyées sur les réseaux sociaux. 
