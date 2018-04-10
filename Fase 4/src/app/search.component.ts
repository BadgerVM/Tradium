import { Component } from '@angular/core';
import { Http } from '@angular/http';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { BooksService } from './books.service';
import { TagContentType } from '@angular/compiler/src/ml_parser/tags';

@Component({
    selector: 'search',
    templateUrl: './search.component.html',
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

  
  export class SearchComponent {

    
    private products: string[] = [];
    private tag: string;
    constructor(private http: Http, private service: BooksService,private activatedRoute: ActivatedRoute) { 
        // subscribe to router event
        this.tag = activatedRoute.snapshot.params['tag'];
        console.log(this.tag);
        this.service.getSearch(this.tag).subscribe(
          products => this.products = products,
          error => console.error(error),
         
        );
    }

    ngOnInit(){
      this.activatedRoute.params
      .map(params => params['tag'])
      .switchMap(tag =>this.service.getSearch(tag))
      .subscribe(products =>this.products =products);
    }

  
  }
  