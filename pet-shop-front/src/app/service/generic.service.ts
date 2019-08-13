import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GenericService {

  baseUrl: string;

  constructor(private http: HttpClient) {
    console.log('environment.baseApiUrl', environment.baseApiUrl);
    this.baseUrl = environment.baseApiUrl;
  }

  private async request(method: string, url: string, data?: any) {
    console.log('request ' + JSON.stringify(data));
    const result = this.http.request(method, url, {
      body: data,
      responseType: 'json',
      observe: 'body'
    });
    return new Promise<any>((resolve, reject) => {
      result.subscribe(resolve as any, reject as any);
    });
  }

  getAll(serviceName: string) {
    return this.request('get', `${this.baseUrl}/${serviceName}`);
  }

  get(serviceName: string, id: string) {
    return this.request('get', `${this.baseUrl}/${serviceName}/${id}`);
  }

  create(serviceName: string, data: any) {
    return this.request('post', `${this.baseUrl}/${serviceName}`, data);
  }

  createRelation(serviceName: string, id: string, data: any) {
    return this.request('post', `${this.baseUrl}/${serviceName}/${id}`, data);
  }

  update(serviceName: string, id: string, data: any) {
    return this.request('put', `${this.baseUrl}/${serviceName}/${id}`, data);
  }

  delete(serviceName: string, id: string) {
    return this.request('delete', `${this.baseUrl}/${serviceName}/${id}`);
  }
}
