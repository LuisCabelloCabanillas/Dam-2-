import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Evento1Page } from './evento1.page';

describe('Evento1Page', () => {
  let component: Evento1Page;
  let fixture: ComponentFixture<Evento1Page>;

  beforeEach(() => {
    fixture = TestBed.createComponent(Evento1Page);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
