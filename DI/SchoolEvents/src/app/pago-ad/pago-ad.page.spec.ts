import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PagoAdPage } from './pago-ad.page';

describe('PagoAdPage', () => {
  let component: PagoAdPage;
  let fixture: ComponentFixture<PagoAdPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(PagoAdPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
