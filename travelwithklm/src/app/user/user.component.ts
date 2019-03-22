import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SampleService } from '../sample.service';
import { Fare } from '../fare';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  fareForm: FormGroup;
  resData = ""
  isLoading = false;
  tokenError = false;
  orgnError = false;
  destError = false;
  submitted = false;
  success = false;
  isNew = true;
  message = null;
  result = null;
  displayItems = null;
  private fareData: Fare = new Fare;
  fare: Fare = new Fare;
  userIdUpdate = null;
  orgnval: any;
  token: string;
  destval: any;
  isOriginSelected: boolean = false;
  isDestSelected: boolean = false;
  localToken: string;

  constructor(
    private formBuilder: FormBuilder,
    private sampleService: SampleService
  ) {

  }

  ngOnInit(): void {
    this.fareForm = this.formBuilder.group({
      origin: ['', Validators.required],
      destination: ['', Validators.required],
      token: ['', Validators.required]
    });
  }

  getFare(originv: string, destv: string) {

    this.submitted = true;

    if (!originv && !destv) {
      this.orgnError = true;
      this.destError = true;
      return;
    }
    this.isLoading = true;
    console.log("dataaaaa :::: ", this.fareForm.value)
    this.fare.origin = originv;
    this.fare.destination = destv
    this.fare.token = JSON.stringify(localStorage.getItem("token"));
    this.sampleService.getFareByOriginAndDest(this.fare).subscribe(res => {
      this.isLoading = false;
      this.fareData = res;
    });

  }

  onSubmit(originv: string, destv: string) {
    console.log("under onsubmittt")
    if (this.fareForm.invalid) {
      return;
    }

  }
  fetchOrigin(val: any) {
    this.isOriginSelected = true;
    this.buildFareData(val);
    this.sampleService.getAirportByTerm(this.fare).subscribe(res => {
      if (res) {
        this.resData="";
        this.result = res.locations;
        return;
      }
      this.resData = "No Data Found";
    });
  }

  fetchDest(value: any) {
    this.isDestSelected = true;
    this.buildFareData(value);
  
    this.fareData.term = value;
    this.sampleService.getAirportByTerm(this.fare).subscribe(res => {
      if (res) {
        this.resData="";
        this.result = res.locations;
        return;
      }
      this.resData = "No Data Found";
    });
  }
  selectOrigin(obj: any) {
    this.orgnval = obj.code;
    this.isOriginSelected = false;
  }
  selectDest(obj: any) {
    this.destval = obj.code;
    this.isDestSelected = false;
  }

  buildFareData(value: any) {
    this.fare.term = value;
  }
}
