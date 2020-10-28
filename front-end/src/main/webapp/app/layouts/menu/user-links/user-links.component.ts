import { Component } from '@angular/core';
import { AccountService } from 'app/core/auth/account.service';
import { LoginService } from 'app/core/login/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'jhi-user-links',
  templateUrl: './user-links.component.html',
  styleUrls: ['./user-links.component.scss']
})
export class UserLinksComponent {
  constructor(private loginService: LoginService, private accountService: AccountService, private router: Router) {}

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  getImageUrl(): string {
    const avatar =
      this.isAuthenticated() && this.accountService.getImageUrl() && this.accountService.getImageUrl().length
        ? this.accountService.getImageUrl()
        : null;
    if (avatar === null) {
      return '../../content/images/jhipster_family_member_0_head-192.png';
    }
    return avatar;
  }

  getUsername(): string | null {
    return this.isAuthenticated() ? this.accountService.getUsername() : null;
  }

  logout(): void {
    this.loginService.logout();
    this.router.navigate(['login']);
  }
}
