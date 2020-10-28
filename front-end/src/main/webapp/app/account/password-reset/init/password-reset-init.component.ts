import { Component, ElementRef, Renderer } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

import { EMAIL_NOT_FOUND_TYPE } from 'app/shared/constants/error.constants';
import { PasswordResetInitService } from './password-reset-init.service';
import { Router } from '@angular/router';
import { SnackbarUtil } from 'app/shared/util/snackbar-util';

@Component({
  selector: 'jhi-password-reset-init',
  templateUrl: './password-reset-init.component.html',
  styleUrls: ['./password-reset-init.component.scss'],
  providers: [SnackbarUtil]
})
export class PasswordResetInitComponent {
  success!: string | null;
  resetRequestForm = this.fb.group({
    email: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(254), Validators.email]]
  });

  constructor(
    private passwordResetInitService: PasswordResetInitService,
    private elementRef: ElementRef,
    private renderer: Renderer,
    private fb: FormBuilder,
    private router: Router,
    private snackBar: SnackbarUtil
  ) {}

  requestReset(): void {
    this.passwordResetInitService.save(this.resetRequestForm.get(['email'])!.value).subscribe(
      () => {
        this.success = 'OK';
        this.snackBar.snackSuccess('reset.request.messages.success');
      },
      response => {
        this.success = null;
        if (response.status === 400 && response.error.type === EMAIL_NOT_FOUND_TYPE) {
          this.snackBar.snackError('reset.request.messages.notfound');
        }
      }
    );
  }

  previousState(): void {
    this.router.navigate(['login']).then();
  }
}
