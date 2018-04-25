import { Component } from '@angular/core';
import { Http } from '@angular/http';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { ProductService, Product } from '../Product/product.service';
import { User, Buyer } from '../User/user.service';
import { TagContentType } from '@angular/compiler/src/ml_parser/tags';
import { BrowserModule } from '@angular/platform-browser';
import { AgmCoreModule } from '@agm/core';

@Component({
    selector: 'sold',
    templateUrl: './sold.component.html',
    styles: [],
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

  export class SoldComponent {  
    public id: string;
    private product: Product;
    public buyers: Buyer[];
    public selectedUser;
    constructor(private route: ActivatedRoute, private service: ProductService) { 
      this.id = route.snapshot.params['id'];
       }
    

    ngOnInit() {  
      
      this.product = {id: null, name: null, description: null,  tags: null, image: null, price: null, bought: false, featured: false, user : null};
      this.service.getProduct(this.id).subscribe(
          product => this.product = product,
          error => console.error("Error in product.component.ts"),
      );

      this.service.sold(this.id).subscribe(
        buyers => this.buyers = buyers,
        error => console.error(error)
      );
      
    }
    sendSeller(){

      alert("Sucesful sale")
      this.service.soldConfirmation(this.id, this.selectedUser).subscribe(
        buyers => this.buyers = buyers,
        error => console.error(error)
      );      

    }

  }

