import { Routes, RouterModule } from '@angular/router';

import { HeaderComponent } from './header.component';
import { FooterComponent } from './footer.component';
import { FeaturedComponent } from './featured.component';
import { AboutUsComponent } from './aboutUs.component';
import { SearchComponent } from './search.component';
import { SellerComponent } from './seller.component';
import { ProductComponent } from './product.component';
import { LoginComponent } from './login.component';
import { RegisterComponent } from './register.component';
import { UploadProComponent } from './uploadPro.component';
import { ChatMenuComponent } from './chatMenu.component';
import { ChatComponent } from './chat.component';
import { SoldComponent } from './sold.component';
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
    { path: '', redirectTo: 'Featured', pathMatch: 'full' }


]
export const routing = RouterModule.forRoot(appRoutes);