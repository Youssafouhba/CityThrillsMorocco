import {Time} from "@angular/common";
import {ActivityCategories} from "../admin/enumerations/activity-categories";

export interface ActivityDto {
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
