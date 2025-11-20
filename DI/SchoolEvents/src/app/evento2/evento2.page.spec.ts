import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Evento2Page } from './evento2.page';

describe('Evento2Page', () => {
  let component: Evento2Page;
  let fixture: ComponentFixture<Evento2Page>;

  beforeEach(() => {
    fixture = TestBed.createComponent(Evento2Page);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
