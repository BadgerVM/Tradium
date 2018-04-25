import { Routes, RouterModule } from '@angular/router';

import { HeaderComponent } from './CommonComponents/header.component';
import { FooterComponent } from './CommonComponents/footer.component';
import { FeaturedComponent } from './Featured/featured.component';
import { AboutUsComponent } from './AboutUs/aboutUs.component';
import { SearchComponent } from './CommonComponents/search.component';
import { SellerComponent } from './User/seller.component';
import { ProductComponent } from './Product/product.component';
import { LoginComponent } from './Login/login.component';
import { RegisterComponent } from './Login/register.component';
import { UploadProComponent } from './Product/uploadPro.component';
import { ChatMenuComponent } from './Chat/chatMenu.component';
import { ChatComponent } from './Chat/chat.component';
import { SoldComponent } from './Valoration/sold.component';
import {ValorationComponent} from './Valoration/valoration.component';
const appRoutes = [
    { path: 'Featured', component: FeaturedComponent,  },
    { path: 'AboutUs', component: AboutUsComponent,  },
    { path: 'Search/:tag', component: SearchComponent,  },
    { path: 'Seller/:id', component: SellerComponent, },
    { path: 'Product/:id', component: ProductComponent, },
    { path: 'Login', component: LoginComponent, },
    { path: 'Register', component: RegisterComponent, },
    { path: 'UploadProduct', component: UploadProComponent, },
    { path: 'Chat', component: ChatMenuComponent, },
    { path: 'Chat/:id', component: ChatComponent, },
    { path: 'Product/:id/Sold', component: SoldComponent, },
    { path: 'User/:id/Valoration', component: ValorationComponent, },
    { path: '', redirectTo: 'Featured', pathMatch: 'full' }


]
export const routing = RouterModule.forRoot(appRoutes);