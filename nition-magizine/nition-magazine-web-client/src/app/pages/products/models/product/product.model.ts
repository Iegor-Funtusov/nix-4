import { AbstractModel } from '../abstract-model';
import { ProductImageModel } from './product-image.model';

export interface ProductModel extends AbstractModel {
  name: string;
  description: string;
  price: number;
  images: ProductImageModel[];
}
