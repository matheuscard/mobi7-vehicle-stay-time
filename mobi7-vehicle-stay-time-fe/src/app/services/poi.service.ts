import { CarStayTime } from './../models/CarStayTime';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, take } from 'rxjs';
import { environment } from '../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class PoiService {

  private apiUrl =  environment.apiUrl;
  constructor(private http: HttpClient) { }

  getCarStayTime() : Observable<CarStayTime[]> {
    let params = {};
    params = {
      'plate': ''
      };
    let result: CarStayTime[] = [];
        return this.http.get<CarStayTime[]>(this.apiUrl, {observe: 'response', params})
    .pipe(
        take(1)
        , map((response) => {
            if(response.body !=null){
              result= response.body;
            }
           return result;
        }));
  }
  getCarStayTimeByPlateOrDate(placa?:string,date?:string) : Observable<CarStayTime[]> {
    let params = {};
    let result: CarStayTime[] = [];
    if(placa !='' && date != ''){
        params = {
          'plate': placa,
          'date': date
          };
    }
    if(placa !='' && date == ''){
      params = {
        'plate': placa
        };
  }
    if(placa =='' && date != ''){
      params = {
        'date': date
        };
  }

    return this.http.get<CarStayTime[]>(this.apiUrl, {observe: 'response', params})
    .pipe(
        take(1)
        , map((response) => {
            if(response.body !=null){
              result= response.body;
            }
           return result;
        }));
  }
}
