import {Component, OnInit} from '@angular/core';
import SwaggerUI from "swagger-ui";


@Component({
  selector: 'app-swagger',
  templateUrl: './swagger.component.html',
  styleUrl: './swagger.component.css'
})
export class SwaggerComponent implements OnInit {
  ngOnInit(): void {
    SwaggerUI({
      url: 'path/to/your/openapi-spec.json', // Remplacez par le chemin vers votre spécification OpenAPI
      dom_id: '#swagger-ui',
    });
  }
}
