import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product.model';
import { ProductService } from 'src/app/services/product/product.service';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {

  private product: Product = {
    id: '',
    name: '',
    price: 0,
    total: 0,
    picture: null
  }

  private currentFile: File;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
  }

  public onFileChanged(event) {
    this.currentFile = event.target.files[0];
  }

  addProduct() {
    console.log(this.currentFile.name);
    this.productService.createProduct(this.product.name, this.product.price.toString(), this.product.total.toString()
    ,this.currentFile)
      .subscribe(data => { alert(data.message); })
  }
  

}
