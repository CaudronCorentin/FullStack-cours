import { Acteur } from './acteur.model';

export type Genre = 'ACTION' | 'COMEDIE' | 'DRAME' | 'HORREUR' | 'SCIENCE_FICTION' | 'ANIMATION';

export interface Film {
  id: number;
  titre: string;
  realisateur: string;
  dateSortie: string;
  genre: Genre;
  acteurs?: Acteur[];
}
