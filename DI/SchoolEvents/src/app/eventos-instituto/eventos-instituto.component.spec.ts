import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { EventosInstitutoComponent } from './eventos-instituto.component';

describe('EventosInstitutoComponent', () => {
  let component: EventosInstitutoComponent;
  let fixture: ComponentFixture<EventosInstitutoComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [EventosInstitutoComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(EventosInstitutoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
