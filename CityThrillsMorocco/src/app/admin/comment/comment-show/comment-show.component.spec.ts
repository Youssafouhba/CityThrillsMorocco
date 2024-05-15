import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentShowComponent } from './comment-show.component';

describe('CommentShowComponent', () => {
  let component: CommentShowComponent;
  let fixture: ComponentFixture<CommentShowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CommentShowComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CommentShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
