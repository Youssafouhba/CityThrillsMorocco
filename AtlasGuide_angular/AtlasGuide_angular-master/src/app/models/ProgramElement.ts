export class ProgramElement{
  id: number;
  name: string;
  description: string;
  day!: string;
  constructor(id: number, name: string, description: string){
    this.id = id;
    this.name = name;
    this.description = description;
  }
}
