import { Component, OnInit } from '@angular/core';
import { AirportsService } from './airports.service';
import { Airport } from '../airport';

@Component({
  selector: 'app-airports',
  templateUrl: './airports.component.html',
  styleUrls: ['./airports.component.css']
})
export class AirportsComponent implements OnInit {
  locations: Airport;
  constructor(
    private airportService: AirportsService
  ) { }

  ngOnInit() {
    this.getLocationData();
  }

  getLocationData() {
    this.airportService.getLocations().subscribe(res => {
      console.log("airport response", res);
      this.locations = res;
    });
  }

}
