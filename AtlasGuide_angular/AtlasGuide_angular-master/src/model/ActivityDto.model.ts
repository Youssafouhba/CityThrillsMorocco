import {City} from "./enumeration/City.enum";


export class ActivityDto {
    id: number=0;

   designation:string="";

   price:number=0.0;

  imageUrl:string="";

  city:City| undefined;



}
