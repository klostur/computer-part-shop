import { Component, OnInit, Input, OnChanges, SimpleChange, SimpleChanges, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticationService } from 'app/security/authentication.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit,OnChanges {
  
  computerParts: any[];
  changelog: string[] = [];
  // @Output() restartComputerPart: EventEmitter<any> = new EventEmitter();
  @Input() computerPart: any = {
    brand: {},
    name: undefined,
    price: null
  };

  ngOnChanges(changes: SimpleChanges) {
    console.log('OnChanges');
    console.log(JSON.stringify(changes));

    // tslint:disable-next-line:forin
    for (const propName in changes) {
         const change = changes[propName];
         const to  = JSON.stringify(change.currentValue);
         const from = JSON.stringify(change.previousValue);
         const changeLog = `${propName}: changed from ${from} to ${to} `;
         this.changelog.push(changeLog);

         if (changes.computerPart.currentValue.price !==0){ //STAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ZASTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo
          this.save();
         }
        }
        
}

  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.loadData();
  }
  loadData() {
    this.http.get('api/shop').subscribe(data => {
      this.computerParts = data as any;
    })
  }
  save(){
    const headers = new HttpHeaders({ "Content-Type": "application/json" });
    this.http.post('api/shop', JSON.stringify(this.computerPart), {headers}).subscribe(data => {
      this.loadData();
    })
    // this.restart(); //posalji majci
  }
  remove(id:number) {
    this.http.delete(`api/shop/${id}`).subscribe(() => {
      this.loadData();
    })
  }
  // restart() {
  //   console.log('pre klika');
  //   console.log(this.computerPart)
  //   this.computerPart = {
  //     brand: {},
  //     name: undefined,
  //     price: 0
  //   };
  //   console.log('posle klika');
  //   console.log(this.computerPart)
  //   this.restartComputerPart.next(this.computerPart);
  // }
}
