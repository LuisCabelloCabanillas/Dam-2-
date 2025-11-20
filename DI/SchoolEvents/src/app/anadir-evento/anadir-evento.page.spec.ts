import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AnadirEventoPage } from './anadir-evento.page';

describe('AnadirEventoPage', () => {
  let component: AnadirEventoPage;
  let fixture: ComponentFixture<AnadirEventoPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(AnadirEventoPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
