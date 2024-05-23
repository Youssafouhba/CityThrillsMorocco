import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentDetailComponent } from './comment-detail.component';

describe('CommentDetailComponent', () => {
  let component: CommentDetailComponent;
  let fixture: ComponentFixture<CommentDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CommentDetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CommentDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
