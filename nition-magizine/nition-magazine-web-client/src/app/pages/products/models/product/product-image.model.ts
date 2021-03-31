import { AbstractModel } from '../abstract-model';

export interface ProductImageModel extends AbstractModel {
  link: string;
  home: boolean;
}
