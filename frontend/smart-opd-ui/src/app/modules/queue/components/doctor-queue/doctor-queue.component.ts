import { Component, OnDestroy, OnInit } from '@angular/core';
import { QueueService } from '../../services/queue.service';
import { Queue } from '../../models/queue';
import { QueueDashboard } from '../../models/queue-dashboard';
import { AuthService } from 'src/app/core/services/auth.service';


@Component({
  selector: 'app-doctor-queue',
  templateUrl: './doctor-queue.component.html',
  styleUrls: ['./doctor-queue.component.scss']
})
export class DoctorQueueComponent implements OnInit,OnDestroy {


  doctorId!:number; // later from JWT
  queueList: Queue[] = [];
  dashboard!: QueueDashboard;
  currentPatient?: Queue;
  intervalId:any;
  constructor(private queueService: QueueService,private authService: AuthService) { }



 ngOnInit() {

  this.authService.getCurrentUser()
    .subscribe({
      next: (user) => {
        console.log(user);
        this.doctorId = user.id;
        this.loadDashboard();
        this.loadQueue();
      },

      error: (err) => {
        console.error("Unable to get user", err);
      }
    });


  this.intervalId = setInterval(() => {
      if(this.doctorId){
        this.refresh();
      }
  },10000);

}

  ngOnDestroy(){
    if(this.intervalId){
      clearInterval(this.intervalId);
    }
  }


  loadDashboard() {
    this.queueService
      .getDashboard(this.doctorId)
      .subscribe(res => {
        this.dashboard = res;
      });
  }

  loadQueue() {
    this.queueService
      .getDoctorQueue(this.doctorId)
      .subscribe(res => {
        this.queueList = res;
      });
  }

  nextPatient() {
    this.queueService
      .nextPatient(this.doctorId)
      .subscribe(() => {
        this.refresh();
      });
  }

  complete() {
    if (this.currentPatient) {
      this.queueService
        .complete(this.currentPatient.id)
        .subscribe(() => {
          this.refresh();
        });
    }

  }

  skip() {
    if (this.currentPatient) {
      this.queueService
        .skip(this.currentPatient.id)
        .subscribe(() => {
          this.refresh();
        });
    }
  }


  recall() {
    if (this.currentPatient) {
      this.queueService
        .recall(this.currentPatient.id)
        .subscribe(() => {
          this.refresh();
        });
    }
  }

  selectPatient(item: Queue) {
    this.currentPatient = item;
  }

  refresh() {
    this.loadDashboard();
    this.loadQueue();
  }


}