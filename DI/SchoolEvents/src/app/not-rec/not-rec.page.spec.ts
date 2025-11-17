import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NotRecPage } from './not-rec.page';

describe('NotRecPage', () => {
  let component: NotRecPage;
  let fixture: ComponentFixture<NotRecPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(NotRecPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
