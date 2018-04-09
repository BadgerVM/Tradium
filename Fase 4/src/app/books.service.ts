import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable} from 'rxjs/Rx';
/*
export class User{
  constructor(public id: number, public name: string, public email: string, public locationX: string, 
    public locationY: string, public medValoration: number, public image: string, public roles: string[]) { }

}

export class Product{
  constructor(public id:number, public name: string, public description: string, public tags: string, public image: string,
  public price: number, public bought: boolean, public featured: boolean){}
}*/

export interface User{
  id: number, name: string, email: string, locationX: string, locationY: string, medValoration: number, image: string,  roles: string[];
}


export interface Product{
  id:number; name: string; description: string;  tags: string; image: string; price: number; bought: boolean; featured: boolean;
}



@Injectable()
export class BooksService {

  constructor(private http: Http) { }

  getBooks() {
    let url = "/api/featured";

    return this.http.get(url)
      .map(response => this.extractNames(response))
      .catch(error => Observable.throw('Server error'));
  }

  getSearch(search: String){
    let url = "/api/search/"+search;
    return this.http.get(url)
    .map(response => this.extractNames(response))
    .catch(error => Observable.throw('Error in getSearch book.service'));
  }

  getUser(id: String){
    let url = "/api/seller/"+id;
    return this.http.get(url)
    .map(response => response.json())
    .catch(error => Observable.throw('Error in getUser book.service'));
  }

  getProduct(id: String):Observable<Product>{
    let url = "/api/product/"+id;
    return this.http.get(url)
    .map(response=>response.json())
    .catch(error => Observable.throw('Error in getProduct book.service'));
    

    /*
    .map(({ id,  name,  description, tags, image, price, bought, featured})=> 
    new Product(id,  name,  description, tags, image, price, bought, featured)));*/

  }

  /*
  
  public search(searchString: string): Observable<ClientSearchResult[]> {
    const params = new HttpParams().set('searchString', searchString);
    return this.http.get(this.searchUrl, { params: params })
        .map((results: Response) => results.json() as ClientSearchResult[]);
}*/




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


}
