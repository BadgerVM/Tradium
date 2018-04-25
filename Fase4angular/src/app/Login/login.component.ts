import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { environment } from './../../environments/environment';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
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
export class LoginComponent {
  private baseUrl:string;
  constructor(private loginService: LoginService) { this.baseUrl = environment.baseURL;}

  logIn(event: any, user: string, pass: string) {

    event.preventDefault();

    this.loginService.logIn(user, pass).subscribe(
      u => console.log(u),
      error => alert('Invalid user or password')
    );
    
  }

  logOut() {
    this.loginService.logOut();
  }

}