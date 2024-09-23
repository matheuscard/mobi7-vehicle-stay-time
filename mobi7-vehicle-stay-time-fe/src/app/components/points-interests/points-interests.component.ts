import { CarStayTime } from './../../models/CarStayTime';
import { ChangeDetectionStrategy, Component, OnInit, AfterViewInit, ViewChild, ChangeDetectorRef, LOCALE_ID} from '@angular/core';
import { PoiService } from '../../services/poi.service';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { MAT_DATE_LOCALE, provideNativeDateAdapter } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import { Subject } from 'rxjs';
import { DatePipe, NgIf } from '@angular/common';
import { registerLocaleData } from '@angular/common';
import localePt from '@angular/common/locales/pt';
import {MatChipsModule} from '@angular/material/chips';
@Component({
  selector: 'app-points-interests',
  standalone: true,
  providers: [provideNativeDateAdapter(), { provide: MAT_DATE_LOCALE, useValue: 'pt-BR' }],
  imports: [MatChipsModule,NgIf,DatePipe,FormsModule,MatSlideToggleModule, MatTableModule,MatPaginatorModule, MatFormFieldModule, MatInputModule,MatDatepickerModule,MatIconModule, MatButtonModule, MatCardModule],
  changeDetection:ChangeDetectionStrategy.OnPush,
  templateUrl: './points-interests.component.html',
  styleUrl: './points-interests.component.css'
})
export class PointsInterestsComponent implements AfterViewInit, OnInit{

  displayedColumns: string[] = ['Placa', 'Permanência', 'Ponto de Interesse', 'Última Posição',  'Última Data','Velocidade', 'Ignição'];
  carStayTimeSource!: MatTableDataSource<CarStayTime> ;
  carStayTime: CarStayTime[]= [];

  termoBuscaChanged: Subject<string> = new Subject<string>();
  placa = ''
  date = new Date();
  term!:string;
  mapsUrl = 'http://maps.google.com/maps?q='
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private breakpointObserver: BreakpointObserver, private poiService: PoiService, private cdr: ChangeDetectorRef){
    registerLocaleData(localePt);
  }

  ngAfterViewChecked() {
    this.cdr.detectChanges();
  }

  ngOnInit(): void {
    this.breakpointObserver.observe([
      Breakpoints.Handset
    ]).subscribe(result => {
      if (result.matches) {
        // Apply specific styles or logic for mobile devices
      }
    });
  }
  ngAfterViewInit() {
    this.poiService.getCarStayTime().subscribe((carStayTime)=>{
      this.carStayTime = carStayTime;
      this.carStayTimeSource = new MatTableDataSource<CarStayTime>(carStayTime);
      this.carStayTimeSource.paginator = this.paginator;
    })
  }

    filterPlates(evt:any):void {
      this.poiService .getCarStayTimeByPlateOrDate(
          this.placa,this.date.toLocaleDateString("en-US", {
            year: "numeric",
            month: "2-digit",
            day: "2-digit",
          })
        ).subscribe((carStayTime) =>{
                        this.carStayTime = carStayTime;
                        this.carStayTimeSource.paginator = this.paginator;
                      });
    }
}
