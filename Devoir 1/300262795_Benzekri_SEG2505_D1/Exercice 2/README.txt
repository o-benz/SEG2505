Cette question disait 10 secondes pour remplir le tableau et ce sera un 
très grand nombre d'éléments qui nécessiteront peut-être plus que la valeur 
maximale de l'entier et cela entraînera également une erreur de mémoire 
insuffisante. Donc, dans cette solution, Je fournis le programme pour que 
seulement 10 millisonds soient nécessaires pour remplir le tableau.

Comparaison des performances -

La sommation des éléments de la liste ou du tableau nécessite l'opération 
get(), c'est-à-dire une récupération au milieu du tableau, de la liste de 
tableaux ou de la liste chaînée. ArrayList est le meilleur choix pour une 
telle exigence si le nombre d'éléments est inconnu au début car la 
récupération de tout élément de la liste de tableaux nécessitera un temps 
constant qui O (1) mais si le nombre d'éléments est connu, les tableaux 
sont le meilleur choix car la récupération de tout élément aura besoin de 
O (1) et les frais généraux sont inférieurs à arrayliat et la liste de liens
 sera le dernier choix car la liste liée doit traverser de la tête à 
l'élément pour la récupération qui aura un coût O (n).

 

O(n) :

ArrayList - O(1)

Array - O(1)

LinkedList - O(n)
 

Par conséquent, si le nombre d'éléments est connu à l'avance, 
les tableaux sont le meilleur choix, sinon ArrayList est recommandé au 
programmeur.