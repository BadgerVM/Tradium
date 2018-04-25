import { Component, OnInit, ViewChild,Renderer2, ElementRef } from '@angular/core';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { ProductService, Product, UploadProduct } from './product.service';
import { User, UserRegister } from '../User/user.service';
import { TagContentType } from '@angular/compiler/src/ml_parser/tags';
import { BrowserModule } from '@angular/platform-browser';
import { NgForm } from '@angular/forms';
import { RequestOptions ,Http, Headers} from '@angular/http';


@Component({
    selector: 'uploadPro',
    templateUrl: './uploadPro.component.html',
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

  export class UploadProComponent{ 
   

    @ViewChild('name') name: ElementRef;
    @ViewChild('description') description: ElementRef;
    @ViewChild('tags') tags: ElementRef;
    @ViewChild('price') price: ElementRef;
    @ViewChild('minPrice') minPrice: ElementRef;
    @ViewChild('pic') image: ElementRef;
    
    constructor(private http: Http, private service: ProductService) { }

    product : UploadProduct = {name : "", description : "", price : null, minPrice : null, tags:"", image : ""};
    
    loadImageData(file){
      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = ()=>{
        this.product.image = reader.result;
      }
    }

    uploadProduct(){
       
        this.product.name = this.name.nativeElement.value;
        this.product.description = this.description.nativeElement.value;
        this.product.tags = this.tags.nativeElement.value;
        this.product.price = this.price.nativeElement.value;
        this.product.minPrice = this.minPrice.nativeElement.value;

        
       
       this.service.newProduct(this.product).subscribe(
          product => alert("Successful upload"),
          error => console.error('Error uploading product: ' + error)
       );
       
        
    }
      
}