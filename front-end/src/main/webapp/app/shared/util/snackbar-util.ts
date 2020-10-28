import { MatSnackBar } from '@angular/material/snack-bar';
import { Inject } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Inject(MatSnackBar)
export class SnackbarUtil {
  constructor(private translate: TranslateService, private snackBar: MatSnackBar) {}

  public snackError(message: string): void {
    this.snackBar.open(this.translate.instant(message), '', {
      duration: 3000,
      panelClass: ['error-snackbar', 'corpus-snackbar'],
      verticalPosition: 'top'
    });
  }

  public snackSuccess(message: string): void {
    this.snackBar.open(this.translate.instant(message), '', {
      duration: 3000,
      panelClass: ['success-snackbar', 'corpus-snackbar'],
      verticalPosition: 'top'
    });
  }
}
