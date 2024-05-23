import { Injectable } from '@angular/core';
import {DatePipe} from "@angular/common";
@Injectable({
  providedIn: 'root'
})
export class PipsService {

  constructor(private datePipe: DatePipe,) { }
  formatDateString(dateString: string, currentFormat: string, desiredFormat: string): string {
    if (dateString){
      const dateObject = new Date(dateString);
      const formattedDate = this.datePipe.transform(dateObject, desiredFormat);
      return <string>formattedDate;
    }else {
      return '-- -- --'
    }


  }
}
