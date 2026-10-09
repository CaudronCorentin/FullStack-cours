import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ActeurService } from '../service/acteur.service';

@Component({
  imports: [FormsModule, RouterLink],
  selector: 'app-acteur-form',
  styleUrl: './acteur-form.css',
  templateUrl: './acteur-form.html',
})
export class ActeurForm {
  private service = inject(ActeurService);
  private router = inject(Router);

  prenom = '';
  nom = '';
  erreur = signal<string | null>(null);

  enregistrer() {
    this.service.creer({ prenom: this.prenom, nom: this.nom }).subscribe({
      next: () => this.router.navigate(['/acteurs']),
      error: () => this.erreur.set('Création impossible'),
    });
  }
}
