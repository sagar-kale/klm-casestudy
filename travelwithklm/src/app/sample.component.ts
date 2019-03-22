import { Component, OnInit } from '@angular/core';
import { SampleService } from './sample.service';
import { Observable, Subject } from 'rxjs';
import { Job } from './job';

@Component({
    selector: 'sample-app',
    templateUrl: './sample.component.html'

})
export class SampleComponent implements OnInit {
    title = "my first angular app";
    jobs: Job[];
    constructor(
        private sampleService: SampleService
    ) { }

    ngOnInit() {
    }
    
} 