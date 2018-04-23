import { Component, OnInit, ViewChild,Renderer2, ElementRef } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { User, BooksService, Product, UserRegister } from './books.service';
import { TagContentType } from '@angular/compiler/src/ml_parser/tags';
import { BrowserModule } from '@angular/platform-browser';
import { NgForm } from '@angular/forms';

@Component({
    selector: 'register',
    templateUrl: './register.component.html',
    styles: [],
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

  export class RegisterComponent implements OnInit { 
   
    user1 = {name: null, email: null,  passwordHash: null, locationX: "", locationY: "", image: null};
    @ViewChild('username') username: ElementRef;
    @ViewChild('password') password: ElementRef;
    @ViewChild('email') email: ElementRef;

    constructor(private renderer: Renderer2, private http:HttpClient) { 
    }
    
    public lat : string = "";
    public lon : string = "";
   
    loadImageData(file){
      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = ()=>{
        this.user1.image = reader.result;
      }
    }

    registerUser() {
        this.user1.name = this.username.nativeElement.value;
        this.user1.passwordHash = this.password.nativeElement.value;
        this.user1.email = this.email.nativeElement.value;
        this.user1.locationX = this.lat;
        this.user1.locationY = this.lon;
      console.log(this.user1);
        let headers = new HttpHeaders().set('Content-Type','application/json');
        console.log( JSON.stringify(this.user1));
        this.http.post('https://localhost:8443/api/user/new', JSON.stringify(this.user1), {headers: headers})
        .subscribe(
            res => {
              console.log(res);
            },
            err => {
              console.log("Error occured");
            }
        );
      }
    location = {};

    setPosition(position){
      var crd = position.coords;
      this.lat= crd.latitude;
      this.lon = crd.longitude;
    }

    ngOnInit() {
        if(navigator.geolocation){
            navigator.geolocation.getCurrentPosition(p => this.setPosition(p));       
        };
    }
      
}