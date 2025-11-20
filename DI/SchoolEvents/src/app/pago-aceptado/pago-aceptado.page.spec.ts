import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PagoAceptadoPage } from './pago-aceptado.page';

describe('PagoAceptadoPage', () => {
  let component: PagoAceptadoPage;
  let fixture: ComponentFixture<PagoAceptadoPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(PagoAceptadoPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
