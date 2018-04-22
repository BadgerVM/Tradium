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
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
   


    return this.http.get('https://localhost:8443/api/chats/', options)
      .map(response => this.extractChatList(response))
      .catch(error => Observable.throw('Error in chat.service.getChatList'));
  }

  getChats(id){

    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    return this.http.get('https://localhost:8443/api/chats/'+id, options)
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

  setNewMessage(id,text:PostNewMessage){
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    const body = JSON.stringify(text);
    return this.http.post('https://localhost:8443/api/chats/'+id+'/new', body, options)
      .map(response => response.json())
      .catch(error =>  Observable.throw('Error in chat.service.setNewMessage'));    
}	


  private extractChatList(response:Response){
    return response.json().map(chatList =>chatList)
  }

  private extractChats(response:Response){
    return response.json().map(chat =>chat)
  }
}