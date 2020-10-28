import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { CorpusManagerSharedModule } from 'app/shared/shared.module';
import { CorpusManagerCoreModule } from 'app/core/core.module';
import { CorpusManagerAppRoutingModule } from './app-routing.module';
import { CorpusManagerHomeModule } from './home/home.module';
import { CorpusManagerEntityModule } from './entities/entity.module';
import {JhiLoginModalComponent} from 'app/shared/login/login-modal.component'; 
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatMenuModule } from '@angular/material/menu';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatBottomSheetModule } from '@angular/material/bottom-sheet';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MenuComponent } from 'app/layouts/menu/menu.component';
import { UserLinksComponent } from 'app/layouts/menu/user-links/user-links.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CorpusSelectionComponent } from './layouts/corpus-selection/corpus-selection.component';
import { CorpusManagerOperationModule } from 'app/operations/operation.module';
import { AccountModule } from 'app/account/account.module';
import { MatNativeDateModule } from '@angular/material/core';
import { NgxEchartsModule } from 'ngx-echarts';
import { ActionLogDeleteDialogComponent } from 'app/home/action-log/action-log-delete-dialog.component';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CorpusManagerSharedModule,
    CorpusManagerCoreModule,
    CorpusManagerHomeModule,
    CorpusManagerEntityModule,
    CorpusManagerOperationModule,
    CorpusManagerAppRoutingModule,
    AccountModule,
    MatNativeDateModule,
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatSnackBarModule,
    MatMenuModule,
    MatExpansionModule,
    MatBottomSheetModule,
    MatCardModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    NgxEchartsModule
  ],
  declarations: [
    MainComponent,
    NavbarComponent,
    ErrorComponent,
    JhiLoginModalComponent,
    PageRibbonComponent,
    ActiveMenuDirective,
    FooterComponent,
    MenuComponent,
    UserLinksComponent,
    CorpusSelectionComponent,
    ActionLogDeleteDialogComponent
  ],
  bootstrap: [MainComponent],
  entryComponents: [ActionLogDeleteDialogComponent]
})
export class CorpusManagerAppModule {}
