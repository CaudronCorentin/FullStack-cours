INSERT INTO film (titre, realisateur, date_sortie, genre) VALUES
                                                              ('Le Seigneur des anneaux : La Communauté de l''anneau', 'Peter Jackson', '2001-12-19', 'ACTION'),
                                                              ('Inception', 'Christopher Nolan', '2010-07-16', 'ACTION'),
                                                              ('Matrix', 'Lana et Lilly Wachowski', '1999-03-31', 'SCIENCE_FICTION'),
                                                              ('Star Wars : Un nouvel espoir', 'George Lucas', '1977-05-25', 'SCIENCE_FICTION'),
                                                              ('Intouchables', 'Olivier Nakache et Éric Toledano', '2011-11-02', 'COMEDIE');

INSERT INTO acteur (nom, prenom) VALUES
                                     ('Wood', 'Elijah'),
                                     ('Mortensen', 'Viggo'),
                                     ('DiCaprio', 'Leonardo'),
                                     ('Reeves', 'Keanu'),
                                     ('Moss', 'Carrie-Anne'),
                                     ('Hamill', 'Mark'),
                                     ('Ford', 'Harrison'),
                                     ('Sy', 'Omar'),
                                     ('Cluzet', 'François');

INSERT INTO film_acteur (id_film, id_acteur) VALUES
                                                 (1, 1), (1, 2),
                                                 (2, 3),
                                                 (3, 4), (3, 5),
                                                 (4, 6), (4, 7),
                                                 (5, 8), (5, 9);