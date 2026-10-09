import { Component, computed, inject, input, OnInit, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { RouterLink, RouterModule } from '@angular/router';
import { ActeurService } from '../service/acteur.service';
import { Acteur } from '../model/acteur.model';
import { Film } from '../model/film.model';



@Component({
  imports: [DatePipe, RouterLink],
  selector: 'app-acteur-detail',
  styleUrl: './acteur-detail.css',
  templateUrl: './acteur-detail.html',
})
export class ActeurDetail implements OnInit {
  private service = inject(ActeurService);

  //gestion
  id = input.required<string>();
  erreur = signal<string | null>(null);

  // acteur
  acteur = signal<Acteur| null>(null);
  acteur_id = computed(()=>Number(this.id()));

  // film
  films = signal<Film[]>([]);

  ngOnInit() {
    this.service.getById(this.acteur_id()).subscribe({
      next: a => this.acteur.set(a),
      error: e => this.erreur.set(e.status === 404 ? 'acteur introuvable' : 'Erreur serveur'),
    });
    this.service.getFilms(this.acteur_id()).subscribe({
      next: f => this.films.set(f),
      error: e => this.erreur.set("erreur serveur"),
    })
  }
}
