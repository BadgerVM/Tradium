import { Component, OnInit, ViewChild,Renderer2, ElementRef } from '@angular/core';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { ProductService, Product, UploadProduct } from '../Product/product.service';
import { User, UserRegister } from '../User/user.service';
import { UploadValoration, ValorationService } from './valoration.service';
import { TagContentType } from '@angular/compiler/src/ml_parser/tags';
import { BrowserModule } from '@angular/platform-browser';
import { NgForm } from '@angular/forms';
import { RequestOptions ,Http, Headers} from '@angular/http';


@Component({
    selector: 'valoration',
    templateUrl: './valoration.component.html',
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

  export class ValorationComponent{ 
    @ViewChild('description') description: ElementRef;
    @ViewChild('valoration') valoration: ElementRef;

    val : UploadValoration={valoration: null, description: null};
    id: string;

    constructor(private route: ActivatedRoute, private service: ValorationService) {
        this.id = route.snapshot.params['id'];
     }
   
    uploadValoration()
    {
        alert("Valoration completed!");
       
        this.val.description = this.description.nativeElement.value;
        this.val.valoration = this.valoration.nativeElement.value;
        
        this.service.newValoration(this.id,this.val).subscribe(
            val => { },
            error => console.error('Error uploading product: ' + error)
         );

        //console.log(this.val.valoration, this.val.description);
    }

  }