import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import {House} from './house';
import { HttpClient } from '@angular/common/http';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class HouseService {
  private houseUrl = 'http://localhost:8080/kits';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  getHouses(): Observable<House[]> {
    return this.http.get<House[]>(this.houseUrl)
    .pipe(
      tap(_ => this.log('fetched all houses')),
      catchError(this.handleError<House[]>(`getHouses`, []))
    );
  }

  addHouse(house: House): Observable<House> {
    return this.http.post<House>(`${this.houseUrl}/create`, house, this.httpOptions).pipe(
      tap((newHouse: House) => this.log(`Added Kit: id=${newHouse.id}`)),
      catchError(this.handleError<House>('addHoue'))

    );
  }


  private log(message: string) {
    this.messageService.add(`HouseService: ${message}`);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
