import { Component } from '@angular/core';
import { Http } from '@angular/http';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { Product, BooksService } from './books.service';
import { TagContentType } from '@angular/compiler/src/ml_parser/tags';

@Component({
    selector: 'product',
    templateUrl: './product.component.html',
    styleUrls:[
      'CSS/fonts/font-awesome-4.7.0/css/font-awesome.min.css',
      'CSS/fonts/themify/themify-icons.css',
      'CSS/fonts/Linearicons-Free-v1.0.0/icon-font.min.css',
      'CSS/fonts/elegant-font/html-css/style.css',
      'CSS/vendor/animate/animate.css',
      'CSS/vendor/bootstrap/css/bootstrap.min.css',
      'CSS/vendor/css-hamburgers/hamburgers.min.css',
      'CSS/vendor/animsition/css/animsition.min.css',
      'CSS/vendor/select2/select2.min.css',
      'CSS/vendor/slick/slick.css',
      'CSS/css/util.css',
      'CSS/css/main.css'
    ]
  })

  export class ProductComponent {
    public id: string;
    private product: Product;
   
    constructor(private route: ActivatedRoute, private service: BooksService) {
        this.id = route.snapshot.params['id'];

    }
     
     
    ngOnInit() {
        
        this.product = {id: null, name: null, description: null,  tags: null, image: null, price: null, bought: false, featured: false};
        this.service.getProduct(this.id).subscribe(
            product => this.product = product,
            error => console.error("Error in product.component.ts"),
          );
    }
  }


