import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MarketplaceComponent } from './marketplace/marketplace.component';
import { HouseComponent } from './house/house.component';
import { HouseDetailsComponent } from './house-details/house-details.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    MarketplaceComponent,
    HouseComponent,
    HouseDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
