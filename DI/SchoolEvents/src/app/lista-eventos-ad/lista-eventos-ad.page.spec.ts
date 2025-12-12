import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ListaEventosAdPage } from './lista-eventos-ad.page';

describe('ListaEventosAdPage', () => {
  let component: ListaEventosAdPage;
  let fixture: ComponentFixture<ListaEventosAdPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaEventosAdPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
