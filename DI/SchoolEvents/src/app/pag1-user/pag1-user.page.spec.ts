import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Pag1UserPage } from './pag1-user.page';

describe('Pag1UserPage', () => {
  let component: Pag1UserPage;
  let fixture: ComponentFixture<Pag1UserPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(Pag1UserPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
