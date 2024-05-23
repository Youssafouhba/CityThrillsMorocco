import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivitesByCityComponent } from './activites-by-city.component';

describe('ActivitesByCityComponent', () => {
  let component: ActivitesByCityComponent;
  let fixture: ComponentFixture<ActivitesByCityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ActivitesByCityComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ActivitesByCityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
