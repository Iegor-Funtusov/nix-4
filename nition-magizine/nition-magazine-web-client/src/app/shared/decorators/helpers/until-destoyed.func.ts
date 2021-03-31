import { takeUntil } from 'rxjs/operators';
import { OperatorFunction } from 'rxjs';

export function untilDestroyed<T>(component: any): OperatorFunction<T, T> {
  return takeUntil(component._destroyed$) as unknown as OperatorFunction<T, T>;
}
