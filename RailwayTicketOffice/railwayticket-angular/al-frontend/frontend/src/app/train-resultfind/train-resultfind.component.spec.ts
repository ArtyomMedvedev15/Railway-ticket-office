import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainResultfindComponent } from './train-resultfind.component';

describe('TrainResultfindComponent', () => {
  let component: TrainResultfindComponent;
  let fixture: ComponentFixture<TrainResultfindComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrainResultfindComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainResultfindComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
