import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PointsInterestsComponent } from './points-interests.component';

describe('PointsInterestsComponent', () => {
  let component: PointsInterestsComponent;
  let fixture: ComponentFixture<PointsInterestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PointsInterestsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PointsInterestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
