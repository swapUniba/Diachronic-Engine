import { Route } from '@angular/router';
import { LoginComponent } from 'app/shared/login/login.component';
import { passwordResetInitRoute } from 'app/account/password-reset/init/password-reset-init.route';
import { LoginFormComponent } from 'app/shared/login/login-form/login-form.component';

export const loginRoute: Route = {
  path: 'login',
  component: LoginComponent,
  data: {
    pageTitle: 'login.title'
  },
  children: [
    {
      path: '',
      component: LoginFormComponent,
      data: {
        animation: 'login'
      }
    },
    passwordResetInitRoute
  ]
};
