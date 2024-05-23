import {AfterViewInit, Component, ElementRef, HostListener, ViewChild} from '@angular/core';
import 'swiper/swiper-bundle.css';
import {Subscription} from "rxjs";

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.css']
})



export class SliderComponent  {
  photos = [
    'assets/images/girl.jpg',
    'assets/images/culture.jpg',
    'assets/images/nature1.jpg',
    'assets/images/sport.jpg',
    'assets/images/food.jpg'
  ].map(img => `url(${img})`);


  images = [
    ...this.photos,
  ];

  styles = this.images.map(url => ({
    backgroundPosition:'center center',
    backgroundRepeat:'no-repeat',
    backgroundSize:'cover',
    backgroundImage: url
  }));

  index = 0;

  onSetIndex(i: number) {
    this.index = i;
  }


  @ViewChild('stickyMenu') menuElement: ElementRef;

  sticky: boolean = false;
  elementPosition: any;

  constructor() { this.menuElement = {} as ElementRef;}

  ngOnInit() {
  }

  ngAfterViewInit(){
    this.elementPosition = this.menuElement.nativeElement.offsetTop;
  }

  @HostListener('window:scroll', ['$event'])
  handleScroll(){
    const windowScroll = window.pageYOffset;
    if(windowScroll >= this.elementPosition){
      this.sticky = true;
    } else {
      this.sticky = false;
    }
  }


}
