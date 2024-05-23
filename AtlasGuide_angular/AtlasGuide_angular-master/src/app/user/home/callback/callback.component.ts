import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-callback',
  templateUrl: './callback.component.html',
  styleUrl: './callback.component.css'
})
export class CallbackComponent implements OnInit{
  nombreTotalClients: number = 100;
  nombreActuelClients: number = 0;

  nombreTotalAgences: number = 250;
  nombreActuelAgences: number = 0;

  nombreTotalDestinations: number = 150;
  nombreActuelDestinations: number = 0;

  nombreTotalActivites: number = 200;
  nombreActuelActivites: number = 0;

  increment: number = 5;
  intervalId: any;

  constructor() { }

  ngOnInit(): void {
    this.intervalId = setInterval(() => {
      // Incrémentation du nombre de clients
      if (this.nombreActuelClients < this.nombreTotalClients) {
        this.nombreActuelClients += this.increment;
      }

      // Incrémentation du nombre d'agences
      if (this.nombreActuelAgences < this.nombreTotalAgences) {
        this.nombreActuelAgences += this.increment;
      }

      // Incrémentation du nombre de destinations
      if (this.nombreActuelDestinations < this.nombreTotalDestinations) {
        this.nombreActuelDestinations += this.increment;
      }

      // Incrémentation du nombre d'activités
      if (this.nombreActuelActivites < this.nombreTotalActivites) {
        this.nombreActuelActivites += this.increment;
      }

      // Vérification pour arrêter l'intervalle lorsque toutes les valeurs sont atteintes
      if (this.toutesLesValeursAtteintes()) {
        clearInterval(this.intervalId);
      }
    }, 100);
  }

  // Fonction pour vérifier si toutes les valeurs sont atteintes
  toutesLesValeursAtteintes(): boolean {
    return this.nombreActuelClients >= this.nombreTotalClients &&
      this.nombreActuelAgences >= this.nombreTotalAgences &&
      this.nombreActuelDestinations >= this.nombreTotalDestinations &&
      this.nombreActuelActivites >= this.nombreTotalActivites;
  }



}
