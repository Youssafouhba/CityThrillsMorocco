import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor() { }

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
