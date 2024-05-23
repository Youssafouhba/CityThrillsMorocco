export class Reservation {
  id: number;
  participantCount: number;
  startDate: Date;
  endDate: Date;
  activityId: number;
  constructor(id: number, participantCount: number,startDate: Date,endDate: Date,activityId: number){
    this.id = id;
    this.participantCount = participantCount;
    this.startDate = startDate;
    this.endDate = endDate;
    this.activityId = activityId;
  }
}
