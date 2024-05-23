import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BodyDetailsComponent } from './body-details.component';

describe('BodyDetailsComponent', () => {
  let component: BodyDetailsComponent;
  let fixture: ComponentFixture<BodyDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BodyDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BodyDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
