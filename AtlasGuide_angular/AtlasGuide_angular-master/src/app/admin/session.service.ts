import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  id!: number;
  constructor() { if (this.getSessionData('user')) this.id = this.getSessionData('user').Uid; }

  setSessionData(key: string, value: any): void {
    localStorage.setItem(key, JSON.stringify(value));
  }

  getSessionData(key: string): any {
    const data = localStorage.getItem(key);
    return data ? JSON.parse(data) : null;
  }

  clearSessionData(key: string): void {
    localStorage.removeItem(key);
  }


}
