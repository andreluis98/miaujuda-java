import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError } from 'rxjs';
import { ServiceService } from 'src/app/services/service.service';

@Component({
  selector: 'app-login-screen',
  templateUrl: './login-screen.component.html',
  styleUrls: ['./login-screen.component.css']
})
export class LoginScreenComponent implements OnInit {
  username!: string;
  password!: string;
  errorMessage: string = '';
  pets: any;
  constructor(
    private petsService: ServiceService,
    private router: Router
  ) { }

  ngOnInit(): void {

  }

  login(user: string, pass: string) {
    this.petsService.getLogin(user, pass).pipe(
      catchError(error => {
        this.errorMessage = error.error.message;
        return ''
      })
    ).subscribe(resp => {
      this.router.navigate(['/home']);
    })
  }

}
