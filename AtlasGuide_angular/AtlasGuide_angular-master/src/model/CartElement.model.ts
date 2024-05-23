import {ActivityDto} from "./ActivityDto.model";


export class CartElement{
   id: number=0;
   nbrOfPerson: number=0;

   activity!:ActivityDto ;
   sub_total: number=0.0;

}
