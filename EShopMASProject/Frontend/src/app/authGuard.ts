import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";

@Injectable(
    {
        providedIn: 'root'
    }
)

export class AuthGuard implements CanActivate {

    constructor(
        private router: Router
    ) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean  {
        console.log()
        if(!localStorage.getItem('user')) {
            this.router.navigateByUrl('/');
            return false;
        } 

        return true;
    }

}