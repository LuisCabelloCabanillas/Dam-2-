import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PagoDenegadoAdPage } from './pago-denegado-ad.page';

describe('PagoDenegadoAdPage', () => {
  let component: PagoDenegadoAdPage;
  let fixture: ComponentFixture<PagoDenegadoAdPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(PagoDenegadoAdPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
