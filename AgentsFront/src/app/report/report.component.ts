import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../model/product.model';
import { ReportService } from '../services/report/report.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  private option: string;

  products: Product[] = [];

  constructor(private router: ActivatedRoute, private reportService: ReportService) { }

  ngOnInit(): void {
    this.option = this.router.snapshot.params.option;
    if(this.option === 'earned'){
      this.reportService.mostEarned().subscribe(
        data => {
          this.products = data
        }
      )

    }else{
      this.reportService.mostSold().subscribe(
        data => {
          this.products = data
        }
      )

    }
  }

}
