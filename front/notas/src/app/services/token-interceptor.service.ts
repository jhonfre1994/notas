
import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';
import { tap } from 'rxjs/operators';
import { JwtHelper } from 'angular2-jwt';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/operator/catch';
@Injectable()
export class RefreshTokenInterceptor implements HttpInterceptor {

    jwtHelper: JwtHelper = new JwtHelper();

    refreshTokenInProgress = false;

    tokenRefreshedSource = new Subject();
    tokenRefreshed$ = this.tokenRefreshedSource.asObservable();


    constructor(public auth: AuthenticationService) { }


    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {


        return next.handle(request).pipe(

            tap((event: HttpEvent<any>) => {
                if (event instanceof HttpResponse) {
                    // Si queremos hacer algo con la respuesta, éste es el sitio.
                    //console.log(event);
                }
            }, (err: any) => {
                if (err instanceof HttpErrorResponse) {
                    switch (err.status) {
                        case 401:
                            this.auth.refreshToken()
                    }
                }
                //console.log(request);
            })
        );
    }



}