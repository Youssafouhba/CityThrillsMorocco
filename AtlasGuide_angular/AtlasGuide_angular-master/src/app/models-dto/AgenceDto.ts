import {User} from "../models/User";

export class AgenceDto {
  id: number;
  name: string;
  location: string;
  email: string;
  phone: string;
  password: string;
  user!: User
  constructor(
    id: number,
    name: string,
    location: string,
    email: string,
    phone: string,
    password: string,) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.email = email;
    this.phone = phone;
    this.password = password;
  }
}
