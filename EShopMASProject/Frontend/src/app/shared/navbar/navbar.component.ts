import { Component } from "@angular/core";
import { Router } from "@angular/router";

@Component({
    selector: 'shared-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css']
  })
  export class NavbarComponent {
      
    constructor(
      private router: Router
    ) { }

    checkRoute(route: string): boolean {
      return this.router.url.startsWith(route);
    }

    chekIfUserAuted(): boolean{
      return localStorage.getItem('user') != null;
    }
  }
  