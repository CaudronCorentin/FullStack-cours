import { Component, OnInit, inject, input, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { FilmService } from '../service/film.service';
import { Film, Genre } from '../model/film.model';

@Component({
  selector: 'app-film-form',
  imports: [FormsModule, RouterLink],
  templateUrl: './film-form.html',
  styleUrl: './film-form.css',
})
export class FilmForm implements OnInit {
  private service = inject(FilmService);
  private router = inject(Router);

  id = input<string>();
  film = signal<Partial<Film> | null>(null);
  erreur = signal<string | null>(null);
  genres: Genre[] = ['ACTION', 'COMEDIE', 'DRAME', 'HORREUR', 'SCIENCE_FICTION', 'ANIMATION'];

  ngOnInit() {
    const id = this.id();
    if (id) {
      this.service.getById(Number(id)).subscribe({
        next: (f) => this.film.set(f),
        error: (e) => this.erreur.set(e.status === 404 ? 'Film introuvable' : 'Erreur serveur'),
      });
    } else {
      this.film.set({ titre: '', realisateur: '', dateSortie: '', genre: 'ACTION' });
    }
  }

  enregistrer(f: Partial<Film>) {
    const id = this.id();
    const requete = id ? this.service.modifier(Number(id), f as Film) : this.service.creer(f);
    requete.subscribe({
      next: () => this.router.navigate(['/films']),
      error: () => this.erreur.set('Enregistrement impossible'),
    });
  }
}
