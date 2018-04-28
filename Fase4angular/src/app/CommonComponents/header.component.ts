import { Component, ViewChild,Renderer2, ElementRef } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { LoginService } from './../Login/login.service';
import { environment } from './../../environments/environment';

@Component({
  selector: 'header',
  templateUrl: './header.component.html',
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

export class HeaderComponent {

  @ViewChild('search') text: ElementRef;
  private baseUrl:string;

  private search: string;

  constructor(public router: Router, private loginService: LoginService) { 
    this.baseUrl = environment.baseURL;
  }

  newSearch(){

    this.search = this.text.nativeElement.value;
    this.router.navigate(['/Search',this.search]);
  }
  
}