## Iteration 0 : Prendre une photo portrait couleur 
	1. Le photomaton doit pouvoir prendre une photo portrait couleur d'un utilisateur.
		* Un portrait représente une unique photo finale.

###Introduction du picture processor 
Le photomaton inclue déjà une brique qui à partir d'une photo brute, retourne une photo traitée selon un ordre. Il s'agit du picture processor. Cette brique utilise un protocole particulier suivant le format : 
	"format;colorimétrie"
Ainsi l'ordre d'un portrait couleur se représente sous la forme : "P:C"
		
## Iteration 1 : Prendre une photo portrait N&B 		
	1. Le photomaton doit pouvoir prendre une photo portrait noir et blanc d'un utilisateur.
		* Ordre du picture processor : "P:BW"	

## Iteration 2 : le photomaton devient payant
	1. Le photomaton ne doit pas permettre à un utilisateur de prendre une photo avant d'avoir payé son prix : 
		* Le prix d'une photo portrait couleur : 3€
		* Le prix d'une photo portrait N&B : 2,75€
[ deux types de tests : 
	* un test pour vérifier le fait de bien appeler le module de validation
	* un test unitaire pour tester la logique métier : monnaie suffisante, insuffisante, etc...  ]
			
	2. Un utilisateur a trois essais pour réussir sa photo. Au bout du troisième essai, la dernière photo part vers le picture processor.
	
[ * un test BDD uniquement côté front ]

## Iteration 3 : Photos d'identité
	1. Le photomaton propose maintenant l'impression de photos d'identité. 
		* 4 photos d'identités sont imprimées sur une même planche
		* Le tarif est de 3,5€
		* Les photos d'idendités ne peuvent pas être imprimées en N&B
		* Nouveau format pour le picture processor "I"
	2. Le photomaton indique à l'utilisateur si la photo qu'il vient de prendre respecte les normes définies
	par le ministère de l'intérieur (interface fournit).

[ * l'interface fournit devra être mockée ]

## Iteration 4 : Plus d'options
	1. Le photomaton propose maintenant la prise de photos Vintage (ajoute un filtre Sepia)
		* Le tarif d'une photo vintage est de 4€
		* Nouvelle colorimétrie pour le picture processor : "V"
	2. Le photomaton propose maintenant un nouveau format mini, ou 16 photos sont imprimées sur la même planche
		* Le tarif du format mini suit le prix selon que la photo soit en couleur, en noir et blanc ou vintage
		* Nouveau format pour le picture processor : "M"

## Iteration 5 : Partage
	1. Le photomaton  propose l'envoie de photos par mail
		* Cela concerne uniquement le format "Portrait"
