import { Component, OnInit } from '@angular/core';
import { ShoppingOrder } from 'src/app/model/shopping-order.model';
import { ShoppingService } from 'src/app/services/shopping/shopping.service';
import { Product } from 'src/app/model/product.model';
import { ProductService } from 'src/app/services/product/product.service';

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {

  private shoppingOrder: ShoppingOrder = {
    customerName: '',
    customerLastName: '',
    address: '',
    productIds: []
  }

  private selectedProducts: Product[] = [];

  private products: Product[] = [];

  constructor(private shoppingService: ShoppingService, private productService: ProductService) { }

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe(
      data => {
        this.products = data
        this.products = this.products.filter(prod => prod.total > 0)
      }
    )

  }

  createOrder(){
    for(let i=0;i<this.selectedProducts.length ;i++){
      this.shoppingOrder.productIds.push(parseInt(this.selectedProducts[i].id))
    }
    this.shoppingService.createShoppingOrder(this.shoppingOrder).subscribe(
      data => {
        alert(data.message);
      }
    )
  }

}
