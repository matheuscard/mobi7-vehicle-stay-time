import { CarStayTime } from './../models/CarStayTime';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, take } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PoiService {

  private apiUrl = 'http://localhost:8080/api/vehicle/stay-time';
  constructor(private http: HttpClient) { }

  getCarStayTime() : Observable<CarStayTime[]> {
    return this.http.get<CarStayTime[]>(this.apiUrl);
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
