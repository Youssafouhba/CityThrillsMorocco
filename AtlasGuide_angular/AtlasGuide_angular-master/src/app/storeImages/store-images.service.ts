import {Injectable, OnInit} from '@angular/core';
import {Cloudinary, CloudinaryImage} from '@cloudinary/url-gen';
import {map, Observable, of} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {sepia} from "@cloudinary/url-gen/actions/effect";

@Injectable({
  providedIn: 'root'
})
export class StoreImagesService implements OnInit {

  img!: CloudinaryImage
  constructor(private http: HttpClient) {
  }

  ngOnInit() {

    const cld = new Cloudinary({
      cloud: {
        cloudName: 'demo'
      }
    });
    // Use the image with public ID, 'front_face'.
    this.img = cld.image('front_face');

    // Apply the transformation.
    this.img
      .effect(sepia());  // Apply a sepia effect.

  }


  public uploadFile(file: File): Observable<string> {
    if (file) {
      const data = new FormData();
      data.append("file", file);
      data.append("upload_preset", "d1zmuabv");
      data.append("cloud_name", "dlkvn0fpz");

      return this.http.post("https://api.cloudinary.com/v1_1/dlkvn0fpz/image/upload", data)
        .pipe(
          map((response: any) => response.secure_url)
        );
    }
    return of("");
  }

}
