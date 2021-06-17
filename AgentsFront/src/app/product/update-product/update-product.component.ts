import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/services/product/product.service';
import { Product } from 'src/app/model/product.model';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  private product: Product = {
    id: '',
    name: '',
    price: 0,
    total: 0,
    picture: null
  }

  private currentFile: File;

  private productId: string;

  constructor(private router: ActivatedRoute, private productService: ProductService) { }

  ngOnInit() {
    this.productId = this.router.snapshot.params.id;
    console.log(this.productId);
  }


  public onFileChanged(event) {
    this.currentFile = event.target.files[0];
  }

  updateProduct() {
    console.log(this.currentFile.name);
    this.productService.update(this.productId, this.product.name, this.product.price.toString(), this.product.total.toString()
      , this.currentFile)
      .subscribe(data => { alert(data.message); })
  }

}
