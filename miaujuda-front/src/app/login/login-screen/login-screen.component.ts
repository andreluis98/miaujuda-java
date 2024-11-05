import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError } from 'rxjs';
import { AppComponent } from 'src/app/app.component';
import { AuthServiceService } from 'src/app/services/auth.service';
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
  id?: string;
  constructor(
    private petsService: ServiceService,
    private authService: AuthServiceService,
    private router: Router
  ) { }

  ngOnInit(): void {

  }

  login(user: string, pass: string) {
    this.petsService.getLogin(user, pass).pipe(
      catchError(error => {
        this.errorMessage = error.error.message;
        return '';
      })
    ).subscribe(resp => {
      const response = resp as { id: string; status: string; message: string };
      this.id = response.id; 
      localStorage.setItem('userId', response.id);
      AppComponent.userId = response.id;
      this.authService.login();
      this.router.navigate(['/home']);
    });
}


}
