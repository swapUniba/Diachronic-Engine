import { ChangeDetectorRef, OnDestroy } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';

export class MobileMediaMatcher implements OnDestroy {
  mobileQuery: MediaQueryList;

  private readonly mobileQueryListener: () => void;

  constructor(protected changeDetectorRef: ChangeDetectorRef, protected media: MediaMatcher) {
    this.mobileQuery = media.matchMedia('(max-width: 991px)');
    this.mobileQueryListener = () => changeDetectorRef.detectChanges();
    try {
      this.mobileQuery.addEventListener('change', () => this.mobileQueryListener);
    } catch (e) {
      this.mobileQuery.addListener(() => this.mobileQueryListener);
    }
  }

  ngOnDestroy(): void {
    try {
      this.mobileQuery.removeEventListener('change', () => this.mobileQueryListener);
    } catch (e) {
      this.mobileQuery.removeListener(() => this.mobileQueryListener);
    }
  }

  isMobile(): boolean {
    return this.mobileQuery.matches;
  }
}
