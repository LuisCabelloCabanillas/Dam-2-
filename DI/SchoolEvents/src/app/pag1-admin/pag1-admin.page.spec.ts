import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Pag1AdminPage } from './pag1-admin.page';

describe('Pag1AdminPage', () => {
  let component: Pag1AdminPage;
  let fixture: ComponentFixture<Pag1AdminPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(Pag1AdminPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
