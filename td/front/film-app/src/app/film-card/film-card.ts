import { Component, input, output } from '@angular/core';
import { DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Film } from '../model/film.model';

@Component({
  selector: 'app-film-card',
  imports: [DatePipe, RouterLink],
  templateUrl: './film-card.html',
  styleUrl: './film-card.css',
})
export class FilmCard {
  film = input.required<Film>();
  supprimer = output<Film>();

  onSupprimer() {
    this.supprimer.emit(this.film());
  }

  estAncien(f: Film) {
    return new Date(f.dateSortie).getFullYear() < 2000;
  }
}
