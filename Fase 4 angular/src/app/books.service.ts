import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable} from 'rxjs/Rx';

/*
export class User{
  constructor(public id: number, public name: string, public email: string, public locationX: string, 
    public locationY: string, public medValoration: number, public image: string, public roles: string[]) { }

}

export class Product{
  constructor(public id:number, public name: string, public description: string, public tags: string, public image: string,
  public price: number, public  bought: boolean, public featured: boolean){}
}*/

export interface UserRegister{
  name: string, passwordHash: string, email: string, locationX: string, locationY: string, image: string;
}

export interface User{
  id: number, name: string, email: string, locationX: string, locationY: string, medValoration: number, image: string,  roles: string[];
}

export interface UploadProduct{
  name: string, description : string,  image : string,  price : number,minPrice : number, tags : string;
}

export interface Product{
  bought: boolean,  description : string, featured : boolean, id : number, image : string,  name : string,  price : number, tags : string, user : User;
}

export interface ProductFeatured{
  id: number, name: string, description: string, tags: string, image: string, price : number, bought : boolean, featured: boolean, user: User; 
}


export interface Valoration{
  id: number, name: string, email: string, locationX: string, locationY: string, medValoration: number, image: string,  roles: string[], valoration: number, description: string, date, Date;
}

export interface UploadValoration{
  valoration: number, description: string;
}

export interface Offer{
 offer : number;
}
export interface Buyer{
  id : number, user : User;
 }
 export interface UploadValoration{
  valoration: number, description: string;
}
 



@Injectable()
export class BooksService {

  constructor(private http: Http) { }

  delete(id: String) {

    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });

    const options = new RequestOptions({ withCredentials: true, headers });
   
      return this.http.delete('https://localhost:8443/api/product/'+id+"/delete", options)
        .map(response => response.json())
        .catch(error => this.handleError(error));    
 
  }

  sold(id: String){

         const headers = new Headers({
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      });
      const options = new RequestOptions({ withCredentials: true, headers });
     
        return this.http.get('https://localhost:8443/api/product/'+id+"/buyers", options)
          .map(response => response.json())
          .catch(error => this.handleError(error));    
   
    }

    soldConfirmation(id: String, user: User){

      const headers = new Headers({
     'Content-Type': 'application/json',
     'X-Requested-With': 'XMLHttpRequest'
   });
   const body = JSON.stringify(user);

   const options = new RequestOptions({ withCredentials: true, headers });
   
     return this.http.post('https://localhost:8443/api/product/'+id+"/sold", body, options)
       .map(response => response.json())
       .catch(error => this.handleError(error));    

 }
 
  counterProduct(id: string, offer: number) {
    let offert: Offer = {offer};
    const body = JSON.stringify(offert);
    console.log(body);
    ///const body = price;
    
     const headers = new Headers({
       'Content-Type': 'application/json',
       'X-Requested-With': 'XMLHttpRequest'
     });
     const options = new RequestOptions({ withCredentials: true, headers });
    
       return this.http.post('https://localhost:8443/api/product/'+id+"/offer", body, options)
         .map(response => response.json())
         .catch(error => this.handleError(error));  
   }

  buyProduct(id: string) {
  
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
   
      return this.http.get('https://localhost:8443/api/product/'+id+"/buy", options)
        .map(response => response.json())
        .catch(error => this.handleError(error));    
 
  }

  getBooks() {
    let url = "https://localhost:8443/api/featured";

    return this.http.get(url)
      .map(response => response.json())
      .catch(error => Observable.throw('Server error'));
  }

  getSearch(search: String){
    let url = "https://localhost:8443/api/search/"+search;
    return this.http.get(url)
    .map(response => this.extractNames(response))
    .catch(error => Observable.throw('Error in getSearch book.service'));
  }

  getUser(id: String){
    let url = "https://localhost:8443/api/seller/"+id;
    return this.http.get(url)
    .map(response => response.json())
    .catch(error => Observable.throw('Error in getUser book.service'));
  }

  getProduct(id: String):Observable<Product>{
    let url = "https://localhost:8443/api/product/"+id;
    return this.http.get(url)
    .map(response=>response.json())
    .catch(error => Observable.throw('Error in getProduct book.service'));
    
  }

  getProducts(id: String):Observable<Product>{
    let url = "https://localhost:8443/api/seller/products/"+id;
    return this.http.get(url)
    .map(response=>response.json())
    .catch(error => Observable.throw('Error in getProducts book.service'));
  }

  getValorations(id: String):Observable<Valoration>{
    let url = "https://localhost:8443/api/seller/"+id+"/valorations";
    return this.http.get(url)
    .map(response=>response.json())
    .catch(error => Observable.throw('Error in getValorations book.service'));
  }

  newProduct(pr: UploadProduct){

    const body = JSON.stringify(pr);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
   
      return this.http.post('https://localhost:8443/api/product/new', body, options)
        .map(response => response.json())
        .catch(error => this.handleError(error));    
 
  }

  newValoration(id, vlr: UploadValoration){

    const body = JSON.stringify(vlr);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
   
      return this.http.post('https://localhost:8443/api/user/'+id+'/new', body, options)
        .map(response => response.json())
        .catch(error => this.handleError(error));    
 
  }

  private extractNames(response:Response){
    //console.log(response.json().map(product => product).lenght);
    return response.json().map(product => product.name)
  }

  private extractUser(response:Response){
    return response.json().map(user => user)
  }
  private extractProduct(response:Response){
    return response.json().map(product =>product)
  }
  
  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }

}
