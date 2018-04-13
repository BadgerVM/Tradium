import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Observable} from 'rxjs/Rx';

export interface ChatList{
    id: number, user2: User2;
  }
export interface User2{
    id: number;
}

export interface Chat{
    trasmitter: User2, text: string; 
}

export interface PostNewMessage{
    text:string;
}

/*

export interface Chat{
    id: number;
}
*/
@Injectable()
export class ChatService {

  constructor(private http: Http) { }

  getChatList() {
    let url = "/api/chats";

    return this.http.get(url)
      .map(response => this.extractChatList(response))
      .catch(error => Observable.throw('Error in chat.service.getChatList'));
  }

  getChats(id){
    let url = "/api/chats/"+id;

    return this.http.get(url)
      .map(response => this.extractChats(response))
      .catch(error => Observable.throw('Error in chat.service.getChats'));
  }
/*
  setNewMessage(id, text){
    let url = "/api/chats/"+id+"/new";
    return this.http.set(url)
      .map(response => this.extractChats(response))
      .catch(error => Observable.throw('Error in chat.service.getChats'));
  }*/

  setNewMessage(id,message:PostNewMessage)/*: Observable<PostNewMessage>*/ {
    let url = "/api/chats/"+id+"/new";
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });/*
    return this.http.post(this.url, message, options)
               .map(this.extractData)
               .catch(this.handleErrorObservable);*/
}	


  private extractChatList(response:Response){
    return response.json().map(chatList =>chatList)
  }

  private extractChats(response:Response){
    return response.json().map(chat =>chat)
  }
}