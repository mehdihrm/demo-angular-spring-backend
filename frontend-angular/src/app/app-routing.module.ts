import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./component/home/home.component";
import {ProfileComponent} from "./component/profile/profile.component";
import {LoginComponent} from "./component/login/login.component";
import {LoadStudentsComponent} from "./component/load-students/load-students.component";
import {LoadPaymentsComponent} from "./component/load-payments/load-payments.component";
import {DashboardComponent} from "./component/dashboard/dashboard.component";
import {StudentsComponent} from "./component/students/students.component";
import {PaymentsComponent} from "./component/payments/payments.component";
import {AdminTemplateComponent} from "./component/admin-template/admin-template.component";
import {AuthGuard} from "./guards/auth.guard";
import {AuthorizationGuard} from "./guards/authorization.guard";

const routes: Routes = [
  {path:"login",component:LoginComponent},
  {path:"",component:LoginComponent},
  {path:"admin",component:AdminTemplateComponent,
    canActivate:[AuthGuard],
    children:[
      {path:"home",component:HomeComponent},
      {path:"profile",component:ProfileComponent},
      {path:"loadStudents",component:LoadStudentsComponent,
        canActivate:[AuthorizationGuard],data : {roles:['ADMIN']}
      },
      {path:"loadPayments",component:LoadPaymentsComponent},
      {path:"dashboard",component:DashboardComponent},
      {path:"students",component:StudentsComponent},
      {path:"payments",component:PaymentsComponent},
    ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
