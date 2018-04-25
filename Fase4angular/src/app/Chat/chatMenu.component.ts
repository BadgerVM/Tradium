import { Component } from '@angular/core';
import { Http } from '@angular/http';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { Product } from './../Product/product.service';
import { User } from './../User/user.service';
import { ChatList, ChatService } from './chat.service';
import { TagContentType } from '@angular/compiler/src/ml_parser/tags';
import { BrowserModule } from '@angular/platform-browser';
import { AgmCoreModule } from '@agm/core';

@Component({
    selector: 'chatMenu',
    templateUrl: './chatMenu.component.html',
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

  export class ChatMenuComponent {
    public id: string;
    private user: User;
    private chatList: ChatList;

    constructor(private route: ActivatedRoute, private service: ChatService) {
      this.chatList={id:null, user2:null};
    }

    ngOnInit() {
      this.service.getChatList().subscribe(
        chatList => this.chatList = chatList,
        error => console.error("Error in chatMenu.component.ts"),
      );
  
  }
}


