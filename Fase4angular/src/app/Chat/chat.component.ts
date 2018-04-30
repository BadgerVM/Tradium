import { Component, ViewChild,Renderer2, ElementRef } from '@angular/core';
import { Http } from '@angular/http';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { Product } from './../Product/product.service';
import { User } from './../User/user.service'
import { Chat, ChatService, PostNewMessage } from './chat.service';
import { TagContentType } from '@angular/compiler/src/ml_parser/tags';
import { BrowserModule } from '@angular/platform-browser';
import { AgmCoreModule } from '@agm/core';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { environment } from './../../environments/environment';

@Component({
    selector: 'chat',
    templateUrl: './chat.component.html',
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

  export class ChatComponent {

    @ViewChild('text') text: ElementRef;


    public id: string;
    private user: User;
    private chat: Chat;
    private message: PostNewMessage;
    private baseUrl:string;


    constructor(private route: ActivatedRoute, private service: ChatService) {
      this.chat=null;
      this.id = route.snapshot.params['id'];
      this.message={text:null};
      this.baseUrl = environment.baseURL;
    }

    ngOnInit() {
      this.service.getChats(this.id).subscribe(
        chat => this.chat = chat,
        error => console.error("Error in chat.component.ts"),
      );

    }
    setNewMessage(){
      this.message.text = this.text.nativeElement.value;
      this.text.nativeElement.value="";
      this.service.setNewMessage(this.id, this.message).subscribe(
        chat=>this.chat=chat
    );
    }
}