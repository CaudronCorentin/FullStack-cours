import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { Film } from '../model/film.model';
import { Acteur } from '../model/acteur.model';

@Injectable({ providedIn: 'root' })
export class FilmService {
  private http = inject(HttpClient);
  private url = '/api/films';

  getAll(): Observable<Film[]> {
    return this.http.get<Film[]>(this.url).pipe(
      catchError((e: HttpErrorResponse) => {
        console.error(e.status, e.error?.detail);
        return of([]);
      }),
    );
  }

  getById(id: number): Observable<Film> {
    return this.http.get<Film>(`${this.url}/${id}`);
  }

  getActeurs(id: number): Observable<Acteur[]> {
    return this.http.get<Acteur[]>(`${this.url}/${id}/acteurs`);
  }

  creer(f: Partial<Film>): Observable<Film> {
    return this.http.post<Film>(this.url, f);
  }

  modifier(id: number, f: Film): Observable<Film> {
    return this.http.put<Film>(`${this.url}/${id}`, f);
  }

  supprimer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  associerActeur(filmId: number, acteurId: number): Observable<Film> {
    return this.http.post<Film>(`${this.url}/${filmId}/acteurs/${acteurId}`, null);
  }

  dissocierActeur(filmId: number, acteurId: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${filmId}/acteurs/${acteurId}`);
  }
}
