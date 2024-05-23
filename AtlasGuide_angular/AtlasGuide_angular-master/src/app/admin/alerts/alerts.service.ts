import {Injectable} from '@angular/core';
import Swal, {SweetAlertOptions} from "sweetalert2";

@Injectable({
  providedIn: 'root'
})
export class AlertsService {

  constructor() { }


  registerAlert(httpcode: number){
    if (httpcode==400) Swal.fire(
      {
        text: 'Email is already in use! .',
        position: 'top',
        width: '400px',
        showCloseButton: true,
        showConfirmButton: false,
        customClass: {
          popup: 'alert alert-danger',
        }
      }
    )
    if (httpcode==200) Swal.fire(
      {
        text: 'Confirm your Account by the link sent to your email address .',
        position: 'top',
        width: '400px',
        showConfirmButton: true,
        customClass: {
          popup: 'alert alert-info',
        }
      }
    )
  }

  loginAlerts(httpCode: number){
    if(httpCode==400){
      Swal.fire(
        {
          text: 'Incorrect username or password',
          position: 'top',
          width: '400px',
          showCloseButton: true,
          showConfirmButton: false,
          customClass: {
            popup: 'alert alert-danger',
          }
        }
      )
    }
    if (httpCode==1){
      Swal.fire(
       {
            title: "Email verified successfully",
            text: 'Proceed to Login.',
            position: 'top',
            width: '400px',
            showConfirmButton: false,
            customClass: {
              popup: 'alert alert-success',
            }
        }
      )
    }
  }

  updateAlert(httpmessage: string){

      Swal.fire(
        {
          title: httpmessage,
          icon: 'success',
          width: '400px',
          showConfirmButton: false,
          timer: 40000,
          timerProgressBar: true,
          customClass: {
            popup: 'alert alert-success active-popup',
          }
        });

  }

  activityAlert(httpCode: number){
    if(httpCode === 201){
      Swal.fire(
        {
          title: 'Activity Added Successfully .',
          icon: 'success',
          width: '400px',
          showConfirmButton: false,
          customClass: {
            popup: 'alert alert-info active-popup',
          }
        });
    }
    if(httpCode === 400){
      Swal.fire(
        {
          title: 'Activity Already Exists !!.',
          icon: 'warning',
          width: '400px',
          showConfirmButton: false,
          customClass: {
            popup: 'alert alert-danger',
          }
        });
    }

  }

  deleteAlert() {
    Swal.fire(
      {
        title: 'Delete Pending',
        icon: 'warning',
        width: '400px',
        showConfirmButton: false,
        customClass: {
          popup: 'alert alert-warning'
        }
      });
  }
}
