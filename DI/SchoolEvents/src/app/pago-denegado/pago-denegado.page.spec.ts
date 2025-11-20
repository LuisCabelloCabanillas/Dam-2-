import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PagoDenegadoPage } from './pago-denegado.page';

describe('PagoDenegadoPage', () => {
  let component: PagoDenegadoPage;
  let fixture: ComponentFixture<PagoDenegadoPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(PagoDenegadoPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
