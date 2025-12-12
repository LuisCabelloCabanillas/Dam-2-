import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Evento2AdPage } from './evento2-ad.page';

describe('Evento2AdPage', () => {
  let component: Evento2AdPage;
  let fixture: ComponentFixture<Evento2AdPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(Evento2AdPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
