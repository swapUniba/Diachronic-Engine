import { NgModule } from '@angular/core';
import { CorpusManagerSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { LoginComponent } from 'app/shared/login/login.component';
import { LoginFormComponent } from 'app/shared/login/login-form/login-form.component';
import { PasswordResetInitComponent } from 'app/account/password-reset/init/password-reset-init.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatCardModule } from '@angular/material/card';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { RolePipeCssClass, RolePipeTranslation } from 'app/shared/util/role-pipe';

@NgModule({
  imports: [CorpusManagerSharedLibsModule, MatCheckboxModule, MatCardModule, RouterModule, MatIconModule],
  declarations: [
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    HasAnyAuthorityDirective,
    LoginComponent,
    LoginFormComponent,
    PasswordResetInitComponent,
    RolePipeTranslation,
    RolePipeCssClass
  ],
  exports: [
    CorpusManagerSharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    HasAnyAuthorityDirective,
    LoginComponent,
    RolePipeTranslation,
    RolePipeCssClass
  ]
})
export class CorpusManagerSharedModule {}
