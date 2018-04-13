import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpModule, JsonpModule } from '@angular/http';
import { routing } from './app.routing';
import { AgmCoreModule } from '@agm/core';

import { AppComponent } from './app.component';
import { BooksService } from './books.service';
import { ChatService } from './chat.service';

import { HeaderComponent } from './header.component';
import { FooterComponent } from './footer.component';
import { FeaturedComponent } from './featured.component';
import { AboutUsComponent } from './aboutUs.component';
import { SearchComponent } from './search.component';
import { SellerComponent } from './seller.component';
import { ProductComponent } from './product.component';
import { LoginComponent } from './login.component';
import { LoginService } from './login.service';
import { RegisterComponent } from './register.component';
import { HttpClientModule } from '@angular/common/http';
import { ChatComponent } from './chat.component';
import { ChatMenuComponent } from './chatMenu.component';



@NgModule({
  declarations: [AppComponent, FooterComponent, HeaderComponent,FeaturedComponent, AboutUsComponent, SearchComponent, SellerComponent, 
    ProductComponent, LoginComponent, RegisterComponent, ChatComponent, ChatMenuComponent],
  imports: [BrowserModule, FormsModule, HttpClientModule, HttpModule, 
    JsonpModule, routing,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDj_yF9UUmmmmpW1rc7cM_w6oNb_pidrto'
    })],
  bootstrap: [AppComponent],
  providers: [BooksService, LoginService, ChatService]
})
export class AppModule { }
