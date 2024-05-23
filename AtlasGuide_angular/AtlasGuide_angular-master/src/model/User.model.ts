export enum Role {
    ROLE_ADMIN = 'ROLE_ADMIN',
    ROLE_USER = 'ROLE_USER'
}

export class User {
    id: number = 0;
    firstname: string ="";
    lastname: string ="";
    email: string ="";
    phone: string ="";
    password: string ="";

    constructor() {

    }

}
