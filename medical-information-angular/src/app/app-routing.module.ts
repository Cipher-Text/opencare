import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DoctorComponent } from './doctor/doctor.component';

const routes: Routes = [
  { path: 'home', component: DoctorComponent },
  { path: 'doctor', component: DoctorComponent },
  { path: 'hospital', component: DoctorComponent },
  { path: 'profile', component: DoctorComponent },
  { path: 'signup', component: DoctorComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],

  exports: [RouterModule],
})
export class AppRoutingModule {}
