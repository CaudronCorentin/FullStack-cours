import { Component, OnInit, computed, inject, input, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { toSignal } from '@angular/core/rxjs-interop';
import { FilmService } from '../service/film.service';
import { ActeurService } from '../service/acteur.service';
import { Film } from '../model/film.model';

@Component({
  selector: 'app-film-detail',
  imports: [DatePipe, FormsModule, RouterLink],
  templateUrl: './film-detail.html',
  styleUrl: './film-detail.css',
})
export class FilmDetail implements OnInit {
  private service = inject(FilmService);
  private acteurService = inject(ActeurService);
  private router = inject(Router);

  //gestion
  id = input.required<string>();
  erreur = signal<string | null>(null);

  //film
  filmId = computed(() => Number(this.id()));

  film = signal<Film | null>(null);

  // acteur
  acteurs = toSignal(this.acteurService.getAll(), { initialValue: [] });
  acteursDisponibles = computed(() =>
    this.acteurs().filter((a) => !this.film()?.acteurs?.some((fa) => fa.id === a.id)),
  ); // tous les acteurs pas dans le film
  acteurSelectionne = signal<number | null>(null); // pour le select

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

  // ----------------------------------------------------------------------
  // Gestion de l'association entre acteur et film
  // ----------------------------------------------------------------------

  associer() {
    const id = this.acteurSelectionne();
    if (!id) return;
    this.service.associerActeur(this.filmId(), id).subscribe({
      next: () => {
        this.acteurSelectionne.set(null);
        this.recharger();
      },
      error: () => this.erreur.set('Association impossible'),
    });
  }

  dissocier(acteurId: number) {
    this.service.dissocierActeur(this.filmId(), acteurId).subscribe({
      next: () => this.recharger(),
      error: () => this.erreur.set('dissociation impossible'),
    });
  }
}
