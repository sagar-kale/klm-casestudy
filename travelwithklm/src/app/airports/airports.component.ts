import { Component, OnInit } from '@angular/core';
import { AirportsService } from './airports.service';
import { Airport } from '../airport';
import { Location } from '../location';

@Component({
  selector: 'app-airports',
  templateUrl: './airports.component.html',
  styleUrls: ['./airports.component.css']
})
export class AirportsComponent implements OnInit {
  locations: Location[];
  indiaMap=new Map<String,Location>();
  constructor(
    private airportService: AirportsService
  ) { }

  ngOnInit() {
    this.getLocationData();
  }

  getLocationData() {
    this.airportService.getLocations().subscribe(res => {
      this.locations = res.locations;
      this.locations.forEach(element => {
        if (element.parent != undefined) {
          if (!!element.parent.parent) {
            if (element.parent.parent.code.toUpperCase() == "IN")
              this.indiaMap.set(element.parent.code, element.parent);
          }
        }
      });
      console.log("all india airports", this.indiaMap);
    });
  }

}
