import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product.model';
import { ProductService } from 'src/app/services/product/product.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-all-products',
  templateUrl: './all-products.component.html',
  styleUrls: ['./all-products.component.css']
})
export class AllProductsComponent implements OnInit {

  products: Product[] = [];
  modalRef: any;
  imageUrl: string;

  constructor(private modalService: NgbModal, private productService: ProductService, private router: Router) { }

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe(
      data => {
        this.products = data
      }
    )
  }

  update(product) {
    console.log(product.id);
    this.router.navigate(['/dashboard/update-product/'+product.id]);
  }

  delete(product) {
    this.productService.delete(product.id).subscribe(data => {
      alert(data.message);
      window.location.reload();
    });

  }

}
