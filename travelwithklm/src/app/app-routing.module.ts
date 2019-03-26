import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { UserComponent } from './user/user.component';
import { SampleComponent } from './sample.component';
import { StatsComponent } from './stats/stats.component';
import { AirportsComponent } from './airports/airports.component';

export const routes: Routes = [
  { path: 'about', component: AboutComponent },
  { path: 'search', component: UserComponent },
  { path: 'view', component: SampleComponent },
  { path: 'home', component: HomeComponent },
  { path: 'stat', component: StatsComponent },
  { path: 'airports', component: AirportsComponent },
  { path: '', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
