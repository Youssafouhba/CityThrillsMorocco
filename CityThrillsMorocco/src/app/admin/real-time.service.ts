import { Injectable, OnDestroy } from '@angular/core';
import {CompatClient, Stomp} from '@stomp/stompjs';
import {StompSubscription} from '@stomp/stompjs/src/stomp-subscription';

export interface Task{
  name: string;
  days: number;
}

export type ListenerCallBack = (message: Task) => void;

@Injectable({
  providedIn: 'root'
})
export class RealTimeService implements OnDestroy{

  private connection: CompatClient | undefined = undefined;

  private subscription: StompSubscription | undefined;

  constructor() {
    this.connection = Stomp.client('http://localhost:8080/chat');
    this.connection.connect({}, () => {});
  }

  public send(task: Task): void {
    if (this.connection && this.connection.connected) {
      this.connection.send('/app/chat', {}, JSON.stringify(task));
    }
  }

  public listen(fun: ListenerCallBack): void {
    if (this.connection) {
      this.connection.connect({}, () => {
        this.subscription = this.connection!.subscribe('/topic/messages', message => fun(JSON.parse(message.body)));
      });
    }
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}

