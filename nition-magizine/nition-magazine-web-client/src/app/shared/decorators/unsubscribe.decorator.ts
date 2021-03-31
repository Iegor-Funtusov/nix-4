import { Subject } from 'rxjs';

export function Unsubscribe(): (any) => void {
  return (componentClass: any) => {
    const destroyHook = componentClass.prototype.ngOnDestroy;
    componentClass.prototype._destroyed$ = new Subject<void>();
    componentClass.prototype.ngOnDestroy = function() {
      componentClass.prototype._destroyed$.next();
      if (destroyHook) {
        destroyHook.apply(this);
      }
    };
  };
}
