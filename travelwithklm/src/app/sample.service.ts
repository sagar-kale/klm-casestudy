import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Fare } from './fare';
import { Airport } from './airport';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class SampleService {

  private global_url = "/"
  constructor(
    private http: HttpClient
  ) { }

  getFareByOriginAndDest(fare: Fare): Observable<Fare> {
    console.log("origin.....", fare.origin);
    console.log("destination.....", fare.destination);
    return this.http.get<Fare>(this.global_url + 'fares/' + fare.origin + '/' + fare.destination, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }
  getAirportByTerm(fare: Fare): Observable<Airport> {
    console.log("token.....", fare.token)
    return this.http.get<Airport>(this.global_url + 'airports?term=' + fare.term, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }
  fetchStat(): Observable<any> {
    return this.http.get<any>(`${this.global_url}metrics/total/requests`);
  }
}