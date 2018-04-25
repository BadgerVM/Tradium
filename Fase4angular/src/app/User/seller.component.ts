import { Component } from '@angular/core';
import { Http } from '@angular/http';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { ProductService, Product } from './../Product/product.service';
import { User, UserService } from './user.service';
import { TagContentType } from '@angular/compiler/src/ml_parser/tags';
import { BrowserModule } from '@angular/platform-browser';
import { AgmCoreModule } from '@agm/core';
import { environment } from './../../environments/environment';

@Component({
    selector: 'seller',
    templateUrl: './seller.component.html',
    styles: [`
    agm-map {
      height: 1000px;
    }
  `],
    styleUrls:[
      '../CSS/fonts/font-awesome-4.7.0/css/font-awesome.min.css',
      '../CSS/fonts/themify/themify-icons.css',
      '../CSS/fonts/Linearicons-Free-v1.0.0/icon-font.min.css',
      '../CSS/fonts/elegant-font/html-css/style.css',
      '../CSS/vendor/animate/animate.css',
      '../CSS/vendor/bootstrap/css/bootstrap.min.css',
      '../CSS/vendor/css-hamburgers/hamburgers.min.css',
      '../CSS/vendor/animsition/css/animsition.min.css',
      '../CSS/vendor/select2/select2.min.css',
      '../CSS/vendor/slick/slick.css',
      '../CSS/css/util.css',
      '../CSS/css/main.css'
  
    ]
  })

  export class SellerComponent {
    public id: string;
    private user: User;
    private products;
    private valorations;
    private baseUrl:string;

    constructor(private route: ActivatedRoute, private service: UserService) {
        this.id = route.snapshot.params['id'];
        this.baseUrl = environment.baseURL;     

    }

    ngOnInit() {
      this.user = {id: null, name: null, email: null,  locationX: null, locationY: null, medValoration: null, image: null, roles: null};
      this.valorations = {id: null, name: null, email: null,  locationX: null, locationY: null, medValoration: null, image: null, roles: null, valoration: null, description: null, date: null};
      this.products=null;
      
      this.service.getUser(this.id).subscribe(
        user => this.user = user,
        error => console.error("Error in seller.component.ts"),
      );
  

      this.service.getProducts(this.id).subscribe(
        products => this.products = products,
        error => console.error("Error in seller2.component.ts"),
      );
      
      console.log(this.products);

      
      this.service.getValorations(this.id).subscribe(
        valorations => this.valorations = valorations,
        error => console.error("Error in seller2.component.ts"),
      );
    
     // let locX = parseFloat(this.user.locationX);
      //let locY = parseFloat(this.user.locationY);
      let locX = 40.4167754;
      let locY =-3.7037901999999576;
      let mapProp = {
        center: new google.maps.LatLng(locX, locY),
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROADMAP        
      };
      
      let map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

    }


  }


