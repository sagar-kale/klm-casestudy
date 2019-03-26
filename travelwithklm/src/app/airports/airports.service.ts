import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Airport } from '../airport';

@Injectable({
  providedIn: 'root'
})
export class AirportsService {

  constructor(
    private http: HttpClient
  ) { }

  public getLocations(): Observable<Airport> {
    return this.http.get<Airport>(environment.airport_url);
  }
}
