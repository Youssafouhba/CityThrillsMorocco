import {ProgramElement} from "./ProgramElement";

export class Program{
  id: number;
  name: string;
  description: string;
  numberOfDays!: number;
  programElements!: ProgramElement[];
  constructor(id: number, name: string, description: string){
    this.id = id;
    this.name = name;
    this.description = description;
  }
}
