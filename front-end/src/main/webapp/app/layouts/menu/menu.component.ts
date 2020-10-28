import { ChangeDetectorRef, Component, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';
import { JhiLanguageService } from 'ng-jhipster';
import { SessionStorageService } from 'ngx-webstorage';
import { AccountService } from 'app/core/auth/account.service';
import { ProfileService } from 'app/layouts/profiles/profile.service';
import { VERSION } from 'app/app.constants';
import { MobileMediaMatcher } from 'app/shared/util/mobile-media-matcher';
import { MatExpansionPanel } from '@angular/material/expansion';
import { MatSidenav } from '@angular/material/sidenav';
import { LANGUAGES } from 'app/core/language/language.constants';

@Component({
  selector: 'jhi-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent extends MobileMediaMatcher implements OnInit {
  inProduction: boolean | undefined;
  languages = LANGUAGES;
  swaggerEnabled: boolean | undefined;
  version: string;

  @ViewChild('sidenav', { static: false }) sidenav: MatSidenav;
  @ViewChildren(MatExpansionPanel) viewPanels: QueryList<MatExpansionPanel>;

  constructor(
    protected changeDetectorRef: ChangeDetectorRef,
    protected media: MediaMatcher,
    private languageService: JhiLanguageService,
    private sessionStorage: SessionStorageService,
    private accountService: AccountService,
    private profileService: ProfileService
  ) {
    super(changeDetectorRef, media);
    this.version = VERSION ? (VERSION.toLowerCase().startsWith('v') ? VERSION : 'v' + VERSION) : '';
  }

  ngOnInit(): void {
    this.profileService.getProfileInfo().subscribe(profileInfo => {
      this.inProduction = profileInfo.inProduction;
      this.swaggerEnabled = profileInfo.swaggerEnabled;
    });
  }

  changeLanguage(languageKey: string): void {
    this.sessionStorage.store('locale', languageKey);
    this.languageService.changeLanguage(languageKey);
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  sidenavAction(): void {
    this.isMobile() ? this.sidenav.toggle() : false;
  }

  closeAllAccordions(): void {
    this.viewPanels.forEach(p => p.close());
  }
}
