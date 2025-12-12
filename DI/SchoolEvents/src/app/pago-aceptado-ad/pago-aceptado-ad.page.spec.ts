import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PagoAceptadoAdPage } from './pago-aceptado-ad.page';

describe('PagoAceptadoAdPage', () => {
  let component: PagoAceptadoAdPage;
  let fixture: ComponentFixture<PagoAceptadoAdPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(PagoAceptadoAdPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
