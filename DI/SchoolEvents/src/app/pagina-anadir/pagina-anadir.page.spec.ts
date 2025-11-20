import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PaginaAnadirPage } from './pagina-anadir.page';

describe('PaginaAnadirPage', () => {
  let component: PaginaAnadirPage;
  let fixture: ComponentFixture<PaginaAnadirPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(PaginaAnadirPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
