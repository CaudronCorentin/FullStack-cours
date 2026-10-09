import { Component, inject } from '@angular/core';
import { ActeurService } from '../service/acteur.service';
import { AsyncPipe } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  imports: [AsyncPipe, RouterLink],
  selector: 'app-acteur-list',
  styleUrl: './acteur-list.css',
  templateUrl: './acteur-list.html',
})
export class ActeurList {
  acteurs$ = inject(ActeurService).getAll();
}
