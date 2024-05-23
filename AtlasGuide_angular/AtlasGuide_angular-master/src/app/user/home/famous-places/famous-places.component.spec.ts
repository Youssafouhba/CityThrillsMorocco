import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FamousPlacesComponent } from './famous-places.component';

describe('FamousPlacesComponent', () => {
  let component: FamousPlacesComponent;
  let fixture: ComponentFixture<FamousPlacesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FamousPlacesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FamousPlacesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
