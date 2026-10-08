import { Component, inject, signal } from '@angular/core';
import { AsyncPipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FilmService } from '../service/film.service';
import { Film } from '../model/film.model';
import { FilmCard } from '../film-card/film-card';

@Component({
  selector: 'app-film-list',
  imports: [AsyncPipe, RouterLink, FilmCard],
  templateUrl: './film-list.html',
  styleUrl: './film-list.css',
})
export class FilmList {
  private service = inject(FilmService);
  films$ = signal(this.service.getAll());
  erreur = signal<string | null>(null);

  onSupprimer(f: Film) {
    this.service.supprimer(f.id).subscribe({
      next: () => this.films$.set(this.service.getAll()),
      error: () => this.erreur.set('Suppression impossible'),
    });
  }

  estAncien(f: Film) {
    return new Date(f.dateSortie).getFullYear() < 2000;
  }

  recents(films: Film[]) {
    return films.filter((f) => !this.estAncien(f));
  }

  anciens(films: Film[]) {
    return films.filter((f) => this.estAncien(f));
  }
}


