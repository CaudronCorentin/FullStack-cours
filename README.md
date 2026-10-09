# Bibliothèque de films 

Application web pour gérer une bibliothèque de films et leurs acteurs :
une **API REST** (Spring Boot + PostgreSQL) et un **front** (Angular).
Le dépôt contient aussi les TP du cours dans `tp/`.

## Structure du dépôt

- `tp/back/`:TP Java / Spring du cours
- `td/back/`:API REST des films (Spring Boot)
- `http/` : requêtes HTTP pour tester l'API
- `docker-compose.yaml`: base PostgreSQL
- `td/front/film-app/` : front Angular

## Prérequis

- **JDK 26** (pour le back)
- **Docker** (pour la base PostgreSQL)
- **Node.js 22.22.3 ou plus** et npm (pour le front)

## Lancer le projet

Il faut démarrer dans cet ordre : base de données, back, puis front.

### 1. La base de données

```bash
cd td/back
docker compose up -d
```

PostgreSQL démarre sur `localhost:5432` (base `FilmBiblio`, utilisateur `cine`, mot de passe `password`).

### 2. Le back (API)

```bash
cd td/back
./gradlew bootRun
```

L'API est disponible sur http://localhost:8080.
Le Swagger est sur http://localhost:8080/swagger-ui.html.

Au démarrage, la base est recréée et remplie avec 5 films et 9 acteurs
(fichier `src/main/resources/data.sql`). Les données ajoutées pendant l'utilisation
sont donc effacées à chaque redémarrage du back.

### 3. Le front

```bash
cd td/front/film-app
npm install
npm start
```

L'application est sur http://localhost:4200.

## Fonctionnalités

- Liste des films, séparés entre récents et anciens (avant 2000)
- Détail d'un film avec ses acteurs
- Création, modification et suppression d'un film
- Liste des acteurs, détail d'un acteur avec ses films
- Création d'un acteur
- Association et dissociation d'un acteur à un film
- Thème clair ou sombre selon le système, messages d'erreur si l'API est injoignable

## L'API

| Ressource         | Requêtes                                                                                          |
| ----------------- | ------------------------------------------------------------------------------------------------- |
| Films             | `GET /films`, `GET /films/{id}`, `POST /films`, `PUT /films/{id}`, `DELETE /films/{id}`           |
| Acteurs d'un film | `GET /films/{id}/acteurs`                                                                         |
| Association       | `POST /films/{id}/acteurs/{acteurId}`, `DELETE /films/{id}/acteurs/{acteurId}`                    |
| Acteurs           | `GET /acteurs`, `GET /acteurs/{id}`, `POST /acteurs`, `PUT /acteurs/{id}`, `DELETE /acteurs/{id}` |
| Films d'un acteur | `GET /acteurs/{id}/films`                                                                         |

Codes de retour : `200` lecture, `201` création (avec l'en-tête `Location`), `204` suppression,
`404` ressource inconnue. Les erreurs sont renvoyées au format `ProblemDetail`.

Pour tester sans le front, ouvrir les fichiers de `td/back/http/` dans VSCode avec l'extension
[REST Client](https://marketplace.visualstudio.com/items?itemName=humao.rest-client),
puis cliquer sur _Send Request_ au-dessus d'une requête.

## Comment le code est organisé

### Back (`td/back`)

- `controller/` :reçoit les requêtes HTTP et renvoie les réponses
- `service/` : contient la logique (par exemple "film introuvable" ; erreur 404)
- `repository/` : accède à la base de données (Spring Data JPA)
- `model/` :les entités : Film, Acteur, Genre
- `DTO/` : les objets envoyés et reçus par l'API
- `exception/` : les erreurs métier et leur transformation en ProblemDetail
- `mapper/` : convertit une entité en DTO et inversement
- `config/` : configuration CORS

### Front (`td/front/film-app/src/app`)

- `film-list`, `film-detail`, `film-form` : pages des films

- `acteur-list`, `acteur-detail`, `acteur-form` : pages des acteurs
- `film-card` : carte réutilisable d'un film
- `not-found` : page 404
- `service/` : `FilmService` et `ActeurService` (tous les appels HTTP)
- `model/` : interfaces `TypeScript` `Film` et `Acteur`

Les composants n'appellent jamais l'API directement : ils passent par les services.
`film-card` reçoit un film en `input()` et prévient la liste avec un `output()` quand on clique
sur "Supprimer" ; c'est la liste qui décide de supprimer.

## Choses en plus du cours

- **Docker Compose** : le fichier `td/back/docker-compose.yaml` lance PostgreSQL dans un conteneur,
  ce qui évite de l'installer sur la machine. Ses identifiants correspondent à ceux de
  `application.properties`.
- **`data.sql`** : Spring exécute ce fichier au démarrage pour insérer les données de départ.
  La propriété `spring.jpa.defer-datasource-initialization=true` fait passer le script
  _après_ la création des tables par Hibernate.
- **`ddl-auto=create-drop`** : Hibernate supprime et recrée les tables à chaque lancement
  (pratique en développement, à ne pas utiliser en production).
- **Swagger (springdoc-openapi)** : génère automatiquement une page de documentation et de test
  de l'API à partir des contrôleurs.
- **Relation ManyToMany** : `Film` est le côté qui possède la relation (table `film_acteur`).
  `Acteur` utilise `mappedBy`. Pour associer ou dissocier, le service modifie les deux côtés.
- **Signals** : l'état des composants (film chargé, message d'erreur…) est stocké dans des
  `signal()`, et `computed()` calcule les valeurs qui en dépendent (par exemple la liste des
  acteurs encore disponibles pour un film).

## Rendus

| Tag   | Contenu                         |
| ----- | ------------------------------- |
| `td1` | API REST, stockage en mémoire   |
| `td2` | persistance JPA, DTO, CORS      |
| `td3` | front Angular branché sur l'API |
