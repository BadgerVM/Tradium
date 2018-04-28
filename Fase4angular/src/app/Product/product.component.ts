import { Component, ViewChild, ElementRef } from '@angular/core';
import { Http } from '@angular/http';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { Product, Offer, ProductService } from './product.service';
import { TagContentType } from '@angular/compiler/src/ml_parser/tags';
import { LoginService } from '../Login/login.service';
import { environment } from '../../environments/environment';

@Component({
    selector: 'product',
    templateUrl: './product.component.html',
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

  export class ProductComponent {

    @ViewChild('offer') price: ElementRef;
    
    public id: string;
    private product: Product;
    private baseUrl:string;
   
    constructor(private route: ActivatedRoute, private service: ProductService, private loginService: LoginService) {
        this.id = route.snapshot.params['id'];
        this.baseUrl = environment.baseURL;
    }
     
     
    ngOnInit() {
        
        this.product = {id: null, name: null, description: null,  tags: null, image: null, price: null, bought: false, featured: false, user : null};
        this.service.getProduct(this.id).subscribe(
            product => this.product = product,
            error => console.error("Error in product.component.ts"),
        );
        
    }

    buy(){

      this.service.buyProduct(this.id).subscribe(
        product => alert("Succesful bought"),
        error => console.error('Error buying product: ' + error)
     );
    }

    counteroffer(){

      let price = this.price.nativeElement.value;

      this.service.counterProduct(this.id, price ).subscribe(
        product => alert("Succesful counteroffer"),
        error => console.error('Error making a counter offer ' + error)
     );
    }

    sold(){

      this.service.sold(this.id).subscribe(
        product => { },
        error => console.error('Error buying product: ' + error)
     );

    }
    delete(){

      this.service.delete(this.id).subscribe(
        product => alert("Succesful delete"),
        error => console.error('Error buying product: ' + error)
     );

      
    }
  }

