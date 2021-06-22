import { Component, OnInit } from "@angular/core";
import { NgForm } from "@angular/forms";
import { LoginService } from "./login.service";
import { catchError } from 'rxjs/operators';
import { throwError } from "rxjs";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
    selector: 'login-login-form',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit{

    private navigateTo: string;

    constructor(
        private loginService: LoginService,
        private router: Router,
    ) {}

    ngOnInit(): void {
        if(localStorage.getItem('user')){
            this.router.navigateByUrl('/');
        }
        this.navigateTo = history.state['navigateTo'];
    }

    failedToLogin: boolean = false;

    onSubmit(form: NgForm): void {
        if(form.valid) {
            this.loginService
             .login(
                {
                   login: form.value.loginInput,
                   password: form.value.passwordInput 
                }
             )
            .pipe(
                catchError(data => {
                    this.failedToLogin = true;
                    return throwError(data);
                })
            )
            .subscribe(data => {
                this.failedToLogin = false;
                localStorage.setItem('user', JSON.stringify(data));
                this.router.navigateByUrl(this.navigateTo ? this.navigateTo : '/');
            });
        } else {
            form.control.markAllAsTouched();
        }
    }
}