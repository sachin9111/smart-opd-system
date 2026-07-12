import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { QueueRoutingModule } from './queue-routing.module';
import { MyQueueComponent } from './components/my-queue/my-queue.component';


@NgModule({
  declarations: [
    MyQueueComponent
  ],
  imports: [
    CommonModule,
    QueueRoutingModule
  ]
})
export class QueueModule { }
