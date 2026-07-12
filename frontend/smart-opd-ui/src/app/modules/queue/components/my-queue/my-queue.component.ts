import { Component, OnInit } from '@angular/core';
import { QueueService } from '../../services/queue.service';
import { Queue } from '../../models/queue';


@Component({

  selector: 'app-my-queue',
  templateUrl: './my-queue.component.html',
  styleUrls: ['./my-queue.component.scss']

})
export class MyQueueComponent implements OnInit {


  queue?: Queue;
  constructor(
    private queueService: QueueService
  ) { }

  ngOnInit() {
    this.loadQueue();
  }

  loadQueue() {

    this.queueService
      .getMyQueue()
      .subscribe({
        next: (response) => {
          if (response.length) {
            this.queue = response[0];
          }
        }
      });
  }


  getPatientsAhead() {
    if (!this.queue) {
      return 0;
    }
    return this.queue.tokenNumber - 1;
  }


}