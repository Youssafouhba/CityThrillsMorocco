import { Component } from '@angular/core';

@Component({
  selector: 'app-testimonial',
  templateUrl: './testimonial.component.html',
  styleUrl: './testimonial.component.css'
})
export class TestimonialComponent {
  index: number = 0;
  images: string[] = [ // Corrected declaration and added sample image URLs
    'assets/images/gallery-4.jpg',
    'assets/images/gallery-2.jpg',
    'assets/images/gallery-6.jpg'
  ];

  previous(): void {
    this.index = (this.index - 1 + this.images.length) % this.images.length;
  }

  next(): void {
    this.index = (this.index + 1) % this.images.length;
  }

}
