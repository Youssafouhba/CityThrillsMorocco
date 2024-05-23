import {Reservation} from "./Reservation";
import {Agence} from "./Agence";
import {Program} from "./Program";
export const isBookingAvailable= (activity:Activity)=>{
  const currentDate = new Date();
  if (!activity.isFlexibleDates) {
    if (
      activity.startDate <= currentDate &&
      (!activity.endDate || activity.endDate >= currentDate)
    ) {
      return true;
    }
  } else {
    if (activity.bookingEndDate && activity.bookingEndDate < currentDate) {
      return false;
    }
  }
  return false;
}

export const isFullyBooked = (activity: Activity) => {
  return activity.isPlacesLimited && activity.participants >= activity.maxParticipants;
}

export class Activity {
  id: number;
  designation: string;
  description: string;
  descriptionDetail: string;
  startDate: Date;
  endDate: Date;
  location: string;
  price: number;
  category: string;
  imageUrl: string;
  maxParticipants: number;
  participants: number;
  isAvailableYearRound: boolean;
  isFlexibleDates: boolean;
  isPlacesLimited: boolean;

  program!: Program;

  status: string;
  bookingStartDate: Date;
  bookingEndDate: Date;
  city: string;


  constructor(
    id: number,
    designation: string,
    description: string,
    descriptionDetail: string,
    startDate: Date,
    endDate: Date,
    location: string,
    price: number,
    category: string,
    imageUrl: string,
    maxParticipants: number,
    participants: number,
    isAvailableYearRound: boolean,
    isFlexibleDates: boolean,
    isPlacesLimited: boolean,
    bookingStartDate: Date,
    bookingEndDate: Date,
    city: string,
    agence: Agence

  ) {
    this.id = id;
    this.designation = designation;
    this.description = description;
    this.descriptionDetail = descriptionDetail;
    this.startDate = startDate;
    this.endDate = endDate;
    this.location = location;
    this.price = price;
    this.category = category;
    this.imageUrl = imageUrl;
    this.status = "Active";
    this.maxParticipants = maxParticipants;
    this.participants = participants;
    this.isAvailableYearRound = isAvailableYearRound;
    this.isFlexibleDates = isFlexibleDates;
    this.isPlacesLimited = isPlacesLimited;
    this.bookingStartDate = bookingStartDate;
    this.bookingEndDate = bookingEndDate;
    this.city = city;
  }


}
