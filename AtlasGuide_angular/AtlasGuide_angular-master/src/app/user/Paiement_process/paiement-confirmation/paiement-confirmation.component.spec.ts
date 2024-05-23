import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaiementConfirmationComponent } from './paiement-confirmation.component';

describe('PaiementConfirmationComponent', () => {
  let component: PaiementConfirmationComponent;
  let fixture: ComponentFixture<PaiementConfirmationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PaiementConfirmationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PaiementConfirmationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
