import { Component } from '@angular/core';
import { Http } from '@angular/http';
import { BooksService } from './books.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls:[
    'CSS/fonts/font-awesome-4.7.0/css/font-awesome.min.css',
    'CSS/fonts/themify/themify-icons.css',
    'CSS/fonts/Linearicons-Free-v1.0.0/icon-font.min.css',
    'CSS/fonts/elegant-font/html-css/style.css',
    'CSS/vendor/animate/animate.css',
    'CSS/vendor/bootstrap/css/bootstrap.min.css',
    'CSS/vendor/css-hamburgers/hamburgers.min.css',
    'CSS/vendor/animsition/css/animsition.min.css',
    'CSS/vendor/select2/select2.min.css',
    'CSS/vendor/slick/slick.css',
    'CSS/css/util.css',
    'CSS/css/main.css'

  ]
})

export class AppComponent {
  constructor(private http: Http, private service: BooksService) { }

  public ngOnInit(): any
  {
    this.books = [];

    this.service.getBooks().subscribe(
      books => this.books = books,
      error => console.error(error),
     
    );
  }
  private books: string[] = [];
  
  search(title: string) {

    this.books = [];

    this.service.getBooks().subscribe(
      books => this.books = books,
      error => console.error(error)
    );
  }
}
