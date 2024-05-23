import {Reservation} from "../models/Reservation";
import {Agence} from "../models/Agence";
import {Program} from "../models/Program";

export class ActivityDto {
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
  agence: Agence;
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
    this.agence= agence;
  }


  isBookingAvailable(): boolean {
    const currentDate = new Date();
    if (!this.isFlexibleDates) {
      if (
        this.startDate <= currentDate &&
        (!this.endDate || this.endDate >= currentDate)
      ) {
        return true;
      }
    } else {
      if (this.bookingEndDate && this.bookingEndDate < currentDate) {
        return false;
      }
    }
    return false;
  }

  isFullyBooked(): boolean {
    return this.isPlacesLimited && this.participants >= this.maxParticipants;
  }


}
