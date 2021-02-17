import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainFindComponent } from './train-find.component';

describe('TrainFindComponent', () => {
  let component: TrainFindComponent;
  let fixture: ComponentFixture<TrainFindComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrainFindComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainFindComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
