import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DoctorQueueComponent } from './components/doctor-queue/doctor-queue.component';
import { MyQueueComponent } from './components/my-queue/my-queue.component';

const routes: Routes = [

{
 path:'',
 component:DoctorQueueComponent
},
{
 path:'my',
 component:MyQueueComponent
}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QueueRoutingModule { }
