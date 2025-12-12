import { ComponentFixture, TestBed } from '@angular/core/testing';
import { OpinionesAdPage } from './opiniones-ad.page';

describe('OpinionesAdPage', () => {
  let component: OpinionesAdPage;
  let fixture: ComponentFixture<OpinionesAdPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(OpinionesAdPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
