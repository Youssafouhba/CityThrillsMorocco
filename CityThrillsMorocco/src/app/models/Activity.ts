import {Time} from "@angular/common";

export interface Activity {
  id: number,
  designation: string,
  starteddate: Date,
  finisheddate: Date,
  startedduration: Time,
  finishedduration: Time,
  description: string,
  descriptiondetail: string,
  price: number,
  imageUrl: string,
  category: string,
  location: string,
  status: string,
}
