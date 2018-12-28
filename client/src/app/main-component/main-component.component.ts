import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { Response, RequestOptions, Headers } from "@angular/http";

import * as _ from "lodash";
import { Observable } from "rxjs";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { AuthenticationService } from "app/security/authentication.service";
import { httpFactory } from "@angular/http/src/http_module";
import { resetComponentState } from "@angular/core/src/render3/state";

@Component({
  selector: "app-main-component",
  templateUrl: "./main-component.component.html",
  styleUrls: ["./main-component.component.css"]
})
export class MainComponentComponent implements OnInit {
  computerParts: any[];
  shopCart: any[];
  brands:any[];
  indexPage: number = 0;
  totalList:number = 0;
  size:number = 50;

  computerPart: any = {
    brand: {name:''},
    name: "",
    price: 0
  };

  ngOnInit(): void {
    this.loadData();
    this.getNumberOfPages();
  }

  constructor(
    private http: HttpClient,
    private authenticationService: AuthenticationService
  ) {}
 

  getNumberOfPages(){
    this.http.get(`api/computer-parts?size=${this.size}`, {observe: 'response'}).subscribe(data  => {
      this.totalList = parseInt(data.headers.get('X-Total-Pages'));
    });
  }
  
  loadData() {
    const params = new HttpParams()
      .set('page', this.indexPage.toString())
      .set('size', this.size.toString());
    this.http.get(`api/computer-parts`,{params}).subscribe(data  => {
      this.computerParts = data as any[];
      this.reset();
    });
    
    this.http.get("api/brands").subscribe(data => {
      this.brands = data as any[];
    })
  }
  
  hasRole(role: string): boolean {
    return this.authenticationService.hasRole(role);
  }

  save() {
    const headers = new HttpHeaders({ "Content-Type": "application/json" });
    if (this.computerPart.id === undefined) {
      this.http
        .post("api/computer-parts", JSON.stringify(this.computerPart), {
          headers
        })
        .subscribe((data: any) => {
          this.loadData();
        });
    } else {
      this.http
        .put(
          `api/computer-parts/${this.computerPart.id}`,
          JSON.stringify(this.computerPart),
          { headers }
        )
        .subscribe((data: any) => {
          this.loadData();
        });
    }
  }
  remove(i:number){
    this.http.delete(`api/computer-parts/${i}`).subscribe(() => {
      this.loadData();
    });
    
  }

  edit(c: any) {
    this.computerPart = c;
  }
  buy(c: any){
    this.computerPart = c;
    setTimeout(() => {this.reset();}, 10);
    
  }

  reset() {
    this.computerPart = {
      brand: {},
      name: "",
      price: 0
  
    };
  }
  up() {
    if(this.indexPage < this.totalList){
      this.loadData();
      this.indexPage++;
    }
  }
  down(){
    this.indexPage--;
    this.loadData();
  }
  
}
