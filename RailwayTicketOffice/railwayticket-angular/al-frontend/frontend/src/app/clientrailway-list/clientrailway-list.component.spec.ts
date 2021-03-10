import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientrailwayListComponent } from './clientrailway-list.component';

describe('ClientrailwayListComponent', () => {
  let component: ClientrailwayListComponent;
  let fixture: ComponentFixture<ClientrailwayListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientrailwayListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientrailwayListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
