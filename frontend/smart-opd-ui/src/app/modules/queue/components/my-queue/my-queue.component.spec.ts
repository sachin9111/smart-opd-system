import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyQueueComponent } from './my-queue.component';

describe('MyQueueComponent', () => {
  let component: MyQueueComponent;
  let fixture: ComponentFixture<MyQueueComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MyQueueComponent]
    });
    fixture = TestBed.createComponent(MyQueueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
