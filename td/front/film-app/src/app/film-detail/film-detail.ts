import { Component, OnInit, computed, inject, input, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { FilmService } from '../service/film.service';
import { Film } from '../model/film.model';

@Component({
  selector: 'app-film-detail',
  imports: [DatePipe, RouterLink],
  templateUrl: './film-detail.html',
  styleUrl: './film-detail.css',
})
export class FilmDetail implements OnInit {
  private service = inject(FilmService);
  private router = inject(Router);

  id = input.required<string>();
  filmId = computed(() => Number(this.id()));

  film = signal<Film | null>(null);
  erreur = signal<string | null>(null);

  ngOnInit() {
    this.recharger();
  }

  recharger() {
    this.service.getById(this.filmId()).subscribe({
      next: (f) => this.film.set(f),
      error: (e) => this.erreur.set(e.status === 404 ? 'Film introuvable' : 'Erreur serveur'),
    });
  }

  supprimer() {
    this.service.supprimer(this.filmId()).subscribe({
      next: () => this.router.navigate(['/films']),
      error: () => this.erreur.set('Suppression impossible'),
    });
  }
}
