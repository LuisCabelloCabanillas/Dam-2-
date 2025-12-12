import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PagEditarPage } from './pag-editar.page';

describe('PagEditarPage', () => {
  let component: PagEditarPage;
  let fixture: ComponentFixture<PagEditarPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(PagEditarPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
