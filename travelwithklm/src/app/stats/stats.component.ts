import { Component, OnInit } from '@angular/core';
import { SampleService } from '../sample.service';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {
  stats: any;
  constructor(
    private sampleService: SampleService
  ) { }

  ngOnInit() {
    this.fetchStat();
  }
  fetchStat() {
    this.sampleService.fetchStat().subscribe(res => {
      this.stats = res;
    });
  }

}
