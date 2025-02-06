# Clash royal POGNON

Pour savoir ou on en est du pognon dans CR.  
- Savoir combien de piece d'or (PO) il faut pour mettre toutes les cartes niveau 14.
- Savoir combien vous avez déjà depensé de PO dans le jeu
- Combien de PO il vous restes à avoir pour passer TOUTES vos cartes niv. 14
- Combien de PO vous devez mettre pour passer vos cartes (qui peuvent etre level up) au prochain niveau.

```shell
usage: java -jar clashroyal.jar
 -f, --file <propertieFile>   lit un fichier .properties les clés sont :
                              "tag" (sans le #) et "token" (JWT)
 -h, --help                   affiche cette aide
 -k, --token <token>          le token (JWT) de l'api clash royale
 -t, --tag <tag>              le tag d'un joueur mais sans le #
```

## Ficher de properties

Voici la tete du fichier de properties:
```java
tag=Tag Du Joueur Sans Le#
token=token JWT 
```

## Lancement du programme

il faut une JRE version 17 minimum.

java -jar clashroyal.jar --tag montag --token tokenJWT

