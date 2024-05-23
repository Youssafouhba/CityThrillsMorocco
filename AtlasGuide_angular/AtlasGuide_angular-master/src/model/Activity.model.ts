export class Activity {
    id: number = 0;
    designation: string = "";
    date: Date = new Date();
    duration: string = "";
    location: string = "";
    description: string = "";
    price: number = 0;
    category: string= "";

    image_path : string = "";
    city: string= "";
    agenceId: number= 0;

    constructor() {
    }
}
