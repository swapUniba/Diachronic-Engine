import { Component } from '@angular/core';
import { slideLoginAnimation } from 'app/shared/login/slideLoginAnimation';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'jhi-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  animations: [slideLoginAnimation]
})
export class LoginComponent {
  constructor() {}

  public getRouterOutletState(outlet: RouterOutlet): boolean {
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation'];
  }
}
