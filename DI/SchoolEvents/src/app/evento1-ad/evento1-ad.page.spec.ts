import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Evento1AdPage } from './evento1-ad.page';

describe('Evento1AdPage', () => {
  let component: Evento1AdPage;
  let fixture: ComponentFixture<Evento1AdPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(Evento1AdPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
