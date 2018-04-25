import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpModule, JsonpModule } from '@angular/http';
import { routing } from './app.routing';
import { AgmCoreModule } from '@agm/core';

import { AppComponent } from './app.component';
import { UserService } from './User/user.service';
import { ChatService } from './Chat/chat.service';
import { ProductService } from './Product/product.service';
import { ValorationService } from './Valoration/valoration.service';

import { HeaderComponent } from './CommonComponents/header.component';
import { FooterComponent } from './CommonComponents/footer.component';
import { FeaturedComponent } from './Featured/featured.component';
import { AboutUsComponent } from './AboutUs/aboutUs.component';
import { SearchComponent } from './CommonComponents/search.component';
import { SellerComponent } from './User/seller.component';
import { ProductComponent } from './Product/product.component';
import { LoginComponent } from './Login/login.component';
import { LoginService } from './Login/login.service';
import { RegisterComponent } from './Login/register.component';
import { HttpClientModule } from '@angular/common/http';
import { UploadProComponent } from './Product/uploadPro.component';
import { ChatComponent } from './Chat/chat.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { ChatMenuComponent } from './Chat/chatMenu.component';
import { SoldComponent } from './Valoration/sold.component';
import { ValorationComponent } from './Valoration/valoration.component';



@NgModule({
  declarations: [AppComponent, FooterComponent, HeaderComponent,UploadProComponent, FeaturedComponent, AboutUsComponent, SearchComponent, SellerComponent, 
    ProductComponent, LoginComponent, RegisterComponent, ChatComponent, ChatMenuComponent, ValorationComponent, SoldComponent],
  imports: [BrowserModule, FormsModule, HttpClientModule, NgbModule.forRoot(), HttpModule, 
    JsonpModule, routing,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDj_yF9UUmmmmpW1rc7cM_w6oNb_pidrto'
    })],
  bootstrap: [AppComponent],
  providers: [ProductService, UserService, ValorationService, LoginService, ChatService]
})
export class AppModule { }
