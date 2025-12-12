import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NotRecAdPage } from './not-rec-ad.page';

describe('NotRecAdPage', () => {
  let component: NotRecAdPage;
  let fixture: ComponentFixture<NotRecAdPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(NotRecAdPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
