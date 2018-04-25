import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable} from 'rxjs/Rx';

export interface Valoration{
    id: number, name: string, email: string, locationX: string, locationY: string, medValoration: number, image: string,  roles: string[], valoration: number, description: string, date, Date;
  }
  
  export interface UploadValoration{
    valoration: number, description: string;
  }
  
  @Injectable()
export class ValorationService {

  constructor(private http: Http) { }

  newValoration(id, vlr: UploadValoration){

    const body = JSON.stringify(vlr);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
   
      return this.http.post('https://localhost:8443/api/seller/'+id+'/valoration', body, options)
        .map(response => response.json())
        .catch(error => this.handleError(error));     
  }
  
  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }

}
