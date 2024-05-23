import {Component, OnInit} from '@angular/core';

import {ActivityService} from "../../../../service/activity/activity.service";
import {Activity} from "../../../models/Activity";
import {PipsService} from "../../../admin/pips/pips.service";
import {CartService} from "../../../../service/cart/cart.service";


@Component({
  selector: 'app-top-activities',
  templateUrl: './top-activities.component.html',
  styleUrl: './top-activities.component.css'
})
export class TopActivitiesComponent implements OnInit{

    activities : Activity[] = [];
    averageNotes: number[] = [];
    numberOfComments : number [] = [];
    clients: any[] = [];
  constructor(
    private  activityService : ActivityService ,
    private cartService:CartService,
    protected Pipe: PipsService) {}

  ngOnInit() {
    this.getAllActivities();
  }

  getActivitiesWithHighRating(): void {
    this.activityService.getActivitiesWithHighRating().subscribe(
      (data: Activity[]) => {
        this.activities = data;
        this.activities.forEach(activity => {
          this.getNote(activity.id);
          this.getnumberOfComments(activity.id);
        });
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getNote(activityId: number) {
    this.activityService.getNote(activityId).subscribe(
      (averageNote: number) => {
        this.averageNotes.push(averageNote);
      }
    );
  }

  getnumberOfComments(activityId : number){
    this.activityService.getNumberOfComments(activityId).subscribe(
      (numberOfComments: number) => {
        this.numberOfComments.push(numberOfComments);
      }
    );
  }

  getAllActivities() : void{
    this.activityService.getAllActivities().subscribe(
      (data : Activity[]) =>{
        this.activities=data;},
      (error) => {
        console.log(error);
      });
  }


  addToCart(activityId: number, userId: number, nbr: number) {
    this.cartService.addToCart(activityId, userId, nbr).subscribe(() => {
      // Réussite de la requête, exécutez ici des actions après l'ajout au panier
      console.log('Élément ajouté au panier avec succès');
      this.cartService.incrementElementCount();
    }, error => {
      console.error('Une erreur s\'est produite lors de l\'ajout au panier :', error);
      // Gérez les erreurs ici
    });
  }



}


