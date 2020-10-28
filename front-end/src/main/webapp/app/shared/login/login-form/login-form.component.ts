import { Component, ElementRef } from '@angular/core';
import { JhiEventManager } from 'ng-jhipster';
import { LoginService } from 'app/core/login/login.service';
import { StateStorageService } from 'app/core/auth/state-storage.service';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { SnackbarUtil } from 'app/shared/util/snackbar-util';

@Component({
  selector: 'jhi-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss'],
  providers: [SnackbarUtil]
})
export class LoginFormComponent {
  authenticationError = false;

  loginForm = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]],
    rememberMe: [false]
  });

  constructor(
    private eventManager: JhiEventManager,
    private loginService: LoginService,
    private stateStorageService: StateStorageService,
    private elementRef: ElementRef,
    private router: Router,
    private fb: FormBuilder,
    private snackBar: SnackbarUtil
  ) {}

  cancel(): void {
    this.authenticationError = false;
    this.loginForm.patchValue({
      username: '',
      password: ''
    });
  }

  login(): void {
    this.loginService
      .login({
        username: this.loginForm.get('username')!.value,
        password: this.loginForm.get('password')!.value,
        rememberMe: this.loginForm.get('rememberMe')!.value
      })
      .subscribe(
        () => {
          this.authenticationError = false;
          this.eventManager.broadcast({
            name: 'authenticationSuccess',
            content: 'Sending Authentication Success'
          });
          this.router.navigate(['/']).then();
        },
        () => this.snackBar.snackError('login.messages.error.authentication')
      );
  }

  register(): void {
    this.router.navigate(['/account/register']).then();
  }

  requestResetPassword(): void {
    this.router.navigate(['/login/reset', 'request']).then();
  }
}
